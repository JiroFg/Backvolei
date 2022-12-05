package mx.uv;

public class Equipo {
    String nombre;
    int score;

    public Equipo(String nombre, int score) {
        this.nombre = nombre;
        this.score = score;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return this.nombre +" "+ this.score;
    }
}
