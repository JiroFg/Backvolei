package mx.uv;
import static spark.Spark.*;
import com.google.gson.*;

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
        get("/", (req, res) -> {
            res.type("application/json");
            return gson.toJson(DAO.GetEquipos());
        });

        //CONSULTA PARTIDOS
        get("/partidos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(DAO.GetPartidos());
        });

        //AGREGAR EQUIPOS
        post("/addEquipo", (req, res) -> {
            String datos = req.body();
            Equipo e = gson.fromJson(datos, Equipo.class);
            //respuesta JSON
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", DAO.agregarEquipo(e));
            return objetoJson;
        });

        post("/delEquipo", (req, res) -> {
            String datos = req.body();
            System.out.println(datos);
            int id = gson.fromJson(datos, Integer.class);
            System.out.println(id);

            // devolver una respuesta JSON
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", DAO.eliminarEquipo(id));
            return objetoJson;
        });

        //AGREGAR PARTIDOS
        post("/addPartido", (req, res) -> {
            String datos = req.body();
            Partido p = gson.fromJson(datos, Partido.class);

            //respuesta JSON
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
