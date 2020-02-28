package be.kdg;

import be.kdg.model.GebruikersLijst;
import be.kdg.model.Nonogram;
import be.kdg.model.Spel;
import be.kdg.view.start.StartPresenter;
import be.kdg.view.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
         /*
        Scanner keyboard = new Scanner(System.in);

        Nonogram test = new Nonogram(5);
        boolean isKlaar = false;
        int opdracht;
        int kolom = 0;
        int rij = 0;
        test.toonGrid();
        test.nonogram1_lijn();

        while (!isKlaar){
            System.out.println("Inkleuren(1) of aanduiden(2):");
            opdracht = keyboard.nextInt();
            if(opdracht == 1){
                System.out.println("Geef een coördinaat in (kolom,rij):");
                kolom = keyboard.nextInt();
                rij = keyboard.nextInt();
                System.out.println(test.kleurIn(rij,kolom));
            }
            else {
                System.out.println("Geef een coördinaat in (kolom,rij):");
                kolom = keyboard.nextInt();
                rij = keyboard.nextInt();
                test.duidAan(rij,kolom);
            }

            if(test.controlleren()){
                isKlaar= true;
            }
        }


        System.out.println(test.kleurIn(1, 5));

        System.out.println(test.kleurIn(1, 3));

        System.out.println(test.kleurIn(3, 5));

        test.duidAan(1, 2);
        test.duidAan(1, 5);

         */

    }

    @Override
    public void start(Stage stage) throws Exception {
        GebruikersLijst model = new GebruikersLijst();
        StartView view = new StartView();
        StartPresenter presenter = new StartPresenter(model,view);

        stage.setTitle("Nonogram startscherm");
        stage.setWidth(900);
        stage.setHeight(800);
        stage.setScene(new Scene(view));
        stage.show();
    }
}
