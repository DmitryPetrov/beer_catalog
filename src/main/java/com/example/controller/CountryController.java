
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

    @Autowired
    private CountryDAO countryDAO;


    @RequestMapping(value = "/all_country", method = RequestMethod.GET)
    public String showCountries(Model model) {
        List<Country> list = countryDAO.getAllCountry();
        model.addAttribute("list", list);

        Country item = new Country();
        model.addAttribute("item", item);

        model.addAttribute("name", "Country");
        model.addAttribute("add", "/add_country");
        model.addAttribute("change", "/change_country");

        return "item_page";
    }


    @RequestMapping(value = "/change_country", method = RequestMethod.POST)
    public String changeCountry(Model model, Country country) {
        countryDAO.setCountry(country);

        return "redirect:/all_country";
    }


    @RequestMapping(value = "/add_country", method = RequestMethod.POST)
    public String addCountry(Model model, Country country) {
        countryDAO.addCountry(country);

        return "redirect:/all_country";
    }
}
