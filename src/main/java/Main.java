package main.java;

import Builder.RandomProcessBuilder;
import Modelo.Event;
import Modelo.ProcessConstructor;
import Modelo.StatesNames;
import com.google.gson.Gson;
import main.java.Util.JsonEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args){
        ArrayList<String> answers=new ArrayList<>();
        ArrayList<Event> events=new ArrayList<>();
        ProcessConstructor constructor=new RandomProcessBuilder();
        Gson gson=new Gson();

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        CorsFilter.apply();
        port(port);

        get("/", (req,res)->{
            return "" +
                    " <h1>Bienvenido a TSS API</h1>" +
                    " <h2>Servicios disponibles: </h2" +
                    " <h3><h3>";
        });

        post("/event", (request, response) -> {
            response.type("application/json");
            JsonEvent eventDTO = gson.fromJson(request.body(), JsonEvent.class);

            String eventDescription=eventDTO.getEventDescription();
            String eventLocationDescription=eventDTO.getLocationDescription();
            Date eventDate=new Date(eventDTO.getTimeMsEventDate());
            long systemMs=System.currentTimeMillis();
            Date eventDelationDate=new Date(systemMs);
            String initialStateName=eventDTO.getInitialState();
            StatesNames initialState;
            String id=generateId(eventDelationDate,events.size());

            if(initialStateName.equalsIgnoreCase("FELCV")){
                initialState=StatesNames.FELCV;
            }else {
                initialState=StatesNames.MINISTERIO_PUBLICO;
            }
            Event event=new Event(id,
                                  eventDescription,
                                  eventLocationDescription,
                                  eventDate,
                                  eventDelationDate,
                                  constructor,
                                  initialState);
            events.add(event);

            return gson.toJson(event);
        });

        get("/event/:id", (request, response) -> {
            response.type("application/json");
            String eventId=request.params(":id");
            int pos=getId(eventId);
            if(((events.size()-1)>=pos)&&pos>=0){
                return gson.toJson(events.get(pos));
            }else {
                return gson.toJson(null);
            }
        });

        get("/events", (request, response) -> {
            response.type("application/json");
            return gson.toJson(events);
        });

        //loveService

        post("/answer/:answer", (request, response) -> {
            String answer=request.params(":answer");
            answers.add(answer);
            return answer;
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

    private static String generateId(Date date, int pos){
        Calendar calendarUtil = Calendar.getInstance();
        calendarUtil.setTime(date);
        String id=""+calendarUtil.get(Calendar.YEAR);
        id+=(calendarUtil.get(Calendar.MONTH)+1);
        id+=calendarUtil.get(Calendar.DAY_OF_MONTH);
        return id+"-"+pos;
    }

    private static int getId(String eventId){
        StringTokenizer stringTokenizer = new StringTokenizer(eventId, "-");
        stringTokenizer.nextToken();
        return Integer.parseInt(stringTokenizer.nextToken());
    }
}
