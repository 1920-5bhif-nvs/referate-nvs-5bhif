package at.htl.quarkus.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long id;


    private String model;
    private String brand;
    private Integer HP;

    public Car(String brand, String model, Integer HP) {
        this.model = model;
        this.brand = brand;
        this.HP = HP;
    }

    public Car(){}

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getHP() {
        return HP;
    }

    public void setHP(Integer HP) {
        this.HP = HP;
    }

}
