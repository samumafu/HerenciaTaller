package com.example.vehiculo;

public class Camioneta extends Vehiculo implements Asegurable {
    private double capacidadKg;

    public Camioneta(String placa, String marca, String modelo, double km, double capacidadKg) {
        super(placa, marca, modelo, km);
        this.capacidadKg = capacidadKg;
    }

    @Override
    public double costoBaseDia() {
        return 70.0; // tarifa base para Camioneta
    }

    @Override
    public double calcularSeguro(int dias) {
        return dias * 15.0;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Camioneta - cap: %.0f kg]", capacidadKg);
    }

    public double getCapacidadKg() {
        return capacidadKg;
    }
}
