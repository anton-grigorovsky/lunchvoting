package ru.topjava.lunchvote.repository.hibernate;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvote.model.Address;
import ru.topjava.lunchvote.model.Dish;
import ru.topjava.lunchvote.model.Restaurant;
import ru.topjava.lunchvote.repository.DishRepository;
import ru.topjava.lunchvote.util.DBCredentials;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 29.05.2018.
 */
@Repository
public class DishRepositoryImpl implements DishRepository {
    @Override
    public Dish get(int id) {
        return null;
    }

    @Override
    public List<Dish> save(List<Dish> dishes) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Dish> getByDateAndRestaurant(LocalDate date, int restaurantId) {
        return null;
    }
}
