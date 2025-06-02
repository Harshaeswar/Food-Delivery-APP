package com.example.fooddeliveryapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private ListView cartList;
    private TextView totalPriceText;
    private CartAdapter cartAdapter;
    private ArrayList<FoodItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        cartList = findViewById(R.id.cartList);
        totalPriceText = findViewById(R.id.totalPrice);

        // Get cart items from Intent (passed from MainActivity)
        cartItems = (ArrayList<FoodItem>) getIntent().getSerializableExtra("cartItems");
        if (cartItems == null) cartItems = new ArrayList<>();

        cartAdapter = new CartAdapter(this, cartItems);
        cartList.setAdapter(cartAdapter);

        // Calculate and display total price
        double total = 0;
        for (FoodItem item : cartItems) {
            total += item.getPrice();
        }
        totalPriceText.setText(String.format("Total: ₹%.2f", total));

        // Handle Place Order button
        findViewById(R.id.placeOrderButton).setOnClickListener(v -> {
            // Add order confirmation logic here (e.g., show a toast or clear cart)
            finish(); // Close activity after placing order
        });
    }
}