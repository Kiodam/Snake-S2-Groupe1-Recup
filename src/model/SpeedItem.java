package src.model;

import javafx.scene.image.Image;

/**
 * Bonus qui augemenbte la vitesse du jeu
 * */
public class SpeedItem extends Item {
    private Image image = new Image("src/ressource/image/Food/banana.png");
    private static int nbSpeedEaten;

    /**
     * @param x Largeur de la fenetre.
     * @param y La hauteur de la fenetre.
     * @param snake l'obstacle sur lequel la power ne doit pas être placée.
     */
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
