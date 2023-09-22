package ir.ac.kntu.gameobjects;

import ir.ac.kntu.constants.Direction;

public interface MovingGameObject extends GameObject {


    public void move(int steps, Direction direction);

}
