package ru.topjava.lunchvote;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.topjava.lunchvote.model.Address;
import ru.topjava.lunchvote.model.Restaurant;
import ru.topjava.lunchvote.service.RestaurantService;

/**
 * Created by Антон on 06.04.2018.
 */
public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml")) {
            RestaurantService service = appCtx.getBean(RestaurantService.class);
            System.out.println("=========================================================");

            service.create(new Restaurant(null, "dfsdf", new Address(null, "СПб", "ул. Ленина", 12)));
            System.out.println("=========================================================");
        }
    }
}
