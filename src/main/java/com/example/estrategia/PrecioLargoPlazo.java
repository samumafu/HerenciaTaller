package com.example.estrategia;

public class PrecioLargoPlazo implements EstrategiaPrecio {
    @Override
    public double total(int dias, double costoBase, double seguro) {
        double total = costoBase * dias + seguro;
        if (dias >= 10) {
            total *= 0.85; // 15% de descuento
        }
        return total;
    }
}
