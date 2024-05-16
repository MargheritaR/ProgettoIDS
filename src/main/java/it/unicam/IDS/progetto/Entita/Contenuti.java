package it.unicam.IDS.progetto.Entita;

public abstract class Contenuti {

    private String idContenuto;

    private String nomeContenuto;

    private int peso;

    public Contenuti(String idContenuto, String nomeContenuto, int peso) {
        this.idContenuto = idContenuto;
        this.nomeContenuto = nomeContenuto;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Contenuti{" +
                "idContenuto='" + idContenuto + '\'' +
                ", nomeContenuto='" + nomeContenuto + '\'' +
                ", peso=" + peso +
                '}';
    }
}
