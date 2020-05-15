package com.mrapp.moviecatalogueservice.models;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/8/2020, 7:51 PM
*/

public class CatalogueItem {
    private String name;
    private String desc;
    private int rating;

    public CatalogueItem() {
    }

    public CatalogueItem(String name, String desc, int rating) {
        this.name = name;
        this.desc = desc;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
