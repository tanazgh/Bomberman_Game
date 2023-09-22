package ir.ac.kntu.animations;

import ir.ac.kntu.gameobjects.GameObject;
import ir.ac.kntu.utils.ImageUtils;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Sprite {
	private double playSpeed;
	private int spriteLocationOnSheetX;
	private int spriteLocationOnSheetY;
	private int numberOfFrames;
	private double width;
	private double height;
	private int scale;
	private int actualSize;
	private boolean reversePlay;
	private Image[] spriteImages;
	private boolean hasValidSpriteImages;
	private GameObject gameObjectReference;

	public Sprite(GameObject e, int actualSize, double playSpeed, int spriteLocationOnSheetX,
				  int spriteLocationOnSheetY, int numberOfFrames, double width, double height,
				  int scale, boolean leftToRight) {
		super();
		this.actualSize = actualSize;
		this.playSpeed = playSpeed;
		this.spriteLocationOnSheetX = spriteLocationOnSheetX;
		this.spriteLocationOnSheetY = spriteLocationOnSheetY;
		this.numberOfFrames = numberOfFrames;
		this.width = width;
		this.height = height;
		this.scale = scale;
		reversePlay = leftToRight;
        this.gameObjectReference =e;
	}

	public int getXPosition() {
		return gameObjectReference.getPositionX();
	}

	public int getYPosition() {
		return gameObjectReference.getPositionY();
	}


    public Sprite(GameObject e, int actualSize, double playSpeed, Image spriteSheet, List<Rectangle> specifications,
				  double width, double height, int scale, boolean leftToRight) {
        super();
        this.actualSize = actualSize;
        this.playSpeed = playSpeed;
        this.numberOfFrames=specifications.size();
        this.width = width;
        this.height = height;
        this.scale = scale;
        reversePlay = leftToRight;
        this.gameObjectReference =e;
        hasValidSpriteImages=true;
        spriteImages=new Image[specifications.size()];
        for (int i = 0; i < specifications.size(); i++) {
            Rectangle specification = specifications.get(i);
            int x=(int)specification.getX();
            int y=(int)specification.getY();
            int w=(int)specification.getWidth();
            int h=(int)specification.getHeight();

            //To DO Check dimensions provided are not going out of spritesheet dimensions\
            spriteImages[i]= ImageUtils.crop(spriteSheet, x, y, w, h);
        }
    }

	public GameObject getGameObjectReference() {
		return gameObjectReference;
	}

	public void setGameObjectReference(GameObject gameObjectReference) {
		this.gameObjectReference = gameObjectReference;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public double getPlaySpeed() {
		return playSpeed;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public boolean isReversePlay() {
		return reversePlay;
	}

	public Image[] getSpriteImages() {
		return spriteImages;
	}

	public int getActualSize() {
		return actualSize;
	}

	public int getNumberOfFrames() {
		return numberOfFrames;
	}

	public int getSpriteLocationOnSheetX() {
		return spriteLocationOnSheetX;
	}

	public boolean isHasValidSpriteImages() {
		return hasValidSpriteImages;
	}

	public int getSpriteLocationOnSheetY() {
		return spriteLocationOnSheetY;
	}
}
