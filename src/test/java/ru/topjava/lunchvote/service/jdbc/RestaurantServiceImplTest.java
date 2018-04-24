package ru.topjava.lunchvote.service.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.topjava.lunchvote.model.Restaurant;
import ru.topjava.lunchvote.service.RestaurantService;
import ru.topjava.lunchvote.util.exception.NotFoundException;

import static ru.topjava.lunchvote.RestaurantTestData.*;

/**
 * Created by Антон on 03.04.2018.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@RunWith(SpringJUnit4ClassRunner.class)
public class RestaurantServiceImplTest {
    @Autowired
    private RestaurantService service;


    @Test
    public void get() {
        Restaurant actual = service.get(RESTAURANT1_ID);
        assertMatch(RESTAURANT1, actual);
    }

    @Test
    public void create() {
        Restaurant created = getCreated();
        service.create(created);
        assertMatch(service.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, created);
    }

    @Test
    public void  getAll() {
        assertMatch(service.getAll(), RESTAURANTS);

    }

    @Test
    public void  update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(RESTAURANT1_ID), updated);

    }

    @Test
    public void  delete() {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), RESTAURANT2, RESTAURANT3, RESTAURANT4);

    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        Restaurant updated = getUpdated();
        updated.setId(1);
        service.update(updated);

    }

}