package ru.topjava.lunchvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.topjava.lunchvote.model.Dish;
import ru.topjava.lunchvote.repository.DishRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static ru.topjava.lunchvote.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Антон on 29.05.2018.
 */
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishRepository repository;

    @Override
    public Dish get(int id) {
        return null;
    }

    @Override
    public List<Dish> create(List<Dish> dishes) {
        return repository.save(dishes);
    }

    @Override
    public void update(Dish dish) {
         checkNotFoundWithId(repository.save(Collections.singletonList(dish)), dish.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Dish> getByDateAndRestaurant(LocalDate date, int restaurantId) {
        return repository.getByDateAndRestaurant(date, restaurantId);
    }
}
