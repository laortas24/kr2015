import java.awt.Color;

import javax.swing.*;

public class MainFrame extends JFrame {
    public final static int WIDTH = 900, HEIGHT = 550;
    public static final int TOP_COORDINATE = 70;
    public static TablePanel tablePanel;
    public static DropMenu dropMenu;

    public MainFrame() {
        initMainFrame();
        initPongPanel();
        initDropMenu();
    }

    public void initMainFrame(){
        setSize(WIDTH, HEIGHT + 20);
        setTitle("Table tennis");
        setBackground(Color.WHITE);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initPongPanel(){
        tablePanel = new TablePanel();
        add(tablePanel);
    }

    private void initDropMenu(){
        dropMenu = new DropMenu();
        setJMenuBar(dropMenu);
    }

    public static int getHufWidth(){
        return WIDTH / 2;
    }

    public static int getHufHeight(){
        return HEIGHT / 2;
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}