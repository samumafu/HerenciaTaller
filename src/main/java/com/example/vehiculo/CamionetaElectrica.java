package com.example.vehiculo;

public class CamionetaElectrica extends Camioneta implements Electrico {
    private int bateria; // 0 - 100

    public CamionetaElectrica(String placa, String marca, String modelo, double km, double capacidadKg, int bateriaInicial) {
        super(placa, marca, modelo, km, capacidadKg);
        this.bateria = Math.max(0, Math.min(100, bateriaInicial));
    }

    @Override
    public void recargar(int minutos) {
        if (minutos <= 0) return;
        int incremento = minutos / 2;
        bateria = Math.min(100, bateria + incremento);
        System.out.println("Recarga: +" + incremento + "% -> nivel " + bateria + "%");
    }

    @Override
    public int nivelBateria() {
        return bateria;
    }

    @Override
    public String toString() {
        return super.toString() + " [Eléctrica, bateria=" + bateria + "%]";
    }
}
