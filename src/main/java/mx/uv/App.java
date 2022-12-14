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
        get("/", (req, res) -> {
            res.type("application/json");
            return gson.toJson(DAO.GetEquipos());
        });

        //CONSULTAR TODOS LOS EQUIPOS
        get("/allEquipos", (req, res) -> {
            return gson.toJson(DAO.GetAllEquipos());
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
            Equipo e = gson.fromJson(datos, Equipo.class);
            // devolver una respuesta JSON
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", DAO.eliminarEquipo(e.getId()));
            return objetoJson;
        });

        post("/updateEquipo", (req, res) -> {
            String datos = req.body();
            Equipo e = gson.fromJson(datos, Equipo.class);
            //respuesta JSON
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", DAO.actualizarEquipo(e));
            return objetoJson;
        });

        //CONSULTA PARTIDOS
        get("/partidos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(DAO.GetPartidos());
        });

        //CONSULTA TODOS LOS PARTIDOS
        get("/allPartidos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(DAO.GetAllPartidos());
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

        post("/delPartido", (req, res) -> {
            String datos = req.body();
            Partido p = gson.fromJson(datos, Partido.class);
            // devolver una respuesta JSON
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", DAO.eliminarPartido(p.getId()));
            return objetoJson;
        });

        post("/updatePartido", (req, res) -> {
            String datos = req.body();
            Partido p = gson.fromJson(datos, Partido.class);
            //respuesta JSON
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", DAO.actualizarPartido(p));
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
