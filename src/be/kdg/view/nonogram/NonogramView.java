package be.kdg.view.nonogram;

import be.kdg.model.Nonogram;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class NonogramView extends BorderPane {
    private Label lblTitel;
    private Button btnBack;

    private Button btnScorenboard;
    private Button btnSpelregels;
    private HBox buttonBox;

    private GridPane nonogramGrid;
    private ArrayList<ArrayList<Label>> nonogram;//niet zeker of deze lijst nodig is

    private GridPane rijGrid;
    private GridPane kolomGrid;
    private GridPane centerGrid;
    private Nonogram model;

    public NonogramView(Nonogram nonogram){
        this.initialiseNodes();
        this.layoutNodes();
        this.model = nonogram;

        this.initialiseGrid(nonogram);
        this.layoutGrid(nonogram.getGrootte());
        this.initialiseUserGrid();
    }

    private void initialiseNodes() {
        this.lblTitel = new Label();//Tekst wordt ingeladen in de presenter

        Image backButton = new Image("foto/backbutton.png");
        ImageView imageView = new ImageView(backButton);
        this.btnBack = new Button("",imageView);

        this.btnScorenboard = new Button("Scoreboard");
        this.btnSpelregels = new Button("Spelregels");
        //de buttons moet genoeg afstand hebben van de rand
        this.buttonBox = new HBox();


        this.nonogramGrid = new GridPane();
        this.rijGrid = new GridPane();
        this.kolomGrid = new GridPane();
        this.centerGrid = new GridPane();
        this.nonogram = new ArrayList<ArrayList<Label>>();
    }

    private void layoutNodes() {
        this.setTop(lblTitel);
        this.setLeft(btnBack);

        centerGrid.add(new Label(),0,0);
        centerGrid.add(rijGrid,0,1);
        centerGrid.add(kolomGrid,1,0);
        centerGrid.add(nonogramGrid,1,1);

        this.setCenter(centerGrid);

        buttonBox.getChildren().add(btnSpelregels);
        buttonBox.getChildren().add(btnScorenboard);
        this.setBottom(buttonBox);

        lblTitel.setStyle("-fx-font-size: 36");
        BorderPane.setAlignment(lblTitel, Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        btnScorenboard.setStyle("-fx-font-size: 24");
        btnSpelregels.setStyle("-fx-font-size: 24");

        nonogramGrid.setGridLinesVisible(true);
        rijGrid.setGridLinesVisible(true);
        kolomGrid.setGridLinesVisible(true);
    }

    private void initialiseGrid(Nonogram viewNon){
        int grote = viewNon.getGrootte();
        for(int rij = 0; rij<grote; rij++){
            ArrayList<Label> kolomLijst = new ArrayList<>();
            for(int kolom = 0; kolom<grote; kolom++){
                Label lbl = new Label("");
                lbl.setMinWidth(100);
                lbl.setMinHeight(100);
                kolomLijst.add(lbl);
                nonogramGrid.add(lbl,kolom,rij);//Labels aan de grid toeveogen
            }
            nonogram.add(kolomLijst);//Labels in de lijst plaatsen

        }

        //Labels aan de grid toevoegen
        for(int rij =0; rij<grote; rij++){
            rijGrid.add(new Label(viewNon.getRij()[rij]),0,rij);
        }

        //Labels aan de grid toevoegen
        for(int kolom =0; kolom<grote; kolom++){
            kolomGrid.add(new Label(viewNon.getKolom()[kolom]),kolom,0);
        }
    }

    private void layoutGrid(int grote){//De vakjes in de grid een hoogte en breedte geven
        //liquide maken => grootte 5=100,6-9=75px 10=50px
        int liquide = 0;
        if(grote<=5){
            liquide=100;
        }else if(grote <= 9){
            liquide=75;
        } else {
            liquide=50;
        }

        for(int kolom = 1; kolom<= grote; kolom++){
            nonogramGrid.getColumnConstraints().add(new ColumnConstraints(liquide));
        }
        for(int rij = 1; rij<= grote; rij++){
            nonogramGrid.getRowConstraints().add(new RowConstraints(liquide));
        }

        for(int kolom = 1; kolom<= grote; kolom++){
            kolomGrid.getColumnConstraints().add(new ColumnConstraints(liquide));
        }
        kolomGrid.getRowConstraints().add(new RowConstraints(liquide));

        rijGrid.getColumnConstraints().add(new ColumnConstraints(liquide));
        for(int rij = 1; rij<= grote; rij++){
            rijGrid.getRowConstraints().add(new RowConstraints(liquide));
        }

        for(int rij = 0; rij<grote; rij++){
            ArrayList<Label> kolomLijst = new ArrayList<>();
            for(int kolom = 0; kolom<grote; kolom++){
                Label lbl = nonogram.get(rij).get(kolom);
                lbl.setMinWidth(liquide);
                lbl.setMinHeight(liquide);
            }
        }
    }

    private void initialiseUserGrid(){//De reeds ingekleurde vakjes van de gebruiker inladen
        for(int rij = 0; rij< model.getGrootte(); rij++){
            ArrayList<Label> kolomlijst = new ArrayList<>();
            kolomlijst = nonogram.get(rij);
            for(int kolom = 0; kolom< model.getGrootte(); kolom++){

                if(model.getPatroon()[rij][kolom].getWaarde().equals("O")){
                    kolomlijst.get(kolom).setStyle("-fx-background-color: black");
                }else if(model.getPatroon()[rij][kolom].getWaarde().equals("+")){
                    kolomlijst.get(kolom).setStyle("-fx-background-color: grey");
                }else {
                    kolomlijst.get(kolom).setStyle("-fx-background-color: none");
                }
            }

        }
    }

    public Label getLblTitel() {
        return lblTitel;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Button getBtnScorenboard() {
        return btnScorenboard;
    }

    public Button getBtnSpelregels() {
        return btnSpelregels;
    }

    public ArrayList<ArrayList<Label>> getNonogram() {
        return nonogram;
    }
}
