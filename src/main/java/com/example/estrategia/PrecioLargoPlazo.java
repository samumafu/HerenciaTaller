package com.example.estrategia;

public class PrecioLargoPlazo implements EstrategiaPrecio {
    @Override
    public double total(int dias, double costoBase, double seguro) {
        double subtotal = dias * costoBase + seguro;
        if (dias >= 10) subtotal *= 0.85; // 15% de descuento
        return subtotal;
    }
}
