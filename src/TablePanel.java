import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TablePanel extends JPanel {
    public static final int SCORE_TO_VICTORY = 10;

    private Ball ball;
    private Racket racket1, racket2;
    private Player player1, player2;
    private JPanel panelWithWhoPlayAndScoreLabels;
    private ScoreLabel scoreLabel;
    private WhoPlayLabel whoPlayLabel;
    private GameUpdaterThread gameUpdaterThread;


    public enum GameMode{
        TWO_HUMAN, TWO_COMPUTER, HUMAN_AND_COMPUTER_EASY, HUMAN_AND_COMPUTER_MEDIUM, HUMAN_AND_COMPUTER_HARD
    }

    public TablePanel() {
        initPongPanel();
        initRackets();
        initPlayersAndBall();
        initKeyListenerForPlayers();
        initGameUpdaterThread();
        initPanelWithWhoPlayAndScoreLabel();
        initScorePanel();
        initWhoPlayLabel();
        pauseGame();
    }

    private void initPongPanel(){
        setBackground(Color.WHITE);
        setFocusable(true);
        setLayout(new BorderLayout());
    }

    private void initRackets(){
        racket1 = new Racket(MainFrame.WIDTH - 36);
        racket2 = new Racket(20);
    }

    private void initPlayersAndBall(){
        ball = new Ball(this);
        player1 = new HumanPlayer(racket1);
        player2 = new HumanPlayer(racket2);
    }

    private void initKeyListenerForPlayers(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                startMovingPlayers(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                stopMovingPlayers(e);
            }
        });
    }

    private void initGameUpdaterThread(){
        gameUpdaterThread = new GameUpdaterThread(this);
        gameUpdaterThread.start();
    }

    private void initPanelWithWhoPlayAndScoreLabel(){
        panelWithWhoPlayAndScoreLabels = new JPanel(new BorderLayout());
        add(panelWithWhoPlayAndScoreLabels, BorderLayout.NORTH);
    }

    private void initScorePanel(){
        scoreLabel = new ScoreLabel();
        panelWithWhoPlayAndScoreLabels.add(scoreLabel, BorderLayout.CENTER);
    }

    private void initWhoPlayLabel(){
        whoPlayLabel = new WhoPlayLabel();
        panelWithWhoPlayAndScoreLabels.add(whoPlayLabel, BorderLayout.NORTH);
    }

    public void setGameModeAndStartGame(GameMode gameMode){
        stopGame();
        switch (gameMode){
            case TWO_COMPUTER:
                player1 = new ComputerPlayer(racket1, ball, ComputerPlayer.Difficult.MEDIUM);
                player2 = new ComputerPlayer(racket2, ball, ComputerPlayer.Difficult.HARD);
                break;
            case TWO_HUMAN:
                player1 = new HumanPlayer(racket1);
                player2 = new HumanPlayer(racket2);
                break;
            case HUMAN_AND_COMPUTER_EASY:
                player1 = new HumanPlayer(racket1);
                player2 = new ComputerPlayer(racket2, ball, ComputerPlayer.Difficult.EASY);
                break;
            case HUMAN_AND_COMPUTER_MEDIUM:
                player1 = new HumanPlayer(racket1);
                player2 = new ComputerPlayer(racket2, ball, ComputerPlayer.Difficult.MEDIUM);
                break;
            case HUMAN_AND_COMPUTER_HARD:
                player1 = new HumanPlayer(racket1);
                player2 = new ComputerPlayer(racket2, ball, ComputerPlayer.Difficult.HARD);
                break;

        }
        whoPlayLabel.setWhoPlay(player1.getName(), player2.getName());
        startGame();
    }

    public void startGame(){
        scoreLabel.clear();
        gameUpdaterThread.start();
    }

    public void pauseGame(){
        player1.stopMoving();
        player2.stopMoving();
        gameUpdaterThread.pause();
    }

    private void stopGame(){
        pauseGame();
        whoPlayLabel.clear();
        scoreLabel.clear();
    }

    public void resumeGame(){
        gameUpdaterThread.resume();
    }

    public Racket getRacket(int racketNo) {
        if (racketNo == 1)
            return racket1;
        else
            return racket2;
    }

    public void increaseScore(int playerNo) {
        if (playerNo == 1)
            scoreLabel.incPlayerOneScores();
        else
            scoreLabel.incPlayerTwoScores();

        checkGameEnd();
    }

    private void checkGameEnd(){
        String text = "";

        if (scoreLabel.getScoreForPlayerOne() == SCORE_TO_VICTORY)
            text = player1.getName() + " выиграл.";
        else if (scoreLabel.getScoreForPlayerTwo() == SCORE_TO_VICTORY)
            text = player2.getName() + " выиграл";

        if (!text.equals("")) {
            Object[] options = {"Заново", "Выбрать другой режим"};
            pauseGame();
            int result = JOptionPane.showOptionDialog(this, text,
                    "Игра окончена",
                    JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            System.out.println(result);
            if (result == 0)
                startGame();
            else{
                MainFrame.dropMenu.openGameModeDropList();
                stopGame();
            }
        }
    }

    public void update() {
        ball.update();
        player1.update();
        player2.update();
        repaint();
    }

    private void startMovingPlayers(KeyEvent e){
        if (isPlayerOneUp(e))
            player1.moveUp();
        else
        if (isPlayerOneDown(e))
            player1.moveDown();
        else
        if (isPlayerTwoUp(e))
            player2.moveUp();
        else
        if (isPlayerTwoDown(e))
            player2.moveDown();
    }

    private void stopMovingPlayers(KeyEvent e){
        if (isPlayerOneUp(e) || isPlayerOneDown(e))
            player1.stopMoving();
        else if (isPlayerTwoDown(e) || isPlayerTwoUp(e))
            player2.stopMoving();
    }

    private boolean isPlayerOneUp(KeyEvent e){
        return e.getKeyCode() == KeyEvent.VK_UP;
    }

    private boolean isPlayerOneDown(KeyEvent e){
        return e.getKeyCode() == KeyEvent.VK_DOWN;
    }

    private boolean isPlayerTwoUp(KeyEvent e){
        return e.getKeyCode() == KeyEvent.VK_W;
    }

    private boolean isPlayerTwoDown(KeyEvent e){
        return e.getKeyCode() == KeyEvent.VK_S;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.paint(g);
        racket1.paint(g);
        racket2.paint(g);
    }
}