package be.kdg.view.felicitatie;

import be.kdg.model.GebruikersLijst;
import be.kdg.view.nonogram.NonogramPresenter;
import be.kdg.view.nonogram.NonogramView;
import be.kdg.view.scorenboard.ScorenboardPresenter;
import be.kdg.view.scorenboard.ScorenboardView;
import be.kdg.view.start.StartPresenter;
import be.kdg.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FelicitatiePresenter {
    private GebruikersLijst model;
    private FelicitatieView view;
    private String gebruikersnaam;

    public FelicitatiePresenter(GebruikersLijst model, FelicitatieView view, String Gebruikersnaam){
        this.model = model;
        this.view = view;
        this.gebruikersnaam = Gebruikersnaam;

        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnNieuwLevel().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                NonogramView nonogramView = new NonogramView(model.getGebruiker(gebruikersnaam).getOpgeslagenSpel().startSpel(model.getGebruiker(gebruikersnaam)));
                NonogramPresenter nonogramPresenter = new NonogramPresenter(model,nonogramView,gebruikersnaam);

                view.getScene().setRoot(nonogramView);
                nonogramView.getScene().getWindow().sizeToScene();
                nonogramView.getScene().getWindow().setHeight(800);
                nonogramView.getScene().getWindow().setWidth(900);
            }
        });
        view.getBtnScorenboard().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ScorenboardView scorenboardView = new ScorenboardView();
                ScorenboardPresenter scorenboardPresenter = new ScorenboardPresenter(model,scorenboardView);

                view.getScene().setRoot(scorenboardView);
                scorenboardView.getScene().getWindow().sizeToScene();
                scorenboardView.getScene().getWindow().setHeight(800);
                scorenboardView.getScene().getWindow().setWidth(900);
            }
        });
        view.getBtnBeginScherm().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartView startView = new StartView();
                StartPresenter startPresenter = new StartPresenter(model, startView);

                view.getScene().setRoot(startView);
                startView.getScene().getWindow().sizeToScene();
                startView.getScene().getWindow().setHeight(800);
                startView.getScene().getWindow().setWidth(900);
            }
        });
    }
}
