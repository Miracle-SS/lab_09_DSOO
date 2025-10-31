package Batalla;

public class Arquero extends Soldado {
    private int flechas;
    public Arquero(String nombre, int vidaMax, int fila, int columna, int flechas) {
        super(nombre, vidaMax, 7, 3, fila, columna); // ataque 7, defensa 3
        this.flechas = flechas;
    }
    public boolean disparar() {
        if (flechas > 0) {
            flechas--;
            return true;
        }
        return false;
    }
    public String tipo() { return "Arquero"; }
    public String mostrarDatos() {
        return super.mostrarDatos() + String.format(" | Flechas: %d", flechas);
    }
}
