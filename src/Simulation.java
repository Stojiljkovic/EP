import java.awt.Color;
import java.util.Scanner;

public class Simulation {

    public static void main(String[] args) {



        /*          Die Teamaufgabe habe ich allein geloest,
                    weil Paul eitschieden hat diese LVA nicht
                    weiter zu machen.
                    LG
                    Milos
        */



        final double dt = 0.1;
        int N = 10000;               // number of bodies
        double radius = 6.00000E05;      // radius of universe
        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);


        Octree tree = new Octree(radius);
        for (int i = 0; i < N; i++) {
            Vector3 position = new Vector3(Math.random() * (radius * 2) - radius,Math.random() * (radius * 2) - radius,Math.random() * (radius * 2) - radius);
            Vector3 velocity = new Vector3(Math.random() * (6.55536E03 * 2) - 6.55536E03,Math.random() * (6.55536E03 * 2) - 6.55536E03,Math.random() * (6.55536E03 * 2) - 6.55536E03);
            double mass = Math.random() * 1.50000E20;
            int red     = (int)(Math.random() * 254)+1;
            int green   = (int)(Math.random() * 254)+1;
            int blue    = (int)(Math.random() * 254)+1;
            Color color = new Color(red, green, blue);
            tree.insert(new Body(position,velocity, mass, color));
        }

        // simulate the universe
        for (double t = 0.0; true; t = t + dt) {
            tree.updatePositions(dt);
            tree = tree.generateNewTree();
            tree.draw();
        }
    }
}
