package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.constants.Direction;
import ir.ac.kntu.gameobjects.movingobjects.Player1;
import ir.ac.kntu.gameobjects.staticobjects.Bomb;
import ir.ac.kntu.scenes.MainMap;
import javafx.scene.input.KeyCode;

import java.util.List;

public class Player1Controller implements InputManager {
    private static Player1Controller instance = new Player1Controller();

    public static Player1Controller getInstance() {
        return instance;
    }

    private Player1Controller() {}

    @Override
    public void handlePlayerMovements(){
        List keyboardInputs = EventHandler.getInputList();
        Player1 player = MainMap.getInstance().getPlayer1();
        //System.err.println(""+keyboardInputs);
        if(keyboardInputs.contains(KeyCode.UP)){
            player.move(5, Direction.UP);
        }
        if(keyboardInputs.contains(KeyCode.DOWN)){
            player.move(5,Direction.DOWN);
        }
        if(keyboardInputs.contains(KeyCode.LEFT)){
            player.move(5,Direction.LEFT);
        }
        if(keyboardInputs.contains(KeyCode.RIGHT)){
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

        if(keyboardInputs.contains(KeyCode.SPACE)) {
            Bomb bomb = new Bomb(player.getPositionX(), player.getPositionY());
            MainMap.getInstance().addGameObject(bomb);
            bomb.explosion();
        }
    }
}
