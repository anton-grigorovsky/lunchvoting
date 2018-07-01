package ru.topjava.lunchvote.repository;

import ru.topjava.lunchvote.model.User;

import java.util.List;

/**
 * Created by Антон on 27.05.2018.
 */
public interface UserRepository {

    User get(int id);
    List<User> getAll();
    User getByEmail(String email);
    boolean delete(int id);
    User save(User user);
}
