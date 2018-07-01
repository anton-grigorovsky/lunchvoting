package ru.topjava.lunchvote.model;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * Created by Антон on 28.03.2018.
 */
public class VoteNote {

    private LocalDate date;
    private Integer restaurantId;
    private Integer userId;

    public VoteNote() {
    }

    public VoteNote(LocalDate date, Integer restaurantId, Integer userId) {
        this.date = date;
        this.restaurantId = restaurantId;
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "VoteNote{" +
                "date=" + date +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                '}';
    }
}
