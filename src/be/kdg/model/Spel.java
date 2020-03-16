package be.kdg.model;

import be.kdg.model.Gebruiker;
import be.kdg.model.Nonogram;
import javafx.scene.image.Image;

import java.time.LocalDateTime;

public class Spel {
    Nonogram mijnNonogram;

    public int startSpel(Gebruiker speler) {
        switch (speler.getLevel()){
            case 1:
                mijnNonogram = new Nonogram(5);
                mijnNonogram.nonogram1_lijn(speler.getGebruikersnaam());
                break;
            case 2:
                mijnNonogram = new Nonogram(5);
                mijnNonogram.nonogram2_lijnVerticaal(speler.getGebruikersnaam());
                break;case 3:
                mijnNonogram = new Nonogram(5);
                mijnNonogram.nonogram3();//verder afwerke
                break;
            case 4:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram4();
                break;
            case 5:
                mijnNonogram = new Nonogram(6);
                mijnNonogram.nonogram5();
                break;
            case 6:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram6();
                break;
            case 7:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram7();
                break;
            case 8:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram8();
                break;
            case 9:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram9();
                break;
            case 10:
                mijnNonogram = new Nonogram(10);
                mijnNonogram.nonogram10();
                break;
        }
        return  mijnNonogram.getGrootte();
    }
    public void startVolgendSpel(Gebruiker speler){
        int lvl = speler.getLevel();
        int lvlUp = lvl+1;

        speler.setLevel(lvlUp);
        speler.setDatum(LocalDateTime.now());
        mijnNonogram.resetGebruikerNonogram(speler.getGebruikersnaam());
    }

    public Image toonNonogramFoto(Gebruiker speler){
        Image foto = null;
        switch (speler.getLevel()-1){
            case 1:
                foto = new Image("./VerticaleLijn.jpg");
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
