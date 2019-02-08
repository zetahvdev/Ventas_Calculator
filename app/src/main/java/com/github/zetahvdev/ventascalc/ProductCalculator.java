package com.github.zetahvdev.ventascalc;

import java.util.ArrayList;

public class ProductCalculator {
    private final ArrayList<Product> products;
    
    private final int totalAmount;
    
    ProductCalculator(ArrayList<Product> products) {
        this.products = products;
        totalAmount = calcTotalAmount();
    }
    
    private int calcTotalAmount() {
        int total = 0;
        for (Product prod : products) {
            total += prod.getAmount();
        }
        return total;
    }

    public int getTotalSold() {
        int patiTotal = getTotalProductSold(ProductFields.PRODUCT_PATI);
        int plantintaTotal = getTotalProductSold(ProductFields.PRODUCT_PLANTINTA);
        int cocadaTotal = getTotalProductSold(ProductFields.PRODUCT_COCADAS);

        return patiTotal + plantintaTotal + cocadaTotal;
    }

    public int getTotalProductSold(String product) {
        return getTotalOfProduct(product, ProductFields.TYPE_VENTA);
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getTotalAmountofType(String type) {
        int total = 0;
        for (Product prod : products) {
            if (prod.getType().equals(type)) {
                total += prod.getAmount();
            }
        }
        return total;
    }

    public int getTotalAmountOfProduct(String product) {
        int total = 0;
        for (Product prod : products) {
            if (prod.getProduct().equals(product)) {
                total += prod.getAmount();
            }
        }
        return total;
    }

    public int getTotalAmountOfProduct(String product, String type) {
        int total = 0;
        for (Product prod : products) {
            if (prod.getProduct().equals(product) && prod.getType().equals(type)) {
                total += prod.getAmount();
            }
        }
        return total;
    }

    public int getTotalOfType(String type) {
        int total = 0;
        for (Product prod : products) {
            if (prod.getType().equals(type)) {
                total += prod.getTotal();
            }
        }
        return total;
    }

    public int getTotalOfProduct(String product, String type) {
        int total = 0;
        for (Product prod : products) {
            if (prod.getType().equals(type) && prod.getProduct().equals(product)) {
                total += prod.getTotal();
            }
        }
        return total;
    }
}
