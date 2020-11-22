package com.stp.crud.Controller;

import com.stp.crud.model.User;
import com.stp.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController  {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String findAll(Model model){
        List<User> user = userService.findAll();
        model.addAttribute("user", user);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(
            @PathVariable("id") Long id
    ) {
        userService.deleteById(id);
        return "redirect:/user";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(/*@ModelAttribute*/ User user){
        userService.saveUser(user);
//        userService.updUserName(user.getName(), user.getId_user());
        return "redirect:/user";
    }
}