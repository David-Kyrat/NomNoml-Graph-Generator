package ch.enums;

import java.util.List;

/**
 * Like a css class, mostly defines a preset nomnoml style
 */
public enum Prefix {
    VERTEX("n"),
    CALL("call"),
    IF("if"),
    DEF("def"),
    INPUT("inp");

    private final String desc;
    public final static String open = "<", end = ">";
    // Immutable list of all values in enum.
    public final static List<Prefix> ALL = List.of(values());

    private Prefix(String desc) {
        this.desc = open + desc + end;
    }
    
    public String desc() { return desc; }

    @Override
    public String toString() {return desc;}

}


