package ir.ac.kntu.scenes;

import ir.ac.kntu.GameLoop;
import ir.ac.kntu.gameobjects.GameObject;
import ir.ac.kntu.gameobjects.movingobjects.Player;
import ir.ac.kntu.gameobjects.movingobjects.Player1;
import ir.ac.kntu.gameobjects.movingobjects.Player2;
import ir.ac.kntu.gameobjects.staticobjects.BrickWall;
import ir.ac.kntu.gameobjects.staticobjects.MetalWall;
import ir.ac.kntu.gameobjects.staticobjects.Wall;
import ir.ac.kntu.gamecontroller.EventHandler;
import ir.ac.kntu.constants.GlobalConstants;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class MainMap {
    private static MainMap instance = new MainMap();

    public static MainMap getInstance() {
        return instance;
    }

    private Scene s;
    private Group root;
    private Canvas c;
    private GraphicsContext gc;
    private static boolean sceneStarted;
    private Player1 player1;
    private Player2 player2;
	private Vector<GameObject> gameObjects;

    private MainMap() {
        sceneStarted = false;
        gameObjects = new Vector<GameObject>();
    }

	public Vector<GameObject> getGameObjects(){
		return new Vector<>(gameObjects);
	}

	private static Comparator<GameObject> layerComparator = new Comparator<GameObject>() {
        @Override
        public int compare(GameObject o1, GameObject o2) {
            int result = Integer.compare(o1.getLayer(),o2.getLayer());
            return result;
        }
    };

	public boolean addGameObject(GameObject go){
		if(!gameObjects.contains(go)){
			gameObjects.add(go);
            Collections.sort(gameObjects,layerComparator);
            return true;
		} else {
			return false;
		}
	}

    private void init() {
        root = new Group();
        s = new Scene(root, GlobalConstants.SCENE_WIDTH, GlobalConstants.SCENE_HEIGHT);
        c = new Canvas(GlobalConstants.CANVAS_WIDTH, GlobalConstants.CANVAS_HEIGHT);
        root.getChildren().add(c);
        gc = c.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.setFill(Color.BLUE);
        GameLoop.getInstance().start(gc);
        GameLoop.getInstance().finishGame();

        try {
            loadMap(new File("src/main/resources/map.txt"));
        } catch (IOException e) {
            System.err.println("Unable to load map file.");
            System.exit(1);
        }

        EventHandler.getInstance().attachEventHandlers(s);
        Pane pane = setBorderPane();
        root.getChildren().add(pane);
    }

    public void loadMap(File file) throws IOException {
    	Vector<Wall> walls = new Vector<Wall>();
    	boolean playerSet = false;

        try(BufferedReader inputStream = new BufferedReader(new FileReader(file))) {
            String line;
            int y = 0;
            while((line = inputStream.readLine()) != null) {
                for(int x = 0; x < line.length(); x++) {
                    switch (line.charAt(x)) {
                        case 'M':
                            walls.add(new MetalWall(x * GlobalConstants.CELL_SIZE, y * GlobalConstants.CELL_SIZE));
                            break;
                        case 'B':
                            walls.add(new BrickWall(x * GlobalConstants.CELL_SIZE, y * GlobalConstants.CELL_SIZE));
                            break;
                        case '1':
                            setPlayer(new Player1(x * GlobalConstants.CELL_SIZE, y * GlobalConstants.CELL_SIZE));
                            playerSet = true;
                            break;
                        case '2' :
                            setPlayer(new Player2(x * GlobalConstants.CELL_SIZE, y * GlobalConstants.CELL_SIZE));
                            playerSet = true;
                            break;
                        default: break;
                    }
                }
                y++;
            }
        }

        if(!playerSet) {
            System.err.println("No player location is set on map.");
            System.exit(1);
        }

    	for(Wall wall : walls) {
    		addGameObject(wall);
    	}
    }

    public void setupScene(){
        if(!sceneStarted){
            init();
            sceneStarted=true;
        }
    }
    public Scene getScene() {
        Pane pane = setBorderPane();
        root.getChildren().add(pane);
        return s;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public Canvas getCanvas() {
        return c;
    }

    public void setPlayer(Player p){
	    if (p.getPlayerNum() == 1) {
            player1 = (Player1) p;
        } else {
            player2 = (Player2) p;
        }
        addGameObject(p);
    }
    public Player1 getPlayer1(){
        return player1;
    }
    public Player2 getPlayer2(){
        return player2;
    }

    private Pane setBorderPane() {
	    Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        pane.setMinWidth(800);
        pane.setMinHeight(60);
        pane.setStyle("-fx-background-color: rgb(70,200,255);");
        Label label = new Label("Player1:");
        label.setLayoutX(100);
        label.setLayoutY(20);
        Label label1 = new Label("Player2:");
        label1.setLayoutX(500);
        label1.setLayoutY(20);
        Label label2 = new Label(player1.getName());
        label2.setLayoutX(300);
        label2.setLayoutY(20);
        Label label3 = new Label(player2.getName());
        label3.setLayoutX(700);
        label3.setLayoutY(20);
        label.setStyle("-fx-font-size: 20px ;\n" + "-fx-font-family: 'sans-serif' ;\n" + "-fx-font-style: italic;\n" +
                "    -fx-text-fill: wheat ;");
        label1.setStyle("-fx-font-size: 20px ;\n" + "-fx-font-family: 'sans-serif' ;\n" + "-fx-font-style: italic;\n" +
                "    -fx-text-fill: wheat ;");
        label2.setStyle("-fx-font-size: 20px ;\n" + "-fx-font-family: 'sans-serif' ;\n" + "-fx-font-style: italic;\n" +
                "    -fx-text-fill: wheat ;");
        label3.setStyle("-fx-font-size: 20px ;\n" + "-fx-font-family: 'sans-serif' ;\n" + "-fx-font-style: italic;\n" +
                "    -fx-text-fill: wheat ;");
        Image image1 = new Image(new File("src/main/resources/assets/player/player_down_standing.png").
                toURI().toString());
        Image image2 = new Image(new File("src/main/resources/assets/player_black/player_black_down_standing.png").
                toURI().toString());
        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        imageView1.setX(200);
        imageView1.setY(20);
        imageView2.setX(600);
        imageView2.setY(20);
        pane.getChildren().addAll(label,label2,label1,label3, imageView1, imageView2);
	    return pane;
    }

    public Scene setEndingScene() {
	    Group group = new Group();
	    Scene scene = new Scene(group, GlobalConstants.SCENE_WIDTH, GlobalConstants.SCENE_HEIGHT);
	    Pane root = new Pane();
	    root.setMinWidth(800);
	    root.setMinHeight(600);
	    root.setLayoutX(0);
	    root.setLayoutY(60);
	    root.setStyle("-fx-background-color: rgb(200, 255, 255);");
	    Label label = new Label();
	    if (GameLoop.getInstance().getWinnerPlayerNum() == 0) {
	        label.setText("Draw");
        } else {
	        label.setText("The Winner:   Player " + GameLoop.getInstance().getWinnerPlayerNum());
        }
	    label.setStyle("-fx-font-size: 40px ;\n" + "-fx-font-family: 'sans-serif' ;\n" +
                "-fx-font-style: italic;\n" + "-fx-text-fill: wheat ;");
	    label.setLayoutX(200);
	    label.setLayoutY(200);
	    root.getChildren().addAll(label);
	    Pane pane = setBorderPane();
	    group.getChildren().addAll(root, pane);
	    return scene;
    }

}
