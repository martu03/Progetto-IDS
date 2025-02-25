package cs.unicam.it.Utenti;

public enum Ruolo {

    ACQUIRENTE,
    ANIMATORE,
    CURATORE,
    DISTRIBUTORE,
    GESTORE,
    PRODUTTORE,
    TRASFORMATORE,
    AZIENDA;

    public static Ruolo fromAuthority(String authority) {
        try {
            return Ruolo.valueOf(authority);
        } catch (IllegalArgumentException e) {
            return null; // Ruolo non valido
        }
    }
}