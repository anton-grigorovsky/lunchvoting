package ru.topjava.lunchvote.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Антон on 28.03.2018.
 */
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedBaseEntity {

    @NotNull
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
