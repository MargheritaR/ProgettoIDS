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

    public String getIdContenuto() {
        return idContenuto;
    }

    public String getNomeContenuto() {
        return nomeContenuto;
    }

    public int getPeso() {
        return peso;
    }

    public void setIdContenuto(String idContenuto) {
        this.idContenuto = idContenuto;
    }

    public void setNomeContenuto(String nomeContenuto) {
        this.nomeContenuto = nomeContenuto;
    }

    public void setPeso(int peso) {
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
