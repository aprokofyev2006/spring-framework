package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.SearchDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityManager entityManager;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, EntityManager entityManager, @Lazy ProjectService projectService, TaskService taskService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.entityManager = entityManager;
        this.projectService = projectService;
        this.taskService = taskService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> listAllUsers() {

        List<User> userList = userRepository.findAll(Sort.by("firstName"));

        return userList.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {

        User user = userRepository.findByUserName(username);
        System.out.println("userMapper.convertToDto(user): " + userMapper.convertToDto(user));
        return userMapper.convertToDto(user);
    }

    @Override
    public void save(UserDTO userDTO) {
        userDTO.setEnabled(true);
        User user = userMapper.convertToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        //Find current user
        User user = userRepository.findByUserName(userDTO.getUserName());

        //Map updated user dto to entity object
        User convertedUser = userMapper.convertToEntity(userDTO);

        //Set id and password to converted object
        convertedUser.setId(user.getId());

        if(convertedUser.getPassword() == null || convertedUser.getPassword().isEmpty()){
            convertedUser.setPassword(user.getPassword());
        }

        convertedUser.setEnabled(true);

        //save updated user
        userRepository.save(convertedUser);
        return findByUserName(userDTO.getUserName());
    }

    @Override
    public boolean hasUpdateUserErrors(BindingResult bindingResult) {

        return bindingResult.getFieldErrors().stream()
                .anyMatch(fieldError -> !fieldError.getField().equals("password") && !fieldError.getField().equals("confirmPassword"));
    }

    @Override
    public void deleteByUserName(String username) {
        userRepository.deleteByUserName(username);
        userRepository.findAll();
    }

    @Override
    public void softDeleteByUserName(String username) {
        User user = userRepository.findByUserName(username);

        if(checkIfUserCanBeDeleted(user)){
            user.setIsDeleted(true);
            user.setUserName(user.getUserName() + "_" + user.getId());
            userRepository.save(user);
        }

    }

    private boolean checkIfUserCanBeDeleted(User user){

        switch (user.getRole().getDescription()) {
            case "Manager":
                List<ProjectDTO> projectDTOList = projectService.readAllByAssignedManager(user);
                return projectDTOList.size() == 0;

            case "Employee":
                List<TaskDTO> taskDTOList = taskService.readAllByAssignedEmployee(user);
                return taskDTOList.size() == 0;

            default:
                return true;
        }
    }

    @Override
    public List<UserDTO> searchForUsers(SearchDTO search) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        // Add more conditions for additional search fields as needed
        if (StringUtils.hasText(search.getFirstName())) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + search.getFirstName().toLowerCase() + "%"));
        }

        if (StringUtils.hasText(search.getLastName())) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + search.getLastName().toLowerCase() + "%"));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<User> users = entityManager.createQuery(criteriaQuery).getResultList();

        return users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> listAllByRole(String roleDescription) {
        List<User> userList = userRepository.findByRole_DescriptionIgnoreCase(roleDescription);

        return userList.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

}
