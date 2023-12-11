package batailleNavale;

public class GrilleNavale {
    private Navire[] navires;
    private int nbNavires;
    private int taille;
    private Coordonnee[] tirsRecus;
    private int nbTirsRecus;
    public GrilleNavale(int taille, int[] taillesNavires) {...}
    public GrilleNavale(int taille, int nbNavires) {...}
    public String toString() {...}
    public int getTaille() {...}
    public boolean ajouteNavire(Navire n) {...}
    public void placementAuto(int[] taillesNavires) {...}
    private boolean estDansGrille(Coordonnee c) {...}
    private boolean estDansTirsRecus(Coordonnee c) {...}
    private boolean ajouteDansTirsRecus(Coordonnee c) {...}
    public boolean recoitTir(Coordonnee c) {...}
    public boolean estTouche(Coordonnee c) {...}
    public boolean estALEau(Coordonnee c) {...}
    public boolean estCoule(Coordonnee c) {...}
    public boolean perdu() {...}
}
