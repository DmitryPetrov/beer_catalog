
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

    private static final String LIST = "/brewery/list";
    private static final String PUT = "/brewery/put";
    private static final String ADD = "/brewery/add";
    
    @Autowired
    private BreweryDAO breweryDAO;


    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public String list(Model model) {
        List<Brewery> list = breweryDAO.getAllBrewery();
        model.addAttribute("list", list);

        Brewery item = new Brewery();
        model.addAttribute("item", item);

        model.addAttribute("name", "Brewery");
        model.addAttribute("add", ADD);
        model.addAttribute("put", PUT);

        return "item_page";
    }


    @RequestMapping(value = PUT, method = RequestMethod.POST)
    public String put(Model model, Brewery brewery) {
        breweryDAO.setBrewery(brewery);

        return "redirect:" + LIST;
    }


    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String add(Model model, Brewery brewery) {
        breweryDAO.addBrewery(brewery);

        return "redirect:" + LIST;
    }
}
