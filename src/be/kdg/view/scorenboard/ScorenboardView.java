package be.kdg.view.scorenboard;

import javafx.scene.control.Button;
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
        //Het label moet gecentreerd worden, een solid border hebben en groot genoeg zijn

        Image backButton = new Image("./backbutton.png");
        ImageView imageView = new ImageView(backButton);
        this.btnBack = new Button("",imageView);
        //kleiner en links vanboven gecentreerd

        this.lblScorenboard = new Label("test");
        //label moet groot genoeg en gecnetreerd zijn met een solid border
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
