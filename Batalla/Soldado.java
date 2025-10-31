package Batalla;

public abstract class Soldado {
    protected String nombre;
    protected int vidaMax;   
    protected int vidaActual;
    protected int ataque;
    protected int defensa;
    protected int fila;
    protected int columna;

    public Soldado(String nombre, int vidaMax, int ataque, int defensa, int fila, int columna) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vidaActual = vidaMax;
        this.ataque = ataque;
        this.defensa = defensa;
        this.fila = fila;
        this.columna = columna;
    }

    public String getNombre() { return nombre; }
    public int getVidaActual() { return vidaActual; }
    public int getVidaMax() { return vidaMax; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public void incrementarVida(int delta) {
        this.vidaActual += delta;
    }
    public String tipo() {
        return "Soldado";
    }
    public String mostrarDatos() {
        return String.format("%s | Tipo: %s | Vida: %d | Ataque: %d | Defensa: %d | Pos: (%d,%d)",
                nombre, tipo(), vidaActual, ataque, defensa, fila, columna);
    }
}
