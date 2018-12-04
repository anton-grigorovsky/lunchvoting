package ru.topjava.lunchvote.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

/**
 * Created by Антон on 28.03.2018.
 */
@MappedSuperclass
public abstract  class NamedBaseEntity extends BaseEntity {

    @NotBlank
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
