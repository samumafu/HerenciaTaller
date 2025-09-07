package com.example.vehiculo;

import com.example.interfaces.Rentable;
import com.example.interfaces.Asegurable;
import com.example.estrategia.EstrategiaPrecio;
import com.example.estrategia.PrecioEstandar;

public abstract class Vehiculo implements Rentable {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected double km;
    protected EstrategiaPrecio estrategia;

    public Vehiculo(String placa, String marca, String modelo, double km) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
    }

    public abstract double costoBaseDia();

    @Override
    public double calcularPrecioAlquiler(int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Días inválidos");

        double seguro = 0.0;
        if (this instanceof Asegurable) {
            seguro = ((Asegurable) this).calcularSeguro(dias);
        }

        EstrategiaPrecio e = (estrategia == null) ? new PrecioEstandar() : estrategia;
        return e.total(dias, costoBaseDia(), seguro);
    }

    public void setEstrategia(EstrategiaPrecio estrategia) {
        this.estrategia = estrategia;
    }

    public EstrategiaPrecio getEstrategia() {
        return estrategia;
    }

    public void sumarKm(double km) {
        if (km < 0) throw new IllegalArgumentException("No se permiten km negativos");
        this.km += km;
    }

    @Override
    public String toString() {
        return placa + " - " + marca + " " + modelo + " (" + km + " km)";
    }
}
