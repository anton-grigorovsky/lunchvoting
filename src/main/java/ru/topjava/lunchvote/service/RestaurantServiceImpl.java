package ru.topjava.lunchvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.topjava.lunchvote.model.Restaurant;
import ru.topjava.lunchvote.repository.RestaurantRepository;
import static ru.topjava.lunchvote.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

/**
 * Created by Антон on 03.04.2018.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository repository;


    @Override
    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
