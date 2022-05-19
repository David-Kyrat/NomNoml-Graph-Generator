package ch.GraphContent;

import java.util.Objects;

import ch.Serializable;
import ch.enums.Prefix;

public class Vertex implements Serializable {

    public final static String OPEN = "[", END = "]";
    public final static String EDGE_SYMBOL = "-"; // if directed becomes -->

    private final static Prefix prefix = Prefix.VERTEX;

    private final int value;

    /**
     * Main constructor of Class Vertex
     * 
     * @param value int, value displayed inside vertex
     */
    public Vertex(int value) {
        this.value = value;
    }

    /**
     * Serialize {@code this} into nomnoml code
     * 
     * @return nomnoml code describing {@code this}
     */
    @Override
    public String ser() {
        return OPEN + prefix.desc() + " " + value + END;
    }

    public int value() {
        return this.value;
    }

    /**
     * @return Deep Copy of {@code this}
     */
    public Vertex copy() {
        return new Vertex(this.value);
    }

    @Override
    public String toString() {
        return Integer.toString(value());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Vertex)) return false;

        Vertex that = (Vertex) o;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, prefix);
    }
}
