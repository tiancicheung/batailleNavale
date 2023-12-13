package batailleNavale;

public class testGrilleNavale {
	public static void main(String[] args) {
    Coordonnee coordonnee = new Coordonnee(1, 2);
    Navire GrilleNavale = new Navire(coordonnee, 6, true);
    int []taillesNavires = {4,5,6,7};

    GrilleNavale grilleNavale = new GrilleNavale(5,6);
    GrilleNavale grilleNavale2 = new GrilleNavale(9,taillesNavires);

	    System.out.println("GrilleNavale getTaille : " + grilleNavale.getTaille());
	    System.out.println("GrilleNavale getTaille : " + grilleNavale2.getTaille());
	    System.out.println("ajouteNavire : " + grilleNavale.ajouteNavire(GrilleNavale));
	    System.out.println("estDansGrille : " + grilleNavale.estDansGrille(coordonnee));
	    System.out.println("estDansTirsRecus : " + grilleNavale2.estDansTirsRecus(coordonnee));
	    System.out.println(grilleNavale.toString());
	    System.out.println(grilleNavale2.toString());
	    for (int i= 0; i<4; i++) {
        System.out.println(grilleNavale2.navires[i]);
    }
	    System.out.println("recoitTir : " + grilleNavale2.recoitTir(coordonnee));
	   System.out.println("estALEau : " + grilleNavale2.estALEau(coordonnee));
	    System.out.println("estCoule : " + grilleNavale2.estCoule(coordonnee));
	    System.out.println("perdu : " + grilleNavale2.perdu());

}
}
