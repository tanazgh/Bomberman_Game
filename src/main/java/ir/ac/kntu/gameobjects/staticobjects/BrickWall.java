package ir.ac.kntu.gameobjects.staticobjects;

public class BrickWall extends Wall {
    private boolean isAlive;

    public BrickWall(int x, int y) {
        super(x, y, true);
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

}
