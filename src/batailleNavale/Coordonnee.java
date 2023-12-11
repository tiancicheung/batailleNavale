package batailleNavale;

public class Coordonnee implements Comparable<Coordonnee> {
    private int ligne;
    private int colonne;
    public Coordonnee(int ligne, int colonne) {...}
    public Coordonnee(String s) {...}
    public String toString() {...}
    public int getLigne() {...}
    public int getColonne() {...}
    public boolean equals(Object obj) {...}
    public boolean voisine(Coordonnee c) {...}
    public int compareTo(Coordonnee c) {...}
}
