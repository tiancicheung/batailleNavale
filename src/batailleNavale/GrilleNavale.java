package batailleNavale;

import java.util.Random;

public class GrilleNavale {
    private Navire[] navires;// tableau des navires présents dans la grille
    private int nbNavires;// nombre de navires présents dans la grille
    private int taille;// taille de la grille
    private Coordonnee[] tirsRecus; // tableau des tirs reçus
    private int nbTirsRecus; // nombre de tirs reçus

    public GrilleNavale(int taille, int[] taillesNavires) {
        // permet d'obtenir une grille navale de taille taille dans laquelle ont été placés automatiquement taillesNavires.length navires dont les tailles sont données dans taillesNavires.
        if (taille<5 || taille>26)
            throw new IllegalArgumentException("choisir une taille entre 5 et 26"); //Vérifie la taille de la grille
        for (int i = 0 ; i<taillesNavires.length ; i++) //vérifie si les navires sont au moins de taille 1
            if (taillesNavires[i]<1)
                throw new IllegalArgumentException("taille minimale navire : 1");
        this.navires = new Navire[taillesNavires.length]; //LONGUEUR EXPECTED - 1
        this.nbNavires = 0;
        this.taille = taille;
        tirsRecus = new Coordonnee[10];
        nbTirsRecus = 0;
        this.placementAuto(taillesNavires);

    }
    public GrilleNavale(int taille, int nbNavires) {
        if (taille <= 0 || taille > 26) {
            throw new IllegalArgumentException("Taille invalide.");
        }
        if (nbNavires <= 0 || nbNavires > taille * taille) {
            throw new IllegalArgumentException("Nombre de navire est invalid.");
        }
        this.taille = taille;
        this.nbNavires = 0;
        this.navires = new Navire[nbNavires];
        this.tirsRecus = new Coordonnee[taille * taille];
        this.nbTirsRecus=0;
    }

    public String toString() {
        char[][] grille = new char[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = '·'; // 初始化为水域
            }
        }

        // 标记船只位置
        for (Navire navire : navires) {
            if (navire != null) {
                Coordonnee debut = navire.getDebut();
                Coordonnee fin = navire.getFin();
                boolean estVertical = navire.estVertical();

                int startRow = debut.getLigne();
                int endRow = estVertical ? fin.getLigne() : startRow;
                int startCol = debut.getColonne();
                int endCol = estVertical ? startCol : fin.getColonne();

                for (int i = startRow; i <= endRow; i++) {
                    for (int j = startCol; j <= endCol; j++) {
                        Coordonnee c = new Coordonnee(i, j);
                        if (estTouche(c)) {
                            grille[i][j] = 'X'; // 船只被击中
                        } else {
                            grille[i][j] = '#'; // 船只未被击中
                        }
                    }
                }
            }
        }

        // 构建字符串表示
        StringBuilder sb = new StringBuilder();
        sb.append("   "); // 列号前的空格
        for (int i = 0; i < taille; i++) {
            sb.append((char) ('A' + i)).append(" "); // 列号
        }
        sb.append("\n");

