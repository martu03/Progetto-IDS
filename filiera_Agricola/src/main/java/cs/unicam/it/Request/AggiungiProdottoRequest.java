package cs.unicam.it.Request;

public class AggiungiProdottoRequest {

    private int idProdotto;
    private int quantita;

    // Getters e Setters
    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
