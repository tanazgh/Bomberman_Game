package ir.ac.kntu.animations;

import ir.ac.kntu.utils.AnimationPlayer;
import ir.ac.kntu.constants.GlobalConstants;
import ir.ac.kntu.gameobjects.GameObject;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class BombAnimations {
    private Sprite blackBomb;
    private double playSpeed;

    public Sprite getBlackBomb() {
        return blackBomb;
    }

    public void setBlackBomb(Sprite blackBomb) {
        this.blackBomb = blackBomb;
    }
    public BombAnimations(GameObject e) {
        Image img = AnimationPlayer.getInstance().getSpiteSheet(1);
        playSpeed=0.3;
        
        List<Rectangle> specs=new ArrayList<>();
        specs.add(new Rectangle(180, 93,18,16));
        specs.add(new Rectangle(211, 93,16,16));
        specs.add(new Rectangle(240, 93,18,17));
        blackBomb = new Sprite(e,30,playSpeed,img, specs, GlobalConstants.PLAYER_WIDTH+2,
                GlobalConstants.PLAYER_HEIGHT+2, e.getScale(), false);
    }
}
