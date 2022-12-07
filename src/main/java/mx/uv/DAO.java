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
            String sql = "SELECT * from equipos2 ORDER BY Score DESC LIMIT 10";
            stm = (Statement) Conexion.getConnection().createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Equipo u = new Equipo(Integer.parseInt(rs.getString(1)), rs.getString(2), Integer.parseInt(rs.getString(3)));
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
            String sql = "SELECT * from partidos2 ORDER BY id DESC LIMIT 6";
            stm = (Statement) Conexion.getConnection().createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Partido p = new Partido(Integer.parseInt(rs.getString(1)),rs.getString(2),Integer.parseInt(rs.getString(3)), rs.getString(4),Integer.parseInt(rs.getString(5)),rs.getString(6));
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
            String sql = "INSERT INTO equipos2 (id, nombre, score) values (?,?,?)";
            stm = (PreparedStatement) Conexion.getConnection().prepareStatement(sql);
            stm.setString(1, "NULL");
            stm.setString(2, e.getNombre());
            stm.setLong(3, e.getScore());
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

    //ELIMINAR EQUIPO
    public static String eliminarEquipo(int id) {
        PreparedStatement stm = null;
        String msj = "";

        try {
            String sql = "DELETE FROM equipos2 WHERE id="+id;
            stm = (PreparedStatement) Conexion.getConnection().prepareStatement(sql);
            if (stm.executeUpdate() > 0){
                msj = "Equipo eliminado";
            }else{
                msj = "El equipo no se pudo eliminar";
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
            String sql = "INSERT INTO partidos2 (id, equipo1, score1, equipo2, score2, status) values (?,?,?,?,?,?)";
            stm = (PreparedStatement) Conexion.getConnection().prepareStatement(sql);
            stm.setString(1, "NULL");
            stm.setString(2, p.getEquipo1());
            stm.setLong(3, p.getScore1());
            stm.setString(4, p.getEquipo2());
            stm.setLong(5, p.getScore2());
            stm.setString(6, p.getStatus());
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
