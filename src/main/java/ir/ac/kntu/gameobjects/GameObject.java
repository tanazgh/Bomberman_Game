package ir.ac.kntu.gameobjects;

import ir.ac.kntu.gameobjects.cell.Cell;

public interface GameObject {
    boolean isColliding(GameObject b);
    boolean isPlayerCollisionFriendly();
    void draw();
    void removeFromScene();
    int getPositionX();
    int getPositionY();
    Cell getBoundingBox();
    int getLayer();
    int getScale();
}
