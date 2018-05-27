package ru.topjava.lunchvote.model;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * Created by Антон on 28.03.2018.
 */
public class VoteNote {

    private LocalDate localDate;
    private Restaurant restaurant;
    private User user;

    public VoteNote() {
    }

    public VoteNote(LocalDate localDate, Restaurant restaurant, User user) {
        this.localDate = localDate;
        this.restaurant = restaurant;
        this.user = user;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
