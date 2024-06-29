package it.unicam.IDS.progetto.Entita;

public class StatoPendingPDIFactory implements IStatoPendingFactory {

    @Override
    public StatoPending newStatoPending(String ruolo) {
        StatoPending statoPending;
        if (ruolo.equalsIgnoreCase("ROLE_CONTRIBUTORI"))
            statoPending = new StatoPendingPuntoInteresse();
        else statoPending = new PuntoInteresse();
        return statoPending;
    }
}
