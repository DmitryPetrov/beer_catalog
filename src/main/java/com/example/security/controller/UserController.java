
package com.example.security.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.product.dao.BeerDAO;
import com.example.product.dao.BreweryDAO;
import com.example.product.dao.CountryDAO;
import com.example.product.dao.SnackDAO;
import com.example.product.dao.StyleDAO;
import com.example.product.form.BeerForm;
import com.example.product.model.Beer;
import com.example.product.model.Brewery;
import com.example.product.model.Country;
import com.example.product.model.Snack;
import com.example.product.model.Style;
import com.example.security.dao.UserBeerDAO;
import com.example.security.dao.UserDAO;
import com.example.security.model.AppUser;
import com.example.security.model.UserBeer;

@Controller
public class UserController {

    private static final String LIST = "/user/beer/list";
    private static final String PUT = "/user/beer/put";
    private static final String ADD = "/user/beer/add";
    private static final String DELETE = "/user/beer/delete";
    private static final String FILTER = "/user/beer/filter";

    @Autowired
    private UserBeerDAO userBeerDAO;
    @Autowired
    private BeerDAO beerDAO;
    @Autowired
    private StyleDAO styleDAO;
    @Autowired
    private CountryDAO countryDAO;
    @Autowired
    private BreweryDAO breweryDAO;
    @Autowired
    private SnackDAO snackDAO;
    @Autowired
    private UserDAO userDAO;


