import java.awt.*;

public class Cube {

    Vector3 center;
    private double length;


    public Cube(Vector3 center, double length) {
        this.center = center;
        this.length = length;
    }


    public double length() {
        return length;
    }


    public boolean contains(Vector3 coordinates) {
        double halfLen = this.length / 2.0;
        return center.contains(halfLen, coordinates);
    }

    public Cube[] divideIntoSmallerCubes() {
        double mewCubeLenght = this.length / 2.0;
        double quaterLenght = this.length / 4;
        Cube[] cubes = new Cube[8];
        int i = 0;

        for (int z = 1; z >= -1; z = z - 2) {
            for (int y = 1; y >= -1; y = y - 2) {
                for (int x = -1; x <= 1; x = x + 2) {
                    cubes[i] = new Cube(new Vector3(center).plus(new Vector3(x * quaterLenght, y * quaterLenght, z * quaterLenght)), mewCubeLenght);
                    i++;
                }
            }
        }
        return cubes;
    }
}
