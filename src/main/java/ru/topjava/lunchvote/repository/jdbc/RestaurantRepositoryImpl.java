package ru.topjava.lunchvote.repository.jdbc;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvote.model.Restaurant;
import ru.topjava.lunchvote.repository.RestaurantRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 30.03.2018.
 */
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private static final String URL = "jdbc:hsqldb:mem:lunchvoting";
    private static final String NAME = "sa";
    private static final String PASSWORD = "";

    private Connection connection;

    @Override
    public Restaurant save(Restaurant restaurant) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        int id = 0;

        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            connection.setAutoCommit(false);
            if(restaurant.isNew()) {
                statement = connection.prepareStatement("INSERT INTO restaurant (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, restaurant.getName());
                statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()){
                    restaurant.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }

            }
            else {
                statement  = connection.prepareStatement("UPDATE restaurant SET name=? WHERE id=?");
                statement.setString(1, restaurant.getName());
                statement.setInt(2, restaurant.getId());
                rs = statement.executeQuery();
                List<Restaurant> restaurants = getRestaurantsFromRs(rs);
                restaurant =  restaurants != null ? DataAccessUtils.singleResult(restaurants) : null;
            }

            connection.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }
            catch (SQLException ex) {
                e.printStackTrace();
            }
        }
        finally {
            try {
                if (connection != null) {
                    statement.close();
                    connection.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return restaurant;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            statement = connection.prepareStatement("DELETE FROM restaurant WHERE id=?");
            statement.setInt(1, id);
            return statement.executeUpdate()!=0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    statement.close();
                    connection.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Restaurant get(int id) {
        PreparedStatement statement = null;
        ResultSet rs;
        List<Restaurant> restaurants = null;
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            statement = connection.prepareStatement("SELECT * FROM restaurant WHERE id=? ");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            restaurants = getRestaurantsFromRs(rs);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    statement.close();
                    connection.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return restaurants != null ? DataAccessUtils.singleResult(restaurants) : null;
    }

    @Override
    public List<Restaurant> getAll() {
        Statement statement = null;
        ResultSet rs = null;
        List<Restaurant> restaurants = null;
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM restaurant");
            restaurants = getRestaurantsFromRs(rs);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    statement.close();
                    connection.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return restaurants;
    }

    private List<Restaurant> getRestaurantsFromRs(ResultSet rs) {
        if(rs==null) {
            return null;
        }
        else {
            List<Restaurant> restaurants = new ArrayList<>();
            try {
                while (rs.next()) {
                    Restaurant restaurant = new Restaurant();
                    restaurant.setId(rs.getInt(1));
                    restaurant.setName(rs.getString(2));
                    restaurants.add(restaurant);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return restaurants;
        }

    }
}
