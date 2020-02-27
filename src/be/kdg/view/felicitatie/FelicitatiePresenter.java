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
                NonogramView nonogramView = new NonogramView();
                NonogramPresenter nonogramPresenter = new NonogramPresenter(model,nonogramView,gebruikersnaam);

                Stage stage = new Stage();
                stage.setTitle("Nonogram speelscherm");
                stage.initOwner(view.getScene().getWindow());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(nonogramView));
                nonogramPresenter.addWindowEventHandlers();
                stage.setWidth(900);
                stage.setHeight(800);
                stage.showAndWait();
            }
        });
        view.getBtnScorenboard().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ScorenboardView scorenboardView = new ScorenboardView();
                ScorenboardPresenter scorenboardPresenter = new ScorenboardPresenter(model,scorenboardView);

                Stage stage = new Stage();
                stage.setTitle("Nonogram scorenboard");
                stage.initOwner(view.getScene().getWindow());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(scorenboardView));
                scorenboardPresenter.addWindowEventHandlers();
                stage.setWidth(900);
                stage.setHeight(800);
                stage.showAndWait();
            }
        });
        view.getBtnBeginScherm().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartView startView = new StartView();
                StartPresenter startPresenter = new StartPresenter(model, startView);

                Stage stage = new Stage();
                stage.setTitle("Nonogram startscherm");
                stage.initOwner(view.getScene().getWindow());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(startView));
                stage.setWidth(900);
                stage.setHeight(800);
                stage.showAndWait();
            }
        });
    }

    public  void addWindowEventHandlers(){
        view.getScene().getWindow().setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                //view.getLblFoto() de juiste foto toevoegen aan de label aan de hand van de level van de user
            }
        });
    }
}
