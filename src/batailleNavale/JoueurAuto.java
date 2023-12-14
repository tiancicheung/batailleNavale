package batailleNavale;

import java.util.Random;
import java.util.Scanner;

public class JoueurAuto extends JoueurAvecGrille {
    // Attributes
    private Scanner sc = new Scanner(System.in);

    // enregistre le nombre de tirs effectués
    private int nbTirs;

    // enregistre l'indice du premier tir touché
    private int premiereTouche;

    // enregistre les résultats des tirs
    private int[] resTirs;

    // enregistre le type de tir effectué
    private char[] typeTirs;

    // enregistre les coordonnées des tirs envoyés
    private Coordonnee[] tirsEnvoyes;

    // enregistre les coordonnées des cases enlevées
    private Coordonnee[] casesEnlevees;

    // enregistre le nombre de cases enlevées
    private int nbConnues;

    // Constructors
    public JoueurAuto(GrilleNavale g, String nom) {
        super(g, nom); // on appelle le constructeur de la classe supérieure
        int tailleGrille = g.getTaille();

        // initialisation des attributs
        nbTirs = -1; // on commence à -1 car le premier tir est le tir 0
        premiereTouche = 0; // on initialise à 0 car on ne connait pas encore la première touche
        resTirs = new int[tailleGrille * tailleGrille]; // on initialise le tableau à la taille de la grille
        typeTirs = new char[tailleGrille * tailleGrille]; // on initialise le tableau à la taille de la grille
        tirsEnvoyes = new Coordonnee[tailleGrille * tailleGrille]; // on initialise le tableau à la taille de la grille
        casesEnlevees = new Coordonnee[tailleGrille * tailleGrille]; // on initialise le tableau à la taille de la grille
        nbConnues = 0; // on initialise à 0 car on ne connait pas encore la première touche
    }

    public JoueurAuto(GrilleNavale g) {
        this(g, "JoueurAuto"); // on appelle le constructeur de la classe supérieure
    }

    protected void retourAttaque(Coordonnee c, int etat) {
        tirsEnvoyes[nbTirs] = c; // on enregistre les coordonnées du tir
        resTirs[nbTirs] = etat; // on enregistre le résultat du tir

        if (etat == Joueur.TOUCHE) { // si le tir est un succès
            premiereTouche = nbTirs; // on enregistre l'indice du premier tir touché
        }
    }

    protected void retourDefense(Coordonnee c, int etat) {
        if (etat == Joueur.COULE) { // si le tir est un succès
            voisineCoule(); // on enregistre les cases voisines
        }
        premiereTouche(); // on enregistre l'indice du premier tir touché
    }

    public void voisineCoule() {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // on définit les directions possibles

        for (int i = premiereTouche; i <= nbTirs; i++) { // on parcourt les tirs envoyés
            if (resTirs[i] == Joueur.TOUCHE || resTirs[i] == Joueur.COULE) { // si le tir est un succès
                for (int[] dir : directions) { // on parcourt les directions possibles
                    int nextLigne = tirsEnvoyes[i].getLigne() + dir[0]; // on calcule la ligne de la case voisine
                    int nextColonne = tirsEnvoyes[i].getColonne() + dir[1]; // on calcule la colonne de la case voisine
                    Coordonnee c = new Coordonnee(nextLigne, nextColonne); // on crée un objet coordonnée
                    if (estDansGrille(c) && !estDansCasesEnlevees(c)) { // si la case est dans la grille et n'est pas déjà enlevée
                        casesEnlevees[nbConnues++] = c; // on enregistre la case
                    }
                }
            }
        }

        premiereTouche = 0; // on réinitialise l'indice du premier tir touché
    }

    public void premiereTouche() {
        for (int i = premiereTouche; i <= nbTirs; i++) { // on parcourt les tirs envoyés
            if (resTirs[i] == Joueur.TOUCHE && (i == premiereTouche || resTirs[i - 1] == Joueur.A_L_EAU)) {
                premiereTouche = i; // on enregistre l'indice du premier tir touché
                break; // on sort de la boucle
            }
        }
    }

    // permet de savoir si une coordonnée est dans la grille
    private boolean estDansGrille(Coordonnee coord) { // on vérifie que la coordonnée est dans la grille
        int ligne = coord.getLigne(); // on récupère la ligne de la coordonnée
        int colonne = coord.getColonne(); // on récupère la colonne de la coordonnée
        return ligne >= 0 && ligne < getTailleGrille() && colonne >= 0 && colonne < getTailleGrille(); // on vérifie que la coordonnée est dans la grille
    }

    // permet de savoir si une coordonnée est dans les cases enlevées
    private boolean estDansCasesEnlevees(Coordonnee coord) { // on vérifie que la coordonnée est dans les cases enlevées
        for (int i = 0; i < nbConnues; i++) { // on parcourt les cases enlevées
            if (casesEnlevees[i].equals(coord)) { // si la coordonnée est dans les cases enlevées
                return true; // on retourne vrai
            }
        }
        return false; // sinon on retourne faux
    }

