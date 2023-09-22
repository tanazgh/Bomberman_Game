package ir.ac.kntu.utils;

import ir.ac.kntu.GameLoop;
import ir.ac.kntu.animations.Sprite;
import ir.ac.kntu.scenes.MainMap;
import ir.ac.kntu.utils.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AnimationPlayer {
    private static AnimationPlayer instance = new AnimationPlayer();

    public static AnimationPlayer getInstance() {
        return instance;
    }

    private Image img1;
    private Image img2;
    private Image img3;

    private AnimationPlayer() {
        img1 = ImageUtils.loadImage("src/main/resources/img/sprites_without_border.png");
        img2 = ImageUtils.loadImage("src/main/resources/img/BombExplosion.png");
        img3 = ImageUtils.loadImage("src/main/resources/img/spriteSheet3.png");
    }

    public Image getSpiteSheet(int imgNum){
        if (imgNum == 1) {
            return img1;
        } else if (imgNum == 2){
            return img2;
        } else {
            return img3;
        }
    }
    public void playAnimation(Sprite sprite, int imgNum) {
        double time = GameLoop.getInstance().getCurrentGameTime();
        GraphicsContext gc = MainMap.getInstance().getGraphicsContext();
        if (sprite.isHasValidSpriteImages()) {
            if (imgNum == 3) {
                playAnimation(sprite.getSpriteImages(), sprite.getPlaySpeed(), sprite.getXPosition()-40,
                        sprite.getYPosition()-40, sprite.getWidth()*sprite.getScale(),
                        sprite.getHeight()*sprite.getScale());
            } else {
                playAnimation(sprite.getSpriteImages(), sprite.getPlaySpeed(), sprite.getXPosition(),
                        sprite.getYPosition(), sprite.getWidth()*sprite.getScale(),
                        sprite.getHeight()*sprite.getScale());
            }

        } else {
            if (imgNum == 1) {
                playAnimation(gc, time, sprite, img1);
            } else {
                playAnimation(gc, time, sprite, img2);
            }

        }

    }

    public void playAnimation(Image[] imgs, double speed, int x, int y, double w, double h) {
        double time = GameLoop.getInstance().getCurrentGameTime();
        GraphicsContext gc = MainMap.getInstance().getGraphicsContext();
        int numberOfFrames = imgs.length;
        int index = findCurrentFrame(time, numberOfFrames, speed);
        gc.drawImage(imgs[index], x, y, w, h);
    }

    private void playAnimation(GraphicsContext gc, double time, Sprite sprite, Image spriteSheet) {

        double speed = sprite.getPlaySpeed() >= 0 ? sprite.getPlaySpeed() : 0.3;

        // index reporesents the index of image to be drawn from the set of images representing frames of animation
        int index = findCurrentFrame(time, sprite.getNumberOfFrames(), speed);

        // newX represents the X coardinate of image in the spritesheet image to be drawn on screen
        int newSpriteSheetX = sprite.isReversePlay() ? sprite.getSpriteLocationOnSheetX() +
                index * sprite.getActualSize() : sprite.getSpriteLocationOnSheetX();
        // newY represents the X coardinate of image in the spritesheet image to be drawn on screen
        int newSpriteSheetY = sprite.isReversePlay() ? sprite.getSpriteLocationOnSheetY() :
                sprite.getSpriteLocationOnSheetY() + index * sprite.getActualSize();
        gc.drawImage(spriteSheet, newSpriteSheetX, newSpriteSheetY, sprite.getWidth(), sprite.getHeight(),
                sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth() * sprite.getScale(),
                sprite.getHeight() * sprite.getScale());
    }

    private int findCurrentFrame(double time, int totalFrames, double speed) {
        return (int) (time % (totalFrames * speed) / speed);
    }
}
