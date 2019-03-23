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

    @Autowired
    private SnackDAO snackDAO;


    @RequestMapping(value = "/all_snack", method = RequestMethod.GET)
    public String showStyles(Model model) {
        List<Snack> list = snackDAO.getAllSnack();
        model.addAttribute("list", list);

        Snack item = new Snack();
        model.addAttribute("item", item);

        model.addAttribute("name", "Snack");
        model.addAttribute("add", "/add_snack");
        model.addAttribute("change", "/change_snack");

        return "item_page";
    }


    @RequestMapping(value = "/change_snack", method = RequestMethod.POST)
    public String changeSnack(Model model, Snack snack) {
        snackDAO.setSnack(snack);

        return "redirect:/all_snack";
    }


    @RequestMapping(value = "/add_snack", method = RequestMethod.POST)
    public String addSnack(Model model, Snack snack) {
        snackDAO.addSnack(snack);

        return "redirect:/all_snack";
    }
}
