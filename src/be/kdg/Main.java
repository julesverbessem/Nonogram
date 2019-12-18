package be.kdg;

public class Main {

    public static void main(String[] args) {
	    Nonogram test = new Nonogram(1, "test", 10);
	    test.toonGrid();
	    test.setAchterliggendeWaarde1();

        System.out.println(test.vergelijk(1,10));
        System.out.println(test.vergelijk(3,10));
        System.out.println(test.vergelijk(1,5));

    }
}
