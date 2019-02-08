package com.github.zetahvdev.ventascalc;

public class ProductFields {
    private ProductFields() { }

    public static final String TABLE_NAME = "PRODUCT_TABLE";
    public static final String FIELD_ID = "id";
    public static final String FIELD_PRODUCT = "Producto";
    public static final String FIELD_PRIZE = "Precio";
    public static final String FIELD_CANTIDAD = "Cantidad";
    public static final String FIELD_TYPE  = "Tipo";

    /// Valid products names
    public static final String PRODUCT_PATI = "Pati";
    public static final String PRODUCT_COCADAS = "Cocada";
    public static final String PRODUCT_PLANTINTA = "Plantinta";

    // Valid types names
    public static final String TYPE_VENTA = "Venta";
    public static final String TYPE_DEUDA = "Deuda";
    public static final String TYPE_GASTO = "Gasto";
    public static final String TYPE_FALTANTE = "Faltante";

    public static int getPrize(String product) {
        switch (product) {
            case PRODUCT_PATI:
                return 1000;
            case PRODUCT_COCADAS:
                return 1200;
            case PRODUCT_PLANTINTA:
                return 1000;
            default:
                return 0;
        }
    }
}
