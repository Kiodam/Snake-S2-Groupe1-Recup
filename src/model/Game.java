package src.model;

import javafx.scene.paint.Color;

/**
 * Garede en mémoire des infomations du jeu ( vitesse , score ... )
 */
public class Game {
    private Color color = Color.web("ccffff");
    private int width;
    private int height;
    private int score;
    private int maxScore;
    private double multiplier;
    private int scorePoints;
    private double speed;
    private boolean powerUp;
    private boolean noBorder;

    /**
     * @param x largeur fenetre
     * @param y longueur fenetre
     */
    public Game(int x, int y, boolean powerUp, char difficulty) {
        if (difficulty == 'E') {
            speed = 8.5;
            noBorder = true;
        } else if (difficulty == 'N') {
            speed = 8.5;
            noBorder = false;
        } else if (difficulty == 'H') {
            speed = 13;
            noBorder = false;
        }
        this.powerUp = powerUp;
        width = x;
        height = y;
        maxScore = x * y - 2;
        this.powerUp = powerUp;
        if (height > 50) {
            multiplier = 10;
        } else {
            multiplier = 20;
        }
    }

    /**
     *return true si score = max score
     */
    public boolean gameWon() {
        return score == maxScore;
    }

    /**
     * incrémentation du score .
     */
    public void incScore() {
        score++;
    }

    /**
     * décrémentation du score max en fonction d'un entienr
     */
    public void decMaxScore(int num) {
        this.maxScore -= num;
    }

    /**
     * récupère la largeur de la fenêtre
     * @return la largeur de la fenêtre
     */
    public int getWidth() {
        return width;
    }

    /**
     * récupère la hauteur de la fenêtre
     * @return la hauteur de la fenêtre
     */
    public int getHeight() {
        return height;
    }

    /**
     * remet le score à 0 pour recommencer la partie
     */
    public void resetScore() {
        score = 0;
        scorePoints = 0;
    }

    /**
     * récupère la couleur de l'objet choisi
     * @return la couleur d'un objet
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return
     */
    public double getMultiplier() {
        return multiplier;
    }

    public void addPoints(int n) {
        scorePoints += n * Multiplicator.getCoef();
    }

    public int getScorePoints() {
        return scorePoints;
    }

    public boolean isPowerUp() {
        return powerUp;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isNoBorder() {
        return noBorder;
    }
}
