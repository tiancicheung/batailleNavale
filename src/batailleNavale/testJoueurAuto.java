package batailleNavale;

public class testJoueurAuto {
    public static void main(String[] args) {
        GrilleNavale g = new GrilleNavale(20, 20);
        JoueurAuto j = new JoueurAuto(g, "JoueurAuto");
        System.out.println(j.choixAttaque());
    }
}
