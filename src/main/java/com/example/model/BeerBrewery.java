package com.example.model;

public class BeerBrewery {

    private long idBeer;
    private long idBrewary;

    public BeerBrewery(long idBeer, long idBrewary) {
        this.idBeer = idBeer;
        this.idBrewary = idBrewary;
    }

    public long getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(long idBeer) {
        this.idBeer = idBeer;
    }

    public long getIdBrewary() {
        return idBrewary;
    }

    public void setIdBrewary(long idBrewary) {
        this.idBrewary = idBrewary;
    }
}
