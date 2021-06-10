package src.model;

import javafx.scene.image.Image;

public class ScoreItem extends Item {
	private Image image = new Image("src/ressource/image/Food/berry.png");
	public static int nbScoreEaten;

	/**
	 * @param x Largeur de la fenetre.
	 * @param y La hauteur de la fenetre.
	 * @param snake l'obstacle sur lequel la power ne doit pas être placée.
	 */
	public ScoreItem(int x, int y, Snake snake, Obstacle obstacle) {
		super(x, y, snake, obstacle);
	}

	/**
	 * @return l'image lié à cet item
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @return le nombre de points obtenus en prenant cet item
	 */
	public int getPoints() {
		int points = 15;
		return points;
	}

	/**
	 * @return le nombre de scoreItem mangé
	 */
	public static int getNbScoreEaten() {
		return nbScoreEaten;
	}

	/**
	 * réinitialise le nombre de scoreItem mangé
	 */
	public static void resetNbScoreEaten() {
		nbScoreEaten = 0;
	}

	/**
	 * incrémentation du nombre de scoreItem mangé
	 */
	public static void incNbScoreEaten() {
		nbScoreEaten++;
	}
}
