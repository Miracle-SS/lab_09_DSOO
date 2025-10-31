package Batalla;

import java.util.*;

public class BatallaMain {
    private static final String[] REINOS = {
        "Inglaterra",
        "Francia",
        "Castilla-Aragón",
        "Moros",
        "Sacro Imperio Romano-Germánico"
    };
    private static Random rand = new Random();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("===Batalla por una jarra de chicha===");
        boolean seguir = true;
        while (seguir) {
            System.out.println("\nOpciones:");
            System.out.println("1) Generar nueva batalla");
            System.out.println("2) Salir");
            System.out.print("Elige una opción: ");
            String opt = sc.nextLine().trim();
            if (opt.equals("1")) {
                ejecutarBatalla();
            } else if (opt.equals("2")) {
                seguir = false;
            } else {
                System.out.println("Opción inválida.");
            }
        }
        System.out.println("Fin del programa.");
    }

    private static void ejecutarBatalla() {
        String reino1 = REINOS[rand.nextInt(REINOS.length)];
        String reino2;
        do {
            reino2 = REINOS[rand.nextInt(REINOS.length)];
        } while (reino2.equals(reino1));

        int cant1 = rand.nextInt(10) + 1;
        int cant2 = rand.nextInt(10) + 1;

        Ejercito e1 = new Ejercito(reino1, true, cant1);  // usa array
        Ejercito e2 = new Ejercito(reino2, false, cant2); // usa ArrayList

        Mapa mapa = new Mapa();
        mapa.generarEjercitos(e1, e2);

        mapa.dibujarMapa(e1, e2);

        e1.mostrarTodosDatos();
        e2.mostrarTodosDatos();

        Soldado m1 = e1.getSoldadoMayorVida();
        Soldado m2 = e2.getSoldadoMayorVida();
        System.out.println("\nMayor vida Ejército 1 (" + e1.getReino() + "): " +
                (m1 != null ? m1.mostrarDatos() : "Ninguno"));
        System.out.println("Promedio vida Ejército 1: " + String.format("%.2f", e1.getPromedioVida()));

        System.out.println("\nMayor vida Ejército 2 (" + e2.getReino() + "): " +
                (m2 != null ? m2.mostrarDatos() : "Ninguno"));
        System.out.println("Promedio vida Ejército 2: " + String.format("%.2f", e2.getPromedioVida()));

        System.out.println("\nSoldados Ejército 1 en orden creado:");
        for (Soldado s : e1.getSoldadosComoLista()) System.out.println("  " + s.mostrarDatos());

        System.out.println("\nSoldados Ejército 2 en orden creado:");
        for (Soldado s : e2.getSoldadosComoLista()) System.out.println("  " + s.mostrarDatos());

        e1.ordenarPorVidaDesc();
        e2.ordenarPorVidaDesc();

        System.out.println("\nRanking Ejército 1 (por vida desc):");
        for (Soldado s : e1.getSoldadosComoLista()) System.out.println("  " + s.getNombre() + " -> " + s.getVidaActual());

        System.out.println("\nRanking Ejército 2 (por vida desc):");
        for (Soldado s : e2.getSoldadosComoLista()) System.out.println("  " + s.getNombre() + " -> " + s.getVidaActual());

        int total1 = e1.getTotalVida();
        int total2 = e2.getTotalVida();
        int suma = total1 + total2;
        double pct1 = (suma == 0) ? 50.0 : (100.0 * total1 / suma);
        double pct2 = (suma == 0) ? 50.0 : (100.0 * total2 / suma);

        System.out.println("\n--- Resumen ---");
        System.out.println("Ejercito 1: " + e1.getReino());
        System.out.println("Cantidad total de soldados: " + e1.getCantidad());
        Map<String,Integer> dist1 = e1.contarTipos();
        System.out.println("Espadachines: " + dist1.get("Espadachin"));
        System.out.println("Arqueros: " + dist1.get("Arquero"));
        System.out.println("Caballeros: " + dist1.get("Caballero"));
        System.out.println("Lanceros: " + dist1.get("Lancero"));

        System.out.println("\nEjercito 2: " + e2.getReino());
        System.out.println("Cantidad total de soldados: " + e2.getCantidad());
        Map<String,Integer> dist2 = e2.contarTipos();
        System.out.println("Espadachines: " + dist2.get("Espadachin"));
        System.out.println("Arqueros: " + dist2.get("Arquero"));
        System.out.println("Caballeros: " + dist2.get("Caballero"));
        System.out.println("Lanceros: " + dist2.get("Lancero"));

        System.out.println("\nEjercito 1: " + e1.getReino() + ": " + total1 + " " + String.format("%.2f", pct1) + "% de probabilidad de victoria");
        System.out.println("Ejercito 2: " + e2.getReino() + ": " + total2 + " " + String.format("%.2f", pct2) + "% de probabilidad de victoria");

        double ale = rand.nextDouble() * 100.0;
        System.out.println("Aleatorio generado (0..100): " + String.format("%.2f", ale));
        String ganador;
        if (ale <= pct1) ganador = "Ejercito 1 de: " + e1.getReino();
        else ganador = "Ejercito 2 de: " + e2.getReino();
        System.out.println("\nEl ganador es: " + ganador + ".");
    }
}
