
package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.product.dao.SnackDAO;
import com.example.product.model.Snack;

@Controller
public class SnackController {

    private static final String LIST = "/snack/list";
    private static final String PUT = "/snack/put";
    private static final String ADD = "/snack/add";
    private static final String DELETE = "/snack/delete";

    @Autowired
    private SnackDAO snackDAO;


    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public String list(Model model) {
        List<Snack> list = snackDAO.getAll();
        model.addAttribute("list", list);

        Snack item = new Snack();
        model.addAttribute("item", item);

        model.addAttribute("name", "Snack");
        model.addAttribute("add", ADD);
        model.addAttribute("put", PUT);
        model.addAttribute("delete", DELETE);

        return "item_page";
    }


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = PUT, method = RequestMethod.POST)
    public String put(Model model, Snack snack) {
        snackDAO.put(snack);

        return "redirect:" + LIST;
    }


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String add(Model model, Snack snack) {
        snackDAO.add(snack);

        return "redirect:" + LIST;
    }


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public String delete(Model model, Snack snack) {
        snackDAO.delete(snack);

        return "redirect:" + LIST;
    }

}
