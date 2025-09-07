package com.example.vehiculo;

import com.example.interfaces.Asegurable;

public class Auto extends Vehiculo implements Asegurable {

    public Auto(String placa, String marca, String modelo, double km) {
        super(placa, marca, modelo, km);
    }

    @Override
    public double costoBaseDia() {
        return 50.0;
    }

    @Override
    public double calcularSeguro(int dias) {
        return dias * 10.0;
    }

    @Override
    public String toString() {
        return super.toString() + " [Auto - 5 asientos]";
    }
}
