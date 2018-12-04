package ru.topjava.lunchvote.model;

import ru.topjava.lunchvote.util.Constants;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Антон on 27.05.2018.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BaseEntity() {

    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NamedBaseEntity that = (NamedBaseEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id : 0;
    }
}
