package ru.topjava.lunchvote.model;

/**
 * Created by Антон on 28.03.2018.
 */
public abstract  class NamedBaseEntity extends BaseEntity {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NamedBaseEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public NamedBaseEntity() {

    }
}
