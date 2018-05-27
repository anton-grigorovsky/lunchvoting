package ru.topjava.lunchvote.model;

import java.util.List;

/**
 * Created by Антон on 28.03.2018.
 */
public class Restaurant extends NamedBaseEntity {

    private Address address;

    public Restaurant() {
        super();
    }

    public Restaurant(Integer id, String name, Address address) {
        super(id, name);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
