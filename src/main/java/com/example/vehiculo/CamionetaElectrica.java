package com.example.vehiculo;

import com.example.interfaces.Electrico;

public class CamionetaElectrica extends Camioneta implements Electrico {
    private int bateria; // %

    public CamionetaElectrica(String placa, String marca, String modelo, double km, double capacidadCarga, int bateria) {
        super(placa, marca, modelo, km, capacidadCarga);
        this.bateria = bateria;
    }

    @Override
    public void recargar(int minutos) {
        bateria = Math.min(100, bateria + minutos / 3);
    }

    @Override
    public int nivelBateria() {
        return bateria;
    }

    @Override
    public String toString() {
        return super.toString() + " [Eléctrica " + bateria + "%]";
    }
}
