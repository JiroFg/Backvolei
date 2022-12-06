package mx.uv;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DAO {

    //Metodo para obtener los Equipos de la base de datos
    public static List<Equipo> GetEquipos() {
        Statement stm = null;
        ResultSet rs = null;
        List<Equipo> resultado = new ArrayList<>();

        try {
            String sql = "SELECT * from equipos ORDER BY Score DESC LIMIT 10";
            stm = (Statement) Conexion.getConnection().createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Equipo u = new Equipo(rs.getString(1), Integer.parseInt(rs.getString(2)));
                resultado.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            rs = null;
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                Conexion.getConnection().close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return resultado;
    }

    //metodo para obtener los Partidos de la base de datos
    public static List<Partido> GetPartidos() {
        Statement stm = null;
        ResultSet rs = null;
        List<Partido> resultado = new ArrayList<>();

        try {
            String sql = "SELECT * from partidos LIMIT 6";
            stm = (Statement) Conexion.getConnection().createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Partido p = new Partido(rs.getString(1),Integer.parseInt(rs.getString(2)), rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5));
                resultado.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            rs = null;
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                Conexion.getConnection().close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return resultado;
    }

    //metodo para agregar un equipo a la base de datos
    public static String agregarEquipo(Equipo e) {
        PreparedStatement stm = null;
        String msj = "";

        try {
            String sql = "INSERT INTO equipos (Nombre, Score) values (?,?)";
            //INSERT INTO `equipos` (`Nombre`, `Score`) VALUES ('China', '210');
            stm = (PreparedStatement) Conexion.getConnection().prepareStatement(sql);
            stm.setString(1, e.getNombre());
            stm.setLong(2, e.getScore());
            if (stm.executeUpdate() > 0){
                msj = "Equipo agregado";
            }else{
                msj = "Equipo no agregado";
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                stm = null;
            }
            try {
                Conexion.getConnection().close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return msj;
    }

    //Metodo para agregar un partido a la base de datos
    public static String agregarPartido(Partido p) {
        PreparedStatement stm = null;
        String msj = "";

        try {
            String sql = "INSERT INTO partidos (equipo1, score1, equipo2, score2, status) values (?,?,?,?,?)";
            //INSERT INTO `partidos` (`equipo1`, `score1`, `equipo2`, `score2`, `status`) VALUES ('Francia', '3', 'México', '2', 'Finalizado');
            stm = (PreparedStatement) Conexion.getConnection().prepareStatement(sql);
            stm.setString(1, p.getEquipo1());
            stm.setLong(2, p.getScore1());
            stm.setString(3, p.getEquipo2());
            stm.setLong(4, p.getScore2());
            stm.setString(5, p.getStatus());
            if (stm.executeUpdate() > 0){
                msj = "Partido agregado";
            }else{
                msj = "Partido no agregado";
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                stm = null;
            }
            try {
                Conexion.getConnection().close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return msj;
    }
}
