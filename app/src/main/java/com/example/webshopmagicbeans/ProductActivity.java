package com.example.webshopmagicbeans;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductActivity extends AppCompatActivity {

    private TextView nameTextView, priceTextView, descriptionTextView, sideEffectsTextView, rarityTextView;
    private Button buttonAddToCart, buttonGoToCart, buttonGoToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // Initialize views
        nameTextView = findViewById(R.id.nameTextView);
        priceTextView = findViewById(R.id.priceTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        sideEffectsTextView = findViewById(R.id.sideEffectsTextView);
        rarityTextView = findViewById(R.id.rarityTextView);
        buttonAddToCart = findViewById(R.id.buttonAddToCart);
        buttonGoToCart = findViewById(R.id.buttonGoToCart);
        buttonGoToMain = findViewById(R.id.buttonGoToMain);

        // Get data from Intent
        String name = getIntent().getStringExtra("beanName");
        int price = getIntent().getIntExtra("beanPrice", 0);
        String description = getIntent().getStringExtra("beanDescription");
        String sideEffects = getIntent().getStringExtra("beanSideEffects");
        int rarity = getIntent().getIntExtra("beanRarity", 0);

        // Set data to views
        nameTextView.setText("Name: " + name);
        priceTextView.setText("Price: " + price);
        descriptionTextView.setText("Description: " + description);
        sideEffectsTextView.setText("Side Effects: " + sideEffects);
        rarityTextView.setText("Rarity: " + rarity);

        // Set click listener for adding the item to the cart
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int defaultId = 0;  // or any value
                Colors defaultColor = Colors.BLACK;  // Example default color

                MagicBeans bean = new MagicBeans(defaultId, price, rarity, name, description, sideEffects, defaultColor);
                CartManager.getInstance().addToCart(bean);
                Toast.makeText(ProductActivity.this, name + " added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        // Navigate to the shopping cart
        buttonGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

        // Navigate back to the main page
        buttonGoToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
