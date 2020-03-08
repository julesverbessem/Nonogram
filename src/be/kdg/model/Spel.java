package be.kdg.model;

import be.kdg.model.Gebruiker;
import be.kdg.model.Nonogram;
import javafx.scene.image.Image;

public class Spel {
    Nonogram mijnNonogram;

    public int startSpel(Gebruiker speler) {
        switch (speler.getLevel()){
            case 1:
                mijnNonogram = new Nonogram(5);
                mijnNonogram.nonogram1_lijn();
                break;
            case 2:
                mijnNonogram = new Nonogram(5);
                mijnNonogram.nonogram2_lijnVerticaal();
                break;
                //aanvullen tot 10
        }
        return  mijnNonogram.getGrootte();
    }
    public void startVolgendSpel(Gebruiker speler){
        int lvl = speler.getLevel();
        int lvlUp = lvl+1;
        speler.setLevel(lvlUp);
    }

    public Image toonNonogramFoto(Gebruiker speler){
        Image foto = null;
        switch (speler.getLevel()-1){
            case 1:
                foto = new Image("./VerticaleLijn (Copy).jpg");
                break;
            case 2:
                foto = new Image("./horizontaleLijn.png");
                break;
            //aanvullen tot 10
        }
        return  foto;

    }

    public Nonogram getMijnNonogram(){return mijnNonogram;}
}
