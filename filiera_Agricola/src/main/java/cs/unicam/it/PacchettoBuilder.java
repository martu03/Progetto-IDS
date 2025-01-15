package cs.unicam.it;

class PacchettoBuilder {
    private ProdottoPacchetto pacchetto;

    public PacchettoBuilder(String nomePacchetto) {
        this.pacchetto = new ProdottoPacchetto(nomePacchetto);
    }

    public PacchettoBuilder aggiungiProdotto(ComponenteProdotto prodotto) {
        pacchetto.aggiungiComponente(prodotto);
        return this;
    }

    public ProdottoPacchetto build() {
        return pacchetto;
    }
}
