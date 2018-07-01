package ru.topjava.lunchvote.repository.jdbc;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvote.model.Role;
import ru.topjava.lunchvote.model.User;
import ru.topjava.lunchvote.repository.UserRepository;
import ru.topjava.lunchvote.util.DBCredentials;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Антон on 12.06.2018.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private Connection connection;

    @Override
    public User get(int id) {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try
        {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            statement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<User> users = getUsersFromRs(resultSet);
            user = users == null ? null : users.get(0);
        }
        catch (SQLException e)
        {
        }
        finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try
        {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users");
            allUsers = getUsersFromRs(resultSet);
        }
        catch (SQLException e)
        {
        }
        finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return allUsers;
    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try
        {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            statement = connection.prepareStatement("SELECT * FROM users WHERE email=?");
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            user = getUsersFromRs(resultSet).get(0);
        }
        catch (SQLException e)
        {
        }
        finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement statement = null;
        int updated = 0;
        try
        {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            statement.setInt(1, id);
            updated = statement.executeUpdate();

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return updated == 1;
    }

    @Override
    public User save(User user) {
        PreparedStatement statement = null;
        try
        {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            connection.setAutoCommit(false);
            if(user.isNew())
            {
                statement = connection.prepareStatement("INSERT INTO users (name, email, password) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.executeUpdate();
                ResultSet generatedRestaurantKey = statement.getGeneratedKeys();
                if(generatedRestaurantKey.next()) {
                    user.setId(generatedRestaurantKey.getInt(1));
                }

                DbUtils.closeQuietly(statement);
                insertRoles(user.getId(), user.getRoles());
            }
            else
            {
                statement = connection.prepareStatement("UPDATE users SET name=?, email=?, password=? WHERE id=?");
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setInt(4, user.getId());
                int updated = statement.executeUpdate();
                DbUtils.closeQuietly(statement);
                if(updated == 1)
                {
                    updateRoles(user);
                }
                else
                {
                    connection.rollback();
                    return null;
                }
            }
            connection.commit();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            try
            {
                connection.rollback();
            }
            catch (SQLException ex)
            {

            }

        }
        finally {
            DbUtils.closeQuietly(connection);
        }
        return user;
    }

    private List<User> getUsersFromRs(ResultSet resultSet)
    {

        if(resultSet==null) {
            return null;
        }
        else {
            List<User> users = new ArrayList<>();
            try {
                while (resultSet.next())
                {
                    User user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setEmail(resultSet.getString(3));
                    user.setPassword(resultSet.getString(4));
                    user.setRegistered(resultSet.getTimestamp(5).toLocalDateTime());
                    users.add(setRole(user));
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return users.size() > 0 ? users : null;
        }
    }

    private User setRole(User user)
    {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT role FROM user_roles WHERE user_id=" + user.getId() + ";");
            Set<Role> roles = new HashSet<>();
            while (resultSet.next())
            {
                roles.add(Role.valueOf(resultSet.getString(1)));
            }
            user.setRoles(roles);
        }
        catch (SQLException e)
        {
        }
        finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(statement);
        }
        return user;
    }

    private void updateRoles(User user)
    {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Set<Role> roles = new HashSet<>();
        try {
            statement = connection.prepareStatement("SELECT role FROM user_roles WHERE user_id=?");
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                roles.add(Role.valueOf(resultSet.getString(1)));
            }
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(statement);
            if(roles.size() == user.getRoles().size() && roles.containsAll(user.getRoles()))
            {
                return;
            }
            else
            {
                Set<Role> setToRemove = new HashSet<>(roles);
                setToRemove.removeAll(user.getRoles());

                Set<Role> setToInsert = new HashSet<>(user.getRoles());
                setToInsert.removeAll(roles);

                if(setToRemove.size() > 0)
                {
                    statement = connection.prepareStatement("DELETE FROM user_roles WHERE user_id=? AND role=?");
                    for(Role role: setToRemove)
                    {
                        statement.setInt(1, user.getId());
                        statement.setString(2, role.name());
                        statement.addBatch();
                    }
                    statement.executeBatch();
                    DbUtils.closeQuietly(statement);
                }
                if(setToInsert.size() > 0)
                {
                    insertRoles(user.getId(), setToInsert);
                }

            }
        }
        catch (SQLException e)
        {}
        finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(statement);
        }
    }

    private void insertRoles(int userId, Set<Role> roles)
    {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO user_roles VALUES (?,?)");
            for (Role role : roles)
            {
                statement.setInt(1, userId);
                statement.setString(2, role.toString());
                statement.addBatch();
            }
            statement.executeBatch();
        }
        catch (SQLException e)
        {

        }
        finally {
            DbUtils.closeQuietly(statement);
        }
    }
}
