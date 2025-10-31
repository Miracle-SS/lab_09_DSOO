package Batalla;

import java.util.*;

public class Ejercito {
    private String reino;
    private boolean esArray;
    private Soldado[] soldadosArray;  
    private ArrayList<Soldado> soldadosList; 
    private int cantidad; 

    public Ejercito(String reino, boolean esArray, int cantidad) {
        this.reino = reino;
        this.esArray = esArray;
        this.cantidad = cantidad;
        if (esArray) {
            soldadosArray = new Soldado[cantidad];
        } else {
            soldadosList = new ArrayList<>();
        }
    }

    public String getReino() { return reino; }
    public int getCantidad() { return cantidad; }

    public void agregarSoldado(Soldado s, int indexIfArray) {
        if (esArray) {
            if (indexIfArray < soldadosArray.length) soldadosArray[indexIfArray] = s;
        } else {
            soldadosList.add(s);
        }
    }
    public List<Soldado> getSoldadosComoLista() {
        if (esArray) {
            List<Soldado> out = new ArrayList<>();
            for (Soldado s : soldadosArray) if (s != null) out.add(s);
            return out;
        } else {
            return new ArrayList<>(soldadosList);
        }
    }
    public void mostrarTodosDatos() {
        System.out.println("\n--- Ejército: " + reino + " | Cantidad: " + cantidad + " ---");
        List<Soldado> todos = getSoldadosComoLista();
        for (Soldado s : todos) System.out.println(s.mostrarDatos());
    }
    public Soldado getSoldadoMayorVida() {
        List<Soldado> todos = getSoldadosComoLista();
        if (todos.isEmpty()) return null;
        Soldado best = todos.get(0);
        for (Soldado s : todos) {
            if (s.getVidaActual() > best.getVidaActual()) best = s;
        }
        return best;
    }
    public double getPromedioVida() {
        List<Soldado> todos = getSoldadosComoLista();
        if (todos.isEmpty()) return 0;
        double sum = 0;
        for (Soldado s : todos) sum += s.getVidaActual();
        return sum / todos.size();
    }

    public int getTotalVida() {
        List<Soldado> todos = getSoldadosComoLista();
        int sum = 0;
        for (Soldado s : todos) sum += s.getVidaActual();
        return sum;
    }
    public Map<String, Integer> contarTipos() {
        Map<String,Integer> m = new LinkedHashMap<>();
        m.put("Espadachin", 0);
        m.put("Arquero", 0);
        m.put("Caballero", 0);
        m.put("Lancero", 0);
        for (Soldado s : getSoldadosComoLista()) {
            m.put(s.tipo(), m.getOrDefault(s.tipo(), 0) + 1);
        }
        return m;
    }
    public void ordenarPorVidaDesc() {
        List<Soldado> list = getSoldadosComoLista();
        // selection sort descending
        for (int i = 0; i < list.size() - 1; i++) {
            int maxIdx = i;
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(j).getVidaActual() > list.get(maxIdx).getVidaActual()) {
                    maxIdx = j;
                }
            }
            if (i != maxIdx) {
                Soldado tmp = list.get(i);
                list.set(i, list.get(maxIdx));
                list.set(maxIdx, tmp);
            }
        }
        if (esArray) {
            for (int i = 0; i < soldadosArray.length; i++) {
                soldadosArray[i] = (i < list.size()) ? list.get(i) : null;
            }
        } else {
            soldadosList = new ArrayList<>(list);
        }
    }
}
