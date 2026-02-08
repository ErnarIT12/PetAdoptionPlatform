package com.petadoption.model;

public class Shelter {
    // Attributes
    private String name;
    private String location;
    private double rating;
    private int id;

    // Constructors
    public Shelter(String name, String location, double rating) {
        this.name = name;
        this.location = location;
        this.rating = rating;
    }

    // ---- Getters and Setters -----
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // ---- Methods ----
    public void printInfo() {
        System.out.println("Shelter: \"" + name + "\"" + ", Location: " + location +
                ", Rating: " + rating);
    }
}
