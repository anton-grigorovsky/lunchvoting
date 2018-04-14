package ru.topjava.lunchvote.service;

import ru.topjava.lunchvote.model.Restaurant;

import java.util.List;

/**
 * Created by Антон on 28.03.2018.
 */
public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant get(int id);

    boolean delete(int id);

    Restaurant update(Restaurant restaurant);

    List<Restaurant> getAll();


}
