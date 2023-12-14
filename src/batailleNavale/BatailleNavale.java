package batailleNavale;

public class BatailleNavale {
    private Joueur joueur1, joueur2;
    private int tailleGrille;
    private void demarrerPartie() {
        new Thread() {
            public void run() {
                joueur1.jouerAvec(joueur2);
            }
        }.start();
    }
}
