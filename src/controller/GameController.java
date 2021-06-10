package src.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import src.model.*;
import src.view.GameView;

import java.util.Random;

/**
 * Contient des méthodes qui contrôlent ce qui se passe
 * par saisie de l'utilisateur.
 */

public class GameController {
    private static Snake snake;
    private static Item item;
    private static GameView gameView;
    private static Game game;
    private static Stage stage;
    private Boolean play = true;
    private String coin = "src/ressource/coinSound.wav";
    private static Obstacle obstacle;
    public static KeyCode lastKey = KeyCode.LEFT;
    private static boolean keyPressed = false;

    /**
     * La direction par défaut dans le jeu est à gauche.
     */
    public static KeyCode key = KeyCode.LEFT;

    /**
     * Enregistre les références d'objet pour le modèle et la vue, puis
     * ils peuvent être manipulés ou contrôlés.
     * @param snake les informations de l'objet snake
     * @param item correspond à l'objet qui apparaît dans le jeu
     * @param game le jeu et toutes ses informations
     * @param gameView la vue du jeu
     * @param obstacle les obstacles présents dans le jeu
     */
    public GameController(Snake snake, Item item, Game game, GameView gameView, Obstacle obstacle, Stage stage) {
        this.snake = snake;
        this.item = item;
        this.gameView = gameView;
        this.game = game;
        this.stage = stage;

        this.obstacle = obstacle;
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setRate(game.getSpeed());
    }

