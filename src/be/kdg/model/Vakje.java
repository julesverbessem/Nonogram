package be.kdg.model;

public class Vakje {
    private boolean ingekleurd;
    private String waarde;

    public Vakje() {
        this.ingekleurd = false;
        this.waarde = "X";
    }

    public String getWaarde() {
        return waarde;
    }
    public void setDefaultWaarde(){
        this.waarde =  "X";
    }

    public boolean isIngekleurd() {
        return ingekleurd;
    }

    public void kleurIn(){
        this.waarde = "O";
        this.ingekleurd = true;
    }
    public void duidAan(){
        this.waarde="*";
        this.ingekleurd = false;
    }

}
