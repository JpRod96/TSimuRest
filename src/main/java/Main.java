package main.java;

import MailVoice.Mailbox;
import com.google.gson.Gson;

import java.util.ArrayList;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args){
        ArrayList<String> answers=new ArrayList<>();
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

        post("/answer/:answer", (request, response) -> {
            String answer=request.params(":answer");
            answers.add(answer);
            return answer;
        });

        get("/mailbox/:id", (request, response) -> {
            response.type("application/json");
            String input=request.params(":id");
            int id=Integer.parseInt(input);
            Mailbox mailbox=new Mailbox("123","hey there",id);
            return new Gson().toJson(mailbox);
        });

        get("/answers", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(answers);
        });

        get("/clear", (request, response) -> {
            response.type("application/json");
            answers.clear();
            return new Gson().toJson(answers);
        });

        get("/loveMessage",(request, response)->{
           return "<div style='text-align:center'>" +
                       "<p>En un día tan bello como hoy...</p> " +
                       "<p>Me gustaría preguntarte...</p> " +
                       "<p><strong>¿Quieres que estemos juntos por el resto de nuestras vidas?</strong></p>" +
                   "</div>";
        });
    }
}
