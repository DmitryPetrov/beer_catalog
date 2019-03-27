
package com.example.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import com.example.form.BeerForm;
import com.example.model.Beer;
import com.example.model.BeerInfo;
import com.example.model.Brewery;
import com.example.model.Country;
import com.example.model.Style;

@Controller
public class BeerController {

    private static final String LIST = "/beer/list";
    private static final String PUT = "/beer/put";
    private static final String ADD = "/beer/add";
    private static final String FILTER = "/beer/filter";

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


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(Model model) {
        List<Beer> beers = beerDAO.getAllBeer();
        model.addAttribute("beers", beers);

        return "list_page";
    }


    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public String list(Model model,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "style", required = false) String style,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "brewery", required = false) String brewery,
            @RequestParam(value = "count", required = false) String count,
            @RequestParam(value = "rate", required = false) String rate,
            @RequestParam(value = "craft", required = false) String craft,
            @RequestParam(value = "star", required = false) String star) {

        if (name != null) {
            List<Beer> beers = beerDAO.getBeerByName(name);
            model.addAttribute("beers", beers);
        } else if (style != null) {
            List<Beer> beers = beerDAO.getBeerByStyle(Integer.valueOf(style));
            model.addAttribute("beers", beers);
        } else if (country != null) {
            List<Beer> beers =
                    beerDAO.getBeerByCountry(Integer.valueOf(country));
            model.addAttribute("beers", beers);
        } else if (brewery != null) {
            List<Beer> beers =
                    beerDAO.getBeerByBrewery(Integer.valueOf(brewery));
            model.addAttribute("beers", beers);
        } else if (count != null) {
            List<Beer> beers = beerDAO.getBeerByCount(Integer.valueOf(count));
            model.addAttribute("beers", beers);
        } else if (rate != null) {
            List<Beer> beers = beerDAO.getBeerByRate(Integer.valueOf(rate));
            model.addAttribute("beers", beers);
        } else if (craft != null) {
            List<Beer> beers = beerDAO.getBeerByCraft(Boolean.valueOf(craft));
            model.addAttribute("beers", beers);
        } else if (star != null) {
            List<Beer> beers = beerDAO.getBeerByStar(Boolean.valueOf(star));
            model.addAttribute("beers", beers);
        } else {
            List<Beer> beers = beerDAO.getAllBeer();
            model.addAttribute("beers", beers);
        }

        return "list_page";
    }


    @RequestMapping(value = PUT, method = RequestMethod.GET)
    public String putGet(Model model,
            @RequestParam(value = "id", required = true) String id) {
        Beer beer = beerDAO.getBeer(Long.parseLong(id));
        BeerForm beerForm = new BeerForm(beer);
        model.addAttribute("beerForm", beerForm);

        List<Long> shouldBeMarkedStyles =
                beerStyleDAO.getIdStyleByIdBeer(beer.getId());
        List<BeerInfo> allStyle = styleDAO.getAllStyleLikeBeerInfo();
        Map<BeerInfo, Boolean> markedStyles =
                setMarkFlags(allStyle, shouldBeMarkedStyles);
        model.addAttribute("markedStyles", markedStyles);

        List<Long> shouldBeMarkedCounties =
                beerCountryDAO.getIdCountryByIdBeer(beer.getId());
        List<BeerInfo> allCountries = countryDAO.getAllCountryLikeBeerInfo();
        Map<BeerInfo, Boolean> markedCountries =
                setMarkFlags(allCountries, shouldBeMarkedCounties);
        model.addAttribute("markedCountries", markedCountries);

        List<Long> shouldBeMarkedBreweries =
                beerBreweryDAO.getIdBreweryByIdBeer(beer.getId());
        List<BeerInfo> allBreweries = breweryDAO.getAllBreweryLikeBeerInfo();
        Map<BeerInfo, Boolean> markedBreweries =
                setMarkFlags(allBreweries, shouldBeMarkedBreweries);
        model.addAttribute("markedBreweries", markedBreweries);

        model.addAttribute("action", "Put");
        model.addAttribute("ref", PUT);

        return "beer_page";
    }


    @RequestMapping(value = ADD, method = RequestMethod.GET)
    public String addGet(Model model) {
        BeerForm beerForm = new BeerForm(50, 1);
        model.addAttribute("beerForm", beerForm);

        List<BeerInfo> allStyle = styleDAO.getAllStyleLikeBeerInfo();
        Map<BeerInfo, Boolean> markedStyles =
                setMarkFlags(allStyle, Collections.emptyList());
        model.addAttribute("markedStyles", markedStyles);

        List<BeerInfo> allCountries = countryDAO.getAllCountryLikeBeerInfo();
        Map<BeerInfo, Boolean> markedCountries =
                setMarkFlags(allCountries, Collections.emptyList());
        model.addAttribute("markedCountries", markedCountries);

        List<BeerInfo> allBreweries = breweryDAO.getAllBreweryLikeBeerInfo();
        Map<BeerInfo, Boolean> markedBreweries =
                setMarkFlags(allBreweries, new ArrayList<Long>());
        model.addAttribute("markedBreweries", markedBreweries);

        model.addAttribute("action", "Add");
        model.addAttribute("ref", ADD);

        return "beer_page";
    }


    private Map<BeerInfo, Boolean> setMarkFlags(List<BeerInfo> allBeerInfo,
            List<Long> shouldBeMarked) {
        Map<BeerInfo, Boolean> markedInfo = new TreeMap<BeerInfo, Boolean>();
        for (BeerInfo info : allBeerInfo) {
            boolean flag = false;
            for (Long styleId : shouldBeMarked) {
                if (info.getId() == styleId.longValue()) {
                    flag = true;
                }
            }
            markedInfo.put(info, flag);
        }
        return markedInfo;
    }


    @RequestMapping(value = PUT, method = RequestMethod.POST)
    public String putPost(Model model, BeerForm beerForm) {
        beerDAO.setBeer(beerForm);

        return "redirect:" + LIST;
    }


    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String addPost(Model model, BeerForm beerForm) {
        beerDAO.addBeer(beerForm);

        return "redirect:" + LIST;
    }


    @RequestMapping(value = FILTER, method = RequestMethod.GET)
    public String filterGet(Model model) {
        List<Style> styles = styleDAO.getAllStyle();
        model.addAttribute("styles", styles);

        List<Country> countries = countryDAO.getAllCountry();
        model.addAttribute("countries", countries);

        List<Brewery> breweries = breweryDAO.getAllBrewery();
        model.addAttribute("breweries", breweries);

        return "beer_filter_page";
    }


    @RequestMapping(value = FILTER, method = RequestMethod.POST)
    public String filterPost(Model model,
            @RequestParam(value = "style", required = false) String style,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "brewery", required = false) String brewery,
            @RequestParam(value = "count", required = false) String count,
            @RequestParam(value = "rate", required = false) String rate,
            @RequestParam(value = "craft", required = false) String craft,
            @RequestParam(value = "star", required = false) String star) {
        //beerDAO.getBeerByParams(beerFilter);

        return "redirect:" + LIST;
    }

    /*
     * @RequestMapping(value = "/deleteBeer", method = RequestMethod.POST)
     * public String deleteBeer(Model model, DeleteBeerByIdForm deleteBeer) {
     * BeerDAO.deleteBeer(deleteBeer.getId());
     * 
     * return "redirect:/Beers"; }
     */
}
