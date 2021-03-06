package org.example.models;

import java.util.Objects;

public class Plant {

    private String name;
    private int age;
    private int is_trimmed;
    private int is_sick;

    public Plant(String name, int age, int is_trimmed, int is_sick) {
        this.name = name;
        this.age = age;
        if (is_trimmed != 0 && is_trimmed != 1) {
            throw new IllegalArgumentException("Is_trimmed can only be 0 or 1.");
        }
        else {
            this.is_trimmed = is_trimmed;
        }

        if (is_sick != 0 && is_sick != 1) {
            throw new IllegalArgumentException("Is_sick can only be 0 or 1.");
        }
        else {
            this.is_sick = is_sick;
        }

    }

    public String getName() {
        return name;
    }



    public int getAge() {
        return age;
    }



    public int getIs_trimmed() {
        return is_trimmed;
    }



    public int getIs_sick() {
        return is_sick;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return age == plant.age && is_trimmed == plant.is_trimmed && is_sick == plant.is_sick && name.equals(plant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, is_trimmed, is_sick);
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", is_trimmed=" + is_trimmed +
                ", is_sick=" + is_sick +
                '}';
    }
}
