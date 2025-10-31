package Batalla;

public class Espadachin extends Soldado {
    private double longitudEspada;
    public Espadachin(String nombre, int vidaMax, int fila, int columna, double longitudEspada) {
        super(nombre, vidaMax, 10, 8, fila, columna); // ataque 10, defensa 8
        this.longitudEspada = longitudEspada;
    }
    public void crearMuroEscudos() {
        // acción especial de defensa: sube defensa en 1
        this.defensa += 1;
    }
    public String tipo() { return "Espadachin"; }
    public String mostrarDatos() {
        return super.mostrarDatos() + String.format(" | Long.esp: %.1f", longitudEspada);
    }
}
