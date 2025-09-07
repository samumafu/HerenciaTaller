package com.example.estrategia;

public class PrecioFinde implements EstrategiaPrecio {
    @Override
    public double total(int dias, double costoBase, double seguro) {
        double total = costoBase * dias + seguro;
        // regla simplificada: si dias >= 2 aplicamos descuento 10%
        if (dias >= 2) {
            total *= 0.90;
        }
        return total;
    }
}
