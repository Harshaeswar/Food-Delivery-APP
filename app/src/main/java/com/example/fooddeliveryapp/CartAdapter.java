package com.example.fooddeliveryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class CartAdapter extends ArrayAdapter<FoodItem> {
    private HashMap<FoodItem, Integer> itemQuantities = new HashMap<>();
    private ArrayList<FoodItem> uniqueItems;

    public CartAdapter(Context context, ArrayList<FoodItem> cartItems) {
        super(context, 0, cartItems);
        uniqueItems = new ArrayList<>();
        for (FoodItem item : cartItems) {
            itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + 1);
        }
        uniqueItems.addAll(itemQuantities.keySet());
    }

    public double getTotalPrice() {
        double total = 0;
        for (FoodItem item : itemQuantities.keySet()) {
            total += item.getPrice() * itemQuantities.get(item);
        }
        return total;
    }

    @Override
    public int getCount() {
        return uniqueItems.size();
    }

    @Override
    public FoodItem getItem(int position) {
        return uniqueItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        FoodItem item = getItem(position);
        int quantity = itemQuantities.get(item);

        TextView text1 = listItemView.findViewById(android.R.id.text1);
        TextView text2 = listItemView.findViewById(android.R.id.text2);

        text1.setText(item.getName() + " (x" + quantity + ")");
        text2.setText(String.format("₹%.2f", item.getPrice() * quantity));

        return listItemView;
    }
}