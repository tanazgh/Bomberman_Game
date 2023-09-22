package ir.ac.kntu.gameobjects.staticobjects;

import ir.ac.kntu.animations.BlockExplosionAnimation;
import ir.ac.kntu.scenes.MainMap;
import ir.ac.kntu.utils.AnimationPlayer;
import ir.ac.kntu.animations.Sprite;
import ir.ac.kntu.gameobjects.GameObject;
import ir.ac.kntu.gameobjects.StaticGameObject;
import ir.ac.kntu.gameobjects.cell.Cell;
import javafx.scene.paint.Color;

import java.util.TimerTask;

public class Wall extends TimerTask implements StaticGameObject {
    private boolean isBreakable;
    private int positionX;
    private int positionY;
    private int height;
    private int width;
    private Color wallColor;
    private Sprite sprite;
    private Cell wallBoundary;
    private int layer;
    private int scale;
    private boolean isPlayerFriendly;
    private boolean isFade;

    public Wall(int x, int y, boolean isBreakable) {
        this.isBreakable = isBreakable;
        positionX = x;
        positionY = y;
        setScale(2);
        width = 16;
        height = 16;
        layer = 1;
        if (isBreakable) {
            sprite = new Sprite(this, 16, 0, 18, 55, 1, width, height,getScale(), false);
        } else {
            sprite = new Sprite(this, 16, 0, 0, 55, 1, width, height ,getScale(), false);
        }
        wallBoundary = new Cell(positionX, positionY, width * getScale(), height * getScale());
        isPlayerFriendly = false;
        isFade = false;
    }

    @Override
    public boolean isColliding(GameObject b) {
        Cell otherEntityBoundary = (Cell) b.getBoundingBox();
        return wallBoundary.checkCollision(otherEntityBoundary);
    }

    @Override
    public void draw() {
        if (!isFade) {
            AnimationPlayer.getInstance().playAnimation(sprite, 2);
        }
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
        return wallBoundary;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return isPlayerFriendly;
    }

    @Override
    public int getLayer() {
        return layer;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public boolean checkFlameCollision() {
        for (GameObject e : MainMap.getInstance().getGameObjects()) {
            if (isBreakable && e instanceof Flame && isColliding(e) && !((Flame) e).isBrickWallCollisionFriendly()) {
                brickBreak();
                return true;
            }
        }
        return false;
    }

    private void brickBreak() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        sprite = new BlockExplosionAnimation(this).getBlockExplosion();
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isPlayerFriendly = true;
        isFade = true;
        ((BrickWall) this).setAlive(false);
    }
}
