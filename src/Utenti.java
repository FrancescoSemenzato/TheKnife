package src;
import java.time.LocalDate;

public class Utenti {
    private String Nome, Cognome,Username, Password, Domicilio, Ruolo;
    private LocalDate DataDiNascita;

    public Utenti(String Nome, String Cognome, String Username, String Password, String Domicilio, String Ruolo, LocalDate DataDiNascita) {
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.Username = Username;
        this.Password = Password;
        this.Domicilio = Domicilio;
        this.Ruolo = Ruolo;
        this.DataDiNascita = DataDiNascita;
    }

    public String getNome() { return Nome; }

    public String getCognome() { return Cognome; }

    public String getUsername() { return Username; }

    public String getPassword() { return Password; }

    public String getDomicilio() { return Domicilio; }

    public String getRuolo() { return Ruolo; }

    public LocalDate getDataDiNascita() { return DataDiNascita; }
}