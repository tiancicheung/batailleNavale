package batailleNavale;

public class JoueurAuto extends JoueurAvecGrille {
    protected int nbTirs;
    protected Coordonnee[] dejaTirs;


    protected int TailleGrille;

    protected boolean modeAttaqueAleatoire;

    protected Coordonnee cibleAttaquePremiere;

    protected Coordonnee cibleAttaqueSeconde;

    protected Navire cibleNavireIllusion;

    protected int etatdernier;


    public JoueurAuto(GrilleNavale g, String nom) {
        super(g, nom);
        TailleGrille = g.getTaille();
        nbTirs = 0;
        dejaTirs = new Coordonnee[g.getTaille() * g.getTaille()];

        modeAttaqueAleatoire = true;
        cibleAttaquePremiere = null;
        cibleAttaqueSeconde = null;
        cibleNavireIllusion = null;
        etatdernier = Joueur.A_L_EAU;
    }

    public JoueurAuto(GrilleNavale g) {
        super(g);
        TailleGrille = g.getTaille();
        nbTirs = 0;
        dejaTirs = new Coordonnee[g.getTaille() * g.getTaille()];

        modeAttaqueAleatoire = true;
        cibleAttaquePremiere = null;
        cibleAttaqueSeconde = null;
        cibleNavireIllusion = null;
        etatdernier = Joueur.A_L_EAU;
    }

    protected void retourAttaque(Coordonnee c, int etat) {
        if (etat == Joueur.TOUCHE) {
            System.out.println("Tir " + c + " : Vous avez touché un navire");
            etatdernier = Joueur.TOUCHE;
        } else if (etat == Joueur.COULE) {
            System.out.println("Tir " + c + " : Vous avez coulé un navire");
            etatdernier = Joueur.COULE;
        } else if (etat == Joueur.A_L_EAU) {
            System.out.println("Tir " + c + " : Vous avez tiré dans l'eau");
            etatdernier = Joueur.A_L_EAU;
        } else if (etat == Joueur.GAMEOVER) {
            System.out.println("Tir " + c + " : Gagné :) ");
            etatdernier = Joueur.GAMEOVER;
        }

    }

    protected void retourDefense(Coordonnee c, int etat) {
        if (etat == Joueur.TOUCHE)
            System.out.println("Tir " + c + " : Votre navire été touché par un tir");
        else if (etat == Joueur.COULE)
            System.out.println("Tir " + c + " : Votre navire été coulé");
        else if (etat == Joueur.A_L_EAU)
            System.out.println("Tir " + c + " : Un tir par l'attaquant est tombé dans l'eau");
        else if (etat == Joueur.GAMEOVER)
            System.out.println("Tir " + c + " : Perdu :( ");
    }


    public Coordonnee choixAttaque() {
        ModeSwitcher();
        if (modeAttaqueAleatoire) {
            return ModeAleatoire();
        } else {
            return ModeChasse();
        }
    }
    //巨大问题，怎样才能获取上次的攻击结果，然后根据这个结果来决定下一步的攻击方向

    //这里我们需要一个结果处理器，针对每次得到的结果，做出不一样的处理

    void ModeSwitcher() {
        cibleAttaquePremiere();
        cibleAttaqueSeconde();
        cibleNavireIllusion();
        if (etatdernier == Joueur.A_L_EAU) {
            if (cibleNavireIllusion() == null) {
                modeAttaqueAleatoire = true;
            } else {
                modeAttaqueAleatoire = false;
            }
        } else {
            modeAttaqueAleatoire = false;
        }
    }


    public Coordonnee cibleAttaquePremiere() {
        if (this.cibleAttaquePremiere == null && etatdernier == Joueur.TOUCHE) {
            cibleAttaquePremiere = new Coordonnee(dejaTirs[nbTirs - 1].getLigne() - 1, dejaTirs[nbTirs - 1].getColonne());
        } else if (this.cibleAttaquePremiere != null && etatdernier == Joueur.COULE) {
            cibleAttaquePremiere = null;
        } else if (this.cibleAttaquePremiere != null && etatdernier == Joueur.TOUCHE) {
        } else {
            cibleAttaquePremiere = null;
        }
        return cibleAttaquePremiere;
    }

    public Coordonnee cibleAttaqueSeconde() {
        if (this.cibleAttaqueSeconde == null && etatdernier == Joueur.TOUCHE && cibleAttaquePremiere != null) {
            cibleAttaqueSeconde = new Coordonnee(dejaTirs[nbTirs - 1].getLigne() + 1, dejaTirs[nbTirs - 1].getColonne());
        } else if (this.cibleAttaqueSeconde != null && etatdernier == Joueur.COULE) {
            cibleAttaqueSeconde = null;
        } else if (this.cibleAttaqueSeconde != null && etatdernier == Joueur.TOUCHE && cibleAttaquePremiere != null) {
            cibleAttaqueSeconde = new Coordonnee(dejaTirs[nbTirs - 1].getLigne() - 1, dejaTirs[nbTirs - 1].getColonne());
        } else {
            cibleAttaqueSeconde = null;
        }
        return cibleAttaqueSeconde;
    }

