
package com.example.security.model;

public class Role {

    private Long id;
    private String name;


    public Role() {

    }


    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long userId) {
        this.id = userId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
