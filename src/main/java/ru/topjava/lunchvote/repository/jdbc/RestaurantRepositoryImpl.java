package ru.topjava.lunchvote.repository.jdbc;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvote.model.Address;
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

        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            connection.setAutoCommit(false);

            Address address = restaurant.getAddress();
            if(!isAddressExists(address))
            {
                statement = connection.prepareStatement("INSERT INTO address (city, street, building) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, address.getCity());
                statement.setString(2, address.getStreet());
                statement.setInt(3, address.getBuilding());
                statement.executeUpdate();
                ResultSet generatedAddressKey = statement.getGeneratedKeys();

                if(generatedAddressKey.next()){
                    address.setId(generatedAddressKey.getInt(1));
                }
                else {
                    throw new SQLException("Creating address failed, no ID obtained.");
                }

                DbUtils.close(generatedAddressKey);
                DbUtils.close(statement);
            }

            if(restaurant.isNew()) {
                statement = connection.prepareStatement("INSERT INTO restaurants (name, address_id) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, restaurant.getName());
                statement.setInt(2, address.getId());
                statement.executeUpdate();
                ResultSet generatedRestaurantKey = statement.getGeneratedKeys();
                if(generatedRestaurantKey.next()) {
                    restaurant.setId(generatedRestaurantKey.getInt(1));
                }
                else {
                    throw new SQLException("Creating restaurant failed, no ID obtained.");
                }
                DbUtils.close(generatedRestaurantKey);
            }
            else {
                statement  = connection.prepareStatement("UPDATE restaurants SET name=?, address_id=?  WHERE id=?;");
                statement.setString(1, restaurant.getName());
                statement.setInt(2, address.getId());
                statement.setInt(3, restaurant.getId());
                if(statement.executeUpdate() == 0) return null;
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
    public boolean delete(Restaurant restaurant) {
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM restaurants WHERE id=?;");
            statement.setInt(1, restaurant.getId());
            int updatedRows = statement.executeUpdate();
            DbUtils.close(statement);

            if(!isAddressUsed(restaurant.getAddress()))
            {
                statement = connection.prepareStatement("DELETE FROM address WHERE id=?;");
                statement.setInt(1, restaurant.getAddress().getId());
            }
            connection.commit();
            DbUtils.close(statement);
            DbUtils.close(connection);

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
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            statement = connection.prepareStatement("SELECT * FROM restaurants r INNER JOIN address a " +
                    "ON r.address_id=a.id WHERE r.id=?;");
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
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM restaurants r INNER JOIN address a ON r.address_id=a.id;");
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
                    address.setId(rs.getInt(4));
                    address.setCity(rs.getString(5));
                    address.setStreet(rs.getString(6));
                    address.setBuilding(rs.getInt(7));
                    restaurant.setAddress(address);
                    restaurants.add(restaurant);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return restaurants;
        }
    }

    private boolean isAddressUsed(Address address)
    {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement("SELECT COUNT(address_id) FROM restaurants WHERE address_id=?");
            statement.setInt(1, address.getId());
            rs = statement.executeQuery();

            if(rs.next() && rs.getInt(1) > 0)
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
        }
        return false;
    }

    private boolean isAddressExists(Address address)
    {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            statement = connection.prepareStatement("SELECT * FROM address WHERE city=? AND street=? AND building=?;");
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setInt(3, address.getBuilding());

            rs = statement.executeQuery();

            if(rs.next())
            {
                address.setId(rs.getInt(1));
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
        }
        return false;
    }
}
