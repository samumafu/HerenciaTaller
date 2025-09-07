package com.example.vehiculo;

import com.example.interfaces.Electrico;

public class AutoElectrico extends Auto implements Electrico {
    private int bateria; // %

    public AutoElectrico(String placa, String marca, String modelo, double km, int bateria) {
        super(placa, marca, modelo, km);
        this.bateria = bateria;
    }

    @Override
    public void recargar(int minutos) {
        bateria = Math.min(100, bateria + minutos / 2);
    }

    @Override
    public int nivelBateria() {
        return bateria;
    }

    @Override
    public String toString() {
        return super.toString() + " [Eléctrico " + bateria + "%]";
    }
}
