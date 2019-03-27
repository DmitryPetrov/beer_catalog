
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.CountryDAO;
import com.example.model.Country;

@Controller
public class CountryController {

    private static final String LIST = "/country/list";
    private static final String PUT = "/country/put";
    private static final String ADD = "/country/add";
    
    @Autowired
    private CountryDAO countryDAO;


    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public String list(Model model) {
        List<Country> list = countryDAO.getAllCountry();
        model.addAttribute("list", list);

        Country item = new Country();
        model.addAttribute("item", item);

        model.addAttribute("name", "Country");
        model.addAttribute("add", ADD);
        model.addAttribute("put", PUT);

        return "item_page";
    }


    @RequestMapping(value = PUT, method = RequestMethod.POST)
    public String put(Model model, Country country) {
        countryDAO.setCountry(country);

        return "redirect:" + LIST;
    }


    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String add(Model model, Country country) {
        countryDAO.addCountry(country);

        return "redirect:" + LIST;
    }
}
