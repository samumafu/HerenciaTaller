package com.example.vehiculo;

import com.example.interfaces.Asegurable;

public class Camioneta extends Vehiculo implements Asegurable {
    private double capacidadCarga;

    public Camioneta(String placa, String marca, String modelo, double km, double capacidadCarga) {
        super(placa, marca, modelo, km);
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public double costoBaseDia() {
        return 80.0;
    }

    @Override
    public double calcularSeguro(int dias) {
        return dias * 15.0;
    }

    @Override
    public String toString() {
        return super.toString() + " [Camioneta - carga: " + capacidadCarga + "kg]";
    }
}
