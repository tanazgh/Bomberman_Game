package ir.ac.kntu.gameobjects.movingobjects;

import ir.ac.kntu.gameobjects.staticobjects.Flame;
import ir.ac.kntu.utils.AnimationPlayer;
import ir.ac.kntu.animations.Player1Animations;
import ir.ac.kntu.animations.Player2Animations;
import ir.ac.kntu.animations.PlayerAnimations;
import ir.ac.kntu.animations.Sprite;
import ir.ac.kntu.constants.Direction;
import ir.ac.kntu.constants.GlobalConstants;
import ir.ac.kntu.gameobjects.GameObject;
import ir.ac.kntu.gameobjects.KillableGameObject;
import ir.ac.kntu.gameobjects.MovingGameObject;
import ir.ac.kntu.gameobjects.cell.Cell;
import ir.ac.kntu.scenes.MainMap;

import java.io.*;
import java.util.TimerTask;

public class Player extends TimerTask implements MovingGameObject, KillableGameObject, Serializable {
    private int playerNum;
    private boolean isAlive;
    private Cell playerBoundary;
    private Sprite currentSprite;
    private PlayerAnimations playerAnimations;
    private Direction currentDirection;
    private int positionX;
    private int positionY;
    private int layer;
    private String name;
    private int scale;
    private double reduceBoundarySizePercent=0.45;
    private boolean isFade = false;
    private String path;

    public Player(int posX, int posY, int playerNum) {
        this.playerNum = playerNum;
        if (playerNum == 1) {
            init1(posX, posY);
        } else {
            init2(posX, posY);
        }
        isAlive = true;
        layer=0;
    }

    private void init1(int x, int y) {
        playerAnimations = new Player1Animations(this,2);
        setScale(2);
        positionX = x;
        positionY = y;

        playerBoundary = new Cell(positionX+(int)(GlobalConstants.PLAYER_WIDTH*getReduceBoundarySizePercent()),
                                            positionY+(int)(GlobalConstants.PLAYER_WIDTH*
                                                    getReduceBoundarySizePercent()),
                                            (GlobalConstants.PLAYER_WIDTH * getScale())-2*+
                                                    (int)(GlobalConstants.PLAYER_WIDTH*getReduceBoundarySizePercent()),
                                            (GlobalConstants.PLAYER_HEIGHT * getScale())-2*+
                                                    (int)(GlobalConstants.PLAYER_HEIGHT*getReduceBoundarySizePercent())
                                            );

        currentSprite = playerAnimations.getPlayerIdleSprite();
    }

    private void init2(int x, int y) {

        playerAnimations = new Player2Animations(this,2);
        setScale(2);
        positionX = x;
        positionY = y;

        playerBoundary = new Cell(positionX+(int)(GlobalConstants.PLAYER_WIDTH*getReduceBoundarySizePercent()),
                positionY+(int)(GlobalConstants.PLAYER_WIDTH*
                        getReduceBoundarySizePercent()),
                (GlobalConstants.PLAYER_WIDTH * getScale())-2*+
                        (int)(GlobalConstants.PLAYER_WIDTH*getReduceBoundarySizePercent()),
                (GlobalConstants.PLAYER_HEIGHT * getScale())-2*+
                        (int)(GlobalConstants.PLAYER_HEIGHT*getReduceBoundarySizePercent())
        );

        currentSprite = playerAnimations.getPlayerIdleSprite();
    }

    public void move(Direction direction) {
        move(1, direction);
    }

    private void setCurrentSprite(Sprite s) {
        if (s != null) {
            currentSprite = s;
        } else {
            System.out.println("Sprite missing!");
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean isColliding(GameObject b) {
        Cell otherEntityBoundary = (Cell) b.getBoundingBox();
        return playerBoundary.checkCollision(otherEntityBoundary);
    }

    @Override
    public void draw() {
        if (currentSprite != null && !isFade) {
            AnimationPlayer.getInstance().playAnimation(currentSprite, 1);
        }
    }

    @Override
    public void die() {
        Thread thread = new Thread(this);
        thread.start();
    }

    private boolean checkCollisions(int x, int y) {
    	playerBoundary.setPosition(x, y,getReduceBoundarySizePercent());

        for (GameObject e : MainMap.getInstance().getGameObjects()) {
            if (e != this && isColliding(e) && !e.isPlayerCollisionFriendly()) {
            	playerBoundary.setPosition(positionX, positionY,getReduceBoundarySizePercent());
                return true;
            }
        }
    	playerBoundary.setPosition(positionX, positionY,getReduceBoundarySizePercent());
        return false;
    }

    @Override
    public void move(int steps, Direction direction) {
        if (steps == 0) {
            setCurrentSprite(playerAnimations.getPlayerIdleSprite());
            return;
        } else {
            switch (direction) {
                case UP:
                	if(!checkCollisions(positionX, positionY - steps)) {
	                    positionY -= steps;
	                    setCurrentSprite(playerAnimations.getMoveUpSprite());
	                    currentDirection = Direction.UP;
                	}
                    break;
                case DOWN:
                	if(!checkCollisions(positionX, positionY + steps)) {
                		positionY += steps;
	                    setCurrentSprite(playerAnimations.getMoveDownSprite());
	                    currentDirection = Direction.DOWN;
                	}
                    break;
                case LEFT:
                	if(!checkCollisions(positionX - steps, positionY)) {
                		positionX -= steps;
	                    setCurrentSprite(playerAnimations.getMoveLeftSprite());
	                    currentDirection = Direction.LEFT;
                	}
                    break;
                case RIGHT:
                	if(!checkCollisions(positionX + steps, positionY)) {
                	    positionX += steps;
	                    setCurrentSprite(playerAnimations.getMoveRightSprite());
	                    currentDirection = Direction.RIGHT;
                	}
                    break;
                default:
                    setCurrentSprite(playerAnimations.getPlayerIdleSprite());
            }
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
        playerBoundary.setPosition(positionX, positionY,getReduceBoundarySizePercent());
        return playerBoundary;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return true;
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
    public double getReduceBoundarySizePercent() {
        return reduceBoundarySizePercent;
    }

    public void setReduceBoundarySizePercent(double reduceBoundarySizePercent) {
        this.reduceBoundarySizePercent = reduceBoundarySizePercent;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    @Override
    public void run() {
        setCurrentSprite(playerAnimations.getPlayerDying());
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isAlive = false;
    }

    public boolean checkFlameCollision() {
        for (GameObject e : MainMap.getInstance().getGameObjects()) {
            if (e instanceof Flame && isColliding(e) && !e.isPlayerCollisionFriendly()) {
                die();
                return true;
            }
        }
        return false;
    }

    public void setFade(boolean fade) {
        isFade = fade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player read() {
        Player obj = null;
        try (FileInputStream file = new FileInputStream(path)){
            try (ObjectInputStream in = new ObjectInputStream(file)) {
                obj = (Player) in.readObject();
                return obj;
            } catch (Exception e){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write() {
        try (FileOutputStream file = new FileOutputStream(path)){
            try (ObjectOutputStream out = new ObjectOutputStream(file)) {
                out.writeObject(this);
            } catch (Exception e) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPath(String path) {
        this.path = path;
    }
}
