package ir.ac.kntu.animations;

import ir.ac.kntu.utils.AnimationPlayer;
import ir.ac.kntu.constants.GlobalConstants;
import ir.ac.kntu.gameobjects.GameObject;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BombExplosionAnimations {
    private Sprite blackBombExplosion;
    private double playSpeed;

    public Sprite getBlackBombExplosion() {
        return blackBombExplosion;
    }

    public void setBlackBombExplosion(Sprite blackBombExplosion) {
        this.blackBombExplosion = blackBombExplosion;
    }
    public BombExplosionAnimations(GameObject e) {
        Image img = AnimationPlayer.getInstance().getSpiteSheet(3);
        playSpeed=0.3;

        List<Rectangle> specs=new ArrayList<>();
        specs.add(new Rectangle(0, 2,68,66));
        specs.add(new Rectangle(74, 6,70,66));
        specs.add(new Rectangle(154, 0,78,76));
        specs.add(new Rectangle(160, 0,70,76));
        blackBombExplosion = new Sprite(e,30,playSpeed,img, specs, GlobalConstants.PLAYER_WIDTH+50,
                GlobalConstants.PLAYER_HEIGHT+50, e.getScale(), false);
    }
}
