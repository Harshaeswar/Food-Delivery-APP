package com.example.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DatabaseHelper dbHelper;
    private ListView foodList;
    private FoodAdapter adapter;
    private TextView cartCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        foodList = findViewById(R.id.foodList);
        cartCountText = findViewById(R.id.cartCount);

        ArrayList<FoodItem> foodItems = dbHelper.getAllFoodItems();
        adapter = new FoodAdapter(this, foodItems, this);
        foodList.setAdapter(adapter);

        // Set up Go to Cart Button
        Button orderButton = findViewById(R.id.orderButton);
        if (orderButton != null) {
            orderButton.setText("Go to Cart");
            orderButton.setOnClickListener(v -> {
                Log.d(TAG, "Go to Cart button clicked");
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putExtra("cartItems", adapter.getCartItems());
                startActivity(intent);
            });
        } else {
            Log.e(TAG, "orderButton is null");
        }
    }

    public void updateCartCount() {
        cartCountText.setText("Cart: " + adapter.getCartSize() + " items");
    }
}