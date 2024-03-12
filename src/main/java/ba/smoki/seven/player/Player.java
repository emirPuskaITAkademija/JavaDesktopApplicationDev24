package ba.smoki.seven.player;

import ba.smoki.seven.sport.Sport;

import java.awt.*;
import java.util.Objects;

//Klasa čija struktura odgovara strukturi tabele players u bazi.
public class Player {
    private Long id;
    private String name;
    private String surname;
    private Color color;
    private Sport sport;
    private Integer years;
    private Boolean vegetarian;

    public Player() {
    }

    public Player(Long id, String name, String surname, Color color, Sport sport, Integer years, Boolean vegetarian) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.color = color;
        this.sport = sport;
        this.years = years;
        this.vegetarian = vegetarian;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + " " + surname + ", " + sport;
    }
}
