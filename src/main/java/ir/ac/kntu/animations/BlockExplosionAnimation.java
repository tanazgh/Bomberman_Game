package ir.ac.kntu.animations;

import ir.ac.kntu.utils.AnimationPlayer;
import ir.ac.kntu.constants.GlobalConstants;
import ir.ac.kntu.gameobjects.GameObject;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BlockExplosionAnimation {
    private Sprite blockExplosion;
    private double playSpeed;

    public Sprite getBlockExplosion() {
        return blockExplosion;
    }

    public BlockExplosionAnimation(GameObject e) {
        Image img = AnimationPlayer.getInstance().getSpiteSheet(2);
        playSpeed=0.3;

        List<Rectangle> specs=new ArrayList<>();
        specs.add(new Rectangle(36, 56,16,16));
        specs.add(new Rectangle(54, 56,16,16));
        specs.add(new Rectangle(72, 56,16,16));
        specs.add(new Rectangle(90, 56,16,16));
        specs.add(new Rectangle(108, 56,16,16));
        specs.add(new Rectangle(126, 56,16,16));
        blockExplosion = new Sprite(e,30,playSpeed,img, specs, GlobalConstants.PLAYER_WIDTH-3,
                GlobalConstants.PLAYER_HEIGHT-3, e.getScale(), false);
    }
}
