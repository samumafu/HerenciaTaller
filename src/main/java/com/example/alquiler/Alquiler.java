package com.example.alquiler;

public class Alquiler {
    private Vehiculo vehiculo;
    private int dias;
    private double total;

    public Alquiler(Vehiculo vehiculo, int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Días debe ser mayor que 0.");
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.total = vehiculo.calcularPrecioAlquiler(dias);
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return String.format("Alquiler -> %s | días=%d | total=%.2f", vehiculo.toString(), dias, total);
    }
}
