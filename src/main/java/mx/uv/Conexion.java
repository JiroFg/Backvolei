package mx.uv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static ProcessBuilder processBuilder = new ProcessBuilder();
    private static String driver =  "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://db4free.net:3306/voleiboard";//URLDB
    private static String user = "userfei";//USERDB
    private static String pass = "fei123456";//PASSDB
    /*
    private static String url = processBuilder.environment().get("URLDB");
    private static String user = processBuilder.environment().get("USERDB");
    private static String pass = processBuilder.environment().get("PASSDB");
    */

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(" SQL: " + e);
        } catch (ClassNotFoundException e){
            System.out.println("Driver: " + e);
        }
        return connection;
    }
}
