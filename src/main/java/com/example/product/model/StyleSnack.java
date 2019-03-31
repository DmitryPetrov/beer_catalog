package com.example.product.model;

public class StyleSnack {

    private long idStyle;
    private long idSnack;

    public StyleSnack(long idStyle, long idSnack) {
        this.idStyle = idStyle;
        this.idSnack = idSnack;
    }

    public long getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(long idStyle) {
        this.idStyle = idStyle;
    }

    public long getIdSnack() {
        return idSnack;
    }

    public void setIdSnack(long idSnack) {
        this.idSnack = idSnack;
    }
}
