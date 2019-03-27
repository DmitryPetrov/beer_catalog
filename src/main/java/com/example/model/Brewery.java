package com.example.model;

public class Brewery implements BeerInfo, Comparable<Brewery>{

    private long id;
    private String name;

    public Brewery(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Brewery() {
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
    public int compareTo(Brewery obj) {
        if (this.id > obj.id) {
            return 1;
        } else if (this.id < obj.id) {
            return -1;
        }
        return 0;
    }
}
