package com.example.vehiculo;

public class Auto extends Vehiculo implements Asegurable {

    public Auto(String placa, String marca, String modelo, double km) {
        super(placa, marca, modelo, km);
    }

    @Override
    public double costoBaseDia() {
        return 50.0; // tarifa base por día para Auto
    }

    @Override
    public double calcularSeguro(int dias) {
        // Por ejemplo: seguro fijo por día
        return dias * 10.0;
    }

    @Override
    public String toString() {
        return super.toString() + " [Auto - 5 asientos]";
    }
}
