package ru.topjava.lunchvote.service.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.topjava.lunchvote.service.test_data.DishTestData;
import ru.topjava.lunchvote.model.Dish;
import ru.topjava.lunchvote.service.DishService;
import ru.topjava.lunchvote.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.topjava.lunchvote.service.test_data.DishTestData.*;

/**
 * Created by Антон on 31.05.2018.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("jdbc")
@RunWith(SpringJUnit4ClassRunner.class)
public class DishServiceImplTest {
    @Autowired
    private DishService service;

    @Test
    public void create()
    {
        List<Dish> dishes = DishTestData.getCreated();
        service.create(dishes);
        assertMatch(service.getByDateAndRestaurant(LocalDate.now(), 100004) ,dishes);
    }

    @Test
    public void update()
    {
        Dish updated = DishTestData.getUpdated();
        service.update(updated);
        assertMatch(service.getByDateAndRestaurant(LocalDate.of(2015, 5, 31), 100004), DISH1, DISH2, updated);
    }

    @Test
    public void delete()
    {
        service.delete(DISH_IDS);
        assertMatch(service.getByDateAndRestaurant(LocalDate.of(2015, 5, 31), 100004), DISH2, DISH3);
    }

    @Test
    public void getByDateAndRestaurant()
    {
        List<Dish> expected = DISHES;
        List<Dish> actual = service.getByDateAndRestaurant(LocalDate.of(2015, 5,31), 100004);
        assertMatch(actual, expected);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound()
    {
        Dish updated = DishTestData.getUpdated();
        updated.setId(1);
        service.update(updated);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound()
    {
        service.delete(1);
    }


}
