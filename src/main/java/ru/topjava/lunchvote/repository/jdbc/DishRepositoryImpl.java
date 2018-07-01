package ru.topjava.lunchvote.repository.jdbc;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvote.model.Dish;
import ru.topjava.lunchvote.model.Restaurant;
import ru.topjava.lunchvote.repository.DishRepository;
import ru.topjava.lunchvote.util.DBCredentials;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 29.05.2018.
 */
@Repository
public class DishRepositoryImpl implements DishRepository {
    private Connection connection;

    @Override
    public Dish get(int id) {

        return null;
    }

    @Override
    public List<Dish> save(List<Dish> dishes) {
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            if(dishes.size() == 1 && !dishes.get(0).isNew())
            {
                Dish dish = dishes.get(0);
                statement = connection.prepareStatement("UPDATE dishes SET name=?, price=? WHERE id=?");
                statement.setString(1, dish.getName());
                statement.setDouble(2, dish.getPrice());
                statement.setInt(3, dish.getId());
                if(statement.executeUpdate() == 0) return null;
            }
            else
            {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement("INSERT INTO dishes (name, price, restaurant_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                for(Dish dish : dishes)
                {
                    statement.setString(1, dish.getName());
                    statement.setDouble(2, dish.getPrice());
                    statement.setInt(3, dish.getRestaurantId());
                    statement.addBatch();
                }
                statement.executeBatch();
                connection.commit();
                ResultSet keys = statement.getGeneratedKeys();
                int index = 0;
                while (keys.next())
                {
                    Dish dish = dishes.get(index++);
                    dish.setId(keys.getInt(1));
                    dish.setDate(LocalDate.now());
                }
            }
        }
        catch (SQLException e)
        {
            try {
                connection.rollback();
            }
            catch (SQLException ex) {
            }
            return null;
        }
        finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return dishes;
    }

    @Override
    public boolean delete(int id) {
        Statement statement = null;
        int updated = 0;
        try {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            statement = connection.createStatement();
            updated = statement.executeUpdate("DELETE FROM dishes WHERE id=" + id);
        }
        catch (SQLException e)
        {
        }
        finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return updated != 0;
    }

    @Override
    public List<Dish> getByDateAndRestaurant(LocalDate date, int restaurantId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dish> dishes = null;

        try
        {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            statement = connection.prepareStatement("SELECT * FROM dishes WHERE restaurant_id=? AND date=? ORDER BY id");
            statement.setInt(1, restaurantId);
            statement.setString(2, date.toString());
            resultSet = statement.executeQuery();
            dishes = getDishesFromRs(resultSet);
        }
        catch (SQLException e)
        {

        }
        finally
        {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return dishes;
    }

    private List<Dish> getDishesFromRs(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        } else {
            List<Dish> dishes = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    Dish dish = new Dish();
                    dish.setId(resultSet.getInt(1));
                    dish.setName(resultSet.getString(2));
                    dish.setPrice(resultSet.getDouble(3));
                    dish.setRestaurantId(resultSet.getInt(4));
                    dish.setDate(resultSet.getDate(5).toLocalDate());
                    dishes.add(dish);
                }
            } catch (SQLException e) {

            }
            return dishes;
        }
    }

}
