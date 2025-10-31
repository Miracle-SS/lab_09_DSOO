package Batalla;

import java.util.*;

public class Mapa {
    public static final int TAM = 8;
    private String tipoTerreno;
    private boolean[][] ocupada;
    private Random rand = new Random();

    private static final Map<String, List<String>> bonosReino = new HashMap<>();
    static {
        bonosReino.put("Inglaterra", Arrays.asList("bosque"));
        bonosReino.put("Francia", Arrays.asList("campo abierto"));
        bonosReino.put("Castilla-Aragón", Arrays.asList("montaña"));
        bonosReino.put("Moros", Arrays.asList("desierto"));
        bonosReino.put("Sacro Imperio Romano-Germánico", Arrays.asList("bosque", "playa", "campo abierto"));
    }

    public Mapa() {
        String[] terrenos = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
        this.tipoTerreno = terrenos[rand.nextInt(terrenos.length)];
        ocupada = new boolean[TAM][TAM];
    }

    public String getTipoTerreno() { return tipoTerreno; }

    // genera dos ejercitos y los posiciona en el mapa
    // reino1 y reino2 deben ser diferentes
    public void generarEjercitos(Ejercito e1, Ejercito e2) {
        for (int i = 0; i < TAM; i++) for (int j = 0; j < TAM; j++) ocupada[i][j] = false;

        generarParaEjercito(e1, 1);
        generarParaEjercito(e2, 2);

        aplicarBonificaciones(e1);
        aplicarBonificaciones(e2);
    }

    private void generarParaEjercito(Ejercito e, int idEjercito) {
        int cantidad = e.getCantidad();
        for (int i = 0; i < cantidad; i++) {
            int tipo = rand.nextInt(4);
            int fila, col;
            do {
                fila = rand.nextInt(TAM);
                col = rand.nextInt(TAM);
            } while (ocupada[fila][col]);
            ocupada[fila][col] = true;

            Soldado s = null;
            String nombreBase;
            switch (tipo) {
                case 0: // Espadachin
                    nombreBase = "Espadachin" + i + "X" + idEjercito;
                    int vidaE = rand.nextInt(3) + 8; // [8..10]
                    double longE = 1.0 + rand.nextDouble() * 2.0;
                    s = new Espadachin(nombreBase, vidaE, fila, col, Math.round(longE*10.0)/10.0);
                    break;
                case 1: // Arquero
                    nombreBase = "Arquero" + i + "X" + idEjercito;
                    int vidaA = rand.nextInt(3) + 3; // [3..5]
                    int flechas = rand.nextInt(6) + 3; // 3..8 por ejemplo
                    s = new Arquero(nombreBase, vidaA, fila, col, flechas);
                    break;
                case 2: // Caballero
                    nombreBase = "Caballero" + i + "X" + idEjercito;
                    int vidaC = rand.nextInt(3) + 10; // [10..12]
                    s = new Caballero(nombreBase, vidaC, fila, col);
                    break;
                default: // Lancero
                    nombreBase = "Lancero" + i + "X" + idEjercito;
                    int vidaL = rand.nextInt(4) + 5; // [5..8]
                    double longL = 1.0 + rand.nextDouble() * 2.0;
                    s = new Lancero(nombreBase, vidaL, fila, col, Math.round(longL*10.0)/10.0);
                    break;
            }
            e.agregarSoldado(s, i); // para array usa i, para list lo ignora
        }
    }
    private void aplicarBonificaciones(Ejercito e) {
        List<Soldado> soldados = e.getSoldadosComoLista();
        List<String> bonitos = bonosReino.getOrDefault(e.getReino(), Collections.emptyList());
        if (bonitos.contains(tipoTerreno)) {
            for (Soldado s : soldados) {
                s.incrementarVida(1);
            }
        }
    }
    public void dibujarMapa(Ejercito e1, Ejercito e2) {
        String[][] tablero = new String[TAM][TAM];
        for (int i = 0; i < TAM; i++) for (int j = 0; j < TAM; j++) tablero[i][j] = ".";

        for (Soldado s : e1.getSoldadosComoLista()) {
            int r = s.getFila(), c = s.getColumna();
            tablero[r][c] = ciudadSigla(e1.getReino()) + tipoSigla(s.tipo());
        }
        for (Soldado s : e2.getSoldadosComoLista()) {
            int r = s.getFila(), c = s.getColumna();
            tablero[r][c] = ciudadSigla(e2.getReino()) + tipoSigla(s.tipo());
        }

        System.out.println("\n--- Mapa (Terreno: " + tipoTerreno + ") ---");
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                System.out.print(String.format("%-4s", tablero[i][j]));
            }
            System.out.println();
        }
        System.out.println("\nLeyenda: Primera letra reino + inicial tipo (E:Espad, A:Arqu, C:Cab, L:Lanc). Ej: I E -> Inglaterra Espadachin");
    }
    private String tipoSigla(String tipo) {
        switch (tipo) {
            case "Espadachin": return "E";
            case "Arquero": return "A";
            case "Caballero": return "C";
            case "Lancero": return "L";
            default: return "?";
        }
    }
    private String ciudadSigla(String reino) {
        if (reino.startsWith("Ingl")) return "I";
        if (reino.startsWith("Fran")) return "F";
        if (reino.startsWith("Cast")) return "CA";
        if (reino.startsWith("Moro") || reino.startsWith("Mor")) return "M";
        if (reino.startsWith("Sacro")) return "S";
        return reino.substring(0,1).toUpperCase();
    }
}
