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
        if (km < 0) throw new IllegalArgumentException("Km no puede ser negativo.");
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
        this.estrategia = new PrecioEstandar(); // por defecto
    }

    // 🔹 Métodos abstractos
    public abstract double costoBaseDia();

    // 🔹 Métodos comunes
    public void sumarKm(double km) {
        if (km < 0) throw new IllegalArgumentException("No se pueden sumar km negativos.");
        this.km += km;
    }

    public void setEstrategia(EstrategiaPrecio estrategia) {
        this.estrategia = estrategia;
    }

    public EstrategiaPrecio getEstrategia() {
        return this.estrategia;
    }

    @Override
    public double calcularPrecioAlquiler(int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Días debe ser mayor a 0.");
        double seguro = 0;
        if (this instanceof Asegurable) {
            seguro = ((Asegurable) this).calcularSeguro(dias);
        }
        return estrategia.total(dias, costoBaseDia(), seguro);
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s (%.1f km)", placa, marca, modelo, km);
    }

    // 🔹 Getters y setters que faltaban
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public double getKm() { return km; }

    public void setPlaca(String placa) { this.placa = placa; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setKm(double km) {
        if (km < 0) throw new IllegalArgumentException("Km no puede ser negativo.");
        this.km = km;
    }
}
