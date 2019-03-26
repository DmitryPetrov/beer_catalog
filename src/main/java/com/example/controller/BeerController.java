
package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DAO.BeerBreweryDAO;
import com.example.DAO.BeerCountryDAO;
import com.example.DAO.BeerDAO;
import com.example.DAO.BeerStyleDAO;
import com.example.DAO.BreweryDAO;
import com.example.DAO.CountryDAO;
import com.example.DAO.StyleDAO;
import com.example.form.BeerFilterForm;
import com.example.form.BeerForm;
import com.example.form.BeerIdForm;
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
    @Autowired
    private BeerBreweryDAO beerBreweryDAO;
    @Autowired
    private BeerStyleDAO beerStyleDAO;
    @Autowired
    private BeerCountryDAO beerCountryDAO;


    @RequestMapping(value = "/all_beer", method = RequestMethod.GET)
    public String showBeers(Model model) {

        List<Beer> beers = beerDAO.getAllBeer();
        model.addAttribute("beers", beers);

        BeerIdForm beerIdForm = new BeerIdForm();
        model.addAttribute("beerIdForm", beerIdForm);

        return "beer_list_page";
    }


    @RequestMapping(value = "/change_beer", method = RequestMethod.GET)
    public String changeBeerGet(Model model,
            @RequestParam(value = "id", required = true) String id) {
        Beer beer = beerDAO.getBeer(Long.parseLong(id));
        BeerForm addBeerForm = new BeerForm(beer);
        model.addAttribute("addBeerForm", addBeerForm);

        Map<Style, Boolean> styleOfBeer = new TreeMap<Style, Boolean>();
        List<Long> checkedStyles =
                beerStyleDAO.getIdStyleByIdBeer(beer.getId());
        List<Style> allStyle = styleDAO.getAllStyle();
        for (Style style : allStyle) {
            boolean bool = false;
            for (Long styleId : checkedStyles) {
                if (style.getId() == styleId.longValue()) {
                    bool = true;
                }
            }
            styleOfBeer.put(style, bool);
        }
        model.addAttribute("styleOfBeer", styleOfBeer);

        Map<Country, Boolean> countyBrewedBeer =
                new TreeMap<Country, Boolean>();
        List<Long> checkedCounties =
                beerCountryDAO.getIdCountryByIdBeer(beer.getId());
        List<Country> allCountries = countryDAO.getAllCountry();
        for (Country country : allCountries) {
            boolean bool = false;
            for (Long countryId : checkedCounties) {
                if (country.getId() == countryId.longValue()) {
                    bool = true;
                }
            }
            countyBrewedBeer.put(country, bool);
        }
        model.addAttribute("countyBrewedBeer", countyBrewedBeer);

        Map<Brewery, Boolean> breweryBrewedBeer =
                new TreeMap<Brewery, Boolean>();
        List<Long> checkedBreweries =
                beerBreweryDAO.getIdBreweryByIdBeer(beer.getId());
        List<Brewery> allBreweries = breweryDAO.getAllBrewery();
        for (Brewery brewery : allBreweries) {
            boolean bool = false;
            for (Long breweryId : checkedBreweries) {
                if (brewery.getId() == breweryId.longValue()) {
                    bool = true;
                }
            }
            breweryBrewedBeer.put(brewery, bool);
        }
        model.addAttribute("breweryBrewedBeer", breweryBrewedBeer);

        model.addAttribute("action", "Change");
        model.addAttribute("ref", "/set_beer");

        return "beer_page";
    }


    @RequestMapping(value = "/set_beer", method = RequestMethod.POST)
    public String changeBeerPost(Model model, BeerForm addBeerForm) {
        beerDAO.setBeer(addBeerForm);

        return "redirect:/all_beer";
    }


    @RequestMapping(value = "/add_beer", method = RequestMethod.GET)
    public String addBeerGet(Model model) {
        BeerForm addBeerForm = new BeerForm(50, 1);
        model.addAttribute("addBeerForm", addBeerForm);

        Map<Brewery, Boolean> breweryBrewedBeer =
                new TreeMap<Brewery, Boolean>();
        List<Brewery> allBreweries = breweryDAO.getAllBrewery();
        for (Brewery brewery : allBreweries) {
            breweryBrewedBeer.put(brewery, new Boolean(false));
        }
        model.addAttribute("breweryBrewedBeer", breweryBrewedBeer);

        Map<Country, Boolean> countyBrewedBeer =
                new TreeMap<Country, Boolean>();
        List<Country> allCountries = countryDAO.getAllCountry();
        for (Country country : allCountries) {
            countyBrewedBeer.put(country, new Boolean(false));
        }
        model.addAttribute("countyBrewedBeer", countyBrewedBeer);

        Map<Style, Boolean> styleOfBeer = new TreeMap<Style, Boolean>();
        List<Style> allStyle = styleDAO.getAllStyle();
        for (Style style : allStyle) {
            styleOfBeer.put(style, new Boolean(false));
        }
        model.addAttribute("styleOfBeer", styleOfBeer);

        model.addAttribute("action", "Add");
        model.addAttribute("ref", "/add_beer");

        return "beer_page";
    }


    @RequestMapping(value = "/add_beer", method = RequestMethod.POST)
    public String addBeerPost(Model model, BeerForm addBeerForm) {
        beerDAO.addBeer(addBeerForm);

        return "redirect:/all_beer";
    }


    @RequestMapping(value = "/beer_filter", method = RequestMethod.GET)
    public String beerFilterGet(Model model) {
        BeerFilterForm addFilter = new BeerFilterForm();

        return "beer_filter_page";
    }


    @RequestMapping(value = "/beer_filter", method = RequestMethod.POST)
    public String beerFilterPost(Model model, BeerFilterForm beerFilter) {
        beerDAO.getBeerByParams(beerFilter);

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
