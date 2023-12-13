package batailleNavale;
public class JoueurAuto extends JoueurAvecGrille {
    public JoueurAuto(GrilleNavale g, String nom) {
        super(g, nom);
    }
    public JoueurAuto(GrilleNavale g) {
        super(g);
    }
    protected void retourAttaque(Coordonnee c, int etat) {
    }
    protected void retourDefense(Coordonnee c, int etat) {

    }
    public Coordonnee choixAttaque() {

        return null;
    }
}