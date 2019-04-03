
package com.example.product.form;

import java.util.List;

import com.example.product.model.Beer;
import com.example.product.model.Brewery;
import com.example.product.model.Country;
import com.example.product.model.Style;
import com.example.security.model.UserBeer;

public class BeerForm {

    private static final int MIN_RATE = 0;
    private static final int MAX_RATE = 100;

    private long id;
    private int rate;
    private int count;
    private boolean star;
    private boolean craft;

    private String name;
    private String description;
    private String photo;

    private List<Brewery> breweries;
    private List<Country> countries;
    private List<Style> styles;

    {
        rate = 50;
        count = 1;
        star = false;
        craft = false;
        name = "";
        description = "";
        photo = "";
    }


    public BeerForm() {

    }


    public BeerForm(Beer beer) {
        this.id = beer.getId();
        this.rate = beer.getRate();
        this.count = beer.getCount();
        this.star = beer.isStar();
        this.craft = beer.isCraft();
        this.name = beer.getName();
        this.description = beer.getDescription();
        this.photo = beer.getPhoto();
    }


    public BeerForm(int rate, int count) {
        this.rate = rate;
        this.count = count;
    }


    public BeerForm(int rate, int count, boolean star, boolean craft,
            String name, String description, String photo,
            List<Brewery> breweries, List<Country> countries,
            List<Style> styles) {
        this.rate = rate;
        this.count = count;
        this.star = star;
        this.craft = craft;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.breweries = breweries;
        this.countries = countries;
        this.styles = styles;
    }


    public BeerForm(UserBeer userBeer) {
        this.id = userBeer.getIdBeer();
        this.rate = userBeer.getRate();
        this.count = userBeer.getCount();
        this.star = userBeer.isStar();
        this.craft = userBeer.isCraft();
        this.name = userBeer.getName();
        this.description = userBeer.getDescription();
        this.photo = userBeer.getPhoto();
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public int getRate() {
        return rate;
    }


    public void setRate(int rate) {
        if (rate < MIN_RATE) {
            this.rate = MIN_RATE;
        }

        if (rate > MAX_RATE) {
            this.rate = MAX_RATE;
        }

        this.rate = rate;
    }


    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }


    public boolean isCraft() {
        return craft;
    }


    public void setCraft(boolean craft) {
        this.craft = craft;
    }


    public boolean isStar() {
        return star;
    }


    public void setStar(boolean star) {
        this.star = star;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public List<Brewery> getBreweries() {
        return breweries;
    }


    public void setBreweries(List<Brewery> breweries) {
        this.breweries = breweries;
    }


    public List<Country> getCountries() {
        return countries;
    }


    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }


    public List<Style> getStyles() {
        return styles;
    }


    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

}
