package be.kdg.view.nonogram;

import be.kdg.model.GebruikersLijst;
import be.kdg.model.Nonogram;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
    }

    private void initialiseNodes() {
        this.lblTitel = new Label();
        //waarde geven via de model in de presenter
        //Het label moet gecentreerd worden, een solid border hebben en groot genoeg zijn

        Image backButton = new Image("./backbutton.png");
        ImageView imageView = new ImageView(backButton);
        this.btnBack = new Button("",imageView);
        //kleiner en links vanboven gecentreerd

        this.btnScorenboard = new Button("Scorenboard");
        this.btnSpelregels = new Button("Spelregels");
        //de buttons moet genoeg afstand hebben van de rand
        this.buttonBox = new HBox();


        //!!!! AFWERKEN
        this.nonogramGrid = new GridPane();
        nonogramGrid.setGridLinesVisible(true);

        this.rijGrid = new GridPane();
        rijGrid.setGridLinesVisible(true);

        this.kolomGrid = new GridPane();
        kolomGrid.setGridLinesVisible(true);

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
    }

    private void initialiseGrid(Nonogram viewNon){
        int grote = viewNon.getGrootte();
        for(int rij = 1; rij<= grote; rij++){
            ArrayList<Label> kolomLijst = new ArrayList<>();
            for(int kolom = 1; kolom<= grote; kolom++){
                Label lbl = new Label("");
                lbl.setMinWidth(100);
                lbl.setMinHeight(100);
                kolomLijst.add(lbl);
                int r = rij;
                int k = kolom;
                nonogramGrid.add(lbl,k-1,r-1);
            }
            nonogram.add(kolomLijst);

        }

        for(int rij =1; rij<=grote; rij++){
            int r = rij;
            rijGrid.add(new Label(viewNon.getRij()[r-1]),0,r-1);
        }

        for(int kolom =1; kolom<=grote; kolom++){
            int k = kolom;
            kolomGrid.add(new Label(viewNon.getKolom()[k-1]),k-1,0);
        }
    }

    private void layoutGrid(int grote){
        //liquide maken => grootte 5 = 100, 7=75, 10 =50, ...
        for(int kolom = 1; kolom<= grote; kolom++){
            nonogramGrid.getColumnConstraints().add(new ColumnConstraints(100));
        }
        for(int rij = 1; rij<= grote; rij++){
            nonogramGrid.getRowConstraints().add(new RowConstraints(100));
        }

        for(int kolom = 1; kolom<= grote; kolom++){
            kolomGrid.getColumnConstraints().add(new ColumnConstraints(100));
        }
        kolomGrid.getRowConstraints().add(new RowConstraints(100));

        rijGrid.getColumnConstraints().add(new ColumnConstraints(100));
        for(int rij = 1; rij<= grote; rij++){
            rijGrid.getRowConstraints().add(new RowConstraints(100));
        }

        lblTitel.setStyle("-fx-font-size: 36");
        BorderPane.setAlignment(lblTitel, Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        btnScorenboard.setStyle("-fx-font-size: 24");
        btnSpelregels.setStyle("-fx-font-size: 24");
    }

    public void initialiseUserGrid(){
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
