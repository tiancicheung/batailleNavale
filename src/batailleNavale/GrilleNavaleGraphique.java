package batailleNavale;

import java.awt.Color; // Pour pouvoir utiliser Color.GREEN, Color.BLUE, Color.RED
public class GrilleNavaleGraphique extends GrilleNavale {
    private GrilleGraphique grille;
    public GrilleNavaleGraphique(int taille) {
        // On prend 4 comme valeur de défaut pour nbNavires, car sinon on ne peut pas utiliser super(taille) tout seul
        // nbNavires = 4, du coup à changer plus tard ?
        super(taille, 4);
        this.grille = new GrilleGraphique(taille);
    }
    public GrilleGraphique getGrilleGraphique() {
        return grille;
    }
    public boolean ajouteNavire(Navire n) {
        boolean ajouterNavire = super.ajouteNavire(n);

        if (ajouterNavire) {
            // Colorier les cases du navire en vert (Color.GREEN)
            Coordonnee debut = n.getDebut();
            Coordonnee fin = n.getFin();
            grille.colorie(debut, fin, Color.GREEN);
        }

        return ajouterNavire;
    }
    public boolean recoitTir(Coordonnee c) {
        boolean tirRecu = super.recoitTir(c);

        if (tirRecu)
            // Colorie la case en rouge (Color.RED) si le tir touche un navire
            grille.colorie(c, Color.RED);
        else
            // Colorie la case en bleu (Color.BLUE) si le tir touche l'eau
            grille.colorie(c, Color.BLUE);

        return tirRecu;
    }

}
