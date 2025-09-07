package com.example.estrategia;

public class PrecioEstandar implements EstrategiaPrecio {
    @Override
    public double total(int dias, double costoBase, double seguro) {
        return costoBase * dias + seguro;
    }
}
