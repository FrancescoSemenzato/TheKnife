import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String path = "FileProva.csv";
        String riga;
        int j=0;

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