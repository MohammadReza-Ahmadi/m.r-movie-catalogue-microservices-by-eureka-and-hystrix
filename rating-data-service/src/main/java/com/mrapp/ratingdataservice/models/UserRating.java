package com.mrapp.ratingdataservice.models;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/10/2020, 7:39 AM
*/

import java.util.List;

public class UserRating {
    private String userId;
    private List<Rating> ratingList;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
