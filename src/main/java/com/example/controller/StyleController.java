
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

    private static final String LIST = "/style/list";
    private static final String PUT = "/style/put";
    private static final String ADD = "/style/add";
    private static final String DELETE = "/style/delete";
    
    @Autowired
    private StyleDAO styleDAO;


    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public String list(Model model) {
        List<Style> list = styleDAO.getAll();
        model.addAttribute("list", list);

        Style item = new Style();
        model.addAttribute("item", item);

        model.addAttribute("name", "Style");
        model.addAttribute("add", ADD);
        model.addAttribute("put", PUT);
        model.addAttribute("delete", DELETE);

        return "item_page";
    }


    @RequestMapping(value = PUT, method = RequestMethod.POST)
    public String put(Model model, Style style) {
        styleDAO.put(style);

        return "redirect:" + LIST;
    }


    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String add(Model model, Style style) {
        styleDAO.add(style);

        return "redirect:" + LIST;
    }
    
    
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public String delete(Model model, Style style) {
        styleDAO.delete(style);

        return "redirect:" + LIST;
    }
    
}
