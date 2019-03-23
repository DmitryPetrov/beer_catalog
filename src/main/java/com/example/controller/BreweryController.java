
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.BreweryDAO;
import com.example.model.Brewery;

@Controller
public class BreweryController {

    @Autowired
    private BreweryDAO breweryDAO;


    @RequestMapping(value = "/all_brewery", method = RequestMethod.GET)
    public String showBrewery(Model model) {
        List<Brewery> list = breweryDAO.getAllBrewery();
        model.addAttribute("list", list);

        Brewery item = new Brewery();
        model.addAttribute("item", item);

        model.addAttribute("name", "Brewery");
        model.addAttribute("add", "/add_brewery");
        model.addAttribute("change", "/change_brewery");

        return "item_page";
    }


    @RequestMapping(value = "/change_brewery", method = RequestMethod.POST)
    public String changeBrewery(Model model, Brewery brewery) {
        breweryDAO.setBrewery(brewery);

        return "redirect:/all_brewery";
    }


    @RequestMapping(value = "/add_brewery", method = RequestMethod.POST)
    public String addBrewery(Model model, Brewery brewery) {
        breweryDAO.addBrewery(brewery);

        return "redirect:/all_brewery";
    }
}
