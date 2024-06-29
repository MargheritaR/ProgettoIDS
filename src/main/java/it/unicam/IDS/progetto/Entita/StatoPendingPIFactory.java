package it.unicam.IDS.progetto.Entita;

public class StatoPendingPIFactory implements IStatoPendingFactory{
    @Override
    public StatoPending newStatoPending(String ruolo) {
        StatoPending statoPending;
        if (ruolo.equalsIgnoreCase("ROLE_CONTRIBUTORI"))
            statoPending = new StatoPendingItinerario();
        else statoPending = new Itinerario();
        return statoPending;
    }
}
