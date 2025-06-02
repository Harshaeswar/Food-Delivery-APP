package com.example.fooddeliveryapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ListView cartList;
    private TextView totalPriceText;
    private CartAdapter cartAdapter;
    private ArrayList<FoodItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartList = findViewById(R.id.cartList);
        totalPriceText = findViewById(R.id.totalPrice);

        // Get cart items from Intent (passed from MainActivity)
        cartItems = (ArrayList<FoodItem>) getIntent().getSerializableExtra("cartItems");
        if (cartItems == null) cartItems = new ArrayList<>();

        cartAdapter = new CartAdapter(this, cartItems);
        cartList.setAdapter(cartAdapter);

        // Display total price
        double total = cartAdapter.getTotalPrice();
        totalPriceText.setText(String.format("Total: ₹%.2f", total));

        // Handle Place Order button
        findViewById(R.id.placeOrderButton).setOnClickListener(v -> {
            Toast.makeText(this, "Order Placed Successfully!", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity after showing the message
        });
    }
}