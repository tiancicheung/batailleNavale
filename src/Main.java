import batailleNavale.GrilleNavale;
import batailleNavale.Coordonnee;
import batailleNavale.Navire;

public class Main {

    public static void main(String[] args) {
        int[] taillesNavires = {4, 5, 6,5,5,5};

        GrilleNavale grilleNavale2 = new GrilleNavale(10, taillesNavires);
        grilleNavale2.placementAuto(taillesNavires);
        System.out.println(grilleNavale2.toString());
        System.out.println(grilleNavale2.estTouche(new Coordonnee(1, 1)));
        grilleNavale2.recoitTir(new Coordonnee(1, 1));
        System.out.println(grilleNavale2.toString());
    }
}