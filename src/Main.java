import static spark.Spark.*;

public class Main {
    public static void main(String[] args){
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);

        get("/", (req,res)->{
            return "" +
                    " <h1>Bienvenido a Voice Mail API</h1>" +
                    " <h2>Servicios disponibles: </h2" +
                    " <h3> <h3>obtener todos los mailbox(GET): </h3></h3> <p>/mailboxs</p>" +
                    " <h3>obtener mailbox por ID(GET): </h3> <p>/mailboxs/:id</p>" +
                    " <h3>editar mailbox(PUT): </h3> <p>/mailboxs/:id</p>" +
                    " <h3>crear mensajes(POST): </h3> <p>/messages</p>" +
                    " <h3>eliminar mensaje(DELETE): </h3> <p>/messages/:id</p>" +
                    "";
        });

        get("/prueba/:id", (request, response) -> {
            String input=request.params(":id");
            return "Id= "+input;
        });

        /*get("/mailboxs/:id", (request, response) -> {
            response.type("application/json");
            String input=request.params(":id");
            return new Gson().toJson(input);
        });*/
    }
}
