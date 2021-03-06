
package com.example.product.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.product.dao.BeerBreweryDAO;
import com.example.product.dao.BeerCountryDAO;
import com.example.product.dao.BeerDAO;
import com.example.product.dao.BeerSnackDAO;
import com.example.product.dao.BeerStyleDAO;
import com.example.product.dao.BreweryDAO;
import com.example.product.dao.CountryDAO;
import com.example.product.dao.SnackDAO;
import com.example.product.dao.StyleDAO;
import com.example.product.form.BeerForm;
import com.example.product.model.Beer;
import com.example.product.model.BeerInfo;
import com.example.product.model.Brewery;
import com.example.product.model.Country;
import com.example.product.model.Snack;
import com.example.product.model.Style;

@Controller
public class BeerController {

    private static final String LIST = "/beer/list";
    private static final String PUT = "/beer/put";
    private static final String ADD = "/beer/add";
    private static final String DELETE = "/beer/delete";
    private static final String FILTER = "/beer/filter";
    private static final String SHOW = "/beer/show";

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
    private BeerBreweryDAO beerBreweryDAO;
    @Autowired
    private BeerStyleDAO beerStyleDAO;
    @Autowired
    private BeerCountryDAO beerCountryDAO;
    @Autowired
    private BeerSnackDAO beerSnackDAO;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(Model model, Principal principal) {
        List<Beer> beers = beerDAO.getAll();
        model.addAttribute("beers", beers);

        if (principal == null) {
            model.addAttribute("button_name", "Show");
            model.addAttribute("button_action", SHOW);
            return "list_page";
        }

        User user = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities != null && !authorities.isEmpty()) {
            for (GrantedAuthority a : authorities) {
                if (a.getAuthority().equalsIgnoreCase("admin")) {
                    model.addAttribute("button_name", "Change");
                    model.addAttribute("button_action", PUT);
                    break;
                }

                if (a.getAuthority().equalsIgnoreCase("user")) {
                    model.addAttribute("button_name", "Add to me");
                    model.addAttribute("button_action", "/user/beer/add");
                    break;
                }
            }
        }

