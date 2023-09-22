package ir.ac.kntu.gameobjects.staticobjects;

import ir.ac.kntu.animations.BombExplosionAnimations;
import ir.ac.kntu.scenes.MainMap;
import ir.ac.kntu.utils.AnimationPlayer;
import ir.ac.kntu.animations.BombAnimations;
import ir.ac.kntu.animations.Sprite;
import ir.ac.kntu.gameobjects.GameObject;
import ir.ac.kntu.gameobjects.StaticGameObject;
import ir.ac.kntu.gameobjects.cell.Cell;

import java.util.*;

public class Bomb extends TimerTask implements StaticGameObject {
    private int positionX;
    private int positionY;
    private int height;
    private int width;
    private Sprite currentSprite;
    private int sheetNum;
    private Cell bombBoundary;
    private BombAnimations bombAnimations;
    private BombExplosionAnimations bombExplosionAnimations;
    private boolean isAlive;
    private int layer;
    private int scale;
    private boolean isPlayerFriendly;

    @Override
    public void run() {
        setCurrentSprite(bombExplosionAnimations.getBlackBombExplosion());
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setFlame();
        isAlive = false;
    }
    
    public Bomb(int x, int y) {
        sheetNum = 1;
        positionX = x;
    	positionY = y;
    	width = 16;
    	height = 16;
    	layer=-2;
    	setScale(2);
        bombAnimations=new BombAnimations(this);
        bombExplosionAnimations = new BombExplosionAnimations(this);
        currentSprite = bombAnimations.getBlackBomb();
        isAlive = true;
        bombBoundary = new Cell(positionX, positionY, width * getScale(), height  * getScale());
        isPlayerFriendly = true;
    }
    
    public boolean isAlive(){
        return isAlive;
    }

    
    @Override
    public boolean isColliding(GameObject b) {
        Cell otherEntityBoundary = (Cell) b.getBoundingBox();
        return bombBoundary.checkCollision(otherEntityBoundary);
    }

    @Override
    public void draw() {
        if (isAlive) {
            AnimationPlayer.getInstance().playAnimation(currentSprite, sheetNum);
        }
    }

    @Override
    public void removeFromScene() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        return bombBoundary;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return isPlayerFriendly;
    }

    @Override
    public int getLayer() {
        return layer;
    }

    @Override
    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setCurrentSprite(Sprite sprite) {
        currentSprite = sprite;
        sheetNum = 3;
    }

    public void explosion() {
        Timer timer = new Timer();
        timer.schedule(this, 3000);
    }

    public void setFlame() {
        Flame flame1 = new Flame(positionX, positionY);
        MainMap.getInstance().addGameObject(flame1);
        flame1.die();
        Flame flame2 = new Flame(positionX+(width*scale), positionY);
        MainMap.getInstance().addGameObject(flame2);
        flame2.die();
        Flame flame3 = new Flame(positionX+(2*width*scale), positionY);
        MainMap.getInstance().addGameObject(flame3);
        flame3.die();
        Flame flame4 = new Flame(positionX-(width*scale), positionY);
        MainMap.getInstance().addGameObject(flame4);
        flame4.die();
        Flame flame5 = new Flame(positionX-(2*width*scale), positionY);
        MainMap.getInstance().addGameObject(flame5);
        flame5.die();
        Flame flame6 = new Flame(positionX, positionY+(height*scale));
        MainMap.getInstance().addGameObject(flame6);
        flame6.die();
        Flame flame7 = new Flame(positionX, positionY+(2*height*scale));
        MainMap.getInstance().addGameObject(flame7);
        flame7.die();
        Flame flame8 = new Flame(positionX, positionY-(height*scale));
        MainMap.getInstance().addGameObject(flame8);
        flame8.die();
        Flame flame9 = new Flame(positionX, positionY-(2*height*scale));
        MainMap.getInstance().addGameObject(flame9);
        flame9.die();
    }

}
