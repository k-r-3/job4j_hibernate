package ru.job4j.hibernate.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "j_car_type")
public class CarType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static CarType of(String name) {
        CarType type = new CarType();
        type.name = name;
        return type;
    }

    public int getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarType carType = (CarType) o;
        return id == carType.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
