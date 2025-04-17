package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {
        /*String path = "files/csvjson.json";
        int j=0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            JSONParser parser = new JSONParser();
            Object jsonData = parser.parse(br);
            
            if (jsonData instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) jsonData;
                for (Object obj : jsonArray) {
                    if (j < 13) {
                        if (obj instanceof JSONObject) {
                            JSONObject jsonObj = (JSONObject) obj;
                            for (Object key : jsonObj.keySet()) {
                                System.out.println("  " + key + ": " + jsonObj.get(key));
                            }
                            System.out.println();
                            j++;
                        }
                    }
                }
            } else if (jsonData instanceof JSONObject) {
                JSONObject jsonObj = (JSONObject) jsonData;
                System.out.println("Contenuto del JSON:");
                for (Object key : jsonObj.keySet()) {
                    System.out.println(key + ": " + jsonObj.get(key));
                }
            } else {
                System.out.println("Formato JSON non supportato.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            }*/

            /*Utente u = new Utente("Francesco", "Semenzato", "Seme", "SuperSeme0451!]", "Via dalle palle", "BOH", LocalDate.of(2005, 06, 20));

            u.setPassword("CambioPassword");*/

        String filePath = "files/utenti.json";
        ArrayList <JSONArray> list = new ArrayList<>();
        JSONArray utentiArray = new JSONArray();
        String content = "";

        try {
            if (Files.exists(Paths.get(filePath))) { //controlla se esiste il file
                content = new String(Files.readAllBytes(Paths.get(filePath))); //salva il file in una stringa
                if (!content.isBlank()) { //se non è vuoto
                    try {
                        JSONParser parser = new JSONParser();
                        utentiArray = (JSONArray) parser.parse(content); //converte il testo JSON in un JSONArray
                    } catch (ParseException e) {
                        // Se il file è corrotto, lo sovrascriviamo con un nuovo array
                        utentiArray = new JSONArray();
                    }
                }
            }
            for(Object utente : utentiArray) {
                JSONObject nuovoUtente = (JSONObject) utente;
                System.out.println(nuovoUtente.get("Username"));
            }
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
