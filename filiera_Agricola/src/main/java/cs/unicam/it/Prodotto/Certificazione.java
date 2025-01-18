package cs.unicam.it.Prodotto;

public enum Certificazione {

    DOP("DOP"),
    IGP("IGP"),
    BIO("BIO"),
    KM_ZERO("Km zero");

    private final String displayCertification;

    Certificazione(String displayCertification) {
        this.displayCertification = displayCertification;
    }

    public String getCertificationName() {
        return displayCertification;
    }

}
