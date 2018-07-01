package ru.topjava.lunchvote.service.test_data;

import ru.topjava.lunchvote.model.Role;
import ru.topjava.lunchvote.model.User;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.lunchvote.model.BaseEntity.START_SEQ;

/**
 * Created by Антон on 30.06.2018.
 */
public class UserTestData {

    public static final int USER_ID = START_SEQ;

    public static final User ADMIN = new User(
            USER_ID,
            "Admin",
            "admin@gmail.com",
            "admin",
            Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User USER_1 = new User(
            USER_ID + 1,
            "User",
            "user@yandex.ru",
            "password",
            Role.ROLE_USER);
    public static final User USER_2 = new User(
            USER_ID + 2,
            "User2",
            "user2@yandex.ru",
            "password",
            Role.ROLE_USER);

    public static final List<User> USERS = Arrays.asList(ADMIN, USER_1, USER_2);

    public static void assertMatch(User actual, User expected) {

        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "password").isEqualTo(expected);
    }

    public static User getCreated() {
        return new User(null, "Созданный", "created@yandex.ru", "password", Role.ROLE_USER);
    }

    public static User getUpdated() {
        return new User(USER_ID + 2, "Обновленный", "updated@yandex.ru", "password", EnumSet.of(/*Role.ROLE_USER,*/ Role.ROLE_ADMIN), null);
    }
}
