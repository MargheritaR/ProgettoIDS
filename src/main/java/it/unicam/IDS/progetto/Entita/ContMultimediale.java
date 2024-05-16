package it.unicam.IDS.progetto.Entita;

public class ContMultimediale extends Contenuti{

    private String formato;


    //TODO guardare come si fa il costruttore con una superclasse

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public ContMultimediale(String idContenuto, String nomeContenuto, int peso, String formato) {
        super(idContenuto, nomeContenuto, peso);
        this.formato = formato;
    }

    @Override
    public String toString() {
        return "ContMultimediale{" +
                "formato='" + formato + '\'' +
                '}';
    }
}
