package src.model;

import javafx.scene.image.Image;

/**La pomme qui supprime les murs (cotés)  */
public class GhostWallItem extends Item {
    private Image image = new Image("src/ressource/image/ghost.png");
    private static int nbGhostEaten;

    public GhostWallItem(int x, int y, Snake snake, Obstacle obstacle) {
        super(x, y, snake, obstacle);
    }

    public int getPoints() {
        int points = 5;
        return points;
    }

    public Image getImage() {
        return image;
    }

    public static int getNbGhostEaten() {
        return nbGhostEaten;
    }

    public static void resetNbGhostEaten() {
        nbGhostEaten = 0;
    }

    public static void incNbGhostEaten() {
        nbGhostEaten ++;
    }
}
