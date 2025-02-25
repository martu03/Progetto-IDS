package cs.unicam.it.Accesso;

import cs.unicam.it.Mappa.Geolocalizzazione;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// DTO per richiesta di registrazione
class RegistrationRequest {
    @NotBlank(message = "Il nome è obbligatorio.")
    private String nome;

    @NotBlank(message = "L'email è obbligatoria.")
    @Email(message = "L'email deve essere valida.")
    private String email;

    @NotBlank(message = "La password è obbligatoria.")
    private String password;
    private String role; // Ruolo dell'utente (es. ACQUISTENTE, ANIMATORE, ecc.)
    private Geolocalizzazione indirizzo; // Solo per Acquirente

    public Geolocalizzazione getSede() {
        return sede;
    }

    public void setSede(Geolocalizzazione sede) {
        this.sede = sede;
    }

    public Geolocalizzazione getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Geolocalizzazione indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private Geolocalizzazione sede; // Solo per Azienda

    public String getNome() { return nome; }

    }