package ir.ac.kntu.animations;

import ir.ac.kntu.utils.AnimationPlayer;
import ir.ac.kntu.constants.GlobalConstants;
import ir.ac.kntu.gameobjects.GameObject;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class PlayerAnimations {
    private int playerNum;
    private Sprite moveRight;
    private Sprite moveLeft;
    private Sprite moveUp;
    private Sprite moveDown;
    private Sprite idle;
    private Sprite die;
    private double playSpeed;
    public PlayerAnimations(GameObject e, int scale, int playerNum) {
        this.playerNum = playerNum;
        if (playerNum == 1) {
            init1(e, scale);
        } else {
            init2(e, scale);
        }
    }

    private void init1(GameObject e, int scale) {
        Image img = AnimationPlayer.getInstance().getSpiteSheet(1);
        playSpeed=0.1;
        moveDown  = new Sprite(e, 30, 0.1, 0,  0,
                3, GlobalConstants.PLAYER_WIDTH, GlobalConstants.PLAYER_HEIGHT, scale, false);
        moveLeft  = new Sprite(e, 30, 0.1, 30, 0,
                3, GlobalConstants.PLAYER_WIDTH, GlobalConstants.PLAYER_HEIGHT, scale, false);
        moveUp    = new Sprite(e, 30, 0.1, 60, 0,
                3, GlobalConstants.PLAYER_WIDTH - 1.5, GlobalConstants.PLAYER_HEIGHT, scale, false);
        moveRight = new Sprite(e, 30, 0.1, 90, 0,
                3, GlobalConstants.PLAYER_WIDTH, GlobalConstants.PLAYER_HEIGHT, scale, false);
        idle      = new Sprite(e, 30, 0.1,118, 0,
                1, GlobalConstants.PLAYER_WIDTH + 2, GlobalConstants.PLAYER_HEIGHT, scale, false);

        List<Rectangle> specs=new ArrayList<>();
        specs.add(new Rectangle(149, 0,20,21));
        specs.add(new Rectangle(179, 1,19,20));
        specs.add(new Rectangle(118, 30,21,21));
        specs.add(new Rectangle(149, 30,20,21));
        specs.add(new Rectangle(179, 30,19,21));
        specs.add(new Rectangle(118, 60,21,21));
        specs.add(new Rectangle(147, 60,23,22));
        die = new Sprite(e,30,0.25,img, specs,GlobalConstants.PLAYER_WIDTH+2,
                GlobalConstants.PLAYER_HEIGHT+2, scale, false);
    }

    private void init2(GameObject e, int scale) {
        Image img = AnimationPlayer.getInstance().getSpiteSheet(1);
        playSpeed=0.1;
        moveDown  = new Sprite(e, 30, 0.1, 210,  0,
                3, GlobalConstants.PLAYER_WIDTH, GlobalConstants.PLAYER_HEIGHT, scale, false);
        moveLeft  = new Sprite(e, 30, 0.1, 240, 0,
                3, GlobalConstants.PLAYER_WIDTH, GlobalConstants.PLAYER_HEIGHT, scale, false);
        moveUp    = new Sprite(e, 30, 0.1, 270, 0,
                3, GlobalConstants.PLAYER_WIDTH - 1.5, GlobalConstants.PLAYER_HEIGHT, scale, false);
        moveRight = new Sprite(e, 30, 0.1, 300, 0,
                3, GlobalConstants.PLAYER_WIDTH, GlobalConstants.PLAYER_HEIGHT, scale, false);
        idle      = new Sprite(e, 30, 0.1,328, 0,
                1, GlobalConstants.PLAYER_WIDTH + 2, GlobalConstants.PLAYER_HEIGHT, scale, false);

        List<Rectangle> specs=new ArrayList<>();
        specs.add(new Rectangle(359, 0,20,21));
        specs.add(new Rectangle(389, 1,19,20));
        specs.add(new Rectangle(328, 30,21,21));
        specs.add(new Rectangle(359, 30,20,21));
        specs.add(new Rectangle(389, 30,19,21));
        specs.add(new Rectangle(328, 60,21,21));
        specs.add(new Rectangle(357, 60,23,22));
        die = new Sprite(e,30,0.1,img, specs,GlobalConstants.PLAYER_WIDTH+2,
                GlobalConstants.PLAYER_HEIGHT+2, scale, false);
    }

    public Sprite getMoveRightSprite() {
        return moveRight;
    }

    public Sprite getMoveLeftSprite() {
        return moveLeft;
    }

    public Sprite getMoveUpSprite() {
        return moveUp;
    }

    public Sprite getMoveDownSprite() {
        return moveDown;
    }
    public Sprite getPlayerIdleSprite(){
        return idle;
    }
    public Sprite getPlayerDying(){
        return die;
    }

    public Sprite getDie() {
        return die;
    }
}
