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

    // tableau avec les liens pour les différentes images
    private String[] paths = {"src/ressource/image/Multiplicator/X2.png", "src/ressource/image/Multiplicator/X3.png", "src/ressource/image/Multiplicator/X5.png"};
    private Image image;

    /**
     * @@param x Largeur de la fenetre.
     * @param y La hauteur de la fenetre.
     * @param snake l'obstacle sur lequel la power ne doit pas être placée.
     */
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

    /**
     * @return retourne l'image de l'item
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return le nombre de points donné par l'item
     */
    public int getPoints() {
        return 0;
    }

    /**
     * @return le coefficient affecté pour la génération de cet item
     */
    public static int getCoef() {
        return coef;
    }

    /**
     * @return true si un multiplicator à été mangé false sinon
     */
    public static boolean isActif() {
        return isActif;
    }

    /**
     * @param isActif défini si l'item est mangé ou non
     */
    public static void setIsActif(boolean isActif) {
        Multiplicator.isActif = isActif;
    }

    /**
     * permet de défini la valeur du coef
     * @param coef valeur définie pour le coef
     */
    public static void setCoef(int coef) {
        Multiplicator.coef = coef;
    }

    /**
     * lance le timer au moment où l'item est mangé
     */
    public void runTimer() {
        isActif = true;
        this.timerReset.schedule(this.resetTimerTask, DELAY_RESET);
    }
}

