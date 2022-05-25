package io.pliant.internship2022.servicelocator.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "name")
public class NameEntity extends BaseEntity{

    private String name;

    public NameEntity() {
    }

    public String getName() {
        return name;
    }

    public NameEntity setName(String name) {
        this.name = name;
        return this;
    }
}
