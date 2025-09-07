package com.example.interfaces;

public interface Rentable {
    double calcularPrecioAlquiler(int dias);

    default String condiciones() {
        return "Política genérica de alquiler: días > 0, se requiere pago anticipado.";
    }
}
