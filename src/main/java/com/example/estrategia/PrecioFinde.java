package com.example.estrategia;

public class PrecioFinde implements EstrategiaPrecio {
    @Override
    public double total(int dias, double costoBase, double seguro) {
        double subtotal = dias * costoBase + seguro;
        if (dias >= 2) subtotal *= 0.9; // 10% de descuento
        return subtotal;
    }
}
