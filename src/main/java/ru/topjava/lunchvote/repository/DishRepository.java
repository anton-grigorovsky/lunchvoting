package ru.topjava.lunchvote.repository;

import ru.topjava.lunchvote.model.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Антон on 25.04.2018.
 */
public interface DishRepository {

    Dish get(int id);

    List<Dish> save(List<Dish> dishes);

    boolean delete(int id);

    List<Dish> getByDateAndRestaurant(LocalDate date, int restaurantId);

}
