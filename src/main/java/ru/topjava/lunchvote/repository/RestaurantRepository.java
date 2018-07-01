package ru.topjava.lunchvote.repository;

import ru.topjava.lunchvote.model.Restaurant;

import java.util.List;

/**
 * Created by Антон on 28.03.2018.
 */
public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();
}
