package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Developer extends PanacheEntity {

    @Column(unique = true)
    public String name;
}
