package ru.topjava.lunchvote.model;

/**
 * Created by Антон on 27.05.2018.
 */
public class Address extends BaseEntity{
    private String city;
    private String street;
    private Integer building;

    public Address() {
    }

    public Address(Integer id, String city, String street, Integer building) {
        super(id);
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
