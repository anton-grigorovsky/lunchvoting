package ru.topjava.lunchvote.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Антон on 24.04.2018.
 */

@DynamicUpdate
@Table(name = "dishes")
public class Dish extends NamedBaseEntity {

    @NotNull
    private double price;

    @NotNull
    private Restaurant restaurant;

    @Generated(GenerationTime.INSERT)
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Dish() {
    }

    public Dish(Integer id, String name, double price, Restaurant restaurant, LocalDate date) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", restaurant=" + restaurant +
                ", date=" + date +
                '}';
    }
}
