package batailleNavale;

public class Navire {
    // Attributs
    private Coordonnee debut;
    private Coordonnee fin;
    private Coordonnee[] partiesTouchees;
    private int nbTouchees;

    // Constructeur
    public Navire(Coordonnee debut, int longueur, boolean estVertical) {
        if (longueur <= 0) { // on vérifie que la longueur du navire est positive
            throw new IllegalArgumentException("La longueur d'un navire ne peut être inférieure à 0.");
        }
        this.debut = debut; // on affecte les valeurs à debut

        if (estVertical) // on affecte les valeurs à fin
            fin = new Coordonnee(debut.getLigne() + longueur - 1, debut.getColonne()); // si le navire est vertical
        else
            fin = new Coordonnee(debut.getLigne(), debut.getColonne() + longueur - 1); // si le navire est horizontal

        partiesTouchees = new Coordonnee[longueur]; // on initialise le tableau partiesTouchees
        nbTouchees = 0; // on initialise le nombre de parties touchées à 0
    }

    // Methods
    public boolean estVertical() {
        return debut.getColonne() == fin.getColonne(); // on vérifie si le navire est vertical, si oui on retourne true
    }

    public String toString() {
        if (debut.getColonne() == fin.getColonne()) // on vérifie si le navire est vertical
            return "Navire (" + debut + ", " + (fin.getLigne() - debut.getLigne() + 1) + ", " + "vertical)"; // si oui on retourne la chaîne correspondante
        return "Navire (" + debut + ", " + (fin.getColonne() - debut.getColonne() + 1) + ", " + "horizontal)"; // si horizontal on retourne la chaîne correspondante
    }

    public Coordonnee getDebut() {
        return debut; // on retourne le début du navire
    }

    public Coordonnee getFin() {
        return fin; // on retourne la fin du navire
    }

    public int getTouchLength() {
        return nbTouchees; // on retourne le nombre de parties touchées
    }

    public boolean contient(Coordonnee c) {
        if (this.estVertical()) {
            if (c.getColonne() == this.debut.getColonne() && c.getLigne() >= this.debut.getLigne() && c.getLigne() <= this.fin.getLigne())
                return true;
        } else if (!this.estVertical()) {
            if (c.getLigne() == this.debut.getLigne() && c.getColonne() >= this.debut.getColonne() && c.getColonne() <= this.fin.getColonne())
                return true;
        }
        return false;
    }

    public boolean touche(Navire n) {
        // Si sur une ligne adjacente et sur même colonne & pas de chevauchement
        if (n.getFin().getLigne() + 1 >= this.getDebut().getLigne() && this.getFin().getLigne() + 1 >= n.getDebut().getLigne()) { // on vérifie si les navires sont sur une ligne adjacente
            if (n.getFin().getColonne() >= this.getDebut().getColonne() && this.getFin().getColonne() >= n.getDebut().getColonne()) { // si oui on vérifie si les navires sont sur la même colonne
                if (!this.chevauche(n)) { // on vérifie si les navires ne se chevauchent pas
                    return true; // si oui on retourne true
                }
            }
        }

        // Si sur une colonne adjacente et sur même ligne & pas de chevauchement
        if (n.getFin().getColonne() + 1 >= this.getDebut().getColonne() && this.getFin().getColonne() + 1 >= n.getDebut().getColonne()) { // on vérifie si les navires sont sur une colonne adjacente
            if (n.getFin().getLigne() >= this.getDebut().getLigne() && this.getFin().getLigne() >= n.getDebut().getLigne()) { // si oui on vérifie si les navires sont sur la même ligne
                if (!this.chevauche(n)) { // on vérifie si les navires ne se chevauchent pas
                    return true; // si oui on retourne true
                }
            }
        }

        return false; // si les navires ne sont pas adjacents ou se chevauchent on retourne false
    }

    public boolean chevauche(Navire n) {
        Coordonnee debutN = n.getDebut(); // on récupère le début et la fin du navire n
        Coordonnee finN = n.getFin();
        for (int i = debutN.getLigne(); i <= finN.getLigne(); i++) { // on parcourt Navire debut -> fin pour voir si y a parti commun
            for (int j = debutN.getColonne(); j <= finN.getColonne(); j++) {
                Coordonnee coord = new Coordonnee(i, j); // on crée une coordonnée
                if (contient(coord)) { // si coord est dans Navire this
                    return true; // on retourne true
                }
            }
        }
        return false; // si on a pas trouvé de parti commun, on retourne false
    }

    //Retourne true si et seulement si this contient c. Dans ce cas, c est ajoutée aux parties touchées si nécessaire.
    public boolean recoitTir(Coordonnee c) {
        if (partiesTouchees.length <= 0) {
            throw new IllegalArgumentException("Le nombre de parties d'un navire ne peut être inférieur à 0, nombre de parties du navire = " + partiesTouchees.length);
        }
        if (contient(c)) {
            for (int i = 0; i < partiesTouchees.length; i++) {
                if (partiesTouchees[i] == null) {
                    partiesTouchees[i] = c; //si y a encore place dans partiesTouchees, on va ajouter c dedans
                    nbTouchees++;
                    break;
                }
            }
            return true; //si c appartient aux éléments dans partiesTouchees, ça va return true et augmenter nbTouchees, update []partiesTouchees
        }
        return false;
    }

    public boolean estTouche(Coordonnee c) {
        if (partiesTouchees.length <= 0) {
            throw new IllegalArgumentException("Le nombre de parties d'un navire ne peut être inférieur à 0, nombre de parties du navire = " + partiesTouchees.length);
        }
        for (int i = 0; i < partiesTouchees.length; i++) {
            if (partiesTouchees[i] != null && partiesTouchees[i].equals(c)) {
                return true; //si c appartient aux éléments dans partiesTouchees de Navire, ça va return true
            }
        }
        return false;
    }

    public void affichepartiesTouchees() {
        if (partiesTouchees.length <= 0) { // on vérifie que le nombre de parties du navire est positif
            throw new IllegalArgumentException("Le nombre de parties d'un navire ne peut être inférieur à 0, nombre de parties du navire = " + partiesTouchees.length);
        }
        for (int i = 0; i < partiesTouchees.length; i++) {
            if (partiesTouchees[i] != null) {
                System.out.print(partiesTouchees[i] + " ");
            }
        }
    }

    public boolean estTouche() {
        if (nbTouchees < 0) {
            throw new IllegalArgumentException("Le nombre de fois que le navire est touché ne peut être inférieur à 0, nbTouchees = " + nbTouchees);
        }
        return nbTouchees > 0; // si nbTouchees > 0, ça veut dire que le navire est touché
    }

    public boolean estCoule() {
        if (nbTouchees < 0) {
            throw new IllegalArgumentException("Le nombre de fois que le navire est touché ne peut être inférieur à 0, nbTouchees = " + nbTouchees);
        }
        return nbTouchees == partiesTouchees.length; // si nbTouchees == partiesTouchees.length, ça veut dire que le navire est coulé
    }

    public int tailleNavire() {
        if (this.estVertical()) {
            return fin.getLigne() - debut.getLigne() + 1;
        } else {
            return fin.getColonne() - debut.getColonne() + 1;
        }
    }
}
