package br.com.fourcamp.ff.models;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    private final String name;
    private final String address;
    private final Map<String, Double> sandwiches;
    private final Map<String, Double> sides;
    private final Map<String, Double> drinks;
    private final Map<String, Double> desserts;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.sandwiches = new HashMap<>();
        this.sides = new HashMap<>();
        this.drinks = new HashMap<>();
        this.desserts = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, Double> getSandwiches() {
        return sandwiches;
    }

    public Map<String, Double> getSides() {
        return sides;
    }

    public Map<String, Double> getDrinks() {
        return drinks;
    }

    public Map<String, Double> getDesserts() {
        return desserts;
    }

    public void addSandwich(String name, double price) {
        sandwiches.put(name, price);
    }

    public void addSide(String name, double price) {
        sides.put(name, price);
    }

    public void addDrink(String name, double price) {
        drinks.put(name, price);
    }

    public void addDessert(String name, double price) {
        desserts.put(name, price);
    }
}
