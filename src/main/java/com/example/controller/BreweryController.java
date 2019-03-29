
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.BreweryDAO;
import com.example.model.Brewery;
import com.example.model.Style;

@Controller
public class BreweryController {

    private static final String LIST = "/brewery/list";
    private static final String PUT = "/brewery/put";
    private static final String ADD = "/brewery/add";
    private static final String DELETE = "/brewery/delete";
    
    @Autowired
    private BreweryDAO breweryDAO;


    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public String list(Model model) {
        List<Brewery> list = breweryDAO.getAll();
        model.addAttribute("list", list);

        Brewery item = new Brewery();
        model.addAttribute("item", item);

        model.addAttribute("name", "Brewery");
        model.addAttribute("add", ADD);
        model.addAttribute("put", PUT);
        model.addAttribute("delete", DELETE);

        return "item_page";
    }


    @RequestMapping(value = PUT, method = RequestMethod.POST)
    public String put(Model model, Brewery brewery) {
        breweryDAO.put(brewery);

        return "redirect:" + LIST;
    }


    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String add(Model model, Brewery brewery) {
        breweryDAO.add(brewery);

        return "redirect:" + LIST;
    }
    
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public String delete(Model model, Brewery brewery) {
        breweryDAO.delete(brewery);

        return "redirect:" + LIST;
    }
    
}
