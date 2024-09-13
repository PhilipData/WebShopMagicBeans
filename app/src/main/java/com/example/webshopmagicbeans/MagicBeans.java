package com.example.webshopmagicbeans;

public class MagicBeans {
    int id;
    int price;
    int rarity;
    String name;
    String description;
    String sideEffects;
    Colors color;

    public MagicBeans(int id, int price, int rarity, String name, String description, String sideEffects, Colors color) {
        this.id = id;
        this.price = price;
        this.rarity = rarity;
        this.name = name;
        this.description = description;
        this.sideEffects = sideEffects;
        this.color = color;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }
}

enum Colors {
    BLACK,RED,VIOLET,BLUE,PINK,YELLOW,POLKADOTTED,ZEBRASTRIPED,RAINBOW,GREEN,BONEWHITE
        }
