package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Contenuti;
import it.unicam.IDS.progetto.Entita.ContestDiContribuzione;
import it.unicam.IDS.progetto.Entita.Messaggio;

import java.util.ArrayList;
import java.util.Scanner;

public class ValidazioneContenutiContest {

    Scanner scanner = new Scanner(System.in);

    String continua = "Y";

    public void ValidaContenutiContest(ArrayList<ContestDiContribuzione> listaContestDiContribuzione,
                                       ContestDiContribuzione contestDiContribuzione) {
       Controlli(listaContestDiContribuzione,contestDiContribuzione);

       System.out.println("Lista dei contest di contribuzione: " + listaContestDiContribuzione);
        while (continua.equalsIgnoreCase("Y")) {
            System.out.println("Lista dei contenuti proposti per il contest di contribuzione:" +
                    contestDiContribuzione.getListaContenuti());

            Contenuti contenutoScelto = Scegli(contestDiContribuzione);
            System.out.println("Vuoi approvare il contenuto proposto?" + '\n' + "Approvare" + '\n' + "Non approvare");
            String appoggio = scanner.nextLine();
            if (appoggio.equalsIgnoreCase("Approvare"))
                AggiungereCont(contestDiContribuzione, contenutoScelto);
            else
                RimuovereCont(contestDiContribuzione, contenutoScelto);

            System.out.println("Vuoi approvare altri contenuti: (Y/N)");
            continua = scanner.nextLine();
        }
    }

    private void AggiungereCont(ContestDiContribuzione contestDiContribuzione, Contenuti contenutoScelto) {
        ArrayList<Contenuti> newListaContenuti = new ArrayList<>(contestDiContribuzione.getListaContenuti());
        ArrayList<Contenuti> newListaContenutiApprovazione = new ArrayList<>(contestDiContribuzione.getContenutiApprovati());
        newListaContenuti.remove(contenutoScelto);
        newListaContenutiApprovazione.add(contenutoScelto);
        contestDiContribuzione.setContenutiApprovati(newListaContenutiApprovazione);
        String intestazione = "Il Contenuto scelto è stato approvato e aggiunto al contest di contribuzione";
        Messaggio messaggio = new Messaggio("Daniele", "Salvatore","Convalida",intestazione);

    }

    private void RimuovereCont(ContestDiContribuzione contestDiContribuzione, Contenuti contenutoScelto) {
        ArrayList<Contenuti> newListaContenuti = new ArrayList<>(contestDiContribuzione.getListaContenuti());
        newListaContenuti.remove(contenutoScelto);
        contestDiContribuzione.setListaContenuti(newListaContenuti);
    }

    private Contenuti FindIndex(ArrayList<Contenuti> listaContenuti, String contenuti) {
        for (int i = 0; i < listaContenuti.size(); i++)
            if (listaContenuti.get(i).getNomeContenuto().equals(contenuti))
                return listaContenuti.get(i);
        return null;
    }

    private boolean Controlli(ArrayList<ContestDiContribuzione> listaContestDiContribuzione,
                              ContestDiContribuzione contestDiContribuzione) {
        if (contestDiContribuzione == null) {
            System.out.println("Il nome del contest di contribuzione non può essere nullo");
            return false;
        }

        if (listaContestDiContribuzione.contains(contestDiContribuzione)) {
            System.out.println("Il contest di contribuzione esiste già");
            return false;
        }

        return true;
    }

    private Contenuti Scegli(ContestDiContribuzione contestDiContribuzione) {
        System.out.println("Scegli il contenuto proposto per il contest di contribuzione:");
        String scegli = scanner.nextLine();

        if (scegli.isEmpty())
            System.out.println("Il nome del contenuto scelto è vuoto");

        Contenuti contenutoScelto = FindIndex(contestDiContribuzione.getListaContenuti(), scegli);
        if (contenutoScelto == null)
            System.out.println("Il contenuto scelto non esiste sulla lista dei contenuti proposti");
        return contenutoScelto;
    }
}
