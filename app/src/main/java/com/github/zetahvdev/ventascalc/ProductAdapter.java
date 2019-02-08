package com.github.zetahvdev.ventascalc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter  extends ArrayAdapter<Product> {
     ProductAdapter(Context context, ArrayList<Product> product) {
         super(context, R.layout.product_item_layout, product);
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
         Product prod = getItem(position);
         if (convertView == null) {
             convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_item_layout, parent, false);
         }

         TextView product = convertView.findViewById(R.id.productItemText);
         TextView amount = convertView.findViewById(R.id.cantidadItemText);
         TextView prizeTotal = convertView.findViewById(R.id.prizeTotalText);
         TextView typeResult = convertView.findViewById(R.id.tiporesultText);

         product.setText(prod.getPlural());
         amount.setText(String.valueOf(prod.getAmount()));
         prizeTotal.setText(String.valueOf(prod.getTotal()));
         typeResult.setText(prod.getType());

         return convertView;
     }
}
