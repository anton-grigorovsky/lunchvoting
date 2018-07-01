package ru.topjava.lunchvote.model;

import java.time.LocalDate;

/**
 * Created by Антон on 24.04.2018.
 */
public class Dish extends NamedBaseEntity {

    private double price;

    private Integer restaurantId;

    private LocalDate date;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Dish() {
    }

    public Dish(Integer id, String name, double price, Integer restaurantId, LocalDate date) {
        super(id, name);
        this.price = price;
        this.restaurantId = restaurantId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                ", date=" + date +
                '}';
    }
}
