package batailleNavale;

public class testCoordonnee {
    public static void main(String[] args) {
        Coordonnee coord1 = new Coordonnee(3, 5);
        Coordonnee coord2 = new Coordonnee("B4");

        System.out.println("Coord1: " + coord1.toString());
        System.out.println("Coord2: " + coord2.toString());

        System.out.println("Coord1 equals Coord2: " + coord1.equals(coord2));

        Coordonnee coord3 = new Coordonnee(2, 5);
        System.out.println("Coord1 and Coord3 voisine: " + coord1.voisine(coord3));

        System.out.println("Comparison result between Coord1 and Coord2: " + coord1.compareTo(coord2));
    }
}
