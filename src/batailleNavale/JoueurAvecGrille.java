package batailleNavale;

public abstract class JoueurAvecGrille extends Joueur {
    private GrilleNavale grille;
    public JoueurAvecGrille(GrilleNavale g, String nom) {
        super(g.getTaille(), nom);
        this.grille=g;
    }
    public JoueurAvecGrille(GrilleNavale g) {
        this(g, "Joueur");
    }
    public int defendre(Coordonnee c) {
        grille.recoitTir(c);
        if (grille.perdu()) {
            return GAMEOVER;
        }
        if (grille.estCoule(c)) {
            return COULE;
        }
        if (grille.estTouche(c)) {
            return TOUCHE;
        } else {
            return A_L_EAU;
        }
    }
}
