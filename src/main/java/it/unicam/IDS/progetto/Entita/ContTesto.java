package it.unicam.IDS.progetto.Entita;

public class ContTesto extends Contenuti{

    private String formato;


    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public ContTesto(String idContenuto, String nomeContenuto, int peso, String formato) {
        super(idContenuto, nomeContenuto, peso);
        this.formato = formato;
    }

    @Override
    public String toString() {
        return "ContTesto{" +
                "formato='" + formato + '\'' +
                '}';
    }
}
