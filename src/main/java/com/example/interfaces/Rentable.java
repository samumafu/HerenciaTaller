package com.example.interfaces;

public interface Rentable {
    double calcularPrecioAlquiler(int dias);

    default String condiciones() {
        return "Condiciones generales: identificación, depósito y devolución en buen estado.";
    }
}
