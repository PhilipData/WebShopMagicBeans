package com.example.webshopmagicbeans;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webshopmagicbeans.CartManager;
import com.example.webshopmagicbeans.MagicBeans;
import com.example.webshopmagicbeans.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private ListView cartListView;
    private Button buttonCheckout, buttonClearCart;
    private List<MagicBeans> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        cartListView = findViewById(R.id.cartListView);
        buttonCheckout = findViewById(R.id.buttonCheckout);
        buttonClearCart = findViewById(R.id.buttonClearCart);

        // Load cart items
        loadCartItems();

        // Checkout button
        buttonCheckout.setOnClickListener(v -> {
            int totalPrice = CartManager.getInstance().getTotalPrice();
            Toast.makeText(ShoppingCartActivity.this, "Total Price: " + totalPrice + ". Checkout successful!", Toast.LENGTH_LONG).show();
            CartManager.getInstance().clearCart();  // Clear cart after checkout
            loadCartItems();  // Refresh cart items
        });

        // Clear cart button
        buttonClearCart.setOnClickListener(v -> {
            CartManager.getInstance().clearCart();
            Toast.makeText(ShoppingCartActivity.this, "Cart cleared!", Toast.LENGTH_SHORT).show();
            loadCartItems();  // Refresh cart items
        });
    }

    private void loadCartItems() {
        cartItems = CartManager.getInstance().getCartItems(); // Get cart items
        List<String> cartItemDetails = new ArrayList<>();
        for (MagicBeans bean : cartItems) {
            cartItemDetails.add(bean.getName() + " - Price: " + bean.getPrice() + " - Rarity: " + bean.getRarity());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartItemDetails);
        cartListView.setAdapter(adapter);

        // Set item click listener
        cartListView.setOnItemClickListener((parent, view, position, id) -> {
            if (cartItems != null && !cartItems.isEmpty()) {
                MagicBeans selectedBean = cartItems.get(position);

                // Create intent to start the new activity
                Intent intent = new Intent(ShoppingCartActivity.this, ProductActivity.class);

                // Pass data about the clicked item to the new activity
                intent.putExtra("beanName", selectedBean.getName());
                intent.putExtra("beanPrice", selectedBean.getPrice());
                intent.putExtra("beanDescription", selectedBean.getDescription());
                intent.putExtra("beanSideEffects", selectedBean.getSideEffects());
                intent.putExtra("beanRarity", selectedBean.getRarity());

                // Start the new activity
                startActivity(intent);
            } else {
                Toast.makeText(ShoppingCartActivity.this, "Cart is empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
