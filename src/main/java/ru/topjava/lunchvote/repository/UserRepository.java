package ru.topjava.lunchvote.repository;

import ru.topjava.lunchvote.model.User;

import java.util.List;

/**
 * Created by Антон on 27.05.2018.
 */
public interface UserRepository {

    public User get(int id);
    public List<User> getAll();
    public User getByEmail();
    public boolean delete(int id);
    public User save(User user);
}
