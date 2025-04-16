package src;

import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utente {
    private String Nome, Cognome, Username, Password, Domicilio, Ruolo;
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
        String filePath = "files/utenti.json";
        JSONArray utentiArray = new JSONArray();

        try {
            if (Files.exists(Paths.get(filePath))) {
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                if (!content.isBlank()) {
                    try {
                        JSONParser parser = new JSONParser();
                        utentiArray = (JSONArray) parser.parse(content);
                    } catch (ParseException e) {
                        // Se il file è corrotto o malformato, lo sovrascriviamo con un nuovo array
                        utentiArray = new JSONArray();
                    }
                }
            }

            JSONObject nuovoUtente = new JSONObject();
            nuovoUtente.put("Nome", Nome);
            nuovoUtente.put("Cognome", Cognome);
            nuovoUtente.put("Username", Username);
            nuovoUtente.put("Password", Password);
            nuovoUtente.put("Domicilio", Domicilio);
            nuovoUtente.put("Ruolo", Ruolo);
            nuovoUtente.put("DataDiNascita", DataDiNascita.toString());

            utentiArray.add(nuovoUtente);

            Files.createDirectories(Paths.get("files"));
            try (FileWriter file = new FileWriter(filePath)) {
                file.write(utentiArray.toJSONString().replace("},", "},\n"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}