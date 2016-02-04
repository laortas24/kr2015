import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropMenu extends JMenuBar {
    private JMenuItem gameModeDropList;
    public DropMenu(){
        initGameModeDropList();
        initHelpDropList();
    }

    private void initGameModeDropList(){
        gameModeDropList = new JMenu("Режим игры");

        JMenuItem twoHuman = new JMenuItem("Два человека");
        twoHuman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.tablePanel.setGameModeAndStartGame(TablePanel.GameMode.TWO_HUMAN);
            }
        });

        JMenuItem twoComputers = new JMenuItem("Два компьютера");
        twoComputers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.tablePanel.setGameModeAndStartGame(TablePanel.GameMode.TWO_COMPUTER);
            }
        });

        JMenu humanAndComputer = new JMenu("Человек и компьютер");

        JMenuItem easy = new JMenuItem("Легкий компьютер");
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.tablePanel.setGameModeAndStartGame(TablePanel.GameMode.HUMAN_AND_COMPUTER_EASY);
            }
        });

        JMenuItem medium = new JMenuItem("Средний компьютер");
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.tablePanel.setGameModeAndStartGame(TablePanel.GameMode.HUMAN_AND_COMPUTER_MEDIUM);
            }
        });

        JMenuItem hard = new JMenuItem("Сложный компьютер");
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.tablePanel.setGameModeAndStartGame(TablePanel.GameMode.HUMAN_AND_COMPUTER_HARD);
            }
        });

        humanAndComputer.add(easy);
        humanAndComputer.add(medium);
        humanAndComputer.add(hard);

        gameModeDropList.add(twoHuman);
        gameModeDropList.add(humanAndComputer);
        gameModeDropList.add(twoComputers);

        add(gameModeDropList);
    }

    private void initHelpDropList(){
        JMenu helpDropList = new JMenu("Помощь");

        JMenuItem rules = new JMenuItem("Правила");
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.tablePanel.pauseGame();
                JOptionPane.showMessageDialog(MainFrame.tablePanel,
                        "Настольный тенис. \n" +
                        "В этой игре вам предстоит забыть опоненту как можно больше голов. Гол засчитывается,\n" +
                        "если игровой шар залетел за ракетку противника. Если вы забили " + TablePanel.SCORE_TO_VICTORY +
                        " голов - победа ваша.\n" +
                        "Иначе - вы програли. Приятной игры.",
                        "Правила",
                        JOptionPane.QUESTION_MESSAGE);
                MainFrame.tablePanel.resumeGame();
            }
        });

        JMenuItem forUser = new JMenuItem("Особенности программы");
        forUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.tablePanel.pauseGame();
                JOptionPane.showMessageDialog(MainFrame.tablePanel,
                        "В игре есть три режима: \"Человек против Человека\", \"Человек против Компьютера\"\n" +
                        "и \"Компьютер против Компьютера\". Их можно выбирать во вкладке \"Режим игры\"\n" +
                        "В режиме \"Человек против человек\" один игрок управляет левой ракеткой с помощью\n" +
                        "кнопок \"W\" и \"S\", а второй правой ракеткой с помощью стрелок вверх и вниз на клавиатуре.\n" +
                        "В режиме \"Компьютер против компьютера\" два компьютера соревнуются между собою и человек не может\n" +
                        "вмешиватся. В режиме \"Человек против компьютера\" есть возможность выбрать сложность компьютера.\n" +
                        "Есть три сложности: слабый, седний и простой. Если вы новичок - рекомендуется попрактиковаться на\n" +
                        "простой сложности, а дальше постепенно увеличивать её. \n\n" +
                        "Игра создана @Gatchenko Roman.",
                        "Особенности программы",
                        JOptionPane.INFORMATION_MESSAGE);
                MainFrame.tablePanel.resumeGame();
            }
        });

        helpDropList.add(rules);
        helpDropList.add(forUser);

        add(helpDropList);
    }

    public void openGameModeDropList(){
        gameModeDropList.doClick();
    }
}