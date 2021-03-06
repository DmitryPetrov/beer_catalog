
package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.product.dao.CountryDAO;
import com.example.product.model.Country;

@Controller
public class CountryController {

    private static final String LIST = "/country/list";
    private static final String PUT = "/country/put";
    private static final String ADD = "/country/add";
    private static final String DELETE = "/country/delete";

    @Autowired
    private CountryDAO countryDAO;


    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public String list(Model model) {
        List<Country> list = countryDAO.getAll();
        model.addAttribute("list", list);

        Country item = new Country();
        model.addAttribute("item", item);

        model.addAttribute("name", "Country");
        model.addAttribute("add", ADD);
        model.addAttribute("put", PUT);
        model.addAttribute("delete", DELETE);

        return "item_page";
    }


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = PUT, method = RequestMethod.POST)
    public String put(Model model, Country country) {
        countryDAO.put(country);

        return "redirect:" + LIST;
    }


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String add(Model model, Country country) {
        countryDAO.add(country);

        return "redirect:" + LIST;
    }


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public String delete(Model model, Country country) {
        countryDAO.delete(country);

        return "redirect:" + LIST;
    }

}
