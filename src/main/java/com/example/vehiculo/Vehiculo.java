package com.example.vehiculo;

public abstract class Vehiculo implements Rentable {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected double km;
    protected EstrategiaPrecio estrategia; // puede ser null -> uso estándar por defecto

    public Vehiculo(String placa, String marca, String modelo, double km) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
        this.estrategia = null;
    }

    public abstract double costoBaseDia();

    // Implementación genérica de calcularPrecioAlquiler usando la estrategia
    @Override
    public double calcularPrecioAlquiler(int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Días debe ser mayor que 0.");
        double seguro = 0;
        if (this instanceof Asegurable) {
            seguro = ((Asegurable) this).calcularSeguro(dias);
        }
        double costoBase = costoBaseDia();
        EstrategiaPrecio e = (estrategia == null) ? new PrecioEstandar() : estrategia;
        return e.total(dias, costoBase, seguro);
    }

    public void setEstrategia(EstrategiaPrecio estrategia) {
        this.estrategia = estrategia;
    }

    public EstrategiaPrecio getEstrategia() {
        return estrategia;
    }

    public void sumarKm(double km) {
        if (km < 0) throw new IllegalArgumentException("No se permiten km negativos.");
        this.km += km;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s (Placa: %s) KM: %.1f",
                this.getClass().getSimpleName(), marca, modelo, placa, km);
    }

    // getters básicos (si los quieres usar desde Main)
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public double getKm() { return km; }
}
