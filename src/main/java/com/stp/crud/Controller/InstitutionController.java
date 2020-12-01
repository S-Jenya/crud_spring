package com.stp.crud.Controller;

import com.stp.crud.model.Card;
import com.stp.crud.model.Institution;
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

        if(card.findInListByInstName(card, name)){
            return "redirect:user";
        }

        Institution inst = institutionService.selectInstByName(name);
        if(inst != null) {  /*уже существует*/
            card.addNewInst(inst);
        } else {
            Institution newInst = new Institution();
            newInst.setName(name);
            card.addNewInst(newInst);
        }

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

        if(card.findInListByInstName(card, name)){
            return "redirect:user";
        }

        /* Удаляем из коллекции старое имя */
        for(Institution institution: card.getInstitutions()){
            if(institution.getName().equals(oldName)){
                card.getInstitutions().remove(institution);
                break;
            }
        }

        /* Новое имя уже существует? */
        Institution inst = institutionService.selectInstByName(name);
        if(inst != null) {  /*уже существует*/
            card.addNewInst(inst);
        } else { /* имя не существует. Создадим новый объект */
            Institution newInst = new Institution();
            newInst.setName(name);
            card.addNewInst(newInst);
        }
        cardService.saveCard(card);

        inst = institutionService.selectInstByName(oldName);
        if(inst.getCards().size() == 0){
            institutionService.deleteById(inst.getId());
        }

        return "redirect:user";
    }

    @GetMapping("/inst-del/{id}")
    public String deleteInst(@PathVariable("id") Long id, @RequestParam("old_name") String name, Model model){
        Card card = cardService.findById(id);

        /* Удаляем из коллекции старок имя */
        for(Institution institution: card.getInstitutions()){
            if(institution.getName().equals(name)){
                card.getInstitutions().remove(institution);
                break;
            }
        }
        cardService.saveCard(card);

        Institution inst = institutionService.selectInstByName(name);
        if(inst.getCards().size() == 0){
            institutionService.deleteById(inst.getId());
        }

        return "redirect:/user";
    }

}
