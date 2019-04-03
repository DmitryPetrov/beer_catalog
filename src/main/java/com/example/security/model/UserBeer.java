package com.example.security.model;

public class UserBeer {

    private static final int MIN_RATE = 0;
    private static final int MAX_RATE = 100;
    
    private long idUser;
    private long idBeer;
    private int rate;
    private int count;
    private boolean star;
    private boolean craft;
    
    private String name;
    private String description;
    private String photo;

    
    public UserBeer(long idUser, long idBeer, int rate, int count, boolean star, boolean craft, String name,
            String description, String photo) {
        this.idUser = idUser;
        this.idBeer = idBeer;
        this.rate = rate;
        this.count = count;
        this.star = star;
        this.craft = craft;
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long id) {
        this.idUser = id;
    }
    
    public long getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(long id) {
        this.idBeer = id;
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
