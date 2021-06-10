package src.model;

import javafx.scene.image.Image;

/**
 *  item qui fait apparaître des obstacles
 */
public class ObstacleItem extends Item {
    private Image image = new Image("src/ressource/image/Food/poison.png");
    private static int nbObstacleEaten;

    /**
     * @param x Largeur de la fenetre.
     * @param y La hauteur de la fenetre.
     * @param snake l'obstacle sur lequel la power ne doit pas être placée.
     */
    public ObstacleItem(int x, int y, Snake snake, Obstacle obstacle) {
        super(x, y, snake, obstacle);
    }

    /**
     * @return l'image liée à l'item
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return le nombres de points obtenus avec cet item
     */
    public int getPoints() {
        int points = 5;
        return points;
    }

    /**
     * @return le nombre d'obstable item mangés
     */
    public static int getNbObstacleEaten() {
        return nbObstacleEaten;
    }

    /**
     * réinitialisation du nombre d'obstable mangés pour refaire une partie
     */
    public static void resetNbObstacleEaten() {
        nbObstacleEaten = 0;
    }
}