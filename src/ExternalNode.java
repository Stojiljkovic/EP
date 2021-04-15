import java.util.Iterator;

public class ExternalNode implements Node {

    private Body body;
    private Cube cube;


    public ExternalNode(Cube c) {
        this.cube = c;
    }


    @Override
    public Cube getCube() {
        return cube;
    }


    public boolean insert(Body b) {
        if (body == null) {
            body = b;
            return true;
        }
        return false;
    }


    public Body getBody() {
        return body;
    }


    public void updateForce(Body b) {
        if (body == null || b.equals(body))
            return;
        b.addForce(body);
    }


    @Override
    public Iterator<Body> iterator() {
        return new ExternalNodeIter();
    }

    class ExternalNodeIter implements Iterator<Body> {
        boolean nodeVisited = false;

        @Override
        public boolean hasNext() {
            return (body != null && !nodeVisited);
        }

        @Override
        public Body next() {
            nodeVisited = true;
            return body;
        }
    }
}
