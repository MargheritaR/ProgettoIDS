package it.unicam.IDS.progetto.Operazioni;
import it.unicam.IDS.progetto.Entita.Comune;
import it.unicam.IDS.progetto.Entita.Coordinate;

import java.util.ArrayList;
import java.util.Scanner;

public class ModificaComune {
    Scanner scanner = new Scanner(System.in);


    public void EditComune(ArrayList<Comune> listaComuni, Comune comune) {
        if (comune == null)
            System.out.println("Il nome del comune non può essere nullo");
        //TODO creare la eccezione e lanciarla
        if (listaComuni.contains(comune))
            System.out.println("Il comune esiste già");
        //TODO creare la eccezione e lanciarla
        System.out.println("Cosa vuoi modificare?" + '\n' + "nome" + '\n' + "coordinate");
        String appoggio = scanner.nextLine();

        if (appoggio.equalsIgnoreCase("nome")) {
            System.out.println("Inserire il nuovo nome del comune:");
            comune.setNomeComune(scanner.nextLine());
        } else {
            System.out.println("Inserire l'asse X del comune");
            int asseX = scanner.nextInt();
            System.out.println("Inserire l'asse Y del comune");
            int asseY = scanner.nextInt();
            Coordinate coordinate = new Coordinate(asseX,asseY);
            System.out.println("Inserire le nuove coordinate del comune");
            comune.setCoordinate(coordinate);
        }

    }
}