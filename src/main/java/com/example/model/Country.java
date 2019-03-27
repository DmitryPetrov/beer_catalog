package com.example.model;

public class Country implements BeerInfo, Comparable<Country>{

    private long id;
    private String name;

    public Country(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country() {
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
    public int compareTo(Country obj) {
        if (this.id > obj.id) {
            return 1;
        } else if (this.id < obj.id) {
            return -1;
        }
        return 0;
    }
}
