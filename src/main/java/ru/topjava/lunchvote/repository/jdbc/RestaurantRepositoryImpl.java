package ru.topjava.lunchvote.repository.jdbc;

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

    private Connection connection;

    @Override
    public Restaurant save(Restaurant restaurant) {
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            connection.setAutoCommit(false);
            Address address = restaurant.getAddress();
            if(restaurant.isNew()) {

                DbUtils.close(statement);
                statement = connection.prepareStatement("INSERT INTO restaurants (name, city, street, building) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, restaurant.getName());
                statement.setString(2, address.getCity());
                statement.setString(3, address.getStreet());
                statement.setInt(4, address.getBuilding());
                statement.executeUpdate();
                ResultSet generatedRestaurantKey = statement.getGeneratedKeys();
                if(generatedRestaurantKey.next()) {
                    restaurant.setId(generatedRestaurantKey.getInt(1));
                }
                else {
                    throw new SQLException("Creating restaurant failed, no ID obtained.");
                }
            }
            else {
                statement = connection.prepareStatement("UPDATE restaurants SET name=?, city=?, street=?, building=? WHERE id=?");
                statement.setString(1, restaurant.getName());
                statement.setString(2, address.getCity());
                statement.setString(3, address.getStreet());
                statement.setInt(4, address.getBuilding());
                statement.setInt(5, restaurant.getId());
                if(statement.executeUpdate() == 0)
                    throw new SQLException("Updating restaurant failed.");

            }
            connection.commit();
        }
        catch (SQLException e) {
            restaurant = null;
            e.printStackTrace();
            try {
                connection.rollback();
            }
            catch (SQLException ex) {
                e.printStackTrace();
            }
        }
        finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return restaurant;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM restaurants WHERE id=?;");
            statement.setInt(1, id);
            int updatedRows = statement.executeUpdate();
            connection.commit();

            return updatedRows!=0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    @Override
    public Restaurant get(int id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Restaurant> restaurants = null;
        try {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            statement = connection.prepareStatement("SELECT * FROM restaurants r WHERE r.id=?;");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            restaurants = getRestaurantsFromRs(rs);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DbUtils.closeQuietly(connection, statement, rs);
        }
        return restaurants != null ? DataAccessUtils.singleResult(restaurants) : null;
    }

    @Override
    public List<Restaurant> getAll() {
        Statement statement = null;
        ResultSet rs = null;
        List<Restaurant> restaurants = null;
        try {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM restaurants;");
            restaurants = getRestaurantsFromRs(rs);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DbUtils.closeQuietly(connection, statement, rs);
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
                    Address address = new Address();
                    address.setCity(rs.getString(3));
                    address.setStreet(rs.getString(4));
                    address.setBuilding(rs.getInt(5));
                    restaurant.setAddress(address);
                    restaurants.add(restaurant);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return restaurants;
        }
    }
}
