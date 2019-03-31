package com.example.product.model;

public class BeerBrewery {

    private long idBeer;
    private long idBrewery;

    public BeerBrewery(long idBeer, long idBrewery) {
        this.idBeer = idBeer;
        this.idBrewery = idBrewery;
    }

    public long getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(long idBeer) {
        this.idBeer = idBeer;
    }

    public long getIdBrewery() {
        return idBrewery;
    }

    public void setIdBrewery(long idBrewery) {
        this.idBrewery = idBrewery;
    }
}
