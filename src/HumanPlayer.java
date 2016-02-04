public class HumanPlayer implements Player {
    private Racket racket;

    public HumanPlayer (Racket racket){
        this.racket = racket;
    }

    @Override
    public void update() {
        racket.update();
    }

    @Override
    public String getName() {
        return "Человек";
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