package batailleNavale;

public class GrilleNavale {
    // Attributs
    private Navire[] navires;
    private int nbNavires;
    private int taille;
    private Coordonnee[] tirsRecus;
    private int nbTirsRecus;


    // Constructeurs
    public GrilleNavale(int taille, int[] taillesNavires) {
        this.taille = taille;
        this.nbNavires = taillesNavires.length;
        this.navires = new Navire[nbNavires];
        this.tirsRecus = new Coordonnee[taille * taille];
        this.nbTirsRecus = 0;
    }


    public GrilleNavale(int taille, int nbNavires) {
        this.taille = taille;
        this.nbNavires = nbNavires;
        this.navires = new Navire[nbNavires];
        this.tirsRecus = new Coordonnee[taille * taille];
        this.nbTirsRecus = 0;
    }




    // Méthodes


    public String toString() {
        String represent = "";
        // Ajout de l'en-tête avec les lettres des colonnes
        represent += "  ";
        for (int j = 1; j <= taille; j++) {
            represent += (char) ('A' + j - 1) + " ";
        }
        represent += "\n";
        // Parcours de chaque ligne et colonne pour construire la représentation
        for (int i = 1; i <= taille; i++) {
            represent += i + " ";
            for (int j = 1; j <= taille; j++) {
                Coordonnee coord = new Coordonnee(i,j);
                // Vérifier l'état de la case
                char symbol = '.';
                if (estDansTirsRecus(coord)) {
                    if (estTouche(coord)) {
                        symbol = 'X';
                    } else if (estALEau(coord)) {
                        symbol = 'O';
                    }
                } else {
                    for (i = 0; i < navires.length; i++) {
                        Navire navire = navires[i];
                        if (navire != null && navire.contient(coord)) {
                            symbol = '#';
                            break;
                        }
                    }
                }
                represent += symbol + " ";
            }
            represent += "\n";
        }
        return represent;	   }

    public int getTaille() {
        return taille;
    }

    public boolean ajouteNavire(Navire n) {
        // Vérifier si le navire chevauche, touche un autre navire déjà présent
        for (int i = 0; i < nbNavires; i++) {
            if (navires[i] != null && (navires[i].chevauche(n) || n.chevauche(navires[i]) || navires[i].touche(n) || n.touche(navires[i]))) {
                return false;  // L'ajout est impossible car il y a chevauchement
            }
        }
        // Vérifier si le navire dépasse les limites de la grille
        if (!estDansGrille(n.getDebut()) || !estDansGrille(n.getFin())) {
            return false;  // L'ajout est impossible car le navire dépasse les limites
        }
        // Ajouter le navire à la grille
        for (int i = 0; i < nbNavires; i++) {
            if (navires[i] == null) {
                navires[i] = n;
                return true;  // L'ajout a réussi
            }
        }
        return false;  // La grille est pleine, l'ajout est impossible
    }

    public void placementAuto(int[] taillesNavires) {
        //A tester
        boolean b = false;
        while (b == false) {
            navires[0] = new Navire(new Coordonnee((int) (Math.random() * (taille - 1)), (int) (Math.random() * (taille - 1))), taillesNavires[0], Math.random() < 0.5);
            if (navires[0].estVertical()) {
                b = navires[0].getDebut().getLigne() <= taille - taillesNavires[0];
            } else {
                b = navires[0].getDebut().getColonne() <= taille - taillesNavires[0];
            }
        }
        for (int i = 1; i < taillesNavires.length; i++) {
            boolean c = false;
            while (c== false) {
                boolean a = false;
                while (a == false) {
                    navires[i] = new Navire(new Coordonnee((int) (Math.random() * (taille - 1)), (int) (Math.random() * (taille - 1))), taillesNavires[i], Math.random() < 0.5);
                    if (navires[i].estVertical()) {
                        a = navires[i].getDebut().getLigne() <= taille - taillesNavires[i];
                    } else {
                        a = navires[i].getDebut().getColonne() <= taille - taillesNavires[i];
                    }
                }
                for (int j = 0; j < i; j++) {
                    if (navires[i].chevauche(navires[j]) || navires[i].touche(navires[j]) || navires[j].touche(navires[i])) {
                        c = false;
                    } else {
                        c = true;
                    }
                }
            }
        }

    }

    private boolean estDansGrille(Coordonnee c) {
        if (c.getLigne() < taille && c.getColonne() < taille && c.getColonne() >= 0 && c.getLigne() >= 0) {
            return true;
        }
        return false;
    }

    private boolean estDansTirsRecus(Coordonnee c) {
        for (int i = 0; i < nbTirsRecus; i++) {
            if (tirsRecus[i].equals(c)) {
                return true;
            }
        }
        return false;
    }


    private boolean ajouteDansTirsRecus(Coordonnee c) {
        for (int i = 0; i < nbTirsRecus; i++) {
            if (tirsRecus[i].equals(c)) {
                return false;
            }
        }
        tirsRecus[nbTirsRecus] = c;
        nbTirsRecus++;
        return true;
    }

    public boolean recoitTir(Coordonnee c) {
        if (estDansTirsRecus(c)){
            return false;
        }else{
            for (int i = 0; i < nbNavires; i++) {
                if (navires[i].contient(c)) {
                    ajouteDansTirsRecus(c);
                    return true;
                }
            }
        }
        ajouteDansTirsRecus(c);
        return false;
    }

    public boolean estTouche(Coordonnee c) {
        for (int i = 0; i < nbNavires; i++) {
            if (navires[i].estTouche(c)) {
                return true;
            }
        }
        return false;
    }


    public boolean estALEau(Coordonnee c) {
        return estDansGrille(c) && !estTouche(c);
    }

    public boolean estCoule(Coordonnee c) {
        for (int i = 0; i < nbNavires; i++){
            if (navires[i].estTouche(c)){
                if (navires[i].estCoule()){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean perdu() {
        for (int i = 0; i < nbNavires; i++){
            if (!navires[i].estCoule()){
                return false;
            }
        }
        return true;
    }
}