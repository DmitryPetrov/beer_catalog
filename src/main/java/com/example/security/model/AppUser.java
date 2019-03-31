package com.example.security.model;


public class AppUser {

    private Long id;
    private String name;
    private String encryptedPassword;
 
    public AppUser() {
 
    }
 
    public AppUser(Long id, String name, String encryptedPassword) {
        this.id = id;
        this.name = name;
        this.encryptedPassword = encryptedPassword;
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
 
    public String getEncrytedPassword() {
        return encryptedPassword;
    }
 
    public void setEncrytedPassword(String encrytedPassword) {
        this.encryptedPassword = encrytedPassword;
    }
 
    @Override
    public String toString() {
        return this.name + "/" + this.encryptedPassword;
    }
 
}
