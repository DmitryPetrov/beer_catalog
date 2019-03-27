package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.SnackDAO;
import com.example.model.Snack;


@Controller
public class SnackController {
    
    private static final String LIST = "/snack/list";
    private static final String PUT = "/snack/put";
    private static final String ADD = "/snack/add";
    
    @Autowired
    private SnackDAO snackDAO;

    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public String list(Model model) {
        List<Snack> list = snackDAO.getAllSnack();
        model.addAttribute("list", list);

        Snack item = new Snack();
        model.addAttribute("item", item);

        model.addAttribute("name", "Snack");
        model.addAttribute("add", ADD);
        model.addAttribute("put", PUT);

        return "item_page";
    }

    @RequestMapping(value = PUT, method = RequestMethod.POST)
    public String put(Model model, Snack snack) {
        snackDAO.setSnack(snack);

        return "redirect:" + LIST;
    }

    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String add(Model model, Snack snack) {
        snackDAO.addSnack(snack);

        return "redirect:" + LIST;
    }
}
