package src.model;

import javafx.scene.image.Image;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Multiplicator extends Item {
    private static Random random = new Random();

    private static int coef = 1;
    private static boolean isActif = false;
    private Timer timerReset = new Timer();

    // in millisecond
    static final int DELAY_RESET = 10000;
    private TimerTask resetTimerTask = new TimerTask() {
        public void run() {
            isActif = false;
            coef = 1;
            timerReset.cancel();
            timerReset.purge();
        }
    };

    private String[] paths = {"src/ressource/image/X2.png", "src/ressource/image/X3.png", "src/ressource/image/X5.png"};
    private Image image;

    public Multiplicator(int x, int y, Snake snake, Obstacle obstacle) {
        super(x, y, snake, obstacle);
        int randInt = random.nextInt(10);
        if (randInt % 9 == 0) {
            image = new Image(paths[2]);
            coef = 5;
        } else if (randInt % 3 == 0) {
            image = new Image(paths[1]);
            coef = 3;
        } else {
            image = new Image(paths[0]);
            coef = 2;
        }
    }

    public Image getImage() {
        return image;
    }

    public int getPoints() {
        return 0;
    }

    public static int getCoef() {
        return coef;
    }

    public static boolean isActif() {
        return isActif;
    }

    public static void setIsActif(boolean isActif) {
        Multiplicator.isActif = isActif;
    }

    public static void setCoef(int coef) {
        Multiplicator.coef = coef;
    }


    public void runTimer() {
        isActif = true;
        this.timerReset.schedule(this.resetTimerTask, DELAY_RESET);
    }
}