    /**
     * Le jeu boucle aussi longtemps que le jeu est lancé par l'utilisateur
     */
    private static final Timeline animation = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        // Quand le serpent meurt, l'animation ne doit rien faire
        if (snake.getDead()) {
            return;
        } else {
            if (key.equals(KeyCode.LEFT)) {
                snake.move('L');
            } else if (key.equals(KeyCode.RIGHT)) {
                snake.move('R');
            } else if (key.equals(KeyCode.UP)) {
                snake.move('U');
            } else if (key.equals(KeyCode.DOWN)) {
                snake.move('D');
            }

            if (!snake.getDead() && snake.ateApple(item)) {
                //   mediaPlayer.play();
                //    mediaPlayer = new MediaPlayer(coinSound);
                powerItem();
                game.incScore();
                newItem();
            }

            if (game.gameWon()) {
                gameView.drawGrid(item, snake, obstacle, gameView.graphicsContext);
                return;
            }

            keyPressed = false;

            if (!snake.getDead() || snake.hitSelf()) {
                gameView.drawGrid(item, snake, obstacle, gameView.graphicsContext);
            }

            gameView.gameWon();
            gameView.gameOver(snake);
            gameView.updateScoreLabel();
            gameView.updateMultiplicatorLabel();
            gameView.updateAppleGridPane();
        }
    }));

    /**
     * handle est appelé en appuyant sur le clavier. Plusieurs choses différentes peuvent arriver :
     * 1. Une touche fléchée est enfoncée et le snake change de position (sauf s'il est en
     * dans le sens inverse) et/ou lancer le jeu s'il était en pause.
     * 2. R est pressé et le jeu est redémarré.
     * 3. P est enfoncé et le jeu est mis en pause
     */
    public void handle(KeyEvent event) {
        if (event.getCode().isArrowKey()) animation.play();

        if (valKey(event.getCode()) && !keyPressed) {
            lastKey = key;
            key = event.getCode();
            keyPressed = true;
            gameView.turnDir(snake);
        }

        // le jeu se met en pause si si la touche "P" est employé
        if (event.getCode().equals(KeyCode.P)) {
            if (play) {
                play = false;
                animation.stop();
                gameView.returnButton.setVisible(true);
                gameView.restartButton.setVisible(true);
            } else {
                play = true;
                animation.setCycleCount(Animation.INDEFINITE);
                animation.setRate(game.getSpeed());
                gameView.returnButton.setVisible(false);
                gameView.restartButton.setVisible(false);
            }
        }

        // Le jeu se redémarre si on appuie sur  R
        if (event.getCode().equals(KeyCode.R)) {
            reset();
        }

        // le jeu se met en pleine écran si on appuie sur F  //// FONCTIONNE MAL
        if (event.getCode().equals(KeyCode.F)) {
            gameView.scaleToFullscreen(true, stage, obstacle, snake, item);
            gameView.highScoreLabel.setStyle("-fx-font-size: 50;");
            gameView.gameOverLabel.setStyle("-fx-font-size: 50;");
            gameView.gameWonLabel.setStyle("-fx-font-size: 50;");
            gameView.scoreLabel.setStyle("-fx-font-size: 35;");
        }

        // Désactive le pleine écran
        if (event.getCode().equals(KeyCode.MINUS)) {
            gameView.scaleToFullscreen(false, stage, obstacle, snake, item);
            gameView.highScoreLabel.setStyle("-fx-font-size: 15;");
            gameView.gameOverLabel.setStyle("-fx-font-size: 15;");
            gameView.gameWonLabel.setStyle("-fx-font-size: 15;");
            gameView.scoreLabel.setStyle("-fx-font-size: 15;");
        }

        if (event.getCode().equals(KeyCode.ESCAPE)) {
            MainMenuController mainMenuController = new MainMenuController();
            mainMenuController.getViewManager().getPrimaryStage().show();
            mainMenuController.getViewManager().getGameStage().close();
        }
    }

    /**
     * Return true si la fleche est une touche directionnel et qu'elle est différente de la
     * touche opposée
     */
    private boolean valKey(KeyCode key) {
        if (!key.isArrowKey()) return false;
        else {
            if (key.equals(KeyCode.LEFT) && !GameController.key.equals(KeyCode.RIGHT)) {
                return true;
            }
            if (key.equals(KeyCode.RIGHT) && !GameController.key.equals(KeyCode.LEFT)) {
                return true;
            }
            if (key.equals(KeyCode.DOWN) && !GameController.key.equals(KeyCode.UP)) {
                return true;
            }
            return key.equals(KeyCode.UP) && !GameController.key.equals(KeyCode.DOWN);
        }
    }

    /**
     * Réinitialise toutes les valeurs du jeu pour recommencer une partie
     */
    public static void reset() {

        // Retourne au début de la TimeLine de l'animation et l'arrête après.
        animation.jumpTo(Duration.ZERO);
        animation.stop();

        // Transforme les snake  /  apples comme ils étaient initialement
        snake = new Snake(game.getWidth(), game.getHeight(), game.isNoBorder(), obstacle);
        item = new Item(game.getWidth(), game.getHeight(), snake, obstacle);

        // Remet les variables par défaut
        animation.setRate(game.getSpeed());
        key = KeyCode.LEFT;
        keyPressed = false;
        game.resetScore();
        obstacle.reset();
        snake.setNbAppleEaten(0);
        obstacle.resetNbObstacleItemToEat();
        resetAllItemEaten();

        // Supression / mise à jour des vues
        gameView.gameWon();
        gameView.gameOver(snake);
        gameView.updateScoreLabel();
        gameView.updateAppleGridPane();
        gameView.updateMultiplicatorLabel();
        gameView.viewHighScore.setVisible(false);
        gameView.restartButton.setVisible(false);
        gameView.returnButton.setVisible(false);
        gameView.highScoreLabel.setVisible(false);

        // réinitialise les canvas
        gameView.drawGrid(item, snake, obstacle, gameView.graphicsContext);

        // réinitialise le coefficient multiplicateur
        Multiplicator.setCoef(1);

        Multiplicator.setIsActif(false);
    }

    /**
     * gérer les actions selon le type de pomme appelé
     */
    private static void powerItem() {
        // action à faire quel que soit l'item mangé
        animation.setRate(game.getSpeed());
        snake.ignoreBorders(false);
        snake.setNbAppleEaten(snake.getNbAppleEaten() + 1);

        if (item instanceof SpeedItem) {
            SpeedItem tempApple = (SpeedItem) item;
            animation.setRate(game.getSpeed() + tempApple.getSpeedBonus());
            SpeedItem.incNbSpeedEaten();

        } else if (item instanceof ObstacleItem) {
            Random rnd = new Random();
            obstacle.newObstacles(snake, item, rnd.nextInt(game.getWidth() * game.getHeight() / Math.max(Math.abs(30 - obstacle.getNbObstableItemToEat()),1) + 3));
            game.decMaxScore(obstacle.getObstacles().size());
            snake.setNbAppleEaten(0);
            Obstacle.setIsBlocked(true);

        } else if (item instanceof GhostWallItem) {
            snake.ignoreBorders(true);
            GhostWallItem.incNbGhostEaten();
        } else if (item instanceof ScoreItem) {
            ScoreItem.incNbScoreEaten();

        } else if (item instanceof Multiplicator) {
            ((Multiplicator) item).runTimer();
        } else {
            Item.incNbItemEaten();
        }

        if (snake.getNbAppleEaten() >= obstacle.getNbObstableItemToEat() && Obstacle.getIsBlocked()) {
            obstacle.reset();
            snake.setNbAppleEaten(0);
            obstacle.incrAppleToEat();
            Obstacle.setIsBlocked(false);
        }

        game.addPoints(item.getPoints());
    }

    /**
     * Fabrique un nouveau type d'item aléatoire avec moins de chance que ce soit un bonus .
     */
    private static void newItem() {
        int gw = game.getWidth();
        int gh = game.getHeight();

        if (game.isPowerUp()) {
            Random rnd = new Random();
            int ran = rnd.nextInt(12);
            if (ran == 1) {
                item = new ScoreItem(gw, gh, snake, obstacle);
            } else if (ran == 2) {
                item = new SpeedItem(gw, gh, snake, obstacle);
            } else if (ran == 3) {
                item = new GhostWallItem(gw, gh, snake, obstacle);
            } else if (ran == 4 && obstacle.getObstacles().size() == 0) {
                item = new ObstacleItem(gw, gh, snake, obstacle);
            } else if ((ran < 7) && !Multiplicator.isActif()) {
                item = new Multiplicator(gw, gh, snake, obstacle);
            } else {
                item = new Item(gw, gh, snake, obstacle);
            }
        } else {
            item = new Item(gw, gh, snake, obstacle);
        }
    }

    /**
     * remet à zéro tous les compteurs d'item
     */
    public static void resetAllItemEaten() {
        Item.resetNbItemEaten();
        ObstacleItem.resetNbObstacleEaten();
        SpeedItem.resetNbSpeedEaten();
        ScoreItem.resetNbScoreEaten();
        GhostWallItem.resetNbGhostEaten();
    }
}