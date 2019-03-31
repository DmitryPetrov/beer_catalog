package com.example.product.model;

public class BeerStyle {

    private long idBeer;
    private long idStyle;

    public BeerStyle(long idBeer, long idStyle) {
        this.idBeer = idBeer;
        this.idStyle = idStyle;
    }

    public long getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(long idBeer) {
        this.idBeer = idBeer;
    }

    public long getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(long idStyle) {
        this.idStyle = idStyle;
    }
}
