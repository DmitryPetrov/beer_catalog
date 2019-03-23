package com.example.model;

public class Beer {

    private static final int MIN_RATE = 0;
    private static final int MAX_RATE = 100;
    
    private long id;
    private int rate;
    private int count;
    private boolean star;
    private boolean craft;
    
    private String name;
    private String description;
    private String photo;
    
    public Beer(long id, int rate, int count, boolean star, boolean craft, String name,
            String description, String photo) {
        this.id = id;
        this.rate = rate;
        this.count = count;
        this.star = star;
        this.craft = craft;
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        if (rate < MIN_RATE) {
            this.rate = MIN_RATE;
        }
        
        if (rate > MAX_RATE) {
            this.rate = MAX_RATE;
        }
        
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCraft() {
        return craft;
    }

    public void setCraft(boolean craft) {
        this.craft = craft;
    }
    
    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
