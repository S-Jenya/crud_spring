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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @RequestMapping(value="/user-create", method= RequestMethod.POST)
    public String createUser(@RequestParam("name") String name, @RequestParam("password") String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userService.saveUser(user);
        return "redirect:user";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
//
        if(user.getCards().size() > 0){
            for(Card card: user.getCards()){
                cardService.deleteById(card.getId_card());
            }
        }
        userService.deleteById(id);
        return "redirect:/user";
    }

    @GetMapping("/user-info/{id}")
    public String userInfoForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        List<Card> card = cardService.selectCardFromUser(id);
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

    @RequestMapping(value="/user-update", method= RequestMethod.POST)
    public String updateUser(@RequestParam("id_user")  Long id, @RequestParam("name") String name){
        userService.updUserName(name, id);
        return "redirect:/user";
    }

}