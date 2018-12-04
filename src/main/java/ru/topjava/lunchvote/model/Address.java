package ru.topjava.lunchvote.model;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * Created by Антон on 27.05.2018.
 */
@Embeddable
@Table
public class Address {
    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private Integer building;

    public Address() {
    }

    public Address(String city, String street, Integer building) {
        this.city = city;
        this.street = street;
        this.building = building;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }
}
