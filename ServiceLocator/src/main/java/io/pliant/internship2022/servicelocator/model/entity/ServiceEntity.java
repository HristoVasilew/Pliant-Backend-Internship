package io.pliant.internship2022.servicelocator.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "service")
public class ServiceEntity extends BaseEntity{
    @Transient
    private Object object;

    @OneToOne
    private NameEntity name;

    public ServiceEntity() {
    }

    public Object getObject() {
        return object;
    }

    public ServiceEntity setObject(Object object) {
        this.object = object;
        return this;
    }

    public NameEntity getName() {
        return name;
    }

    public ServiceEntity setName(NameEntity name) {
        this.name = name;
        return this;
    }
}
