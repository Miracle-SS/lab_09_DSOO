package Batalla;

public class Caballero extends Soldado {
    private boolean montado;
    private String armaActual;
    public Caballero(String nombre, int vidaMax, int fila, int columna) {
        super(nombre, vidaMax, 13, 7, fila, columna);
        this.montado = true;
        this.armaActual = "lanza";
    }
    public void alternarArma() {
        if (armaActual.equals("lanza")) armaActual = "espada"; else armaActual = "lanza";
    }
    public void montar() {
        if (!montado) {
            montado = true;
            armaActual = "lanza";
        }
    }
    public void desmontar() {
        if (montado) {
            montado = false;
            armaActual = "espada";
            this.defensa += 1;
        }
    }
    public int envestir() {
        return montado ? 3 : 2;
    }
    public String tipo() { return "Caballero"; }
    public String mostrarDatos() {
        return super.mostrarDatos() + String.format(" | Montado: %b | Arma: %s", montado, armaActual);
    }
}
