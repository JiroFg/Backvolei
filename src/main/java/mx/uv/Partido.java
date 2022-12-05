package mx.uv;

public class Partido {
    String equipo1;
    int score1;
    String equipo2;
    int score2;
    String status;

    public Partido(String equipo1, int score1, String equipo2, int score2, String status) {
        this.equipo1 = equipo1;
        this.score1 = score1;
        this.equipo2 = equipo2;
        this.score2 = score2;
        this.status = status;
    }
    
    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.equipo1 +" "+ this.score1 +" | "+ this.equipo2 +" "+ this.score2 +" | "+ this.status;
    }
}
