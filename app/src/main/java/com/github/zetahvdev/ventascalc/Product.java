package com.github.zetahvdev.ventascalc;

public class Product {
    private final String product;
    private final String type;
    private final int prize;
    private final int total;
    private final int amount;

    Product(String product, String type, int prize, int amount) {
        this.product = product;
        this.type = type;
        this.prize = prize;
        this.amount = amount;
        this.total = amount * prize;
    }

    public String getProduct() {
        return this.product;
    }

    public String getType() {
        return this.type;
    }

    public int getPrize() {
        return this.prize;
    }

    public int getTotal() {
        return this.total;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getPlural() {
        if (this.amount == 1) return this.product;
        else return this.product + "s";
    }
}
