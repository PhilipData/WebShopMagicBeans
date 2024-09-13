package com.example.webshopmagicbeans;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.webshopmagicbeans.MagicBeans;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String url = "http://10.131.199.168:8888/beanshop";
    private RequestQueue requestQueue;
    private ListView listViewBeans;
    private List<MagicBeans> magicbeanslist = new ArrayList<>();
    private Button buttonGoToCartFromMain;  // Declare the button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewBeans = findViewById(R.id.listViewBeans);
        buttonGoToCartFromMain = findViewById(R.id.buttonGoToCartFromMain);  // Initialize the button
        requestQueue = Volley.newRequestQueue(this);

        // Fetch beans from API
        getBeans();

        // Set click listener for the button
        buttonGoToCartFromMain.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
            startActivity(intent);
        });
    }

    void getBeans() {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("VolleyBean", "API Response: " + response);

                        // Deserialize the response
                        Type listType = new TypeToken<List<MagicBeans>>() {}.getType();
                        magicbeanslist = new Gson().fromJson(response, listType);

                        if (magicbeanslist != null && !magicbeanslist.isEmpty()) {
                            populateListView(magicbeanslist);
                        } else {
                            Log.d("VolleyBean", "No beans found in the response.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError vError) {
                        Log.e("VolleyBean", "Error fetching Data: " + vError.getMessage(), vError);
                        Toast.makeText(MainActivity.this,"Failed to get Data", Toast.LENGTH_LONG).show();
                    }
                });

        requestQueue.add(request);
    }

    private void populateListView(List<MagicBeans> beansList) {
        List<String> beanNames = new ArrayList<>();
        for (MagicBeans bean : beansList) {
            beanNames.add(bean.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, beanNames);
        listViewBeans.setAdapter(adapter);

        listViewBeans.setOnItemClickListener((parent, view, position, id) -> {
            if (magicbeanslist != null && !magicbeanslist.isEmpty()) {
                MagicBeans selectedBean = magicbeanslist.get(position);

                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                intent.putExtra("beanName", selectedBean.getName());
                intent.putExtra("beanPrice", selectedBean.getPrice());
                intent.putExtra("beanDescription", selectedBean.getDescription());
                intent.putExtra("beanSideEffects", selectedBean.getSideEffects());
                intent.putExtra("beanRarity", selectedBean.getRarity());
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Data not loaded yet!", Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("VolleyBean", "ListView populated with beans.");
    }
}
