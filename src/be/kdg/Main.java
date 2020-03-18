package be.kdg;

import be.kdg.model.GebruikersLijst;
import be.kdg.model.Nonogram;
import be.kdg.model.Spel;
import be.kdg.view.start.StartPresenter;
import be.kdg.view.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) { Application.launch(args);}

    @Override
    public void start(Stage stage) throws Exception {
        GebruikersLijst model = new GebruikersLijst();
        StartView view = new StartView();
        StartPresenter presenter = new StartPresenter(model,view);

        stage.setTitle("Nonogram");
        stage.getIcons().add(new Image("favicon-32x32.png"));//Icontje van de applicatie
        stage.setWidth(900);
        stage.setHeight(800);
        stage.setScene(new Scene(view));
        stage.show();
    }
}
