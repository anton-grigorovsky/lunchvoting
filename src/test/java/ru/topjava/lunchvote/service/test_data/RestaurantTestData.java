package ru.topjava.lunchvote.service.test_data;

import ru.topjava.lunchvote.model.Address;
import ru.topjava.lunchvote.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.lunchvote.util.Constants.START_SEQ;

/**
 * Created by Антон on 03.04.2018.
 */
public class RestaurantTestData {
    public static final int RESTAURANT_IDS = START_SEQ + 3;

    public static final Restaurant RESTAURANT1 = new Restaurant(
            RESTAURANT_IDS,
            "Строганофф",
            new Address("СПб", "Невский пр.", 17));
    public static final Restaurant RESTAURANT2 = new Restaurant(
            RESTAURANT_IDS + 1,
            "Terrassa",
            new Address("СПб", "Лиговский пр.", 11));
    public static final Restaurant RESTAURANT3 = new Restaurant(
            RESTAURANT_IDS + 2,
            "Столовая",
            new Address("СПб", "Большая Конюшенная ул", 43));
    public static final Restaurant RESTAURANT4 = new Restaurant(
            RESTAURANT_IDS + 3,
            "McDonalds",
            new Address("СПб", "Наб. Реки Фонтанки", 82));

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
        return new Restaurant(null, "Созданный", new Address("СПб", "Пулковская ул.", 10));
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_IDS, "Обновленный", new Address("СПб", "Пулковская ул.", 10));
    }
}
