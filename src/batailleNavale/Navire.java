package batailleNavale;

public class Navire {
    private Coordonnee debut;
    private Coordonnee fin;
    private Coordonnee[] partiesTouchees;
    private int nbTouchees;
    public Navire(Coordonnee debut, int longueur, boolean estVertical) {...}
    public String toString() {...}
    public Coordonnee getDebut() {...}
    public Coordonnee getFin() {...}
    public boolean contient(Coordonnee c) {...}
    public boolean touche(Navire n) {...}
    public boolean chevauche(Navire n) {...}
    public boolean recoitTir(Coordonnee c) {...}
    public boolean estTouche(Coordonnee c) {...}
    public boolean estTouche() {...}
    public boolean estCoule() {...}
}