        for (int i = 0; i < taille; i++) {
            sb.append(String.format("%2d ", i + 1)); // 行号
            for (int j = 0; j < taille; j++) {
                sb.append(grille[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }



    public int getTaille() {
        return taille;
    }


    public boolean ajouteNavire(Navire n) {
        if (n == null) {
            throw new IllegalArgumentException("Le navire est null.");
        }
        // Vérifier si le navire chevauche, touche un autre navire déjà présent
        for (int i = 0; i < nbNavires; i++) {
            if (navires[i] != null && (navires[i].chevauche(n) || n.chevauche(navires[i]) || navires[i].touche(n) || n.touche(navires[i]))) {
                return false;  // L'ajout est impossible car il y a chevauchement
            }
        }
//        // Vérifier si le navire dépasse les limites de la grille, cette code est fausse mais pour quoi ?
        if (!estDansGrille(n.getDebut()) || !estDansGrille(n.getFin())) {
            return false;  // L'ajout est impossible car le navire dépasse les limites
        }
        //la ancienne version ne augumente pas la taille du tableau navires, ainsi provoque une erreur de dépassement de indice
        //une ajoute de taille de tableau navires est nécessaire, ci-dessous est la nouvelle version
        if (navires.length == nbNavires) {
            Navire[] a = new Navire[navires.length+5];//augumenter la taille du tableau navires
            for (int i=0 ; i<nbNavires ; i++) {
                a[i] = navires[i];
            }
            navires = a;//renouveler la liste navires en associant à une nouvelle liste a
        }
        // Ajouter le navire
        navires[nbNavires] = n;
        nbNavires++;
        return true;  // L'ajout a réussi
    }

    //    public void placementAuto(int[] taillesNavires) {
//        // Place automatiquement et aléatoirement taillesNavires.length navires dont les tailles sont données dans taillesNavire.
//        for (int i = 0; i < taillesNavires.length; ){
//            if (this.ajouteNavire(new Navire(new Coordonnee((int) (Math.random() * (taille - 1)), (int) (Math.random() * (taille - 1))), taillesNavires[0], Math.random() < 0.5)))
//            {
//                i++;
//            }
//        }
//  }
    public void placementAuto(int[] taillesNavires) {
        // Place automatiquement et aléatoirement taillesNavires.length navires dont les tailles sont données dans taillesNavire.
        for (int i = 0; i < taillesNavires.length; )
            if (this.ajouteNavire(new Navire(new Coordonnee(new Random().nextInt(taille - taillesNavires[i]), new Random().nextInt(taille - taillesNavires[i])), taillesNavires[i], new Random().nextBoolean())))
                i++;
    }

    private boolean estDansGrille(Coordonnee c) {
        // Retourne true si et seulement si c est dans this.
        return (c.getColonne() < taille && c.getLigne() < taille);
    }

    private boolean estDansTirsRecus(Coordonnee c) {
        // Retourne true si et seulement si c correspond à un tir reçu par this.
        for (int i = 0; i < nbTirsRecus; i++) {
            if (tirsRecus[i].equals(c))
                return true;
        }
        return false;
    }

    private boolean ajouteDansTirsRecus(Coordonnee c) {
        // Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement si this est modifié.

        // Vérif si tableau tirsRecus pleint, dans ce cas, ajoute 10 slots
        if (tirsRecus.length == nbTirsRecus) {
            Coordonnee[] tab = new Coordonnee[tirsRecus.length+10];
            for (int i = 0; i < nbTirsRecus; i++) {
                tab[i] = tirsRecus[i];
            }
            tirsRecus = tab;
        }
        // Si le tir n'est pas déjà reçu sur c, alors enregistre et incrémente nbTirsRecus + return true
        if (!(this.estDansTirsRecus(c))) {
            tirsRecus[nbTirsRecus] = c;
            nbTirsRecus ++;
            return true;
        }
        return false;
    }

    public boolean recoitTir(Coordonnee c) {
        // Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement si c ne correspondait pas déjà à un tir reçu et a permis de toucher un navire de this.
        if (!(this.estDansTirsRecus(c))) {
            for (int i = 0; i < nbNavires; i++)
                // pas ce recoitTir, mais celui de navires !
                if (navires[i].recoitTir(c))
                    return true;
        }
        return false;

    }

    public boolean estTouche(Coordonnee c) {
        // Retourne true si et seulement si un des navires présents dans this a été touché en c.
        for (int i = 0; i < nbNavires; i++) {
            if (navires[i].estTouche(c))
                return true;
        }
        return false;
    }

    public boolean estALEau(Coordonnee c) {
        // Retourne true si et seulement si c correspond à un tir reçu dans l'eau par this.
        for (int i = 0; i < nbNavires; i++) {
            if (navires[i].estTouche(c))
                return false;
        }
        return true;

    }

    public boolean estCoule(Coordonnee c) {
        // Retourne true si et seulement si un des navires présents dans this a été touché en c et est coulé.
        for (int i = 0; i < nbNavires; i++) {
            if (navires[i].contient(c) && navires[i].estCoule())//navires[i].estTouche(c) &&
                return true;
        }
        return false;
    }

    public boolean perdu() {
        //Retourne true si et seulement si tous les navires de this ont été coulés.
        for (int i = 0; i < nbNavires; i++) {
            if (!(navires[i].estCoule()))
                return false;
        }
        return true;
    }


    public int[] getTailleSelonGrille() {
        int[] res = null;
        if (taille > 5 && taille < 10) {
            res = new int[] {2, 2, 3};
        }
        else if (taille < 15) {
            res = new int[] {2, 2, 3, 3, 4};
        }
        else if (taille < 20) {
            res = new int[] {2, 2, 3, 3, 4, 4, 5};
        }
        else {
            res = new int[] {2, 2, 3, 3, 4, 4, 5, 5, 6};
        }
        return res;
    }


}