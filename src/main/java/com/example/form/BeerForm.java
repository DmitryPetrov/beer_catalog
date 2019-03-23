package com.example.form;

public class BeerForm {
    
    private long id;
    
    public BeerForm() {
        
    }
    
    public BeerForm(long id) {
        this.setId(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
