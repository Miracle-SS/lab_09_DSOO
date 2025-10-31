package Batalla;

public class Lancero extends Soldado {
    private double longitudLanza;
    public Lancero(String nombre, int vidaMax, int fila, int columna, double longitudLanza) {
        super(nombre, vidaMax, 5, 10, fila, columna); // ataque 5, defensa 10
        this.longitudLanza = longitudLanza;
    }
    public void schiltrom() {
        this.defensa += 1;
    }
    public String tipo() { return "Lancero"; }
    public String mostrarDatos() {
        return super.mostrarDatos() + String.format(" | Long.lza: %.1f", longitudLanza);
    }
}
