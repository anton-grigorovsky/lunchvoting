package ru.topjava.lunchvote.model;

/**
 * Created by Антон on 28.03.2018.
 */
public abstract  class AbstractBaseEntity {
    public static final int START_SEQ = 100000;

    protected Integer id;
    protected String name;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractBaseEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public AbstractBaseEntity() {

    }

    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractBaseEntity that = (AbstractBaseEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id : 0;
    }
}
