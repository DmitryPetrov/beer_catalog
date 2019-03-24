
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.BeerDAO;
import com.example.DAO.BreweryDAO;
import com.example.DAO.CountryDAO;
import com.example.DAO.StyleDAO;
import com.example.form.AddBeerForm;
import com.example.form.BeerForm;
import com.example.model.Beer;
import com.example.model.Brewery;
import com.example.model.Country;
import com.example.model.Style;

@Controller
public class BeerController {

    @Autowired
    private BeerDAO beerDAO;
    @Autowired
    private StyleDAO styleDAO;
    @Autowired
    private CountryDAO countryDAO;
    @Autowired
    private BreweryDAO breweryDAO;


    @RequestMapping(value = "/all_beer", method = RequestMethod.GET)
    public String showBeers(Model model) {

        List<Beer> beers = beerDAO.getAllBeer();
        model.addAttribute("beers", beers);

        BeerForm beerForm = new BeerForm();
        model.addAttribute("beerForm", beerForm);

        return "beer_page";
    }


    @RequestMapping(value = "/change_beer", method = RequestMethod.GET)
    public String changeBeer(Model model, AddBeerForm addBeerForm) {
        beerDAO.addBeer(addBeerForm);

        return "redirect:/all_beer";
    }


    @RequestMapping(value = "/add_beer", method = RequestMethod.GET)
    public String addBeerGet(Model model) {
        AddBeerForm addBeerForm = new AddBeerForm(50, 1);
        model.addAttribute("addBeerForm", addBeerForm);
        
        List<Brewery> breweryList = breweryDAO.getAllBrewery();
        model.addAttribute("breweryList", breweryList);
        
        List<Style> styleList = styleDAO.getAllStyle();
        model.addAttribute("styleList", styleList);
        
        List<Country> countryList = countryDAO.getAllCountry();
        model.addAttribute("countryList", countryList);

        return "add_beer_page";
    }


    @RequestMapping(value = "/add_beer", method = RequestMethod.POST)
    public String addBeerPost(Model model, AddBeerForm addBeerForm) {
        beerDAO.addBeer(addBeerForm);

        return "redirect:/all_beer";
    }

    /*
     * @RequestMapping(value = "/deleteBeer", method = RequestMethod.POST)
     * public String deleteBeer(Model model, DeleteBeerByIdForm deleteBeer) {
     * BeerDAO.deleteBeer(deleteBeer.getId());
     * 
     * return "redirect:/Beers"; }
     */
}
