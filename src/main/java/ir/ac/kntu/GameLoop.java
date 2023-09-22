package ir.ac.kntu;

import ir.ac.kntu.constants.GlobalConstants;
import ir.ac.kntu.gameobjects.GameObject;
import ir.ac.kntu.gameobjects.movingobjects.Player;
import ir.ac.kntu.gameobjects.staticobjects.Bomb;
import ir.ac.kntu.gamecontroller.Player1Controller;
import ir.ac.kntu.gamecontroller.Player2Controller;
import ir.ac.kntu.gameobjects.staticobjects.BrickWall;
import ir.ac.kntu.gameobjects.staticobjects.Flame;
import ir.ac.kntu.gameobjects.staticobjects.Wall;
import ir.ac.kntu.scenes.MainMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class GameLoop extends TimerTask{
    private static GameLoop instance = new GameLoop();

    public static GameLoop getInstance() {
        return instance;
    }

    private GameLoop() {}

    @Override
    public void run() {
        gameState = GameState.FINISHED;
    }


    private static double currentGameTime;
    private static double oldGameTime;
    private final static long START_NANO_TIME = System.nanoTime();
    private GameState gameState;
    private int winnerPlayerNum = 0;
    private Stage stage;

    public double getCurrentGameTime() {
        return currentGameTime;
    }

    public void start(GraphicsContext gc) {
        gameState = GameState.RUNNING;
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
            	oldGameTime = currentGameTime;
            	currentGameTime = (currentNanoTime - START_NANO_TIME) / 1000000000.0;
                gc.clearRect(0, 0, GlobalConstants.CANVAS_WIDTH, GlobalConstants.CANVAS_WIDTH);
            	if (gameState == GameState.RUNNING) {
                    updateGame();
                    renderGame();
                } else {
            	    stage.setScene(MainMap.getInstance().setEndingScene());
            	    stage.show();
                }
            }
        }.start();
    }

    public void updateGame() {
        Player1Controller.getInstance().handlePlayerMovements();
        Player2Controller.getInstance().handlePlayerMovements();
        Vector<GameObject> gameObjects = MainMap.getInstance().getGameObjects();
        Iterator<GameObject> it = gameObjects.iterator();
        while (it.hasNext()) {
            GameObject gameObject = it.next();
            if(gameObject instanceof Bomb){
                boolean alive = ((Bomb) gameObject).isAlive();
                if(!alive){
                    it.remove();
                }
            } else if (gameObject instanceof BrickWall) {
                ((Wall) gameObject).checkFlameCollision();
                boolean alive = ((BrickWall) gameObject).isAlive();
                if (!alive) {
                    it.remove();
                }
            } else if (gameObject instanceof Player) {
                ((Player) gameObject).checkFlameCollision();
                boolean alive = ((Player) gameObject).isAlive();
                if (!alive) {
                    if (((Player) gameObject).getPlayerNum() == 1) {
                        winnerPlayerNum = 2;
                    } else if (((Player) gameObject).getPlayerNum() == 2){
                        winnerPlayerNum = 1;
                    }
                    gameState = GameState.FINISHED;
                    ((Player) gameObject).setFade(true);
                }
            } else if (gameObject instanceof Flame) {
                boolean alive = ((Flame) gameObject).isAlive();
                if (!alive) {
                    ((Flame) gameObject).setPLayerFriendly(true);
                    it.remove();
                }
            }
        }
    }

    public void renderGame() {
        for (GameObject go : MainMap.getInstance().getGameObjects()) {
            go.draw();
        }
    }

    public void finishGame() {
        Timer timer = new Timer();
        timer.schedule(this, 185000);
    }

    public int getWinnerPlayerNum() {
        return winnerPlayerNum;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
