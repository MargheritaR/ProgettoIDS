package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.ContTesto;
import it.unicam.IDS.progetto.Entita.Contenuti;

import java.util.ArrayList;
import java.util.Scanner;

public class ModificaContenuti {

    AggiuntaContenuti aggiuntaContenuti = new AggiuntaContenuti();

    EliminaContenuti eliminaContenuti = new EliminaContenuti();

    public void EditContenuti(ArrayList<Contenuti> listaContenuti, Contenuti contenuti) {
        Scanner scanner = new Scanner(System.in);
        //String contenutoScelto = Mostra(listaContenuti);
        System.out.println("Inserire che azione si vuole fare sulla lista dei Contenuti:" + '\n' +
                "Aggiungere" + '\n' +
                "Rimuovere");
        String azione = scanner.next();

        if (azione.equalsIgnoreCase("aggiungere")) {
            eliminaContenuti.RimuoviContenuto(listaContenuti, contenuti);
            aggiuntaContenuti.AddContenuti(listaContenuti, NuovoContenuto());
        } else {
            eliminaContenuti.RimuoviContenuto(listaContenuti, contenuti);
        }
    }

    private String Mostra(ArrayList<Contenuti> listaContenuti) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista dei contenuti");
        System.out.println(listaContenuti);

        System.out.println('\n' + "Quale contenuto vuoi modificare: (nome del contenuto)");
        String contenutoSelezionato = scanner.nextLine();

        return contenutoSelezionato;
    }

    private Contenuti NuovoContenuto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserire l'id del contenuto");
        String contId = scanner.next();
        System.out.println("Inserire il nome del contenuto");
        String contNome = scanner.next();
        System.out.println("Inserire il peso del contenuto");
        String contPeso = scanner.next();
        System.out.println("Inserire il formato del contenuto");
        String contFormato = scanner.next();

        Contenuti contM = new ContTesto(contId,contNome,Integer.parseInt(contPeso),contFormato);
        return contM;
    }
}
