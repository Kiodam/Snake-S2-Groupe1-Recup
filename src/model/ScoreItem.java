package src.model;

import javafx.scene.image.Image;

public class ScoreItem extends Item {
	private Image image = new Image("src/ressource/image/berry.png");
	public static int nbScoreEaten;

	public ScoreItem(int x, int y, Snake snake, Obstacle obstacle) {
		super(x, y, snake, obstacle);
	}

	public Image getImage() {
		return image;
	}

	public int getPoints() {
		int points = 15;
		return points;
	}

	public static int getNbScoreEaten() {
		return nbScoreEaten;
	}

	public static void resetNbScoreEaten() {
		nbScoreEaten = 0;
	}

	public static void incNbScoreEaten() {
		nbScoreEaten++;
	}
}
