package org.example.travlingprojetsb.Controller;

import org.example.travlingprojetsb.Entity.User;
import org.example.travlingprojetsb.Entity.Role;
import org.example.travlingprojetsb.Service.UserService;
import org.example.travlingprojetsb.Service.RoleService;
import org.example.travlingprojetsb.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

     //Display update form
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/users?error=UserNotFound";
        }

        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "update_user";
    }

    // Update user
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserDto userDto, @RequestParam List<Long> roleIds) {
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            return "redirect:/users?error=UserNotFound";
        }

        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());

        List<Role> roles = roleService.findRolesByIds(roleIds);
        existingUser.setRoles(roles);

        userService.updateUser(existingUser,roles);
        return "redirect:/users?success=UserUpdated";
    }
    // Display the update form
//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable Long id, Model model) {
//        User user = userService.findById(id);
//        model.addAttribute("user", user);
//        return "update_user"; // This corresponds to your Thymeleaf template
//    }
//
//    // Handle the update request
//    @PostMapping("/update")
//    public String updateUser(@RequestParam Long id,
//                             @RequestParam String name,
//                             @RequestParam String email,
//                             @RequestParam String password,
//                             @RequestParam List<Long> roleIds,
//                             Model model) {
//        try {
//            userService.updateUser(id, name, email, password, roleIds);
//            return "redirect:/users"; // Redirect to the user list page after success
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("error", e.getMessage());
//            return "update_user"; // Return to the form with the error message
//        }
//    }

    //handler methods for getting list of users.
    @GetMapping()
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "users";
    }
}

