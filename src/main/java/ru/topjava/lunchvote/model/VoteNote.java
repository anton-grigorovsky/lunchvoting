package ru.topjava.lunchvote.model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import ru.topjava.lunchvote.model.hibernate.VoteNoteKey;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Антон on 28.03.2018.
 */
@Entity
@Table(name = "vote_notes")
@IdClass(VoteNoteKey.class)
public class VoteNote {

    @Id
    @Column(insertable = false, updatable = false)
    @NotNull
    private LocalDate date;

    @NotNull
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Id
    @NotNull
    @Column(name = "user_id")
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

    public VoteNoteKey getKey()
    {
        return new VoteNoteKey(date, userId);
    }
}
