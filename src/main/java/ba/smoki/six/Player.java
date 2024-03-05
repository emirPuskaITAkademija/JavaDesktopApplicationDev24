package ba.smoki.six;

import java.awt.*;
//Klasa čija struktura odgovara strukturi tabele players u bazi.
public class Player {
    private Long id;
    private String name;
    private String surname;
    private Color color;
    private String sport;
    private Integer years;
    private Boolean vegetarian;

    public Player() {
    }

    public Player(Long id, String name, String surname, Color color, String sport, Integer years, Boolean vegetarian) {
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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
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
}
