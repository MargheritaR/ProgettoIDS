package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.ContestDiContribuzione;
import java.util.ArrayList;

public class EliminaContestDiContribuzione {

    public void RimuoviContest(ArrayList<ContestDiContribuzione> listaContestDiContribiuzione, ContestDiContribuzione contestDiContribuzione) {
        if (contestDiContribuzione.getNomeContest().isEmpty())
            System.out.println("Il nome del contest di contribuzione non può essere nullo");
        if (!listaContestDiContribiuzione.contains(contestDiContribuzione))
            System.out.println("Il nome del contest di contribuzione non esiste");

        listaContestDiContribiuzione.remove(contestDiContribuzione);
    }
}
