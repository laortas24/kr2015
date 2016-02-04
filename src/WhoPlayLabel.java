import javax.swing.*;
import java.awt.*;

public class WhoPlayLabel extends JLabel{
    public WhoPlayLabel(){
        initWhoPlayLabel();
        clear();
    }

    private void initWhoPlayLabel(){
        setOpaque(true);
        setBackground(new Color(0x000000));
        setForeground(new Color(0xFFFEFD));
        setHorizontalAlignment(CENTER);
        setFont(new Font("Tahoma", Font.BOLD, 20));
    }

    public void setWhoPlay(String name1, String name2){
        setText(name2 + " VS " + name1);
    }

    public void clear(){
        setText("Никто не играет :(");
    }
}