package ru.topjava.lunchvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.topjava.lunchvote.model.User;
import ru.topjava.lunchvote.repository.UserRepository;
import ru.topjava.lunchvote.util.exception.NotFoundException;

import java.util.List;
import static ru.topjava.lunchvote.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Антон on 30.06.2018.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User update(User user) throws NotFoundException {
        return checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
