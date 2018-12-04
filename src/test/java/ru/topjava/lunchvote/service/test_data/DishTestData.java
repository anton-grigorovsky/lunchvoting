package ru.topjava.lunchvote.service.test_data;

import ru.topjava.lunchvote.model.Dish;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.lunchvote.service.test_data.RestaurantTestData.RESTAURANT2;
import static ru.topjava.lunchvote.util.Constants.START_SEQ;

/**
 * Created by Антон on 31.05.2018.
 */
public class DishTestData {
    public static final int DISH_IDS = START_SEQ + 22;

    public static final Dish DISH1 = new Dish(DISH_IDS, "Щи", 1.3, RESTAURANT2, LocalDate.of(2015, 5,31));
    public static final Dish DISH2 = new Dish(DISH_IDS + 1, "Солянка", 2.15, RESTAURANT2, LocalDate.of(2015, 5,31));
    public static final Dish DISH3 = new Dish(DISH_IDS + 2, "Салат Цезарь", 10.55, RESTAURANT2, LocalDate.of(2015, 5,31));

    public static final List<Dish> DISHES = Arrays.asList(DISH1, DISH2, DISH3);

    public static void assertMatch(Dish actual, Dish expected) {

        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static List<Dish> getCreated() {
        return Arrays.asList(
                new Dish(null, "Кашка", 3.25, RESTAURANT2, null),
                new Dish(null, "Супчик", 3.75, RESTAURANT2, null));
    }

    public static Dish getUpdated() {
        return new Dish(DISH_IDS + 2, "Обновленный", 1.2, RESTAURANT2, LocalDate.of(2015, 5,31));
    }
}
