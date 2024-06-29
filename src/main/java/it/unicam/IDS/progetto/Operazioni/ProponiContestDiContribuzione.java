package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Contenuti;
import it.unicam.IDS.progetto.Entita.ContestDiContribuzione;

import java.util.ArrayList;

public class ProponiContestDiContribuzione {

    public void PropostaContest(ArrayList<ContestDiContribuzione> listaContestDiContribuzione,
                                ContestDiContribuzione contestDiContribuzione, Contenuti contenuto) {
        if (contestDiContribuzione.getNomeContest().isEmpty())
            System.out.println("Il nome del contest di contribuzione non può essere nullo");
        if (listaContestDiContribuzione.contains(contestDiContribuzione))
            System.out.println("Il contest di contribuzione esiste già");
        ControlliContenuto(contestDiContribuzione,contenuto);
        contestDiContribuzione.getListaContenuti().add(contenuto);
        System.out.println("Il contenuto è stato aggiunto al contest di contribuzione");

    }
    private boolean ControlliContenuto(ContestDiContribuzione contestDiContribuzione,Contenuti contenuto){
        if(contenuto==null){
            System.out.println("Il contenuto non può essere nullo");
            return false;
        }
        if(contestDiContribuzione.getListaContenuti().contains(contestDiContribuzione)){
            System.out.println("Il contenuto proposto esiste già");
            return false;
        }
        return true;
    }
}
