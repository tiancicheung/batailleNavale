package batailleNavale;

import java.util.Random;

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
        this.nbTirsRecus=0;
    }

    public GrilleNavale(int taille, int nbNavires) {
        this.taille = taille;
        this.nbNavires = nbNavires;
        this.navires = new Navire[nbNavires];
        this.tirsRecus = new Coordonnee[taille * taille];
        this.nbTirsRecus=0;
    }     
  
    public String toString() {
        char[][] grille = new char[taille + 1][taille + 1];
        for (int i = 1; i < taille + 1; i++) {
            grille[0][i] = (char) ((int) ('A') + i - 1);
        }
        for (int i = 1; i < taille + 1; i++) {
            grille[i][0] = Character.forDigit(i, 10);
        }
        if (this.tirsRecus == null){

        }else {
            for (int i = 0; i < nbTirsRecus; i++) {
                grille[tirsRecus[i].getLigne()][tirsRecus[i].getColonne()] = '◯';
            }
        }
        for (int i = 0; i < navires.length; i++) {
            if (navires[i].estVertical()) {
                // vertical
                int startLigne = navires[i].getDebut().getLigne()+1;
                int startColonne = navires[i].getDebut().getColonne()+1;
                int length = Math.abs(navires[i].getDebut().getLigne() - navires[i].getFin().getLigne()) + 1;

                for (int j = 0; j < length; j++) {
                    int currentLigne = startLigne + j;
                    int currentColonne = startColonne;

                    if (currentLigne < grille.length && currentColonne < grille[currentLigne].length) {
                        Coordonnee b = new Coordonnee(currentLigne, currentColonne);
                        if (estTouche(b)) {
                            grille[currentLigne][currentColonne] = 'X';
                        } else {
                            grille[currentLigne][currentColonne] = '#';
                        }
                    }
                }
            } else {
                // orientation horizontale
                int startLigne = navires[i].getDebut().getLigne()+1;
                int startColonne = navires[i].getDebut().getColonne()+1;
                int length = Math.abs(navires[i].getDebut().getColonne() - navires[i].getFin().getColonne()) + 1;

                for (int j = 0; j < length; j++) {
                    int currentLigne = startLigne;
                    int currentColonne = startColonne + j;

                    if (currentLigne < grille.length && currentColonne < grille[currentLigne].length) {
                        Coordonnee b = new Coordonnee(currentLigne, currentColonne);
                        if (estTouche(b)) {
                            grille[currentLigne][currentColonne] = 'X';
                        } else {
                            grille[currentLigne][currentColonne] = '#';
                        }
                    }
                }
            }
        }

        for (int i = 1; i < taille + 1; i++) {
            for (int j = 1; j < taille + 1; j++) {
                if (grille[i][j] == '\u0000') {
                    grille[i][j] = '·';
                }
            }
        }
        grille[0][0] = ' ';
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < taille + 1; i++) {
            for (int j = 0; j < taille + 1; j++) {
                a.append(grille[i][j]);
                a.append("  ");
            }
            a.append("\n");
        }
        return a.toString();
    }


    public void placementAuto(int [] taillesNavires){
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
            while (c == false) {
                boolean a = false;
                while (a == false) {
                    navires[i] = new Navire(new Coordonnee((int) (Math.random() * (taille - 1)), (int) (Math.random() * (taille - 1))), taillesNavires[i], Math.random() < 0.5);
                    //is it a vertical ship?
                    if (navires[i].estVertical()) {
                        a = navires[i].getDebut().getLigne() <= taille - taillesNavires[i];
                    } else {
                        //is it a horizontal ship?
                        a = navires[i].getDebut().getColonne() <= taille - taillesNavires[i];
                    }
                }
                for (int j = 0; j < i; j++) {//is it overlapping another ship or touching it?
                    if (navires[i].chevauche(navires[j]) || navires[i].touche(navires[j]) || navires[j].touche(navires[i])) {
                        c = false;
                        break;
                    } else {
                        c = true;
                    }
                }
            }
        }
    }
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

    private boolean estDansGrille(Coordonnee c) {
        if (c.getLigne() < taille && c.getLigne() >= 0 && c.getColonne() < taille && c.getColonne() >= 0 )
            return true;
        return false;
    }

    private boolean estDansTirsRecus(Coordonnee c) {
        for (int i = 0; i < nbTirsRecus; i++) {
            if (tirsRecus[i].equals(c))
                return true;
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
        if (navires == null) {
            throw new IllegalArgumentException("La grille ne contient aucun navire.");
        }
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


    public static void main(String[] args) {
        int[] taillesNavires = {4, 5, 6,5,5,5};

        GrilleNavale grilleNavale2 = new GrilleNavale(10, taillesNavires);
        grilleNavale2.placementAuto(taillesNavires);
        for (int i= 0; i<6; i++) {
            System.out.println(grilleNavale2.navires[i]);
        }
        System.out.println(grilleNavale2.toString());



    }
}
