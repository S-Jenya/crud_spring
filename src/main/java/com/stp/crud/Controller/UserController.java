package com.stp.crud.Controller;

import com.stp.crud.model.Card;
import com.stp.crud.model.Institution;
import com.stp.crud.model.User;
import com.stp.crud.service.CardService;
import com.stp.crud.service.InstitutionService;
import com.stp.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController  {

    private final UserService userService;
    private final CardService cardService;
    private final InstitutionService institutionService;

    @Autowired
    public UserController(UserService userService, CardService cardService, InstitutionService institutionService) {
        this.userService = userService;
        this.cardService = cardService;
        this.institutionService = institutionService;
    }

    @GetMapping("/user")
    public String findAll(Model model){
        List<User> user = userService.customSelect();
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

    @GetMapping("/user-info/{id}")
    public String userInfoForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        List<Card>  card = cardService.selectCardFromUser(id);
        for(Card card1: card){
//            List<Institution> institutions = institutionService.selectInstFromCard(card1.getId_card());
//            for (Institution institutions1: institutions){
//                card1.setInstitutions(institutions1.setCards(););
//            }
        }
        model.addAttribute("card", card);
        model.addAttribute("user", user);
        return "/user-info";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(/*@ModelAttribute*/ User user){
//        userService.saveUser(user);
        userService.updUserName(user.getName(), user.getId_user());
        return "redirect:/user";
    }
}