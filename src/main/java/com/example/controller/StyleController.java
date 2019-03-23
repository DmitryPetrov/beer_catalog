
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.StyleDAO;
import com.example.model.Style;

@Controller
public class StyleController {

    @Autowired
    private StyleDAO styleDAO;


    @RequestMapping(value = "/all_style", method = RequestMethod.GET)
    public String showStyles(Model model) {
        List<Style> list = styleDAO.getAllStyle();
        model.addAttribute("list", list);

        Style item = new Style();
        model.addAttribute("item", item);

        model.addAttribute("name", "Style");
        model.addAttribute("add", "/add_style");
        model.addAttribute("change", "/change_style");

        return "item_page";
    }


    @RequestMapping(value = "/change_style", method = RequestMethod.POST)
    public String changeStyle(Model model, Style style) {
        styleDAO.setStyle(style);

        return "redirect:/all_style";
    }


    @RequestMapping(value = "/add_style", method = RequestMethod.POST)
    public String addStyle(Model model, Style style) {
        styleDAO.addStyle(style);

        return "redirect:/all_style";
    }
}
