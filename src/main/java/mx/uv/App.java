package mx.uv;
import static spark.Spark.*;
import com.google.gson.*;
import java.util.List;

public class App {
    public static Gson gson = new Gson();

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        //CORS
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            System.out.println(accessControlRequestHeaders);
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            System.out.println(accessControlRequestMethod);
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));

        //CONSULTA EQUIPOS
        //Prueba de la lista de equipos obtenidos de la base de datos.
        List<Equipo> resultado = DAO.GetEquipos();
        for(Equipo e: resultado){
            System.out.println(e.toString());
        }
        //---->>> Se convierte a Json para enviarlo
        get("/", (req, res) -> {
            res.type("application/json");
            return gson.toJson(DAO.GetEquipos());
        });

        //CONSULTA PARTIDOS
        //Prueba de la lista de partidos obtenidos de la base de datos.
        List<Partido> partidos = DAO.GetPartidos();
        for(Partido p: partidos){
            System.out.println(p.toString());
        }
        //---->>> Se convierte a Json para enviarlo
        get("/partidos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(DAO.GetPartidos());
        });

        //AGREGAR EQUIPOS
        //Prueba de agregar equipo a la base de datos
        //System.out.println(DAO.agregarEquipo(new Equipo("España", 190)));

        //
        post("/admin", (req, res) -> {
            String datos = req.body();
            Equipo e = gson.fromJson(datos, Equipo.class);

            // devolver una respuesta JSON
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", DAO.agregarEquipo(e));
            return objetoJson;
        });

        //AGREGAR PARTIDOS
        //Prueba de agregar equipo a la base de datos
        System.out.println(DAO.agregarPartido(new Partido("Japón",3,"USA",2,"Finalizado")));

        //
        post("/admin", (req, res) -> {
            String datos = req.body();
            Partido p = gson.fromJson(datos, Partido.class);

            // devolver una respuesta JSON
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", DAO.agregarPartido(p));
            return objetoJson;
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
