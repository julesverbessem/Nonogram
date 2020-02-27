package be.kdg.view.scorenboard;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ScorenboardView extends BorderPane {
    private Label lblTitel;
    private Button btnBack;
    private Label lblScorenboard;

    public ScorenboardView(){
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblTitel = new Label("Scorenboard");
        lblTitel.setStyle("-fx-font-size: 36");
        lblTitel.setAlignment(Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.getStyleClass().add("outline");
        //Het label moet gecentreerd worden, een solid border hebben en groot genoeg zijn
        //normaal oke

        Image backButton = new Image("./backbutton.png");
        ImageView imageView = new ImageView(backButton);
        this.btnBack = new Button("",imageView);
        btnBack.setStyle("-fx-font-size: 36");
        btnBack.setAlignment(Pos.TOP_LEFT);
        btnBack.setContentDisplay(ContentDisplay.CENTER);
        //kleiner en links vanboven gecentreerd
        //normaal oke

        this.lblScorenboard = new Label("test");
        lblScorenboard.setStyle("-fx-font-size: 36");
        lblScorenboard.setAlignment(Pos.CENTER);
        lblScorenboard.setContentDisplay(ContentDisplay.CENTER);
        lblScorenboard.getStyleClass().add("outline");
        //label moet groot genoeg en gecnetreerd zijn met een solid border
        //normaal oke
    }

    private void layoutNodes() {
        this.setTop(lblTitel);
        this.setLeft(btnBack);
        this.setCenter(lblScorenboard);
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLblScorenboard() {
        return lblScorenboard;
    }
}
