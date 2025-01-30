package cs.unicam.it.Prodotto;

// Enumerazione delle possibili certificazioni di un prodotto
public enum Certificazione {

    DOP("DOP"),
    IGP("IGP"),
    BIO("BIO"),
    KM_ZERO("Km zero");

    private final String displayCertificazione;

    Certificazione(String displayCertificazione) {
        this.displayCertificazione = displayCertificazione;
    }

    public String getCertificationName() {
        return displayCertificazione;
    }

}
