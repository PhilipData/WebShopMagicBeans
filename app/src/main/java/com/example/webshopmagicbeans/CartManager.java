package com.example.webshopmagicbeans;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<MagicBeans> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(MagicBeans bean) {
        cartItems.add(bean);
    }

    public List<MagicBeans> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    // Method to calculate total price of items in the cart
    public int getTotalPrice() {
        int total = 0;
        for (MagicBeans bean : cartItems) {
            total += bean.getPrice();
        }
        return total;
    }
}
