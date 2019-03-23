package com.example.model;

public class Snack {

    private long id;
    private String name;

    public Snack(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Snack() {
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
}
