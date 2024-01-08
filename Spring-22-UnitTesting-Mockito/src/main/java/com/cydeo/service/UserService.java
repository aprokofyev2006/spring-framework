package com.cydeo.service;

import com.cydeo.dto.SearchDTO;
import com.cydeo.dto.UserDTO;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    boolean hasUpdateUserErrors(BindingResult bindingResult);
    void deleteByUserName(String username);
    void softDeleteByUserName(String username);
    List<UserDTO> searchForUsers(SearchDTO search);
    List<UserDTO> listAllByRole(String roleDescription);

}