    public Navire cibleNavireIllusion() {
        //mise à jour de cibleNavireIllusion
        if (this.cibleNavireIllusion == null && etatdernier == Joueur.TOUCHE) {
            cibleNavireIllusion = new Navire(cibleAttaquePremiere, 1, true);
        } else if (this.cibleNavireIllusion != null && etatdernier == Joueur.COULE) {
            cibleNavireIllusion = null;
        } else if (this.cibleNavireIllusion != null && etatdernier == Joueur.TOUCHE) {
            int a = cibleAttaqueSeconde.getLigne();
            int b = cibleAttaqueSeconde.getColonne();
            int c = cibleAttaquePremiere.getColonne();
            int d = cibleAttaquePremiere.getLigne();
            boolean e = cibleNavireIllusion.estVertical();
            int taille = 1;
            if (a - d == 0) {
                e = true;
                taille = Math.abs(b - c) + 1;
            } else if (b - c > 0) {
                e = false;
                taille = Math.abs(a - d) + 1;
            }
            cibleNavireIllusion = new Navire(cibleAttaquePremiere, taille, e);
        } else {
            cibleNavireIllusion = null;
        }
        return cibleNavireIllusion;
    }


    public Coordonnee ModeAleatoire() {
        if (nbTirs < TailleGrille * TailleGrille) {
            Coordonnee coordonnee;
            // choose a random coordinate if the array resteATirer contains the coordinate
            do {
                coordonnee = new Coordonnee((int) (Math.random() * TailleGrille), (int) (Math.random() * TailleGrille));
            } while (containsCoordonnee(dejaTirs, nbTirs, coordonnee));
            nbTirs++;
            dejaTirs[nbTirs - 1] = coordonnee; //remember the coordinate
            return coordonnee;
        } else {
            return null; // all the place are attacked, nothing to do
        }

    }

    private Coordonnee ModeChasse() {
        int taille = cibleNavireIllusion.taille;
        if (taille == 1) {
            //进入四周围绕攻击模式
            Coordonnee coordonnee = cibleNavireIllusion.getDebut();

            while (containsCoordonnee(dejaTirs, nbTirs, coordonnee)) {

                int randomDirection = (int) (Math.random() * 4); // 0:上, 1:左, 2:下, 3:右

                switch (randomDirection) {
                    case 0:
                        if (cibleNavireIllusion.getDebut().getLigne() - 1 > 0) {

                            coordonnee = new Coordonnee(cibleNavireIllusion.getDebut().getLigne() - 1, cibleNavireIllusion.getDebut().getColonne());
                        } else {
                            coordonnee = cibleNavireIllusion.getDebut();
                        }
                        break;

                    case 1:
                        if (cibleNavireIllusion.getDebut().getColonne() - 1 > 0) {
                            coordonnee = new Coordonnee(cibleNavireIllusion.getDebut().getLigne(), cibleNavireIllusion.getDebut().getColonne() - 1);
                        } else {
                            coordonnee = cibleNavireIllusion.getDebut();
                        }
                        break;

                    case 2:
                        if (cibleNavireIllusion.getDebut().getLigne() + 1 < TailleGrille) {
                            coordonnee = new Coordonnee(cibleNavireIllusion.getDebut().getLigne() + 1, cibleNavireIllusion.getDebut().getColonne());
                        } else {
                            coordonnee = cibleNavireIllusion.getDebut();
                        }
                        break;
                    case 3:
                        if (cibleNavireIllusion.getDebut().getColonne() + 1 < TailleGrille) {
                            coordonnee = new Coordonnee(cibleNavireIllusion.getDebut().getLigne(), cibleNavireIllusion.getDebut().getColonne() + 1);
                        } else {
                            coordonnee = cibleNavireIllusion.getDebut();
                        }
                        break;
                }
            }
            nbTirs++;
            dejaTirs[nbTirs - 1] = coordonnee; //remember the coordinate
            return coordonnee;
        } else if (taille > 1) {
            //进入两头追击模式
            Coordonnee coordonnee = cibleNavireIllusion.getDebut();
            Coordonnee coordonnee1;
            Coordonnee coordonnee2;
            if (cibleNavireIllusion.estVertical()) {
                int colone = cibleNavireIllusion.getDebut().getColonne();
                int b = cibleNavireIllusion.getDebut().getLigne() - 1;
                int c = cibleNavireIllusion.getDebut().getLigne() + taille;
                if (b > 0) {
                    coordonnee1 = new Coordonnee(b, colone);
                } else {
                    coordonnee1 = cibleNavireIllusion.getDebut();
                }
                if (c < TailleGrille) {
                    coordonnee2 = new Coordonnee(c, colone);
                } else {
                    coordonnee2 = cibleNavireIllusion.getDebut();
                }
            } else {
                int linge = cibleNavireIllusion.getDebut().getLigne();
                int b = cibleNavireIllusion.getDebut().getColonne() - 1;
                int c = cibleNavireIllusion.getDebut().getColonne() + taille;
                if (b > 0) {
                    coordonnee1 = new Coordonnee(linge, b);
                } else {
                    coordonnee1 = cibleNavireIllusion.getDebut();
                }
                if (c < TailleGrille) {
                    coordonnee2 = new Coordonnee(linge, c);
                } else {
                    coordonnee2 = cibleNavireIllusion.getDebut();
                }
            }
            if (!coordonnee1.equals(cibleNavireIllusion.getDebut()) || !containsCoordonnee(dejaTirs, nbTirs, coordonnee1)) {
                coordonnee = coordonnee1;
            } else if (!coordonnee2.equals(cibleNavireIllusion.getDebut()) || !containsCoordonnee(dejaTirs, nbTirs, coordonnee2)) {
                coordonnee = coordonnee2;
            } else {
                throw new IllegalArgumentException("taille de navireFontom est invalide");
            }

            nbTirs++;
            dejaTirs[nbTirs - 1] = coordonnee; //remember the coordinate
            return coordonnee;
        } else {
            throw new IllegalArgumentException("taille de navireFontom est invalide");
        }
    }


    private boolean containsCoordonnee(Coordonnee[] array, int length, Coordonnee target) {
        for (int i = 0; i < length; i++) {
            if (array[i] != null && array[i].equals(target)) {
                return true;
            }
        }
        return false;
    }
}
