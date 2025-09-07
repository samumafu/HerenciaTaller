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
            System.out.println("7) Agregar vehículo nuevo");
            System.out.println("8) Editar vehículo existente");
            System.out.println("9) Eliminar vehículo");
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
                case 7: agregarVehiculo(sc); break;
                case 8: editarVehiculo(sc); break;
                case 9: eliminarVehiculo(sc); break;
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

    private static void agregarVehiculo(Scanner sc) {
        try {
            System.out.println("Tipos: (1 Auto) - (2 Moto) - (3 Camioneta) - (4 Auto Eléctrico) - (5 Camioneta Eléctrica)");
            System.out.print("Elige tipo: ");
            int tipo = Integer.parseInt(sc.nextLine());

            System.out.print("Placa: ");
            String placa = sc.nextLine();
            System.out.print("Marca: ");
            String marca = sc.nextLine();
            System.out.print("Modelo: ");
            String modelo = sc.nextLine();
            System.out.print("Kilometraje: ");
            double km = Double.parseDouble(sc.nextLine());

            Vehiculo nuevo = null;
            switch (tipo) {
                case 1: nuevo = new Auto(placa, marca, modelo, km); break;
                case 2: nuevo = new Moto(placa, marca, modelo, km); break;
                case 3:
                    System.out.print("Capacidad carga (kg): ");
                    double carga = Double.parseDouble(sc.nextLine());
                    nuevo = new Camioneta(placa, marca, modelo, km, carga);
                    break;
                case 4:
                    System.out.print("Nivel batería (%): ");
                    int nb = Integer.parseInt(sc.nextLine());
                    nuevo = new AutoElectrico(placa, marca, modelo, km, nb);
                    break;
                case 5:
                    System.out.print("Capacidad carga (kg): ");
                    double carga2 = Double.parseDouble(sc.nextLine());
                    System.out.print("Nivel batería (%): ");
                    int nb2 = Integer.parseInt(sc.nextLine());
                    nuevo = new CamionetaElectrica(placa, marca, modelo, km, carga2, nb2);
                    break;
                default:
                    System.out.println("Tipo inválido."); return;
            }
            flota.add(nuevo);
            System.out.println("Vehículo agregado: " + nuevo.toString());
        } catch (Exception e) {
            System.out.println("Error al agregar: " + e.getMessage());
        }
    }

    private static void editarVehiculo(Scanner sc) {
        listarVehiculos();
        try {
            System.out.print("Índice vehículo a editar: ");
            int idx = Integer.parseInt(sc.nextLine());
            Vehiculo v = flota.get(idx);

            System.out.print("Nueva marca ("+v.getMarca()+"): ");
            String marca = sc.nextLine();
            if (!marca.isBlank()) v.setMarca(marca);

            System.out.print("Nuevo modelo ("+v.getModelo()+"): ");
            String modelo = sc.nextLine();
            if (!modelo.isBlank()) v.setModelo(modelo);

            System.out.print("Nuevo km ("+v.getKm()+"): ");
            String kmStr = sc.nextLine();
            if (!kmStr.isBlank()) v.sumarKm(Double.parseDouble(kmStr) - v.getKm());

            System.out.println("Vehículo actualizado: " + v.toString());
        } catch (Exception e) {
            System.out.println("Error al editar: " + e.getMessage());
        }
    }

    private static void eliminarVehiculo(Scanner sc) {
        listarVehiculos();
        try {
            System.out.print("Índice vehículo a eliminar: ");
            int idx = Integer.parseInt(sc.nextLine());
            Vehiculo eliminado = flota.remove(idx);
            System.out.println("Vehículo eliminado: " + eliminado.toString());
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    private static void pruebasRapidas() {
        System.out.println("\n--- PRUEBAS RÁPIDAS ---");
        for (Vehiculo v : flota) {
            double total = v.calcularPrecioAlquiler(5);
            System.out.printf("%s -> total(5 días) = %.2f\n", v.getClass().getSimpleName(), total);
        }
    }
}
