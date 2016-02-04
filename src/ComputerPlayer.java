public class ComputerPlayer implements Player{
    private Racket racket;
    private Ball ball;
    private int currentUpdates;
    private Difficult difficult;

    public enum Difficult{
        EASY(200), MEDIUM(70), HARD(10);

        Difficult(int updateCount){
            this.updateCount = updateCount;
        }

        public int updateCount;
    }

    public ComputerPlayer(Racket racket, Ball ball, Difficult difficult){
        this.ball = ball;
        this.racket = racket;
        this.difficult = difficult;
    }

    public void update(){
        moveTowardBall();
        racket.update();
    }

    @Override
    public String getName() {
        String text = "";
        switch (difficult){
            case EASY:
                text += "Слабак";
                break;
            case MEDIUM:
                text += "Средненький";
                break;
            case HARD:
                text += "Мужик";
                break;
        }

        return text + " (комп)";
    }

    private void moveTowardBall() {
        if (++currentUpdates == difficult.updateCount) {
            int ballCoordinateY = ball.getCurrentY();
            int racketCoordinateY = (int) racket.getCurrentYCoordinate();

            if (ballCoordinateY < racketCoordinateY)
                moveUp();
            else if (ballCoordinateY > racketCoordinateY)
                moveDown();
            else
                stopMoving();

            currentUpdates = 0;
        }
    }

    @Override
    public void moveUp() {
        racket.moveUp();
    }

    @Override
    public void moveDown() {
        racket.moveDown();
    }

    @Override
    public void stopMoving() {
        racket.stopMoving();
    }
}