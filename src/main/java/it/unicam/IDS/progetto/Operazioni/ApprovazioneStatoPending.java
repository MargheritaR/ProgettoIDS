package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Contenuti;
import it.unicam.IDS.progetto.Entita.ContestDiContribuzione;
import it.unicam.IDS.progetto.Entita.Itinerario;
import it.unicam.IDS.progetto.Entita.PuntoInteresse;

import java.util.ArrayList;
import java.util.Scanner;

public class ApprovazioneStatoPending<T> {

    Scanner scanner = new Scanner(System.in);

    public void Approvazione(ArrayList<T> statoPending, ArrayList<PuntoInteresse> listaPDI, PuntoInteresse pdiScelto) {
        System.out.println("" + pdiScelto + '\n' + '\n' + "Vuoi approvare il punto di interesse:  (Y/N)");
        if (scanner.nextLine().equals("Y")) {
            InserimentoPDI appoggio = new InserimentoPDI();
            appoggio.AggiungiPDI(listaPDI, pdiScelto);
            System.out.println("Il punto di interesse è stato approvato");
        } else {
            statoPending.remove(pdiScelto);
            System.out.println("Il punto di interesse non è stato approvato quindi è stato eliminato");
        }
    }

//    public void Approvazione(ArrayList<T> statoPending, ArrayList<Contenuti> listaContenuti, Contenuti contenutoScelto){
//        System.out.println("" + contenutoScelto + '\n' + '\n' + "Vuoi approvare il contenuto:  (Y/N)");
//        if (scanner.nextLine().equals("Y")) {
//            AggiuntaContenuti appoggio = new AggiuntaContenuti();
//            appoggio.AddContenuti(listaContenuti, contenutoScelto);
//            System.out.println("Il contenuto è stato approvato");
//        } else {
//            statoPending.remove(contenutoScelto);
//            System.out.println("Il contenuto non è stato approvato quindi è stato eliminato");
//        }
//    }

    public void Approvazione(ArrayList<T> statoPending, ArrayList<Itinerario> listaItinerari, Itinerario itinerarioScelto){
        System.out.println("" + itinerarioScelto + '\n' + '\n' + "Vuoi approvare l'itinerario:  (Y/N)");
        if (scanner.nextLine().equals("Y")) {
            CreaItinerario appoggio = new CreaItinerario();
            appoggio.AggiungiItinerario(listaItinerari, itinerarioScelto);
            System.out.println("L'itinerario è stato approvato");
        } else {
            statoPending.remove(itinerarioScelto);
            System.out.println("L'itinerario non è stato approvato quindi è stato eliminato");
        }
    }

    public void Approvazione(ArrayList<T> statoPending, ArrayList<ContestDiContribuzione> listaContestDiContribuzione,
                             ContestDiContribuzione contestScelto){
        System.out.println("" + contestScelto + '\n' + '\n' + "Vuoi approvare il contest di contribuzione:  (Y/N)");
        if (scanner.nextLine().equals("Y")) {
            CreaContestDiContribuzione appoggio = new CreaContestDiContribuzione();
            appoggio.AggiungiContest(listaContestDiContribuzione, contestScelto);
            System.out.println("Il contest di contribuzione è stato approvato");
        } else {
            statoPending.remove(contestScelto);
            System.out.println("Il contest di contribuzione non è stato approvato quindi è stato eliminato");
        }
    }

}
