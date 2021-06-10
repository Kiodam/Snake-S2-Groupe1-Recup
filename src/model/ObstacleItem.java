package src.model;

import javafx.scene.image.Image;



public class ObstacleItem extends Item {
    private Image image = new Image("src/ressource/image/Food/poison.png");
    private static int nbObstacleEaten;

    public ObstacleItem(int x, int y, Snake snake, Obstacle obstacle) {
        super(x, y, snake, obstacle);
    }

    public Image getImage() {
        return image;
    }

    public int getPoints() {
        int points = 5;
        return points;
    }

    public static int getNbObstacleEaten() {
        return nbObstacleEaten;
    }

    public static void resetNbObstacleEaten() {
        nbObstacleEaten = 0;
    }

    public static void incNbObstableEaten() {
        nbObstacleEaten++;
    }
}