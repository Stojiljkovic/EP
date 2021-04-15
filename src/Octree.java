public class Octree {

    InternalNode root;
    double radius;

    public Octree(double radius) {
        this.radius = radius;
        root = new InternalNode(new Cube(new Vector3(0, 0, 0), radius * 2));
        // I haven't implemented root as External node, becouse we will always insert more then one body
    }


    public void insert(Body b) {
        root.insert(b);
    }


    public Octree generateNewTree() {
        Octree newTree = new Octree(radius);
        for (Body b : root) {
            newTree.insert(b);
        }
        return newTree;
    }

    public void updatePositions(double dt) {
        for (Body b : root) {
            b.resetForce();
            root.updateForce(b);
            b.move(dt);
        }
    }

    public void draw() {
        root.drawBodies();
    }


}
