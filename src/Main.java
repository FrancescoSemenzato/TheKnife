//mac
// javac -cp "lib/json-simple-1.1.1.jar" -d bin -Xlint:unchecked src/*.java
// java -cp "lib/json-simple-1.1.1.jar:bin" src.Main     
//windows
// javac -cp "lib/json-simple-1.1.1.jar" -d bin -Xlint;unchecked src/*.java
// java -cp "lib/json-simple-1.1.1.jar;bin" src.Main   

package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {
        String path = "files/csvjson.json";
        String riga;
        int j=0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            JSONParser parser = new JSONParser();
            // Legge l'intero file come JSON (può essere un JSONArray o JSONObject)
            Object jsonData = parser.parse(br);
            
            if (jsonData instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) jsonData;
                for (Object obj : jsonArray) {
                    if (j >= 13) break; // Limita a 13 elementi
                    if (obj instanceof JSONObject) {
                        JSONObject jsonObj = (JSONObject) obj;
                        System.out.println("Riga " + (j + 1) + ":");
                        for (Object key : jsonObj.keySet()) {
                            System.out.println("  " + key + ": " + jsonObj.get(key));
                        }
                        System.out.println();
                        j++;
                    }
                }
            } else if (jsonData instanceof JSONObject) {
                // Se il file contiene un singolo JSONObject
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
        }
    }
}