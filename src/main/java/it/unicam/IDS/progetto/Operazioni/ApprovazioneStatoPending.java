package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.PuntoInteresse;

import java.util.ArrayList;
import java.util.Scanner;

public class ApprovazioneStatoPending {

    public void Approvazione(ArrayList<PuntoInteresse> statoPending, ArrayList<PuntoInteresse> listaPDI) {
        //TODO gestire lo stato di pending anche per i contenuti
        Scanner scanner = new Scanner(System.in);
        String contenutoScelto = Mostra(statoPending);

        System.out.println('\n' + "Il punto di interesse/contenuto scelto: ");
        int index = FindIndex(statoPending,contenutoScelto);
        if (index == -1)
            System.out.println("eccezione");

        System.out.println("" + statoPending.get(index) + '\n' + '\n' + "Vuoi approvare il contenuto:  (Y/N)");
        String approvazione = scanner.nextLine();

        if (approvazione.equals("Y")) {
            InserimentoPDI appoggio = new InserimentoPDI();
            appoggio.AggiungiPDI(listaPDI, statoPending.get(index));
            System.out.println("Il punto di interesse/contenuto è stato approvato");
        } else {
            statoPending.remove(statoPending.get(index));
            System.out.println("Il punto di interesse/contenuto non è stato approvato quindi è stato eliminato");
        }
    }

    // stampa tutti i punti di interesse/contenuti
    private String Mostra(ArrayList<PuntoInteresse> statoPending) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista dei punti di interesse/contentuti da approvare");
        System.out.println(statoPending);

        System.out.println('\n' + "Quale punto di interesse/contenuto vuoi approvare: (nome del pdi o contenuto)");
        String contenutoScelto = scanner.nextLine();

        return contenutoScelto;
    }


    // metodo per trovare l'indice del punto di interesse se non trova l'indice ritorna -1
    private int FindIndex(ArrayList<PuntoInteresse> statoPending, String contenutoScelto) {
        for (int i = 0; i < statoPending.size(); i++)
            if (statoPending.get(i).getNomePDI().equals(contenutoScelto))
                return i;
        return -1;
    }


}
