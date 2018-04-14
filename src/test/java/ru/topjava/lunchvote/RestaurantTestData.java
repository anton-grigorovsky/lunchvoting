package ru.topjava.lunchvote;

import ru.topjava.lunchvote.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.lunchvote.model.AbstractBaseEntity.START_SEQ;

/**
 * Created by Антон on 03.04.2018.
 */
public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 3;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Строганофф");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Terrassa");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Столовая");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3, "McDonalds");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4);

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static Restaurant getCreated() {
        return new Restaurant(null, "Созданный");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Обновленный");
    }
}
