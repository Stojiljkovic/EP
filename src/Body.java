
import java.awt.Color;

public class Body {

    // gravitational constant
    private static final double G = 6.67e-11;

    private Vector3 position;
    private Vector3 velocity;
    private Vector3 force;
    private double mass;
    private Color color;


    public Body(Vector3 position, Vector3 velocity, double mass, Color color) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.color = color;
    }


    public void move(double dt) {
        velocity = velocity.plus(force.times(dt).times(1 / mass));
        position = position.plus(velocity.times(dt));
    }

    public void resetForce() {
        force = new Vector3(0, 0, 0);
    }

    public void addForce(Body b) {
        Vector3 direction = b.position.minus(this.position);
        double r = direction.length();
        direction.normalize();
        double force = G * this.mass * b.mass / (r * r);
        this.force = this.force.plus(direction.times(force));
    }

    public void draw() {
        position.drawAsDot(color);
    }


    public boolean in(Cube c) {
        return c.contains(position);
    }


    public Vector3 getPosition() {
        return position;
    }

    public double getMass() {
        return mass;
    }
}
