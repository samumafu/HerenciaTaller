package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.alquiler.Alquiler;
import com.example.estrategia.PrecioEstandar;
import com.example.estrategia.PrecioFinde;
import com.example.estrategia.PrecioLargoPlazo;
import com.example.vehiculo.*;

public class Main {

    private static List<Vehiculo> flota = new ArrayList<>();

    public static void main(String[] args) {
        cargarVehiculosEjemplo();
        menu();
    }

    private static void cargarVehiculosEjemplo() {
        flota.add(new Auto("ABC123", "Toyota", "Corolla", 50000));
        flota.add(new Moto("XYZ789", "Yamaha", "MT-07", 12000));
        flota.add(new Camioneta("JKL456", "Ford", "Ranger", 30000, 1000));
        flota.add(new AutoElectrico("ELEC01", "Tesla", "Model 3", 15000, 80));
        flota.add(new CamionetaElectrica("ELEC02", "Rivian", "R1T", 8000, 1500, 60));
    }

    private static void menu() {
        Scanner sc = new Scanner(System.in);
        int opt = -1;
        while (opt != 0) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1) Listar vehículos");
            System.out.println("2) Asignar estrategia de precio a un vehículo");
            System.out.println("3) Simular alquiler (vehículo + días)");
            System.out.println("4) Recargar eléctrico / mostrar nivel");
            System.out.println("5) Sumar km a vehículo");
            System.out.println("6) Ejecutar pruebas rápidas");
            System.out.println("0) Salir");
            System.out.print("Elige opción: ");
            try {
                opt = Integer.parseInt(sc.nextLine());
            } catch (Exception ex) {
                opt = -1;
            }
            switch (opt) {
                case 1: listarVehiculos(); break;
                case 2: asignarEstrategia(sc); break;
                case 3: simularAlquiler(sc); break;
                case 4: recargarElectrico(sc); break;
                case 5: sumarKmMenu(sc); break;
                case 6: pruebasRapidas(); break;
                case 0: System.out.println("Adiós!"); break;
                default: System.out.println("Opción inválida."); break;
            }
        }
        sc.close();
    }

    private static void listarVehiculos() {
        System.out.println("\n--- FLOTA ---");
        for (int i = 0; i < flota.size(); i++) {
            Vehiculo v = flota.get(i);
            System.out.printf("[%d] %s\n", i, v.toString());
            System.out.print("     Interfaces: Rentable");
            if (v instanceof com.example.interfaces.Asegurable) System.out.print(", Asegurable");
            if (v instanceof com.example.interfaces.Electrico) System.out.print(", Electrico");
            System.out.print("\n");
            System.out.println("     Estrategia: " + ((v.getEstrategia() == null) ? "PrecioEstandar (por defecto)" : v.getEstrategia().getClass().getSimpleName()));
        }
    }

    private static void asignarEstrategia(Scanner sc) {
        listarVehiculos();
        try {
            System.out.print("Índice vehículo: ");
            int idx = Integer.parseInt(sc.nextLine());
            Vehiculo v = flota.get(idx);
            System.out.println("Estrategias:\n1) PrecioEstandar\n2) PrecioFinde\n3) PrecioLargoPlazo");
            System.out.print("Elige estrategia: ");
            int s = Integer.parseInt(sc.nextLine());
            switch (s) {
                case 1: v.setEstrategia(new PrecioEstandar()); break;
                case 2: v.setEstrategia(new PrecioFinde()); break;
                case 3: v.setEstrategia(new PrecioLargoPlazo()); break;
                default: System.out.println("Estrategia inválida."); return;
            }
            System.out.println("Estrategia asignada a " + v.toString());
        } catch (Exception e) {
            System.out.println("Error al asignar estrategia: " + e.getMessage());
        }
    }

    private static void simularAlquiler(Scanner sc) {
        listarVehiculos();
        try {
            System.out.print("Índice vehículo: ");
            int idx = Integer.parseInt(sc.nextLine());
            Vehiculo v = flota.get(idx);
            System.out.print("Días de alquiler: ");
            int dias = Integer.parseInt(sc.nextLine());
            Alquiler alq = new Alquiler(v, dias);
            System.out.println("Total: " + String.format("%.2f", alq.getTotal()));
        } catch (Exception e) {
            System.out.println("Error simulando alquiler: " + e.getMessage());
        }
    }

    private static void recargarElectrico(Scanner sc) {
        listarVehiculos();
        try {
            System.out.print("Índice vehículo: ");
            int idx = Integer.parseInt(sc.nextLine());
            Vehiculo v = flota.get(idx);
            if (!(v instanceof com.example.interfaces.Electrico)) {
                System.out.println("El vehículo seleccionado no es eléctrico.");
                return;
            }
            com.example.interfaces.Electrico e = (com.example.interfaces.Electrico) v;
            System.out.println("Nivel actual: " + e.nivelBateria() + "%");
            System.out.print("Minutos a recargar: ");
            int minutos = Integer.parseInt(sc.nextLine());
            e.recargar(minutos);
            System.out.println("Nivel ahora: " + e.nivelBateria() + "%");
        } catch (Exception ex) {
            System.out.println("Error al recargar: " + ex.getMessage());
        }
    }

    private static void sumarKmMenu(Scanner sc) {
        listarVehiculos();
        try {
            System.out.print("Índice vehículo: ");
            int idx = Integer.parseInt(sc.nextLine());
            Vehiculo v = flota.get(idx);
            System.out.print("Km a sumar: ");
            double km = Double.parseDouble(sc.nextLine());
            v.sumarKm(km);
            System.out.println("KM actualizado: " + v.getKm());
        } catch (Exception e) {
            System.out.println("Error sumando km: " + e.getMessage());
        }
    }

    private static void pruebasRapidas() {
        System.out.println("\n--- PRUEBAS RÁPIDAS ---");

        // Polimorfismo: Rentable a distintos tipos (mismos dias = 5)
        System.out.println("\nPolimorfismo (mismo dias=5) sobre diferentes vehículos:");
        for (Vehiculo v : flota) {
            com.example.interfaces.Rentable r = v; // referencia Rentable
            double total = r.calcularPrecioAlquiler(5);
            System.out.printf("%s -> total(5 días) = %.2f\n", v.getClass().getSimpleName(), total);
        }

        // Estrategias: comparar PrecioEstandar vs PrecioLargoPlazo
        System.out.println("\nEstrategias (mismos insumos) sobre primer vehículo:");
        Vehiculo v0 = flota.get(0);
        v0.setEstrategia(new PrecioEstandar());
        double tEst = v0.calcularPrecioAlquiler(12);
        v0.setEstrategia(new PrecioLargoPlazo());
        double tLargo = v0.calcularPrecioAlquiler(12);
        System.out.printf("PrecioEstandar(12 días)=%.2f, PrecioLargoPlazo(12 días)=%.2f\n", tEst, tLargo);

        // Vehículo no asegurado (Moto)
        System.out.println("\nVehículo no asegurado (Moto) no suma seguro:");
        Vehiculo moto = null;
        for (Vehiculo v : flota) if (v instanceof Moto) { moto = v; break; }
        if (moto != null) {
            double base = moto.costoBaseDia() * 3;
            double total = moto.calcularPrecioAlquiler(3);
            System.out.printf("Moto base(3 días)=%.2f, total calculado=%.2f (seguro esperado=0)\n", base, total);
        } else {
            System.out.println("No se encontró Moto en la flota.");
        }

        // Recarga en eléctricos respeta tope 100
        System.out.println("\nRecarga en eléctricos respeta tope 100:");
        Vehiculo electrico = null;
        for (Vehiculo v : flota) if (v instanceof com.example.interfaces.Electrico) { electrico = v; break; }
        if (electrico != null) {
            com.example.interfaces.Electrico e = (com.example.interfaces.Electrico) electrico;
            System.out.println("Nivel actual: " + e.nivelBateria());
            e.recargar(5000); // muchos minutos -> tope en 100
            System.out.println("Nivel tras recargar mucho: " + e.nivelBateria());
        } else {
            System.out.println("No hay eléctricos en la flota para probar.");
        }

        System.out.println("\n--- FIN DE PRUEBAS ---");
    }
}
