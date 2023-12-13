package batailleNavale;
public class BatailleNavale {
    private Joueur joueur1, joueur2;
    private int tailleGrille;

    public BatailleNavale(int tailleGrille) {
        this.tailleGrille = tailleGrille;

        GrilleNavaleGraphique grilleDefense1 = new GrilleNavaleGraphique(tailleGrille);
        GrilleNavaleGraphique grilleDefense2 = new GrilleNavaleGraphique(tailleGrille);
        GrilleGraphique grilleTirs1 = grilleDefense2.getGrilleGraphique();
        GrilleGraphique grilleTirs2 = grilleDefense1.getGrilleGraphique();

        joueur1 = new JoueurGraphique(grilleDefense1, grilleTirs1, "Joueur 1");
        joueur2 = new JoueurGraphique(grilleDefense2, grilleTirs2, "Joueur 2");
    }
    private void demarrerPartie() {
        new Thread() {
            public void run() {
                joueur1.jouerAvec(joueur2);
            }
        }.start();
    }
    public static void main(String[] args) {
        BatailleNavale batailleNavale = new BatailleNavale(10); // Remplacez 10 par la taille de la grille désirée
        batailleNavale.demarrerPartie();
    }
}
