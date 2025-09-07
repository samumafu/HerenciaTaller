package com.example.vehiculo;

public class Moto extends Vehiculo {

    public Moto(String placa, String marca, String modelo, double km) {
        super(placa, marca, modelo, km);
    }

    @Override
    public double costoBaseDia() {
        return 25.0; // tarifa base para Moto
    }

    @Override
    public String toString() {
        return super.toString() + " [Moto - 2 asientos]";
    }
}
