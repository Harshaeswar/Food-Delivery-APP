package com.example.fooddeliveryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<FoodItem> {
    private ArrayList<FoodItem> cartItems = new ArrayList<>();
    private MainActivity mainActivity;

    public FoodAdapter(Context context, ArrayList<FoodItem> foodItems, MainActivity activity) {
        super(context, 0, foodItems);
        this.mainActivity = activity;
    }

    public int getCartSize() {
        return cartItems.size();
    }

    public ArrayList<FoodItem> getCartItems() {
        return cartItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.food_item, parent, false);
        }

        FoodItem currentFood = getItem(position);

        TextView nameText = listItemView.findViewById(R.id.foodName);
        TextView priceText = listItemView.findViewById(R.id.foodPrice);
        ImageView imageView = listItemView.findViewById(R.id.foodImage);
        Button addButton = listItemView.findViewById(R.id.addToCartButton);

        nameText.setText(currentFood.getName());
        priceText.setText(String.format("₹%.2f", currentFood.getPrice()));
        int resourceId = getContext().getResources().getIdentifier(currentFood.getImage(), "drawable", getContext().getPackageName());
        Glide.with(getContext())
                .load(resourceId > 0 ? resourceId : null)
                .placeholder(R.drawable.frames)
                .into(imageView);

        addButton.setOnClickListener(v -> {
            cartItems.add(currentFood);
            mainActivity.updateCartCount();
        });

        return listItemView;
    }
}