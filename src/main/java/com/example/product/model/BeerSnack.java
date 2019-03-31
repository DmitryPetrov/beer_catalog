package com.example.product.model;

public class BeerSnack {
    
    private long idBeer;
    private long idSnack;

    public BeerSnack(long idBeer, long idSnack) {
        this.idBeer = idBeer;
        this.idSnack = idSnack;
    }

    public long getIdSnack() {
        return idSnack;
    }

    public void setIdSnack(long idSnack) {
        this.idSnack = idSnack;
    }

    public long getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(long idBeer) {
        this.idBeer = idBeer;
    }
}
