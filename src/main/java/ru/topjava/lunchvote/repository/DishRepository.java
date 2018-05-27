package ru.topjava.lunchvote.repository;

import ru.topjava.lunchvote.model.Dish;

import java.util.List;

/**
 * Created by Антон on 25.04.2018.
 */
public interface DishRepository {

    public Dish get(int id);

    public Dish save(Dish dish);

    public boolean delete(int id);

    public List<Dish> getAll(int restaurantId);

}
