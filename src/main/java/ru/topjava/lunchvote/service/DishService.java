package ru.topjava.lunchvote.service;

import ru.topjava.lunchvote.model.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Антон on 29.05.2018.
 */
public interface DishService {

    public Dish get(int id);

    public List<Dish> create(List<Dish> dishes);

    public void update(Dish dish);

    public void delete(int id);

    public List<Dish> getByDateAndRestaurant(LocalDate date, int restaurantId);
}
