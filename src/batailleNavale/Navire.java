package batailleNavale;

public class Navire {
    //Attributes
    private Coordonnee debut;
    private Coordonnee fin;
    private Coordonnee[] partiesTouchees;
    int nbTouchees;
    int taille;


    public Navire(Coordonnee debut, int longueur, boolean estVertical) {
        if (longueur < 0) {
            throw new IllegalArgumentException("La longueur d'un navire ne peut être inférieure à 0, longueur = " + longueur);
        }
        this.debut = debut;
        if (estVertical)
            this.fin = new Coordonnee(debut.getLigne() + longueur - 1, debut.getColonne());
        else
            this.fin = new Coordonnee(debut.getLigne(), debut.getColonne() + longueur - 1);
        this.partiesTouchees = new Coordonnee[longueur];
        this.nbTouchees = 0;
        taille = longueur;

    }


    //Methods
    public boolean estVertical() {
        return debut.getColonne() == fin.getColonne();
    }


    public String toString() {
        if (debut.getColonne() == fin.getColonne())
            return "Navire (" + debut + ", " + (fin.getLigne() - debut.getLigne() + 1) + ", " + "vertical)";
        return "Navire (" + debut + ", " + (fin.getColonne() - debut.getColonne() + 1) + ", " + "horizontal)";
    }


    public Coordonnee getDebut() {
        return this.debut;
    }

    public Coordonnee getFin() {
        return this.fin;
    }


    public int getTouchLength() {
        return this.nbTouchees;
    }

    public boolean contient(Coordonnee c) {
        if (this.estVertical()){
            if (c.getColonne() == this.debut.getColonne() && c.getLigne() >= this.debut.getLigne() && c.getLigne() <= this.fin.getLigne())
                return true;
        }else if (!this.estVertical()){
            if (c.getLigne() == this.debut.getLigne() && c.getColonne() >= this.debut.getColonne() && c.getColonne() <= this.fin.getColonne())
                return true;
        }
        return false;
    }


    public boolean touche(Navire n) {
        // Si sur une ligne adjacente et sur même colonne & pas de chevauchement
        if (n.getFin().getLigne() + 1 >= this.getDebut().getLigne() && this.getFin().getLigne() + 1 >= n.getDebut().getLigne())
            if (n.getFin().getColonne() >= this.getDebut().getColonne() && this.getFin().getColonne() >= n.getDebut().getColonne())
                if (this.chevauche(n) == false)
                    return true;


        // Si sur une colonne adjacente et sur même ligne & pas de chevauchement
        if (n.getFin().getColonne() + 1 >= this.getDebut().getColonne() && this.getFin().getColonne() + 1 >= n.getDebut().getColonne())
            if (n.getFin().getLigne() >= this.getDebut().getLigne() && this.getFin().getLigne() >= n.getDebut().getLigne())
                if (this.chevauche(n) == false)
                    return true;


        return false;
    }


    public boolean chevauche(Navire n) {

        Coordonnee debutN = n.getDebut();
        Coordonnee finN = n.getFin();
        for (int i = debutN.getLigne(); i <= finN.getLigne(); i++) {
            for (int j = debutN.getColonne(); j <= finN.getColonne(); j++) {
                Coordonnee coord = new Coordonnee(i, j); //on parcourt Navire debut -> fin pour voir si y a parti commun
                if (contient(coord)) {
                    return true; //y a parti commun, est collapsed
                }
            }
        }
        return false;
    }

    //Retourne true si et seulement si this contient c. Dans ce cas, c est ajoutée aux parties touchées si nécessaire.
    public boolean recoitTir(Coordonnee c) {
        if (partiesTouchees.length < 0) {
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

    //Retourne true si et seulement si this a été touché par un tir en c. Pour Navire
    public boolean estTouche(Coordonnee c) {
        for (int i = 0; i < partiesTouchees.length; i++) {
            if (partiesTouchees[i] != null &&  partiesTouchees[i].equals(c)) {
                return true; //si c appartient aux éléments dans partiesTouchees de Navire, ça va return true
            }
        }
        return false;
    }

    public void affichepartiesTouchees() {
        if (partiesTouchees.length < 0) {
            throw new IllegalArgumentException("Le nombre de parties d'un navire ne peut être inférieur à 0, nombre de parties du navire = " + partiesTouchees.length);
        }


        for (int i = 0; i < partiesTouchees.length; i++)
            System.out.print(partiesTouchees[i] + " ");
    }

    public boolean estTouche() {
        if (nbTouchees < 0) {
            throw new IllegalArgumentException("Le nombre de fois que le navire est touché ne peut être inférieur à 0, nbTouchees = " + nbTouchees);
        }


        if (nbTouchees > 0)
            return true;
        return false;

    }

    public boolean estCoule() {
        if (nbTouchees < 0) {
            throw new IllegalArgumentException("Le nombre de fois que le navire est touché ne peut être inférieur à 0, nbTouchees = " + nbTouchees);
        }
        if (nbTouchees > partiesTouchees.length) {
            throw new IllegalArgumentException("Le nombre de fois que le navire est touché ne peut pas être supérieur au nombre de parties du navire, nbTouchees = " + nbTouchees + " , nombre de parties du navire = " + partiesTouchees.length);
        }


        if (nbTouchees == partiesTouchees.length)
            return true; // si partiesTouchees [] est tout remplit, navire est mort
        return false;
    }
}