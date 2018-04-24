package ru.topjava.lunchvote.model;

/**
 * Created by Антон on 24.04.2018.
 */
public class Dish extends AbstractBaseEntity {
    private double price;
    private Restaurant restaurant;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
