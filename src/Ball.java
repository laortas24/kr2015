import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
    private static final double STEP_IN_PIXELS = 2.5;
    private static final int DIAMETER_OF_BALL = 30;

    private TablePanel table;
    private double x, y, stepByX, stepByY;

    public Ball(TablePanel table) {
        this.table = table;
        initStartCoordinate();
    }

    private void initStartCoordinate(){
        x = MainFrame.getHufWidth();
        y = MainFrame.getHufHeight();
        stepByX = STEP_IN_PIXELS;
        stepByY = STEP_IN_PIXELS;
    }

    public void update() {
        updateLocation();
        checkCollisionsWidthSides();
        checkCollisionWithRockets();
    }

    private void updateLocation(){
        x += stepByX;
        y += stepByY;
    }

    private void checkCollisionsWidthSides(){
        if (isOutOfLeftSide())
            increaseScoreForPlayerAndRespawnBalls(1);
        else if (isOutOfRightSide())
            increaseScoreForPlayerAndRespawnBalls(2);
        else if (isOutOfTopOrBottom())
            changeDirectionOfMotion();
    }

    private boolean isOutOfLeftSide(){
        return x < 0;
    }

    private void increaseScoreForPlayerAndRespawnBalls(int player){
        table.increaseScore(player);
        x = MainFrame.getHufWidth();
        y = MainFrame.getHufHeight();
        stepByX = -stepByX;
    }

    private boolean isOutOfRightSide(){
        return x > MainFrame.WIDTH - DIAMETER_OF_BALL - 7;
    }

    private boolean isOutOfTopOrBottom(){
        return y < MainFrame.TOP_COORDINATE || y > MainFrame.HEIGHT - DIAMETER_OF_BALL - 29;
    }

    private void changeDirectionOfMotion(){
        stepByY = -stepByY;
    }

    public void checkCollisionWithRockets() {
        if (table.getRacket(1).getBounds().intersects(getBounds()) || table.getRacket(2).getBounds().intersects(getBounds()))
            stepByX = -stepByX;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, DIAMETER_OF_BALL, DIAMETER_OF_BALL);
    }

    public void paint(Graphics g) {
        g.fillOval((int)x, (int)y, DIAMETER_OF_BALL, DIAMETER_OF_BALL);
    }

    public int getCurrentY(){
        return (int)y;
    }
}