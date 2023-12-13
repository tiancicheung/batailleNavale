package batailleNavale;

public class testNavire {
    public static void main(String[] args) {

        Coordonnee debut = new Coordonnee(1, 1);
        Navire navire = new Navire(debut, 4, true);
        System.out.println("getDebut: " + navire.getDebut());
        System.out.println("toString: " + navire.toString());
        System.out.println("Fin : " + navire.getFin());
        Coordonnee insideCoord = new Coordonnee(2, 1);
        Coordonnee outsideCoord = new Coordonnee(3, 3);
        System.out.println("contain : " + navire.contient(insideCoord));
        System.out.println("contain : " + navire.contient(outsideCoord));
        Navire navire2 = new Navire(new Coordonnee(4, 0), 3, false);
        Navire navire3 = new Navire(new Coordonnee(6, 1), 3, true);
        System.out.println("Navires touch: " + navire.touche(navire2));// navire touch

        System.out.println("Navires chevauche: " + navire.chevauche(navire3));

        System.out.println("Navire prend un bull à c : " + navire.recoitTir(insideCoord));
        System.out.println("Navire estTouche à c : " + navire.estTouche(insideCoord));
        System.out.println("Navire estTouche à quelque part : " + navire.estTouche());
        System.out.println("Navire dit au revoir du monde : " + navire.estCoule());
        System.out.println("navire nbrpartiesTouchees: " + navire.nbTouchees);
        navire.affichepartiesTouchees();
    }
}

