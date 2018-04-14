package ru.topjava.lunchvote.model;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Антон on 28.03.2018.
 */
public class VoteNote {
    private LocalDate localDate;
    private Set<User> users;
    private Restaurant restaurant;

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
