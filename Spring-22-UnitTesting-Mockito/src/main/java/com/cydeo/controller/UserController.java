package com.cydeo.controller;

import com.cydeo.dto.SearchDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    RoleService roleService;
    UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model){

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles",roleService.listAllRoles());

        if (model.getAttribute("redirectSearch") != null){
            model.addAttribute("users", model.getAttribute("searchResult"));
            model.addAttribute("search", model.getAttribute("redirectSearch"));
        }else{
            model.addAttribute("users",userService.listAllUsers());
            model.addAttribute("search", new SearchDTO());
        }

        return "user/create";
    }

    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.listAllRoles());
            model.addAttribute("users", userService.listAllUsers());
            model.addAttribute("search", new SearchDTO());

            return "/user/create";
        }

        userService.save(user);

        return "redirect:/user/create";
    }

    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model){

        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("roles",roleService.listAllRoles());
        model.addAttribute("users",userService.listAllUsers());

        return "user/update";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model){

        if (userService.hasUpdateUserErrors(bindingResult)) {
            model.addAttribute("roles", roleService.listAllRoles());
            model.addAttribute("users", userService.listAllUsers());

            return "/user/update";
        }

        userService.update(user);

        return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username, Model model){

        //userService.deleteByUserName(username);
        userService.softDeleteByUserName(username);

        return "redirect:/user/create";
    }

    @PostMapping("/search")
    public String searchUser(@ModelAttribute("search") SearchDTO search, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("redirectSearch", search);
        redirectAttributes.addFlashAttribute("searchResult", userService.searchForUsers(search));

        return "redirect:/user/create";
    }

    @GetMapping("/resetSearch")
    public String resetSearch(){
        return "redirect:/user/create";
    }
}
