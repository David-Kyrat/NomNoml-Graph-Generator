package ch.GraphContent;

import ch.Serializable;

public class Edge implements Serializable {
    private final Serializable v1;
    private final Serializable v2;

    public Edge(Serializable v1, Serializable v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(int v1, int v2) {
        this.v1 = new Vertex(v1);
        this.v2 = new Vertex(v2);
    }

    @Override
    public String ser() {
        return v1.ser() + " " + Vertex.EDGE_SYMBOL + " " + v2.ser();
    }


    /**
     * Getter for v1.
     * This function returns the value of the field named v1.
     * @return The value of the first field.
     */
    public Serializable v1() { return this.v1; }

    /**
     * Getter for v2.
     * This function returns the value of the v2 field.
     * @return The value of the second element of the tuple.
     */
    public Serializable v2() { return this.v2; }

    /**
     * 
     * @param idx if 0 return v1 else return v2
     * @throws IllegalStateException if wanted index is not an instance of Vertex
     * 
     * @return if idx == 0 return v1 else return v2, or null if not valid
     */
    public Vertex vert(int idx) {
        Vertex out = null;
        try {
            out = idx == 0 ? ((Vertex) v1())  : ((Vertex) v2());
        }
        catch (ClassCastException cce) {
            throw new IllegalStateException("Wanted index was not an instance of Vertex");
        }
        return out;
    }

    @Override
    public String toString() {
        return "e = (" + v1.toString() + ", " + v2.toString() + ")";
    }
}
