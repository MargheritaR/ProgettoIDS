package it.unicam.IDS.progetto.Entita;

import java.util.List;

// Composite
public class StatoPending implements Component{

    private List<Component> statoPending;

    public List<Component> getStatoPending() {
        return statoPending;
    }

    public void setStatoPending(List statoPending) {
        this.statoPending = statoPending;
    }
}
