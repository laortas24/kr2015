public class GameUpdaterThread implements Runnable{
    private volatile boolean isPause, isExit;
    private TablePanel tablePanel;

    public GameUpdaterThread(TablePanel tablePanel){
        Thread t = new Thread(this);
        this.tablePanel = tablePanel;
        pause();
        t.start();
    }

    @Override
    public void run() {
        while (!isExit){
            checkPause();
            tablePanel.update();

            try {
                Thread.sleep(TIME_BETWEEN_SCREEN_UPDATE_IN_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void checkPause(){
        while (isPause)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void stop() {
        pause();
    }

    public void pause(){
        isPause = true;
    }

    public synchronized void resume(){
        isPause = false;
        notify();
    }

    public void start(){
        resume();
    }

    public static final long TIME_BETWEEN_SCREEN_UPDATE_IN_MILLIS = 5;
}