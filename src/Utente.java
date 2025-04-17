package src;

import java.time.LocalDate;

import javax.xml.crypto.Data;

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
        this.Password = CifraPassword(Password);
        this.Domicilio = Domicilio;
        this.Ruolo = Ruolo;
        this.DataDiNascita = DataDiNascita;
        aggiungiAJson();
    }

    public String getNome() { return Nome; }
    public void setNome(String newNome) {Nome = newNome;}

    public String getCognome() { return Cognome; }
    public void setCognome(String newCognome) {Cognome = newCognome;}

    public String getUsername() { return Username; }
    public void setUsername(String newUsername) {Username = newUsername;}

    public String getPassword() { return DecifraPassword(Password);}
    public void setPassword(String newPassword){
        Password = CifraPassword(newPassword);
        //c'è da fare la ricerca per sostituire la password
    }

    public String getDomicilio() { return Domicilio;}
    public void setDomicilio(String newDomicilio){ Domicilio = newDomicilio;}

    public String getRuolo() { return Ruolo; }
    public void setRuolo(String newRuolo){ Ruolo = newRuolo;}

    public LocalDate getDataDiNascita() { return DataDiNascita; }
    public void setDataDiNascita(LocalDate newDataDiNascita){ DataDiNascita = newDataDiNascita;}

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

    private static String CifraPassword(String psw) {
        String pswCifrata = "";
    
        for (int i = 0; i < psw.length(); i++) {
            char c = psw.charAt(i);
    
            if (Character.isAlphabetic(c)) {
                // Se vocale
                if ("AEIOUaeiou".indexOf(c) != -1)
                    pswCifrata += (char) (c + 3);
                else
                    pswCifrata += (char) (c + 7);
            } 
            else if (Character.isDigit(c)) {
                int valore = Character.getNumericValue(c);
                if (valore % 2 == 0) {
                    valore = (valore + 10 - 3) % 10;
                } else {
                    valore = (valore + 10 - 7) % 10;
                }
                pswCifrata += (char) ('0' + valore);
            } 
            else {
                pswCifrata += (char) (c + 1);
            }
        }
        return pswCifrata;
    }

    private static String DecifraPassword(String pswCifrata) {
        String pswDecifrata = "";
    
        for (int i = 0; i < pswCifrata.length(); i++) {
            char c = pswCifrata.charAt(i);
    
            if (Character.isAlphabetic(c)) {
                if ("AEIOUaeiou".indexOf((char)(c - 3)) != -1)
                    pswDecifrata += (char) (c - 3);
                else
                    pswDecifrata += (char) (c - 7);
            } 
            else if (Character.isDigit(c)) {
                int valore = Character.getNumericValue(c);
                // Per decifrare devo invertire il modulo di prima
                if ((valore + 3) % 2 == 0) {
                    valore = (valore + 3) % 10;
                } else {
                    valore = (valore + 7) % 10;
                }
                pswDecifrata += (char) ('0' + valore);
            } 
            else {
                pswDecifrata += (char) (c - 1);
            }
        }
    
        return pswDecifrata;
    }
}