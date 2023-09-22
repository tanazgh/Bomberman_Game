package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.constants.Direction;
import ir.ac.kntu.gameobjects.movingobjects.Player2;
import ir.ac.kntu.gameobjects.staticobjects.Bomb;
import ir.ac.kntu.scenes.MainMap;
import javafx.scene.input.KeyCode;

import java.util.List;

public class Player2Controller implements InputManager {
    private static Player2Controller instance = new Player2Controller();

    public static Player2Controller getInstance() {
        return instance;
    }

    private Player2Controller() {}

    @Override
    public void handlePlayerMovements() {
        List keyboardInputs = EventHandler.getInputList();
        Player2 player = MainMap.getInstance().getPlayer2();
        //System.err.println(""+keyboardInputs);
        if(keyboardInputs.contains(KeyCode.W)){
            player.move(5, Direction.UP);
        }
        if(keyboardInputs.contains(KeyCode.S)){
            player.move(5,Direction.DOWN);
        }
        if(keyboardInputs.contains(KeyCode.A)){
            player.move(5,Direction.LEFT);
        }
        if(keyboardInputs.contains(KeyCode.D)){
            player.move(5,Direction.RIGHT);
        }
        if( !keyboardInputs.contains(KeyCode.LEFT) &&
                !keyboardInputs.contains(KeyCode.RIGHT) &&
                !keyboardInputs.contains(KeyCode.UP) &&
                !keyboardInputs.contains(KeyCode.DOWN) &&
                !keyboardInputs.contains(KeyCode.W) &&
                !keyboardInputs.contains(KeyCode.A) &&
                !keyboardInputs.contains(KeyCode.S) &&
                !keyboardInputs.contains(KeyCode.D)
        ) {
            player.move(0, Direction.DOWN);
        }

        if(keyboardInputs.contains(KeyCode.C)){
            Bomb bomb = new Bomb(player.getPositionX(), player.getPositionY());
            MainMap.getInstance().addGameObject(bomb);
            MainMap.getInstance().addGameObject(bomb);
            bomb.explosion();

        }
    }
}