    @Secured(value = { "user" })
    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public String list(Model model, Principal principal,
            @RequestParam(value = "name", required = false) String beerName,
            @RequestParam(value = "style", required = false) String style,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "brewery", required = false) String brewery,
            @RequestParam(value = "count", required = false) String count,
            @RequestParam(value = "rate", required = false) String rate,
            @RequestParam(value = "craft", required = false) String craft,
            @RequestParam(value = "star", required = false) String star) {

        User user = (User) ((Authentication) principal).getPrincipal();
        String userName = user.getUsername();

        if (beerName != null) {
            List<UserBeer> beers =
                    userBeerDAO.getBeerByBeerNameForUser(userName, beerName);
            model.addAttribute("beers", beers);
        } else if (style != null) {
            List<UserBeer> beers = userBeerDAO.getBeerByStyleForUser(userName,
                    Integer.valueOf(style));
            model.addAttribute("beers", beers);
        } else if (country != null) {
            List<UserBeer> beers = userBeerDAO.getBeerByCountryForUser(userName,
                    Integer.valueOf(country));
            model.addAttribute("beers", beers);
        } else if (brewery != null) {
            List<UserBeer> beers = userBeerDAO.getBeerByBreweryForUser(userName,
                    Integer.valueOf(brewery));
            model.addAttribute("beers", beers);
        } else if (count != null) {
            List<UserBeer> beers = userBeerDAO.getBeerByCountForUser(userName,
                    Integer.valueOf(count));
            model.addAttribute("beers", beers);
        } else if (rate != null) {
            List<UserBeer> beers = userBeerDAO.getBeerByRateForUser(userName,
                    Integer.valueOf(rate));
            model.addAttribute("beers", beers);
        } else if (craft != null) {
            List<UserBeer> beers = userBeerDAO.getBeerByCraftForUser(userName,
                    Boolean.valueOf(craft));
            model.addAttribute("beers", beers);
        } else if (star != null) {
            List<UserBeer> beers = userBeerDAO.getBeerByStarForUser(userName,
                    Boolean.valueOf(star));
            model.addAttribute("beers", beers);
        } else {
            List<UserBeer> beers = userBeerDAO.getAllBeerForUser(userName);
            model.addAttribute("beers", beers);
        }

        model.addAttribute("button_name", "Change");
        model.addAttribute("button_action", PUT);

        return "user_list_page";
    }


    @Secured(value = { "user" })
    @RequestMapping(value = ADD, method = RequestMethod.GET)
    public String addGet(Model model, Principal principal,
            @RequestParam(value = "id", required = true) String id) {

        Beer beer = beerDAO.getBeer(Long.parseLong(id));
        BeerForm beerForm = new BeerForm(beer);
        model.addAttribute("beerForm", beerForm);

        List<Style> styles = styleDAO.getByBeerId(beer.getId());
        model.addAttribute("styles", styles);

        List<Country> countries = countryDAO.getByBeerId(beer.getId());
        model.addAttribute("countries", countries);

        List<Brewery> breweries = breweryDAO.getByBeerId(beer.getId());
        model.addAttribute("breweries", breweries);

        List<Snack> snacks = snackDAO.getByBeerId(beer.getId());
        model.addAttribute("snacks", snacks);

        model.addAttribute("action", "Add");
        model.addAttribute("ref", ADD);

        return "user_beer_page";
    }


    @Secured(value = { "user" })
    @RequestMapping(value = PUT, method = RequestMethod.GET)
    public String putGet(Model model, Principal principal,
            @RequestParam(value = "id", required = true) String id) {
        User user = (User) ((Authentication) principal).getPrincipal();
        String userName = user.getUsername();

        UserBeer userBeer =
                userBeerDAO.getBeerForUser(userName, Long.parseLong(id));
        BeerForm beerForm = new BeerForm(userBeer);
        model.addAttribute("beerForm", beerForm);

        List<Style> styles = styleDAO.getByBeerId(userBeer.getIdBeer());
        model.addAttribute("styles", styles);

        List<Country> countries = countryDAO.getByBeerId(userBeer.getIdBeer());
        model.addAttribute("countries", countries);

        List<Brewery> breweries = breweryDAO.getByBeerId(userBeer.getIdBeer());
        model.addAttribute("breweries", breweries);

        List<Snack> snacks = snackDAO.getByBeerId(userBeer.getIdBeer());
        model.addAttribute("snacks", snacks);

        model.addAttribute("action", "Put");
        model.addAttribute("ref_put", PUT);
        model.addAttribute("ref_delete", DELETE);

        return "user_beer_page";
    }


    @Secured(value = { "user" })
    @RequestMapping(value = PUT, method = RequestMethod.POST)
    public String putPost(Model model, Principal principal, BeerForm beerForm) {
        User user = (User) ((Authentication) principal).getPrincipal();

        AppUser appUser = userDAO.get(user.getUsername());

        userBeerDAO.putBeerForUser(appUser.getId(), beerForm);

        return "redirect:" + LIST;
    }


    @Secured(value = { "user" })
    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String addPost(Model model, Principal principal, BeerForm beerForm) {
        User user = (User) ((Authentication) principal).getPrincipal();

        AppUser appUser = userDAO.get(user.getUsername());

        userBeerDAO.addBeerForUser(appUser.getId(), beerForm);

        return "redirect:" + LIST;
    }


    @Secured(value = { "user" })
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public String delete(Model model, Principal principal, BeerForm beerForm) {
        User user = (User) ((Authentication) principal).getPrincipal();

        AppUser appUser = userDAO.get(user.getUsername());

        userBeerDAO.deleteBeerForUser(appUser.getId(), beerForm);

        return "redirect:" + LIST;
    }


    @Secured(value = { "user" })
    @RequestMapping(value = FILTER, method = RequestMethod.GET)
    public String filterGet(Model model, Principal principal) {
        model.addAttribute("button_action", LIST);

        List<Style> styles = styleDAO.getAll();
        model.addAttribute("styles", styles);

        List<Country> countries = countryDAO.getAll();
        model.addAttribute("countries", countries);

        List<Brewery> breweries = breweryDAO.getAll();
        model.addAttribute("breweries", breweries);

        return "beer_filter_page";
    }


    @Secured(value = { "user" })
    @RequestMapping(value = FILTER, method = RequestMethod.POST)
    public String filterPost(Model model, Principal principal,
            @RequestParam(value = "style", required = false) String style,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "brewery", required = false) String brewery,
            @RequestParam(value = "count", required = false) String count,
            @RequestParam(value = "rate", required = false) String rate,
            @RequestParam(value = "craft", required = false) String craft,
            @RequestParam(value = "star", required = false) String star) {
        // beerDAO.getBeerByParams(beerFilter);

        return "redirect:" + LIST;
    }
}
