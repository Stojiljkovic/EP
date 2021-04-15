public interface Node extends Iterable<Body> {
    public boolean insert(Body b);

    public void updateForce(Body b);

    public Cube getCube();
}
