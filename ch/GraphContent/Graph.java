package ch.GraphContent;

import static ch.GraphContent.Vertex.END;
import static ch.GraphContent.Vertex.OPEN;
import static ch.enums.GraphType.DEFAULT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.text.html.HTMLDocument.RunElement;

import ch.Serializable;
import ch.enums.GraphType;
import ch.enums.Prefix;

public class Graph {
    public final static String HEADER = """
            #spacing:30
            #font:Cascadia, monospace
            #.def: visual=note title=bold stroke=#1e90ff body=italic
            #.if: visual=rhomb title=bold italic stroke=darkviolet
            #.call: visual=roundrect title=bold body=italic stroke=#1e90ff visual=roundrect
            #.t: title=regular
            #.n: visual=ellipse title=bold
            #.v: visual=ellipse title=bold fill=#a9a9a9
            #.in: stroke=purple visual=input\n""";

    private  final Serializable title;
    private final String titleStr;

    public final GraphType type = DEFAULT;

    private final Set<Vertex> vertices;
    private final Vertex root;
    private final List<Edge> edges;
    private final int n, ne;

    // List of item to render & draw in nomnoml code
    private Set<Serializable> toDraw;

    /**
     * Primary constructor of Class Graph
     * 
     * @param title    String, Title of nomnoml Graph
     * @param vertices List of vertices ({@code Vertex})
     * @param edges    List of edges ({@code Edge})
     */
    public Graph(String title, List<Vertex> vertices, List<Edge> edges) {
        this.title = title.equals("") ? null
                : () -> OPEN + Prefix.DEF.desc() + title + END + "-/-";

        this.vertices = vertices.stream().collect(Collectors.toUnmodifiableSet());
        this.root = vertices.get(0).copy(); // Copy for immutability
        this.edges = initEdge(edges);

        n = this.vertices.size();
        ne = this.edges.size();
        this.toDraw = initToDraw(edges);
        titleStr = title;
    }

    /**
     * Secondary constructor of Graph that computes list of vertices based on given
     * list of edges
     * 
     * @param title String, Title of nomnoml Graph
     * @param edges List of edges ({@code Edge})
     * @param root  Vertex, root of Graph
     */
    public Graph(String title, List<Edge> edges, Vertex root) {
        this.title = title.equals("") ? null
                : () -> OPEN + Prefix.DEF.desc() + title + END + "-/-";
        this.root = root;
        this.vertices = edges
                .stream()
                .flatMap(e -> Stream.of(e.vert(0), e.vert(1)))
                .collect(Collectors.toUnmodifiableSet());
        this.edges = initEdge(edges);

        n = this.vertices.size();
        ne = this.edges.size();
        this.toDraw = initToDraw(this.edges);
        titleStr = title;
    }

    /**
     * root is edges.get(0).v1() => will cast as vertex = does not check for
     * correctness
     * 
     * @param title String
     * @param edges List of edges
     */
    public Graph(String title, List<Edge> edges) {
        this(title, edges, (Vertex) edges.get(0).v1());
    }

    /**
     * NO TITLE +
     * root is edges.get(0).v1() => will cast as vertex = does not check for
     * correctness.
     * 
     * 
     * @param title String
     * @param edges List of edges
     */
    public Graph(List<Edge> edges) {
        this("", edges, (Vertex) edges.get(0).v1());
    }

    /**
     * "Draws" the entier nomnoml code for this graph,
     * i.e. header + {@code this.ser()}
     * (Can be used to update the state of each element to draw)
     * 
     * @return String containing the whole nomnoml code representing this Graph
     */
    public String draw() {
        return HEADER + toDraw
                .stream()
                .map(Serializable::ser)
                .collect(Collectors.joining("\n", "\n", "\n"));
    }

    /**
     * It takes a list of edges, adds a new edge to it, and returns an immutable
     * copy of the list
     * 
     * @param edges List of edges
     * @return A list of edges.
     */
    private List<Edge> initEdge(List<Edge> edges) {
        List<Edge> tmpEdge = new ArrayList<>(edges);
        if (title != null) {
            Edge titleEdge = new Edge(this.title, this.root);
            tmpEdge.add(titleEdge);
        }
        return List.copyOf(tmpEdge);
    }

    /**
     * This function takes a list of edges and returns a immutable set of
     * serializable objects
     * 
     * @param edges List of edges to be drawn
     * @return A set of edges.
     */
    private Set<Serializable> initToDraw(List<Edge> edges) {
        Set<Serializable> tmpToDraw = new HashSet<>(edges);
        tmpToDraw = new HashSet<Serializable>(edges);
        // tmpToDraw.add(() -> HEADER);
        return Set.copyOf(tmpToDraw);
    }

    public Serializable titleSer() {
        return this.title;
    }

    public String title() {
        return this.titleStr;
    }

    public GraphType type() {
        return this.type;
    }

    public Set<Vertex> vertices() {
        return this.vertices;
    }

    public Vertex root() {
        return this.root;
    }

    public List<Edge> edges() {
        return this.edges;
    }

    public int vertexNb() {
        return this.n;
    }

    public int edgeNb() {
        return this.ne;
    }

    public Set<Serializable> toDraw() {
        return this.toDraw;
    }

}
