package it.unicam.IDS.progetto.Entita;

public class Foto {

    private String codiceFoto;

    private String formato;

    public Foto(String codiceFoto, String formato) {
        this.codiceFoto = codiceFoto;
        this.formato = formato;
    }

    public String getCodiceFoto() {
        return codiceFoto;
    }

    public void setCodiceFoto(String codiceFoto) {
        this.codiceFoto = codiceFoto;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "codiceFoto: " + codiceFoto + '\'' +
                ", formato:" + formato + '\'' +
                '}';
    }
}
