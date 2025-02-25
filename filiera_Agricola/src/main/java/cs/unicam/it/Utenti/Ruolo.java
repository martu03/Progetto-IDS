package cs.unicam.it.Utenti;

public enum Ruolo {

    GESTORE,
    CURATORE,
    ANIMATORE,
    ACQUIRENTE,
    AZIENDA,
    PRODUTTORE,
    TRASFORMATORE,
    DISTRIBUTORE;

    public static Ruolo fromAuthority(String authority) {
        try {
            return Ruolo.valueOf(authority);
        } catch (IllegalArgumentException e) {
            return null; // Ruolo non valido
        }
    }
}