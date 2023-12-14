package batailleNavale;

public abstract class Joueur {
    // Attributes
    // Les constantes définies ci-dessous représentent les résultats possibles d'un tir pendant le jeu.
    public final static int TOUCHE = 1;
    public final static int COULE = 2;
    public final static int A_L_EAU = 3;
    public final static int GAMEOVER = 4;
    private Joueur adversaire;
    private int tailleGrille;
    private String nom;

    // Constructors
    public Joueur(int tailleGrille, String nom) {
        if (tailleGrille < 5 || tailleGrille > 26) { // on vérifie que la taille de la grille est comprise entre 5 et 26
            throw new IllegalArgumentException("La taille de la grille doit être comprise entre 5 et 26.");
        }
        if (nom == null || nom.isEmpty()) { // on vérifie que le nom du joueur n'est pas vide
            throw new IllegalArgumentException("Le nom du joueur ne peut pas être vide.");
        }
        this.tailleGrille = tailleGrille;
        this.nom = nom;
    }

    public Joueur(int tailleGrille) {
        this (tailleGrille, "");
    }

    // Methods
    public int getTailleGrille() {
        return tailleGrille;
    }

    public String getNom() {
        return nom;
    }

    public void jouerAvec(Joueur j) {
        this.adversaire = j;
        j.adversaire = this;
        deroulementJeu(this, j);
    }

    private static void deroulementJeu(Joueur attaquant, Joueur defenseur) {
        int res = 0;
        while (res != GAMEOVER) {
            Coordonnee c = attaquant.choixAttaque();
            res = defenseur.defendre(c);
            attaquant.retourAttaque(c, res);
            defenseur.retourDefense(c, res);
            Joueur x = attaquant;
            attaquant = defenseur;
            defenseur = x;
        }
    }

    protected abstract void retourAttaque(Coordonnee c, int etat);

    protected abstract void retourDefense(Coordonnee c, int etat);

    public abstract Coordonnee choixAttaque();

    public abstract int defendre(Coordonnee c);


}
