import java.util.Iterator;

public class InternalNode implements Node {

    private Cube cube;
    public Node[] externalNodes = new Node[8];
    double mass;
    Vector3 center = new Vector3(0, 0, 0);


    public InternalNode(Cube c) {
        this.cube = c;

        Cube[] cubes = this.cube.divideIntoSmallerCubes();
        for (int i = 0; i < 8; i++) {
            externalNodes[i] = new ExternalNode(cubes[i]);
        }
    }


    @Override
    public Cube getCube() {
        return cube;
    }

    //Inserts a body into the appropriate cube.
    public boolean insert(Body b) {
        updateCenterOfMass(b);
        for (int i = 0; i < 8; i++) {
            if (b.in(externalNodes[i].getCube())) {
                if (!externalNodes[i].insert(b)) {
                    ExternalNode tempNode = (ExternalNode) externalNodes[i];
                    Body temp = tempNode.getBody();
                    externalNodes[i] = new InternalNode(externalNodes[i].getCube());
                    externalNodes[i].insert(temp);
                    externalNodes[i].insert(b);
                }
                break;
            }
        }
        return true;
    }


    public void updateCenterOfMass(Body b) {
        double m = this.mass + b.getMass();
        center = center.times(this.mass).plus(b.getPosition().times(b.getMass())).divideBy(m);
        mass = m;
    }


    public void drawBodies() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        for (Body b : this) {
            b.draw();
        }
        StdDraw.show();
    }


    public void updateForce(Body b) {

        double d = center.distanceTo(b.getPosition());
        double s = cube.length();
        double theta = 0.5;

        if ((s / d) < theta) {
            b.addForce(new Body(center, null, mass, null));   // b is far away
        } else {
            for (Node n : externalNodes) {
                n.updateForce(b);
            }
        }
    }


    @Override
    public Iterator<Body> iterator() {
        return new InternalNodeIter();
    }


    class InternalNodeIter implements Iterator<Body> {
        int i;
        Iterator<Body> iter;

        @Override
        public boolean hasNext() {
            if (iter instanceof InternalNodeIter) {
                if (iter.hasNext()) {
                    return true;
                } else {
                    i++;
                }
            }

            for (int j = i; j < 8; j++) {
                iter = externalNodes[j].iterator();
                if (externalNodes[j] instanceof ExternalNode) {
                    if (iter.hasNext()) {
                        i = j;
                        return true;
                    }
                } else {
                    if (iter.hasNext()) {
                        i = j;
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public Body next() {
            if (iter instanceof ExternalNode.ExternalNodeIter) {
                i++;
            }
            return iter.next();
        }

    }
}

