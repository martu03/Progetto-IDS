package cs.unicam.it;

// Classe per rappresentare un prodotto singolo
class ProdottoSingolo implements ComponenteProdotto {
    private String nome;
    private double prezzo;
    private boolean certificato;

    public ProdottoSingolo(String nome, double prezzo, boolean certificato) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.certificato = certificato;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Prodotto: " + nome + ", Prezzo: " + prezzo + ", Certificato: " + certificato);
    }

    @Override
    public double getPrezzo() {
        return prezzo;
    }
    //per gli sconti
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    // validare per tutto: il curatore se accetta viene caricato nel marketplace, se rifiuta viene eliminato, il false rappresenta
    // che il prodotto non è stato verificato o è in attesa
    public boolean isCertificato() {
        return certificato;
    }

    public void setCertificato(boolean certificato) {
        this.certificato = certificato;
    }

    @Override
    public String toString() {
        return "ProdottoSingolo{" +
                "nome='" + nome + '\'' +
                ", prezzo=" + prezzo +
                ", certificato=" + certificato +
                '}';
    }
}
