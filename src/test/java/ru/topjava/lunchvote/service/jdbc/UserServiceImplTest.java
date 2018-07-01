package ru.topjava.lunchvote.service.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.topjava.lunchvote.model.User;
import ru.topjava.lunchvote.service.UserService;
import ru.topjava.lunchvote.util.exception.NotFoundException;

import static ru.topjava.lunchvote.service.test_data.UserTestData.*;

/**
 * Created by Антон on 30.06.2018.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
    @Autowired
    private UserService service;

    @Test
    public void get() {
        User actual = service.get(USER_ID);
        assertMatch(ADMIN, actual);
    }

    @Test
    public void create() {
        User created = getCreated();
        service.create(created);
        assertMatch(service.getAll(), ADMIN, USER_1, USER_2, created);
    }
    @Test
    public void  getAll() {
        assertMatch(service.getAll(), USERS);

    }

    @Test
    public void  update() {
        User updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(USER_ID + 2), updated);

    }

    @Test
    public void  delete() {
        service.delete(USER_1.getId());
        assertMatch(service.getAll(), ADMIN, USER_2);

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
        User updated = getUpdated();
        updated.setId(1);
        service.update(updated);

    }
}
