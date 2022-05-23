package ch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import ch.GraphContent.Edge;
import ch.GraphContent.Graph;
import ch.GraphContent.Vertex;

public class Main {

    public void test() {
        List<Edge> testEdges = List.of(
                new Edge(new Vertex(1), new Vertex(2)),
                new Edge(new Vertex(2), new Vertex(3)),
                new Edge(new Vertex(1), new Vertex(4)),
                new Edge(new Vertex(4), new Vertex(6)),
                new Edge(new Vertex(3), new Vertex(6)));
        String title = "Test Graph";

        Graph g = new Graph(title, testEdges);
        System.out.println(g.draw());

        String fileName = "GraphTest2.nomnoml";
        FileUtils.drawToFile(fileName, g);
    }

    public static void main(String[] args) throws IOException {
        if (args.length <= 0) { System.out.println("No args."); return; }
        //String arg = "GraphTest3# 1,2 ; 2,3 ; 1,4 ; 4,6 ; 3,6";

        Graph g = parseArgs(args[0]);
        //System.out.println(g.draw());
        FileUtils.drawToFile(g);        
    }

    /**
     * Parses Main methode argument of the form `title#edge1,edge2;edge3,edge4;...`
     * and returns a `Graph` object
     * 
     * @param arg the string that is passed in from the command line
     * @return A graph object.
     */
    public static Graph parseArgs(String arg) {
        String[] args = arg.split("#");
        String title = args[0].strip(), rest = args[1];
        String[] edgesStr = rest.split(";");
        List<Edge> edges = Arrays
                .stream(edgesStr)
                .map(e -> e.strip().split(","))
                .map(e -> new Edge(Integer.parseInt(e[0]), Integer.parseInt(e[1])))
                .toList();

        return new Graph(title, edges);
    }

    final class FileUtils {
        public final static String RES_DIR = "res/";

        public static void deleteIfPresent(String fileName) throws IOException {
            Files.deleteIfExists(Path.of(RES_DIR, fileName));
        }

        public static void moveToRes(String fileName) {
            try {
                deleteIfPresent(fileName);
                Files.move(Path.of(fileName), Path.of(RES_DIR, fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * This function takes a fileName as parameter, creates a file with that name at
         * RES_DIR/fileName,
         * writes the output of the draw() function to that file, and then closes the
         * file
         * 
         * @param fileName The name of the file to write to.
         * @param g        Graph, Graph to Draw
         */
        public static void drawToFile(String fileName, Graph g) {
            File nomnomFile = new File(fileName);
            try (FileWriter fileWriter = new FileWriter(nomnomFile, Charset.forName("utf8"), false)) {
                fileWriter.write(g.draw());
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //deleteIfPresent(fileName);
                nomnomFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //moveToRes(fileName);
            System.out.printf("Created File %s at \"%s\\%s\"\n", fileName, Path.of(RES_DIR).toAbsolutePath(), fileName);
        }

        public static void drawToFile(Graph g) {
            String title = g.title() + ".nomnoml";
            drawToFile(title, g);
        }
    }
}