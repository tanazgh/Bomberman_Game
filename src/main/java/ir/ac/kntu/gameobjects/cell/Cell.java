package ir.ac.kntu.gameobjects.cell;

import ir.ac.kntu.constants.GlobalConstants;
import javafx.geometry.Rectangle2D;

public class Cell {
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle2D boundary;

    public Cell(int x, int y, int w, int h){
        this.x=x;
        this.y=y;
        width=w;
        height=h;
        boundary = new Rectangle2D(x, y, width, height);
    }

    public Rectangle2D getBoundary() {
        return boundary;
    }

    public void setBoundary(Rectangle2D boundaryRect){
        boundary = boundaryRect;
    }

    public boolean checkCollision(Cell b) {
        return b.getBoundary().intersects(getBoundary());
    }

    public void setPosition(int x, int y, double reductionPercent) {
    	this.x = x+(int)(GlobalConstants.PLAYER_WIDTH*reductionPercent);
    	this.y = y+(int)(GlobalConstants.PLAYER_HEIGHT*reductionPercent);
    	boundary = new Rectangle2D(this.x, this.y, width, height);
    }

}
