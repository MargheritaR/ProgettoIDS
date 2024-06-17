package it.unicam.IDS.progetto.Entita;

import java.util.List;

public class StatoPending implements Component{

    //TODO implementare component in Punto Interesse, Itinerario, Contenuti, Contest di Contribuzione
    private List<Component> statoPending;

    public List<Component> getStatoPending() {
        return statoPending;
    }

    public void setStatoPending(List statoPending) {
        this.statoPending = statoPending;
    }
}
