package com.github.zetahvdev.ventascalc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ProductAdapter adapter;
    private final ProductDatabaseHelper productDBHelper = new ProductDatabaseHelper(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productListInit();

        Spinner sp = findViewById(R.id.productSpinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String product = parent.getItemAtPosition(position).toString();
                EditText precioEntry = findViewById(R.id.precioEntry);
                precioEntry.setText(String.valueOf(ProductFields.getPrize(product)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ListView productList = findViewById(R.id.listProducts);
        productList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Estas Seguro?");
                builder.setMessage("Eliminar este producto de la lista");
                builder.setNegativeButton("Cancelar", null);
                builder.setPositiveButton("Confirmar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.remove(adapter.getItem(position));
                                productListUpdate();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }

    private void productListInit() {
        ListView productList = findViewById(R.id.listProducts);

        SQLiteDatabase productDB = productDBHelper.getReadableDatabase();
        ArrayList<Product> products = ProductDBManager.LoadAll(productDB);

        adapter = new ProductAdapter(this, products);

        productList.setAdapter(adapter);
    }

    private void productListUpdate() {
        ListView productList = findViewById(R.id.listProducts);
        productList.setAdapter(adapter);
    }

    public void AddProduct(View view) {
        Spinner productSpinner = findViewById(R.id.productSpinner);
        Spinner tipoSpinner = findViewById(R.id.tipoSpinner);
        EditText cantidadEntry = findViewById(R.id.cantidadEntry);
        EditText precioEntry = findViewById(R.id.precioEntry);

        String productSelected = productSpinner.getSelectedItem().toString();
        String tipoSelected = tipoSpinner.getSelectedItem().toString();
        String cantidadSelected = cantidadEntry.getText().toString();
        String precioSelected = precioEntry.getText().toString();

        if (cantidadSelected.equals("")) {
            cantidadEntry.setError("Éste campo no puede estar vacío");
            return;
        }
        else if (precioSelected.equals("")) {
            precioEntry.setError("Éste campo no puede estar vacío");
            return;
        }
        else {
            cantidadEntry.setError(null);
            precioEntry.setError(null);
        }

        int cantidadSel = Integer.parseInt(cantidadSelected);
        int precioSel = Integer.parseInt(precioSelected);
        adapter.add(new Product(productSelected, tipoSelected, precioSel, cantidadSel));
        productListUpdate();
    }

    public void OpenResultActivity(View view) {
        Intent activityIntent = new Intent(this, ResultActivity.class);
        startActivity(activityIntent);
    }

    @Override
    protected void onPause() {
        SQLiteDatabase productDB = productDBHelper.getWritableDatabase();
        productDB.delete(ProductFields.TABLE_NAME, null, null);
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < adapter.getCount(); i++) {
            Product prod = adapter.getItem(i);
            products.add(prod);
        }

        ProductDBManager.SaveAll(products, productDB);
        super.onStop();
    }
}
