package ru.topjava.lunchvote.service;

import ru.topjava.lunchvote.model.Restaurant;
import ru.topjava.lunchvote.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Антон on 28.03.2018.
 */
public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant get(int id) throws NotFoundException;

    void delete(Restaurant restaurant) throws NotFoundException;

    Restaurant update(Restaurant restaurant) throws NotFoundException;

    List<Restaurant> getAll();


}
