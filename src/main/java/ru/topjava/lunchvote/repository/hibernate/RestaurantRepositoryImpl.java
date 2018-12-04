package ru.topjava.lunchvote.repository.hibernate;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvote.model.Address;
import ru.topjava.lunchvote.model.Restaurant;
import ru.topjava.lunchvote.repository.RestaurantRepository;
import ru.topjava.lunchvote.util.DBCredentials;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 30.03.2018.
 */
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Restaurant get(int id) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
