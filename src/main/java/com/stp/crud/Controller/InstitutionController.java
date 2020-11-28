package com.stp.crud.Controller;

import com.stp.crud.model.Card;
import com.stp.crud.model.Institution;
import com.stp.crud.model.User;
import com.stp.crud.service.CardService;
import com.stp.crud.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InstitutionController {

    private final InstitutionService institutionService;
    private final CardService cardService;

    @Autowired
    public InstitutionController(InstitutionService institutionService, CardService cardService) {
        this.institutionService = institutionService;
        this.cardService = cardService;
    }


    @GetMapping("/inst-create/{id}")
    public String createCardForm(@PathVariable("id") Long id, Model model){
        Card card = cardService.findById(id);
        model.addAttribute("card", card);
        return "inst-create";
    }

    @RequestMapping(value="/create-inst", method= RequestMethod.POST)
    public String updateCard(@RequestParam("id") Long id, @RequestParam("name") String name){
        Card card = cardService.findById(id);
        List<Institution> inst = new ArrayList<Institution>();
        Institution institution = new Institution();

        for (Institution institution1: card.getInstitutions()){
            inst.add(institution1);
        }

        institution.setName(name);
        inst.add(institution);
        card.setInstitutions(inst);
        cardService.saveCard(card);
        return "redirect:user";
    }


    @GetMapping("/inst-update/{id}")
    public String updateInstForm(@PathVariable("id") Long id, @RequestParam("old_name") String oldName, Model model){
        Card card = cardService.findById(id);
        model.addAttribute("card", card);
        model.addAttribute("inst", oldName);
        return "inst-update";
    }

    @RequestMapping(value="/update-inst", method= RequestMethod.POST)
    public String updateInst(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("oldName") String oldName){
        Card card = cardService.findById(id);

        for (Institution institution1: card.getInstitutions()){
            if(institution1.getName().equals(oldName)){
                institution1.setName(name);
            }
        }

        cardService.saveCard(card);
        return "redirect:user";
    }

    @GetMapping("/inst-del/{id}")
    public String deleteInst(@PathVariable("id") Long id, @RequestParam("old_name") String name, Model model){
        Card card = cardService.findById(id);

        List<Institution> inst = new ArrayList<Institution>();

        for (Institution institution1: card.getInstitutions()){
            if(!institution1.getName().equals(name)){
                inst.add(institution1);
            }
        }

        card.setInstitutions(inst);
        cardService.saveCard(card);
        return "redirect:/user";
    }

}
