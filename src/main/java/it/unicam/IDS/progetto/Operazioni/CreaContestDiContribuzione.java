package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.ContestDiContribuzione;

import java.util.ArrayList;

public class CreaContestDiContribuzione {

    public void AggiungiContest(ArrayList<ContestDiContribuzione> listaContestDiContribuzione,
                                ContestDiContribuzione contestDiContribuzione) {
        if (contestDiContribuzione.getNomeContest().isEmpty())
            System.out.println("Il nome del contest di contribuzione non può essere nullo");
        //TODO creare la eccezione e lanciarla
        if (listaContestDiContribuzione.contains(contestDiContribuzione))
            System.out.println("Il contest di contribuzione esiste già");
        //TODO creare la eccezione e lanciarla

        Controlli(listaContestDiContribuzione, contestDiContribuzione);
        ControlloTempo(contestDiContribuzione);
        ControlloTermineMassimo(contestDiContribuzione);

        listaContestDiContribuzione.add(contestDiContribuzione);
    }

    private boolean Controlli(ArrayList<ContestDiContribuzione> listaContestDiContribuzione,
                              ContestDiContribuzione contestDiContribuzione) {
        // controllo obiettivo
        if (contestDiContribuzione.getObiettivo().isEmpty()) {
            System.out.println("L'obiettivo del contest di contribuzione non può essere nullo ");
            return false;
        }
        if (ObiettivoEsiste(contestDiContribuzione, listaContestDiContribuzione)) {
            System.out.println("L'obiettivo del contest di contribuzione non può essere già presente in un contest di contribuzione ");
            return false;
        }

        //controllo tematica
        if (contestDiContribuzione.getTematica().isEmpty()) {
            System.out.println("La tematica del contest di contribuzione non può essere nulla");
            return false;
        }

        //controllo soglia inviti
        if (contestDiContribuzione.getSogliaInviti() == 0) {
            System.out.println("La soglia degli invitati non può essere nulla");
            return false;
        }
        return true;
    }

    private boolean ControlloTempo(ContestDiContribuzione contestDiContribuzione) {
        if (contestDiContribuzione.getTempoInizio() == (null) || contestDiContribuzione.getTempoFine() == (null)) {
            System.out.println("Il lasso di tempo del contest di contribuzione non può essere nullo");
            return false;
        }

        if (!contestDiContribuzione.getTempoInizio().isBefore(contestDiContribuzione.getTempoFine())) {
            System.out.println("Il tempo di inizio deve essere temporalmente prima del tempo di fine");
            return false;
        }

        if (contestDiContribuzione.getLimiteMassimoC() == null) {
            System.out.println("Il limite massimo di tempo del contest di contribuzione non può essere nullo");
            return false;
        }

        if (ControlloLassoDiTempo(contestDiContribuzione)) {
            System.out.println("Il limite massimo di aggiunta contenuti del contest non può essere al di fuori " +
                    "del lasso di tempo del contest di contribuzione");
            return false;
        }

        return true;
    }


    private boolean ControlloTermineMassimo(ContestDiContribuzione contestDiContribuzione) {
        //controllo termine massimo inviti spediti
        if (contestDiContribuzione.getTermineMassimoS().isEmpty()) {
            System.out.println("Il termine massimo di spedizione inviti non può essere nullo");
            return false;
        }

        //controllo termine massimo risposta agli inviti
        if (contestDiContribuzione.getTermineMassimoR().isEmpty()) {
            System.out.println("Il termine massimo di risposta agli inviti non può essere nullo");
            return false;
        }

        return true;
    }

    private boolean ObiettivoEsiste(ContestDiContribuzione contestDiContribuzione,
                                    ArrayList<ContestDiContribuzione> listaContestDiContribuzione) {
        for (ContestDiContribuzione o : listaContestDiContribuzione)
            if (o.getObiettivo().equals(contestDiContribuzione.getObiettivo()))
                return true;
        return false;
    }

    private boolean ControlloLassoDiTempo(ContestDiContribuzione contestDiContribuzione) {
        return !contestDiContribuzione.getTempoInizio().isBefore(contestDiContribuzione.getLimiteMassimoC()) &&
                !contestDiContribuzione.getTempoFine().isAfter(contestDiContribuzione.getLimiteMassimoC());
    }
}