    public Coordonnee choixAttaque() {
        nbTirs++; // on incrémente le nombre de tirs effectués

        if (nbTirs == 0) { // si c'est le premier tir
            return tirDir(0, "random"); // on tire au hasard
        } else if (nbTirs == 1) { // si c'est le deuxième tir
            if (resTirs[nbTirs - 1] == Joueur.COULE || resTirs[nbTirs - 1] == Joueur.A_L_EAU) {
                return tirDir(0, "random"); // on tire au hasard
            } else {
                return tirDir(0, "droite"); // on tire à droite
            }
        } else if (resTirs[nbTirs - 1] == Joueur.COULE) { // si le dernier tir est un succès
            return tirDir(0, "random"); // on tire au hasard
        } else if (resTirs[nbTirs - 1] == Joueur.TOUCHE) { // si le dernier tir est un succès
            if (premiereTouche == 0) { // si c'est le premier tir touché
                premiereTouche = nbTirs - 1; // on enregistre l'indice du premier tir touché
                return tirDir(nbTirs - 1, "droite"); // on tire à droite
            } else { // si ce n'est pas le premier tir touché
                return attaqueContinue(); // on continue l'attaque
            }
        } else if (resTirs[nbTirs - 1] == Joueur.A_L_EAU && typeTirs[nbTirs - 1] != 'r') { // si le dernier tir est un échec
            if (typeTirs[nbTirs - 1] == 'd') { // si le dernier tir était à droite
                return tirDir(premiereTouche, "gauche"); // on tire à gauche
            } else if (typeTirs[nbTirs - 1] == 'g') { // si le dernier tir était à gauche
                return tirDir(premiereTouche, "haut"); // on tire en haut
            } else { // si le dernier tir était en haut
                return tirDir(premiereTouche, "bas"); // on tire en bas
            }
        } else if (resTirs[nbTirs - 1] == Joueur.A_L_EAU) { // si le dernier tir est un échec
            return tirDir(0, "random"); // on tire au hasard
        } else { // si le dernier tir est un échec
            return tirDir(0, "random"); // on tire au hasard
        }
    }

    public Coordonnee attaqueContinue() {
        if (typeTirs[nbTirs - 1] == 'd') { // si le dernier tir était à droite
            return tirDir(nbTirs - 1, "droite"); // on tire à droite
        } else if (typeTirs[nbTirs - 1] == 'g') { // si le dernier tir était à gauche
            return tirDir(nbTirs - 1, "gauche"); // on tire à gauche
        } else if (typeTirs[nbTirs - 1] == 'h') { // si le dernier tir était en haut
            return tirDir(nbTirs - 1, "haut"); // on tire en haut
        } else { // si le dernier tir était en bas
            return tirDir(nbTirs - 1, "bas"); // on tire en bas
        }
    }

    public boolean contient(Coordonnee c, Coordonnee[] x) { // on vérifie si une coordonnée est dans un tableau de coordonnées
        for (int i = 0; i < x.length; i++) { // on parcourt le tableau de coordonnées
            if (c.equals(x[i])) { // si la coordonnée est dans le tableau de coordonnées
                return true; // on retourne vrai
            }
        }
        return false; // sinon on retourne faux
    }

    public Coordonnee tirDir(int indiceTouche, String s) { // on tire dans une direction

        if (s.equals("droite")) { // si on tire à droite
            Coordonnee x = new Coordonnee(tirsEnvoyes[indiceTouche].getLigne(), tirsEnvoyes[indiceTouche].getColonne() + 1); // on calcule la coordonnée
            if (contient(x, tirsEnvoyes) || contient(x, casesEnlevees) || limite(x)) { // si la coordonnée est dans les tirs envoyés ou dans les cases enlevées ou hors de la grille
                return tirDir(premiereTouche, "gauche"); // on tire à gauche
            } else {
                typeTirs[nbTirs] = 'd'; // on enregistre le type de tir
                return x; // on retourne la coordonnée
            }

        } else if (s.equals("gauche")) {
            Coordonnee x = new Coordonnee(tirsEnvoyes[indiceTouche].getLigne(), tirsEnvoyes[indiceTouche].getColonne() - 1);
            if (contient(x, tirsEnvoyes) || contient(x, casesEnlevees) || limite(x)) {
                return tirDir(premiereTouche, "haut");
            } else {
                typeTirs[nbTirs] = 'g';
                return x;
            }
        } else if (s.equals("haut")) {

            Coordonnee x = new Coordonnee(tirsEnvoyes[indiceTouche].getLigne() - 1, tirsEnvoyes[indiceTouche].getColonne());
            if (contient(x, tirsEnvoyes) || contient(x, casesEnlevees) || limite(x)) {
                return tirDir(premiereTouche, "bas");
            } else {
                typeTirs[nbTirs] = 'h';
                return x;
            }
        } else if (s.equals("bas")) {
            Coordonnee x = new Coordonnee(tirsEnvoyes[indiceTouche].getLigne() + 1, tirsEnvoyes[indiceTouche].getColonne());
            if (contient(x, tirsEnvoyes) || contient(x, casesEnlevees) || limite(x)) {
                return tirDir(0, "random");
            } else {
                typeTirs[nbTirs] = 'b';
                return x;
            }
        } else {
            int ligneRandom = (int) (Math.random() * this.getTailleGrille());
            int colonneRandom = (int) (Math.random() * this.getTailleGrille());
            Coordonnee x = new Coordonnee(ligneRandom, colonneRandom);

            if (contient(x, tirsEnvoyes) || contient(x, casesEnlevees)) {
                return tirDir(0, "random");
            } else {
                typeTirs[nbTirs] = 'r';
                return x;
            }
        }
    }

    public boolean limite(Coordonnee c) {
        if (c.getLigne() >= getGrille().getTaille() || c.getLigne() < 0) {
            return true;
        } else if (c.getColonne() >= getGrille().getTaille() || c.getColonne() < 0) {
            return true;
        }
        return false;
    }
}