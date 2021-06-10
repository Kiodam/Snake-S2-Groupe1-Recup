package src.model;

import javafx.scene.image.Image;

/**
 * Bonus qui augemenbte la vitesse du jeu
 * */
public class SpeedItem extends Item {
    private Image image = new Image("src/ressource/image/banana.png");
    private static int nbSpeedEaten;

    public SpeedItem(int x, int y, Snake snake, Obstacle obstacle) {
        super(x, y, snake, obstacle);
    }

    public int getPoints() {
        int points = 5;
        return points;
    }

    public double getSpeedBonus() {
        double speedBonus = 5;
        return speedBonus;
    }

    public Image getImage() {
        return image;
    }

    public static int getNbSpeedEaten() {
        return nbSpeedEaten;
    }

    public static void incNbSpeedEaten() {
        nbSpeedEaten++;
    }

    public static void resetNbSpeedEaten() {
        nbSpeedEaten = 0;
    }
}
