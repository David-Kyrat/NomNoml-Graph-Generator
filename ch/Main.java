package ch;

import static ch.Helpers.Nodes.withAction;
import static ch.Helpers.Nodes.withClass;

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
import ch.Helpers.Nodes;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

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

    public static void main(String[] args) {
            launch(args);
    }

    private static String title = "", edgesStr = "";
    private static String finalArgs;

    @Override
    public void start(Stage primaryStage) {
        int SCENE_W = 800, SCENE_H = 600;
        Button testBtn = new Button("Confirm");
        Text countText = new Text("0");
        countText.getStyleClass().add("boxR");

        countText.getStyleClass().add("boxBl");

        //Label testLabel = new Label(" = #clicked ", countText);

        Text titleText = Nodes.newTxt("Enter Graph Title & Edges", countText.getFont().getName(), Color.PURPLE, 25,
                FontWeight.BOLD);

        TextField field1 = withAction(new TextField(), tf -> ((TextField) tf).setPromptText("Enter Title."));
        TextField field2 = withAction(new TextField(), tf -> ((TextField) tf).setPromptText("Enter Edges."));
        int inputPadding = 100;

        testBtn.setOnMousePressed(event -> {
            boolean succ = handleConfirm(event, field1, field2);
           // boolean succ = generateNomNom();
            handleExit(primaryStage);
        });

        int fontSize = 15;
        Text res1 = Nodes.newTxt("", Color.MIDNIGHTBLUE, fontSize, FontWeight.NORMAL),
                res2 = Nodes.newTxt("", Color.MIDNIGHTBLUE, fontSize, FontWeight.NORMAL);
        res1.textProperty().bind(field1.textProperty());
        res2.textProperty().bind(field2.textProperty());

        HBox resCntnr1 = Nodes.setUpNewHBox(10, Pos.TOP_CENTER, true, new Label("TITLE: "), res1);
        HBox resCntnr2 = Nodes.setUpNewHBox(10, Pos.TOP_CENTER, true, new Label("EDGES: "), res2);
        VBox inputFieldCntr = Nodes.setUpNewVBox(30, Pos.CENTER, true, titleText, field1, field2, resCntnr1, resCntnr2);
        inputFieldCntr.setPadding(new Insets(50, inputPadding, 50, inputPadding));

        VBox center = Nodes.setUpNewVBox(20, Pos.CENTER, true,
                withClass(inputFieldCntr, "boxC"), testBtn);
        // enter, new Insets(20, 0, 20, 0));

        HBox bottom = Nodes.setUpNewHBox(20, Pos.CENTER, true);
        center.getStyleClass().add("boxB");

        BorderPane subroot = new BorderPane(center);
        subroot.setBottom(bottom);

        StackPane root = new StackPane(subroot);
        Scene scene = new Scene(root, SCENE_W, SCENE_H);
        //scene.getStylesheets().add("/res/debug.css");

        Nodes.setShow(primaryStage, scene);

    }

    /**
     * Handle what happens when confirm button is Clicked
     * 
     * @param e         MousEvent
     * @param countText Text
     * @param f1        TextField
     * @param f2        TextField
     */
    public static boolean handleConfirm(MouseEvent e, TextField f1, TextField f2) {
        title = f1.getText();
        edgesStr = f2.getText();
        finalArgs = title.strip() + "#" + edgesStr.strip();
        boolean succ = generateNomNom();
        event.consume();
        return succ;
        //System.out.println("Final Args: " + finalArgs);
    }

    

    /**

     * << Main >> method of Model i.e. rest of program non-graphic related

     * @return true if creation succeeded flase otherwise of nomnoml file.

     */

    public static boolean generateNomNom() {

        boolean succ = false;



        Graph g = parseArgs(finalArgs);

        if (g == null) return succ;

        succ = succ || FileUtils.drawToFile(g);

        return succ;

    }
    
    /**
     * Parses Main methode argument of the form `title#edge1,edge2;edge3,edge4;...`
     * and returns a `Graph` object
     * 
     * @param arg the string that is passed in from the command line
     * @return A graph object.
     */
    public static Graph parseArgs(String arg) {
        if (arg.isBlank()) return null;
        Graph out = null;

        try {
            String[] args = arg.split("#");
            String title = args[0].strip(), rest = args[1];
            String[] edgesStr = rest.split(";");
            List<Edge> edges = Arrays
                    .stream(edgesStr)
                    .map(e -> e.strip().split(","))
                    .map(e -> new Edge(Integer.parseInt(e[0]), Integer.parseInt(e[1])))
                    .toList();
            out = new Graph(title, edges);
        } catch (Exception e) {
            out = null; //to avoid undefined behavior that may occure if forget to handle some exceptions
            System.out.println("Given argument \"" + arg + "\" is not valid." );
        }
    
        return out;
    }

    
    public static void handleExit(Stage primaryStage) {
        //primaryStage.hide();
        Platform.exit();
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
         * @return success
         */
        public static boolean drawToFile(String fileName, Graph g) {
            boolean succ = true;
            File nomnomFile = new File(fileName);
            try (FileWriter fileWriter = new FileWriter(nomnomFile, Charset.forName("utf8"), false)) {
                fileWriter.write(g.draw());
                fileWriter.flush();
            } catch (IOException e) {
                succ = false;
                e.printStackTrace();
            }
            try {
                // deleteIfPresent(fileName);
                nomnomFile.createNewFile();
            } catch (IOException e) {
                succ = false;
                e.printStackTrace();
            }
            // moveToRes(fileName);
            if (succ)
                System.out.printf("Created File %s at \"%s\\%s\"\n", fileName, Path.of(RES_DIR).toAbsolutePath(), fileName);
            return succ;
        }

        public static void drawToFile(Graph g) {
            String title = g.title() + ".nomnoml";
            drawToFile(title, g);
        }
    }

}

/*
 * public static void main(String[] args) {
 * if (args.length <= 0) { System.out.println("No args."); return; }
 * //String arg = "GraphTest3# 1,2 ; 2,3 ; 1,4 ; 4,6 ; 3,6";
 * 
 * Graph g = parseArgs(args[0]);
 * //System.out.println(g.draw());
 * FileUtils.drawToFile(g);
 * }
 */
