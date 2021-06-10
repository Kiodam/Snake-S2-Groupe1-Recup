package src.model;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.Random;


/**
* Modèle destiné à contenir des informations sur une instance de power.
**/
public class Item {
    private Point pos;
    private Image image = new Image("src/ressource/image/apple.png");
    private static int nbItemEaten;

    /**
     * @param x Largeur de la fenetre.
     * @param y La hauteur de la fenetre.
     * @param snake l'obstacle sur lequel la power ne doit pas être placée.
     */
    public Item(int x, int y, Snake snake, Obstacle obstacle) {
        int tx;
        int ty;
        do {
            Random random = new Random();
            tx = random.nextInt(x);
            ty = random.nextInt(y);
            pos = new Point(tx, ty);
        } while (snake.getHead().equals(pos) || snake.getBody().contains(pos) || obstacle.getPointsObstacles().contains(pos));
    }

    /**
     * @return la position de l'item
     */
    public Point getPos() {
        return new Point(pos);
    }

    /**
     * le nombre de points donnés par l'item
     */
    public int getPoints() {
        int points = 5;
        return points;
    }

    /**
     * @return l'image de l'item
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return le nombre d'item mangé
     */
    public static int getNbItemEaten() {
        return nbItemEaten;
    }

    /**
     * réinitialise le nombre d'item mangé
     */
    public static void resetNbItemEaten() {
        nbItemEaten = 0;
    }

    /**
     * incrémentation du nombre d'item mangé
     */
    public static void incNbItemEaten() {
        nbItemEaten++;
    }


}