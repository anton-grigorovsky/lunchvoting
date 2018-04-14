package ru.topjava.lunchvote.model;

/**
 * Created by Антон on 28.03.2018.
 */
public class Restaurant extends AbstractBaseEntity {

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant() {
        super();
    }



    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
