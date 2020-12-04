package com.stp.crud.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CardController {

    private final CardService cardService;
    private final UserService userService;
    private final InstitutionService institutionService;

    @Autowired
    public CardController(CardService cardService, ObjectMapper mapper, UserService userService, InstitutionService institutionService) {
        this.cardService = cardService;
        this.userService = userService;
        this.institutionService = institutionService;
    }

    @GetMapping("/card-create/{id}")
    public String createCardForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("card", user);
        return "card-create";
    }

    @RequestMapping(value="/new-card", method= RequestMethod.POST)
    public String createNewCard(@RequestParam("id") Long id, @RequestParam("name") String name){
        User user = userService.findById(id);
        Card card = new Card();
        card.setHeadline(name);
        card.setUser(user);
        cardService.saveCard(card);
        return "redirect:user-info/" + id;
    }

    @GetMapping("/card-update/{id}")
    public String updateCardForm(@PathVariable("id") Long id, Model model){
        Card card = cardService.findById(id);
        model.addAttribute("card", card);
        return "card-update";
    }

    @RequestMapping(value="/update-card", method= RequestMethod.POST)
    public String updateCard(@RequestParam("id") Long id, @RequestParam("name") String name){
        cardService.updCard(name, id);
        return "redirect:user";
    }

    @RequestMapping(value="/del-card", method= RequestMethod.POST)
    public String DelCard(@RequestParam("idCard") Long id){
        Card card = cardService.findById(id);
        List<String> instNameInCard = new ArrayList<String>();

        for(Institution institution: card.getInstitutions()){
            instNameInCard.add(institution.getName());
        }

        card.cleanInstList();
        cardService.saveCard(card);
        cardService.deleteById(id);

        /* Выполним очистку из таблици Институты */
        for (String oldInstName: instNameInCard){
            Institution inst = institutionService.selectInstByName(oldInstName);
            if(inst.getCards().size() == 0){
                institutionService.deleteById(inst.getId());
            }
        }

        return "redirect:user";
    }


}
