package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;

import java.util.List;

public interface ProjectService{

    ProjectDTO getByProjectCode(String code);
    List<ProjectDTO> listAllProjects();
    void save(ProjectDTO projectDTO);
    void update(ProjectDTO projectDTO);
    void delete(ProjectDTO projectDTO);
    void softDeleteByProjectCode(String code);

    void complete(String code);

    List<ProjectDTO> listAllProjectDetails();

    List<ProjectDTO> getCountedListOfProjectsDTO(UserDTO manager);

    List<ProjectDTO> readAllByAssignedManager(User assignedManager);
}
