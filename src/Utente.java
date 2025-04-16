package src;
import java.time.LocalDate;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utente {
    private String Nome, Cognome,Username, Password, Domicilio, Ruolo;
    private LocalDate DataDiNascita;

    public Utente(String Nome, String Cognome, String Username, String Password, String Domicilio, String Ruolo, LocalDate DataDiNascita) {
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.Username = Username;
        this.Password = Password;
        this.Domicilio = Domicilio;
        this.Ruolo = Ruolo;
        this.DataDiNascita = DataDiNascita;
        aggiungiAJson();
    }

    public String getNome() { return Nome; }

    public String getCognome() { return Cognome; }

    public String getUsername() { return Username; }

    public String getPassword() { return Password; }

    public String getDomicilio() { return Domicilio; }

    public String getRuolo() { return Ruolo; }

    public LocalDate getDataDiNascita() { return DataDiNascita; }

    private void aggiungiAJson() {
        try {
            String filePath = "TheKnife/utenti.json";
            JSONArray utentiArray;

            if (Files.exists(Paths.get(filePath))) {
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                utentiArray = new JSONArray(content);
            } else {
                utentiArray = new JSONArray();
            }

            JSONObject nuovoUtente = new JSONObject();
            nuovoUtente.put("Nome", Nome);
            nuovoUtente.put("Cognome", Cognome);
            nuovoUtente.put("Username", Username);
            nuovoUtente.put("Password", Password);
            nuovoUtente.put("Domicilio", Domicilio);
            nuovoUtente.put("Ruolo", Ruolo);
            nuovoUtente.put("DataDiNascita", DataDiNascita.toString());

            utentiArray.put(nuovoUtente);

            try (FileWriter file = new FileWriter(filePath)) {
                file.write(utentiArray.toString(4)); // Indent with 4 spaces for readability
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}