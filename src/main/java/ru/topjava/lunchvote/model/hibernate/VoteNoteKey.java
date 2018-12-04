package ru.topjava.lunchvote.model.hibernate;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Антон on 28.07.2018.
 */
@Embeddable
public class VoteNoteKey implements Serializable {

    private LocalDate date;

    private Integer userId;

    protected VoteNoteKey() {
    }

    public VoteNoteKey(LocalDate date, Integer userId) {
        this.date = date;
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteNoteKey)) return false;

        VoteNoteKey that = (VoteNoteKey) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
