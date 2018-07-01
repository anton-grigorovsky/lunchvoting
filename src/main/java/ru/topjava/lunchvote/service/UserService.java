package ru.topjava.lunchvote.service;

import ru.topjava.lunchvote.model.User;
import ru.topjava.lunchvote.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Антон on 30.06.2018.
 */
public interface UserService {
    User create(User restaurant);

    User get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    User update(User restaurant) throws NotFoundException;

    List<User> getAll();
}
