package mx.uv;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DAO {

    public static List<Equipo> GetAllEquipos() {
        Statement stm = null;
        ResultSet rs = null;
        List<Equipo> resultado = new ArrayList<>();

        try {
            String sql = "SELECT * from equipos2";
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

    //VISUALIZAR DATOS DE LA BASE
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

    //AGREGAR EQUIPO A LA BASE DE DATOS 
    public static String agregarEquipo(Equipo e) {
        PreparedStatement stm = null;
        String msj = "";

        try {
            String sql = "INSERT INTO equipos2 (nombre, score) values (?,?)";
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

    //ELIMINAR EQUIPO DE LA BASE DE DATOS
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

    //ACTUALIZAR EQUIPO DE LA BASE DE DATOS
    public static String actualizarEquipo(Equipo e) {
        PreparedStatement stm = null;
        String msj = "";

        try {
            String sql = "UPDATE equipos2 SET nombre='"+e.getNombre()+"', score="+e.getScore()+" WHERE id="+e.getId();
            stm = (PreparedStatement) Conexion.getConnection().prepareStatement(sql);
            if (stm.executeUpdate() > 0){
                msj = "Equipo actualizado";
            }else{
                msj = "El equipo no se pudo actualizar";
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

    //VISUALIZAR PARTIDOS DE LA BASE
    public static List<Partido> GetAllPartidos() {
        Statement stm = null;
        ResultSet rs = null;
        List<Partido> resultado = new ArrayList<>();

        try {
            String sql = "SELECT * from partidos2";
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

    //VISUALIZAR PARTIDOS DE LA BASE
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

    //AGREGAR PARTIDOS A LA BASE DE DATOS
    public static String agregarPartido(Partido p) {
        PreparedStatement stm = null;
        String msj = "";

        try {
            String sql = "INSERT INTO partidos2 (equipo1, score1, equipo2, score2, status) values (?,?,?,?,?)";
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

    //ELIMINAR PARTIDO DE LA BASE DE DATOS
    public static String eliminarPartido(int id) {
        PreparedStatement stm = null;
        String msj = "";

        try {
            String sql = "DELETE FROM partidos2 WHERE id="+id;
            stm = (PreparedStatement) Conexion.getConnection().prepareStatement(sql);
            if (stm.executeUpdate() > 0){
                msj = "Partido eliminado";
            }else{
                msj = "El partido no se pudo eliminar";
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

    //ACTUALIZAR PARTIDO DE LA BASE DE DATOS
    public static String actualizarPartido(Partido p) {
        PreparedStatement stm = null;
        String msj = "";

        try {
            String sql = "UPDATE partidos2 SET equipo1='"+p.getEquipo1()+"', score1="+p.getScore1()+", equipo2='"+p.getEquipo2()+"', score2="+p.getScore1()+", status='"+p.getStatus()+"' WHERE id="+p.getId();
            System.out.println(sql);
            stm = (PreparedStatement) Conexion.getConnection().prepareStatement(sql);
            if (stm.executeUpdate() > 0){
                msj = "Partido actualizado";
            }else{
                msj = "El partido no se pudo actualizar";
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