        return "list_page";
    }


    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public String list(Model model, Principal principal,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "style", required = false) String style,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "brewery", required = false) String brewery,
            @RequestParam(value = "count", required = false) String count,
            @RequestParam(value = "rate", required = false) String rate,
            @RequestParam(value = "craft", required = false) String craft,
            @RequestParam(value = "star", required = false) String star) {

        if (principal == null) {
            model.addAttribute("button_name", "Show");
            model.addAttribute("button_action", SHOW);
        } else {
            User user = (User) ((Authentication) principal).getPrincipal();
            Collection<GrantedAuthority> authorities = user.getAuthorities();

            if (authorities != null && !authorities.isEmpty()) {
                for (GrantedAuthority a : authorities) {
                    if (a.getAuthority().equalsIgnoreCase("admin")) {
                        model.addAttribute("button_name", "Change");
                        model.addAttribute("button_action", PUT);
                        break;
                    }

                    if (a.getAuthority().equalsIgnoreCase("user")) {
                        model.addAttribute("button_name", "Add to me");
                        model.addAttribute("button_action", "/user/beer/add");
                        break;
                    }
                }
            }
        }

        if (name != null) {
            List<Beer> beers = beerDAO.getByName(name);
            model.addAttribute("beers", beers);
        } else if (style != null) {
            List<Beer> beers = beerDAO.getByStyle(Integer.valueOf(style));
            model.addAttribute("beers", beers);
        } else if (country != null) {
            List<Beer> beers = beerDAO.getByCountry(Integer.valueOf(country));
            model.addAttribute("beers", beers);
        } else if (brewery != null) {
            List<Beer> beers = beerDAO.getByBrewery(Integer.valueOf(brewery));
            model.addAttribute("beers", beers);
        } else if (count != null) {
            List<Beer> beers = beerDAO.getByCount(Integer.valueOf(count));
            model.addAttribute("beers", beers);
        } else if (rate != null) {
            List<Beer> beers = beerDAO.getByRate(Integer.valueOf(rate));
            model.addAttribute("beers", beers);
        } else if (craft != null) {
            List<Beer> beers = beerDAO.getByCraft(Boolean.valueOf(craft));
            model.addAttribute("beers", beers);
        } else if (star != null) {
            List<Beer> beers = beerDAO.getByStar(Boolean.valueOf(star));
            model.addAttribute("beers", beers);
        } else {
            List<Beer> beers = beerDAO.getAll();
            model.addAttribute("beers", beers);

        }

        return "list_page";
    }


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = PUT, method = RequestMethod.GET)
    public String putGet(Model model,
            @RequestParam(value = "id", required = true) String id) {
        Beer beer = beerDAO.getBeer(Long.parseLong(id));
        BeerForm beerForm = new BeerForm(beer);
        model.addAttribute("beerForm", beerForm);

        List<Long> shouldBeMarkedStyles =
                beerStyleDAO.getIdStyleByIdBeer(beer.getId());
        List<BeerInfo> allStyle = styleDAO.getAllLikeBeerInfo();
        Map<BeerInfo, Boolean> markedStyles =
                setMarkFlags(allStyle, shouldBeMarkedStyles);
        model.addAttribute("markedStyles", markedStyles);

        List<Long> shouldBeMarkedCounties =
                beerCountryDAO.getIdCountryByIdBeer(beer.getId());
        List<BeerInfo> allCountries = countryDAO.getAllLikeBeerInfo();
        Map<BeerInfo, Boolean> markedCountries =
                setMarkFlags(allCountries, shouldBeMarkedCounties);
        model.addAttribute("markedCountries", markedCountries);

        List<Long> shouldBeMarkedBreweries =
                beerBreweryDAO.getIdBreweryByIdBeer(beer.getId());
        List<BeerInfo> allBreweries = breweryDAO.getAllLikeBeerInfo();
        Map<BeerInfo, Boolean> markedBreweries =
                setMarkFlags(allBreweries, shouldBeMarkedBreweries);
        model.addAttribute("markedBreweries", markedBreweries);

        model.addAttribute("action", "Put");
        model.addAttribute("ref_put", PUT);
        model.addAttribute("ref_delete", DELETE);

        return "beer_page";
    }


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = ADD, method = RequestMethod.GET)
    public String addGet(Model model) {
        BeerForm beerForm = new BeerForm(50, 1);
        model.addAttribute("beerForm", beerForm);

        List<BeerInfo> allStyle = styleDAO.getAllLikeBeerInfo();
        Map<BeerInfo, Boolean> markedStyles =
                setMarkFlags(allStyle, Collections.emptyList());
        model.addAttribute("markedStyles", markedStyles);

        List<BeerInfo> allCountries = countryDAO.getAllLikeBeerInfo();
        Map<BeerInfo, Boolean> markedCountries =
                setMarkFlags(allCountries, Collections.emptyList());
        model.addAttribute("markedCountries", markedCountries);

        List<BeerInfo> allBreweries = breweryDAO.getAllLikeBeerInfo();
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


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = PUT, method = RequestMethod.POST)
    public String putPost(Model model, BeerForm beerForm) {
        beerDAO.put(beerForm);

        return "redirect:" + LIST;
    }


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String addPost(Model model, BeerForm beerForm) {
        beerDAO.add(beerForm);

        return "redirect:" + LIST;
    }


    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public String delete(Model model, BeerForm beerForm) {
        beerDAO.delete(beerForm);

        return "redirect:" + LIST;
    }


    @RequestMapping(value = FILTER, method = RequestMethod.GET)
    public String filterGet(Model model) {
        model.addAttribute("button_action", LIST);

        List<Style> styles = styleDAO.getAll();
        model.addAttribute("styles", styles);

        List<Country> countries = countryDAO.getAll();
        model.addAttribute("countries", countries);

        List<Brewery> breweries = breweryDAO.getAll();
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

        return "redirect:" + LIST;
    }


    @RequestMapping(value = SHOW, method = RequestMethod.GET)
    public String showGet(Model model,
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

        model.addAttribute("action", "Show");

        return "show_beer_page";
    }

}
