package com.github.zetahvdev.ventascalc;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends Activity {

    ArrayList<Product> products;
    ProductDatabaseHelper prodDBHelper = new ProductDatabaseHelper(ResultActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        SQLiteDatabase productDB = prodDBHelper.getReadableDatabase();
        products = ProductDBManager.LoadAll(productDB);

        ResultsInit();
    }

    private void ResultsInit() {
        // ganancias total de Alisson con el dinero teóricamente
        TextView gananciasTotalAlissonDinero = findViewById(R.id.GananciaTotalAlissonMoney);

        // ganancias total de Alisson con el dinero teóricamente
        TextView gananciasTotalReinerioDinero = findViewById(R.id.GananciaTotalReinerioMoney);

        TextView cantidadPatiInfoProduct = findViewById(R.id.productPatiCantidadInfo);

        TextView cantidadPlantintaInfoProduct = findViewById(R.id.productPlantintaCantidadInfo);

        TextView cantidadCocadasInfoProduct = findViewById(R.id.productCocadaCantidadInfo);

        TextView patiProductInfo = findViewById(R.id.productPatiInfo);

        TextView plantintaProductInfo = findViewById(R.id.productPlantintaInfo);

        TextView cocadaProductInfo = findViewById(R.id.productCocadaInfo);

        TextView patiPrizeProductInfo = findViewById(R.id.productPatiPrizeInfo);

        TextView plantintaPrizeProductInfo = findViewById(R.id.productPlantintaPrizeInfo);

        TextView cocadaPrizeProductInfo = findViewById(R.id.productCocadaPrizeInfo);

        TextView cantidadPatiInfoDeuda = findViewById(R.id.deudaPatiCantidadInfo);

        TextView cantidadPlantintaInfoDeuda = findViewById(R.id.deudaPlantintaCantidadInfo);

        TextView cantidadCocadasInfoDeuda = findViewById(R.id.deudaCocadaCantidadInfo);

        TextView patiInfoDeuda = findViewById(R.id.deudaPatiInfo);

        TextView plantintaInfoDeuda = findViewById(R.id.deudaPlantintaInfo);

        TextView cocadaInfoDeuda = findViewById(R.id.deudaCocadaInfo);

        TextView patiPrizeDeudaInfo = findViewById(R.id.deudaPatiPrizeInfo);

        TextView plantintaPrizeDeudaInfo = findViewById(R.id.deudaPlantintaPrizeInfo);

        TextView cocadaPrizeDeudaInfo = findViewById(R.id.deudaCocadaPrizeInfo);

        // la cantidad total de dinero obtenido en el dia de venta
        TextView dineroTotal = findViewById(R.id.DineroTotalDinero);

        // la cantidad obtenida de alisson sin las deudas canceladas
        TextView dineroAlissonObtenida = findViewById(R.id.GananciasAlissonDinero);

        // la cantidad obtenida de Alisson con las deudas canceladas
        TextView dineroAlissonObtenible = findViewById(R.id.GananciasAlissonObtenibleDinero);

        ProductCalculator productCalculator = new ProductCalculator(products);

        int totalProducts = productCalculator.getTotalOfType(ProductFields.TYPE_VENTA);
        int totalDeuda = productCalculator.getTotalOfType(ProductFields.TYPE_DEUDA);
        int totalFaltante = productCalculator.getTotalOfType(ProductFields.TYPE_FALTANTE);
        int total  = totalProducts + totalDeuda + totalFaltante;

        gananciasTotalAlissonDinero.setText(String.valueOf(total / 2));
        gananciasTotalReinerioDinero.setText(String.valueOf(total / 2));

        cantidadPatiInfoProduct.setText(String.valueOf(productCalculator.getTotalAmountOfProduct(ProductFields.PRODUCT_PATI, ProductFields.TYPE_VENTA)));
        patiProductInfo.setText(ProductFields.PRODUCT_PATI);
        patiPrizeProductInfo.setText(String.valueOf(productCalculator.getTotalProductSold(ProductFields.PRODUCT_PATI)));

        cantidadPlantintaInfoProduct.setText(String.valueOf(productCalculator.getTotalAmountOfProduct(ProductFields.PRODUCT_PLANTINTA, ProductFields.TYPE_VENTA)));
        plantintaProductInfo.setText(ProductFields.PRODUCT_PLANTINTA);
        plantintaPrizeProductInfo.setText(String.valueOf(productCalculator.getTotalProductSold(ProductFields.PRODUCT_PLANTINTA)));

        cantidadCocadasInfoProduct.setText(String.valueOf(productCalculator.getTotalAmountOfProduct(ProductFields.PRODUCT_COCADAS, ProductFields.TYPE_VENTA)));
        cocadaProductInfo.setText(ProductFields.PRODUCT_COCADAS);
        cocadaPrizeProductInfo.setText(String.valueOf(productCalculator.getTotalProductSold(ProductFields.PRODUCT_COCADAS)));

        cantidadPatiInfoDeuda.setText(String.valueOf(productCalculator.getTotalAmountOfProduct(ProductFields.PRODUCT_PATI, ProductFields.TYPE_DEUDA)));
        patiInfoDeuda.setText(ProductFields.PRODUCT_PATI);
        patiPrizeDeudaInfo.setText(String.valueOf(productCalculator.getTotalOfProduct(ProductFields.PRODUCT_PATI, ProductFields.TYPE_DEUDA)));

        cantidadPlantintaInfoDeuda.setText(String.valueOf(productCalculator.getTotalAmountOfProduct(ProductFields.PRODUCT_PLANTINTA, ProductFields.TYPE_DEUDA)));
        plantintaInfoDeuda.setText(ProductFields.PRODUCT_PLANTINTA);
        plantintaPrizeDeudaInfo.setText(String.valueOf(productCalculator.getTotalOfProduct(ProductFields.PRODUCT_PLANTINTA, ProductFields.TYPE_DEUDA)));

        cantidadCocadasInfoDeuda.setText(String.valueOf(productCalculator.getTotalAmountOfProduct(ProductFields.PRODUCT_COCADAS, ProductFields.TYPE_DEUDA)));
        cocadaInfoDeuda.setText(ProductFields.PRODUCT_COCADAS);
        cocadaPrizeDeudaInfo.setText(String.valueOf(productCalculator.getTotalOfProduct(ProductFields.PRODUCT_COCADAS, ProductFields.TYPE_DEUDA)));

        dineroTotal.setText(String.valueOf(productCalculator.getTotalSold() - productCalculator.getTotalOfType(ProductFields.TYPE_GASTO)));
        dineroAlissonObtenida.setText(String.valueOf((totalProducts / 2) - productCalculator.getTotalOfType(ProductFields.TYPE_GASTO)));
        dineroAlissonObtenible.setText(String.valueOf((totalProducts + totalDeuda) / 2));
    }
}
