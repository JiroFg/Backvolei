package mx.uv;

public class Equipo {
    int id;
    String nombre;
    int score;

    public Equipo(int id, String nombre, int score) {
        this.id = id;
        this.nombre = nombre;
        this.score = score;
    }

    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
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
        return this.id + " " + this.nombre + " " + this.score;
    }
}
