package ir.ac.kntu.gameobjects.staticobjects;

import ir.ac.kntu.gameobjects.GameObject;
import ir.ac.kntu.gameobjects.StaticGameObject;
import ir.ac.kntu.gameobjects.cell.Cell;

import java.util.Timer;
import java.util.TimerTask;

public class Flame extends TimerTask implements StaticGameObject {
    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private Cell flameBoundary;
    private int scale;
    private int layer;
    private boolean isAlive;
    private boolean isPLayerFriendly;

    public Flame (int x, int y) {
        this.positionX = x;
        this.positionY = y;
        scale = 2;
        layer = 1;
        width = 16;
        height = 16;
        flameBoundary = new Cell(positionX, positionY, width*scale, height*scale);
        isAlive = true;
        isPLayerFriendly = false;
    }

    @Override
    public boolean isColliding(GameObject b) {
        return false;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return isPLayerFriendly;
    }

    public boolean isBrickWallCollisionFriendly() {
        return false;
    }

    @Override
    public void draw() {

    }

    @Override
    public void removeFromScene() {

    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public Cell getBoundingBox() {
        return flameBoundary;
    }

    @Override
    public int getLayer() {
        return layer;
    }

    @Override
    public int getScale() {
        return scale;
    }

    @Override
    public void run() {
        isAlive = false;
    }

    public void die() {
        Timer timer = new Timer();
        timer.schedule(this, 500);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setPLayerFriendly(boolean pLayerFriendly) {
        isPLayerFriendly = pLayerFriendly;
    }
}
