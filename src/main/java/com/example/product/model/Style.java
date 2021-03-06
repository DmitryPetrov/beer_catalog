package com.example.product.model;

public class Style implements BeerInfo, Comparable<Style>{

    private long id;
    private String name;

    public Style(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Style() {
        // TODO Auto-generated constructor stub
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Style obj) {
        if (this.id > obj.id) {
            return 1;
        } else if (this.id < obj.id) {
            return -1;
        }
        return 0;
    }
}
