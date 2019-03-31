package com.example.product.model;

public class BeerCountry {

    private long idBeer;
    private long idCountry;

    public BeerCountry(long idBeer, long idCountry) {
        this.idBeer = idBeer;
        this.idCountry = idCountry;
    }

    public long getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(long idBeer) {
        this.idBeer = idBeer;
    }

    public long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(long idCountry) {
        this.idCountry = idCountry;
    }
}
