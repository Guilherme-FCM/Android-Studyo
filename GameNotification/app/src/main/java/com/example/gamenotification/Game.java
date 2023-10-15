package com.example.gamenotification;

public class Game {
    private String name;
    private double price;
    private String gender;
    private String plataform;

    public Game() {}

    public Game(String name, double price, String gender, String plataform) {
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.plataform = plataform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlataform() {
        return plataform;
    }

    public void setPlataform(String plataform) {
        this.plataform = plataform;
    }
}
