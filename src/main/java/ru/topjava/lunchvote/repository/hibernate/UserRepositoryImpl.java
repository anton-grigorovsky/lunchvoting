package ru.topjava.lunchvote.repository.hibernate;

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
    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User save(User user) {
        return null;
    }
}
