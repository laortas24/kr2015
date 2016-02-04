import java.awt.Graphics;
import java.awt.Rectangle;

public class Racket {
    public static final double STEP_IN_PIXELS = 2;
    private static final int WIDTH = 10, HEIGHT = 100;
    private double xCoordinate, yCoordinate, oneStep;

    public Racket(int xCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = MainFrame.getHufHeight();
    }

    public void update() {
        if (isWithinTheScreen())
            yCoordinate += oneStep;
        else if (isHitTop())
            yCoordinate += STEP_IN_PIXELS;
        else if (isHitBottom())
            yCoordinate -= STEP_IN_PIXELS;
    }

    private boolean isWithinTheScreen(){
        return yCoordinate > MainFrame.TOP_COORDINATE && yCoordinate < MainFrame.HEIGHT - HEIGHT - 29;
    }

    private boolean isHitTop(){
        return yCoordinate <= MainFrame.TOP_COORDINATE;
    }

    private boolean isHitBottom(){
        return yCoordinate >= MainFrame.HEIGHT - HEIGHT - 29;
    }

    public void moveUp(){
        oneStep = -STEP_IN_PIXELS;
    }

    public void moveDown(){
        oneStep = STEP_IN_PIXELS;
    }

    public void stopMoving(){
        oneStep = 0;
    }

    public double getCurrentYCoordinate(){
        return yCoordinate;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)xCoordinate, (int)yCoordinate, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        g.fillRect((int)xCoordinate, (int)yCoordinate, WIDTH, HEIGHT);
    }
}