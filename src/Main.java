// javac -cp "lib/json-simple-1.1.1.jar" -d bin -Xlint:unchecked src/*.java
// java -cp "lib/json-simple-1.1.1.jar:bin" src.Main     

package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        String path = "FileProva.csv";
        String riga;
        int j=0;
        Utente utente1 = new Utente(
            "Gino", 
            "Apao", 
            "masafsfario.f", 
            "password123", 
            "Via Roma 1, Milano", 
            "Ristoratore", 
            LocalDate.of(1985, 5, 15)
        );
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((riga = br.readLine()) != null && j<10) {
                String[] valori = riga.split(",");
                for(String valore : valori)
                    System.out.println(valore);
                System.out.println();
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}