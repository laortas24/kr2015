import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel{
    private int score1, score2;

    public ScoreLabel(){
        initScoreLabel();
        paintScores();
    }
    private void initScoreLabel(){
        setOpaque(true);
        setBackground(new Color(0x000000));
        setForeground(new Color(0xFFFEFD));
        setHorizontalAlignment(CENTER);
        setFont(new Font("Jokerman", Font.BOLD, 30));
    }

    private void paintScores(){
        setText(score1 + " : " + score2);
    }

    public void incPlayerOneScores(){
        score1++;
        paintScores();
    }

    public void incPlayerTwoScores(){
        score2++;
        paintScores();
    }

    public int getScoreForPlayerOne(){
        return score1;
    }

    public int getScoreForPlayerTwo(){
        return score2;
    }

    public void clear(){
        score1 = 0;
        score2 = 0;
        paintScores();
    }
}