package be.kdg.view.nonogram;

import be.kdg.model.GebruikersLijst;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class NonogramView extends BorderPane {
    private Label lblTitel;
    private Button btnBack;

    private Button btnScorenboard;
    private Button btnSpelregels;
    private HBox buttonBox;

    private GridPane grid;
    private ArrayList<Label> nonogram;


    public NonogramView(){
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblTitel = new Label();
        lblTitel.setStyle("-fx-font-size: 36");
        lblTitel.setAlignment(Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.getStyleClass().add("outline");//waarde geven via de model in de presenter
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
        this.grid = new GridPane();
        grid.setGridLinesVisible(true);
        this.nonogram = new ArrayList<>();

    }

    private void layoutNodes() {
        this.setTop(lblTitel);
        this.setLeft(btnBack);
        this.setCenter(grid);

        buttonBox.getChildren().add(btnSpelregels);
        buttonBox.getChildren().add(btnScorenboard);
        this.setBottom(buttonBox);
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

    public ArrayList<Label> getNonogram() {
        return nonogram;
    }
}
