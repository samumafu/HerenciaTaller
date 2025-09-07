package com.example.vehiculo;

public class AutoElectrico extends Auto implements Electrico {
    private int bateria; // 0 - 100

    public AutoElectrico(String placa, String marca, String modelo, double km, int bateriaInicial) {
        super(placa, marca, modelo, km);
        this.bateria = Math.max(0, Math.min(100, bateriaInicial));
    }

    @Override
    public void recargar(int minutos) {
        if (minutos <= 0) return;
        int incremento = minutos / 2; // ejemplo: cada 2 minutos = +1% (ajustable)
        bateria = Math.min(100, bateria + incremento);
        System.out.println("Recarga: +" + incremento + "% -> nivel " + bateria + "%");
    }

    @Override
    public int nivelBateria() {
        return bateria;
    }

    @Override
    public String toString() {
        return super.toString() + " [Eléctrico, bateria=" + bateria + "%]";
    }
}
