package src.model;

import javafx.scene.image.Image;

/**L'item qui supprime les murs (cotés)  */
public class GhostWallItem extends Item {
    private Image image = new Image("src/ressource/image/Food/ghost.png");
    private static int nbGhostEaten;

    /**
     * constructeur pour l'item du fantôme
     * @param x Largeur de la fenetre.
     * @param y La hauteur de la fenetre.
     * @param snake l'obstacle sur lequel l'item ne doit pas être placée.
     * @param obstacle les obstacles sur lesquels l'item ne doit pas être placé
     */
    public GhostWallItem(int x, int y, Snake snake, Obstacle obstacle) {
        super(x, y, snake, obstacle);
    }

    /**
     * @return le points obtenus pour cet item
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
     * @return le nombre de ghostItems mangés (pour le compteur sur la fenêtre)
     */
    public static int getNbGhostEaten() {
        return nbGhostEaten;
    }

    /**
     * remet à zéros le nombre de ghostItems mangés
     */
    public static void resetNbGhostEaten() {
        nbGhostEaten = 0;
    }

    /**
     * incrémentation du nombre de ghostItems mangés
     */
    public static void incNbGhostEaten() {
        nbGhostEaten ++;
    }
}
