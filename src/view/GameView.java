package src.view;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import src.controller.GameController;
import src.controller.MainMenuController;
import src.model.*;

import java.awt.*;

/**
 * Information visuels / canvans ici
 */
public class GameView {

    public Canvas scoreCanvas;
    public GraphicsContext scoreGraphicsContext;

    public Canvas gridCanvas;
    public GraphicsContext graphicsContext;

    private Image redApple = new Image("src/ressource/image/apple.png");
    private Image berry = new Image("src/ressource/image/berry.png");
    private Image ghost = new Image("src/ressource/image/ghost.png");
    private Image banana = new Image("src/ressource/image/banana.png");
    private Image brownApple = new Image("src/ressource/image/poison.png");

    public Label gameOverLabel;
    public Label gameWonLabel;
    public Label scoreLabel;
    public Label multiplicatorLabel;
    public TilePane tileMultiplicator;
    public TilePane tileButtons;
    public GridPane gridPaneApple;

    public Label nbRedAppleImage;
    public Label nbBlueAppleImage;
    public Label nbYellowAppleImage;
    public Label nbRainbowAppleImage;
    public Label nbBrownAppleImage;

    public Button viewHighScore;
    public Button restartButton;
    public Button returnButton;
    public Button returnButtonScore;
    private boolean gameOverVis = false;
    private boolean gameWonVis = false;

    private Game game;
    private double initX;
    private double initY;
    private double fullScale = 1;
    private boolean fullsize = false;
    private boolean gameRunning = false;
    private HighScore highScore;
    public Label highScoreLabel = new Label();
    private boolean highScoreVis = false;
    public TextField userName = new TextField();
    private int[][] turnArray = new int[MainMenuController.getxValue()][MainMenuController.getyVALUE()];

    public GameView(Game game) {
        this.game = game;
        highScore = new HighScore();
    }

    public void makeScene(Scene scene, Snake snake, Item item) {
        scoreCanvas = new Canvas(scene.getWidth(), scene.getHeight());
        scoreGraphicsContext = scoreCanvas.getGraphicsContext2D();
        scoreGraphicsContext.setFill(Color.web("27ffd4"));
        scoreGraphicsContext.fillRect(0, scoreCanvas.getHeight()-100, scoreCanvas.getWidth(), 100);

        gridCanvas = new Canvas(scene.getWidth(), scene.getHeight());
        initX = scene.getWidth();
        initY = scene.getHeight();

        graphicsContext = gridCanvas.getGraphicsContext2D();
        drawGrid(item, snake, graphicsContext);

        gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setId("gameOverLabel");
        String css = "src/ressource/style/styling.css";
        gameOverLabel.getStylesheets().add(css);
        gameOverLabel.setVisible(false);

        gameWonLabel = new Label("YOU WON!");
        gameWonLabel.setId("gameWonLabel");
        gameWonLabel.getStylesheets().add(css);
        gameWonLabel.setVisible(false);

        scoreLabel = new Label("Score : " + game.getScorePoints()+"\n  ");
        scoreLabel.setId("scoreLabel");
        scoreLabel.getStylesheets().add(css);
        scoreLabel.setFont(new Font("Arial", 20));
        scoreLabel.setVisible(true);

        tileMultiplicator = new TilePane();

        ImageView redAppleImage = new ImageView();
        makeAppleImages(redAppleImage, redApple);
        nbRedAppleImage = new Label(" : 0  ");

        ImageView blueAppleImage = new ImageView();
        makeAppleImages(blueAppleImage, berry);
        nbBlueAppleImage = new Label(" : 0  ");

        ImageView yellowAppleImage = new ImageView();
        makeAppleImages(yellowAppleImage, banana);
        nbYellowAppleImage = new Label(" : 0  ");

        ImageView rainbowAppleImage = new ImageView();
        makeAppleImages(rainbowAppleImage, ghost);
        nbRainbowAppleImage = new Label(" : 0  ");

        ImageView brownAppleImage = new ImageView();
        makeAppleImages(brownAppleImage, brownApple);
        nbBrownAppleImage = new Label(" : 0  ");

        multiplicatorLabel = new Label("point x "+Multiplicator.getCoef()+"\n ");
        multiplicatorLabel.setId("scoreLabel");
        multiplicatorLabel.getStylesheets().add(css);
        multiplicatorLabel.setFont(new Font("Arial", 20));
        multiplicatorLabel.setVisible(true);

        tileMultiplicator.getChildren().add(multiplicatorLabel);
        multiplicatorLabel.setAlignment(Pos.CENTER_LEFT);
        tileMultiplicator.setAlignment(Pos.BOTTOM_RIGHT);

        gridPaneApple = new GridPane();
        gridPaneApple.add(redAppleImage,0,0);
        gridPaneApple.add(nbRedAppleImage,1,0);
        gridPaneApple.add(blueAppleImage,2,0);
        gridPaneApple.add(nbBlueAppleImage,3,0);
        gridPaneApple.add(yellowAppleImage,0,1);
        gridPaneApple.add(nbYellowAppleImage,1,1);
        gridPaneApple.add(rainbowAppleImage,2,1);
        gridPaneApple.add(nbRainbowAppleImage,3,1);
        gridPaneApple.add(brownAppleImage,0,2);
        gridPaneApple.add(nbBrownAppleImage,1,2);

        gridPaneApple.setAlignment(Pos.BOTTOM_CENTER);
        gridPaneApple.setVgap(5);

        viewHighScore = new Button();
        viewHighScore.setId("highScoreButton");
        viewHighScore.getStylesheets().add(css);
        viewHighScore.setPrefSize(180,40);
        viewHighScore.setText("Enregistrer le score");
        viewHighScore.setOnAction(event -> {
            viewHighScore.setVisible(false);
            restartButton.setVisible(false);
            returnButton.setVisible(false);
            showHighScore(true);
        });
        viewHighScore.setVisible(false);

        restartButton = new Button();
        restartButton.setId("restartButton");
        restartButton.getStylesheets().add(css);
        restartButton.setPrefSize(115,40);
        restartButton.setText("Restart");
        restartButton.setOnAction(event -> {
            restartButton.setVisible(false);
            GameController.reset();
        });
        restartButton.setVisible(false);

        returnButton = new Button();
        returnButton.setId("returnButton");
        returnButton.getStylesheets().add(css);
        returnButton.setPrefSize(90,40);
        returnButton.setText("Return");
        returnButton.setOnAction(event -> {
            GameController.reset();
            MainMenuController mainMenuController = new MainMenuController();
            mainMenuController.getViewManager().getPrimaryStage().show();
            mainMenuController.getViewManager().getGameStage().close();
        });
        returnButton.setVisible(false);

        returnButtonScore = new Button();
        returnButtonScore.setId("returnButtonScore");
        returnButtonScore.getStylesheets().add(css);
        returnButtonScore.setPrefSize(90,40);
        returnButtonScore.setText("Return");
        returnButtonScore.setOnAction(event -> {
            GameController.reset();
            MainMenuController mainMenuController = new MainMenuController();
            mainMenuController.getViewManager().getPrimaryStage().show();
            mainMenuController.getViewManager().getGameStage().close();
        });
        returnButtonScore.setVisible(false);

        tileButtons = new TilePane(Orientation.VERTICAL);
        tileButtons.setVgap(2);
        tileButtons.setAlignment(Pos.CENTER);
        tileButtons.getChildren().addAll(viewHighScore, restartButton, returnButton);

        userName = new TextField("Nom");
        userName.setId("userNameTextField");
        userName.getStylesheets().add(css);
        userName.setVisible(false);
        userName.setAlignment(Pos.CENTER);
        addTextLimiter(userName, 3);
        userName.setMaxWidth(100);

        highScoreLabel = new Label("HIGH SCORE \n");
        highScoreLabel.setId("highScoreLabel");
        highScoreLabel.getStylesheets().add(css);
        highScoreLabel.setVisible(false);
    }

    /**
     * Mise à l'échelle des graphiques du jeu en plein écran
     */
    public void scaleToFullscreen(boolean fullScreen, Stage primaryStage, Obstacle obstacle, Snake snake, Item item) {
        primaryStage.setFullScreen(fullScreen);

        double xScaling = Screen.getPrimary().getVisualBounds().getHeight() / initY;
        double yScaling = Screen.getPrimary().getVisualBounds().getWidth() / initX;

        if (fullScreen && !fullsize) {
            if (xScaling < yScaling) {
                fullScale = xScaling;
            } else {
                fullScale = yScaling;
            }

            gridCanvas.setWidth(gridCanvas.getWidth() * fullScale);
            gridCanvas.setHeight(gridCanvas.getHeight() * fullScale);
            fullsize = true;
        } else if (!fullScreen) {
            fullScale = 1;
            gridCanvas.setWidth(initX);
            gridCanvas.setHeight(initY);
            fullsize = false;
        }

        // gameover/gamewon labels adapté selon le plein écran
        gameOverLabel.setFont(new Font("Arial", 25 * fullScale));
        gameWonLabel.setFont(new Font("Arial", 25 * fullScale));
        drawGrid(item, snake, obstacle, graphicsContext);
    }

    /**
     * Met à jour le chemin en fonction de la position du serpent et de la pomme.
     */
    public void drawGrid(Item item, Snake snake, Obstacle obstacle, GraphicsContext gc) {
        // Dessiner l'arrière-plan
        gc.setFill(game.getColor());
        gc.fillRect(0, 0, gridCanvas.getWidth(), gridCanvas.getHeight());

        // Dessine la pomme
        gc.drawImage(item.getImage(), (item.getPos().x * game.getMultiplier()) * fullScale, (item.getPos().y * game.getMultiplier()) * fullScale, game.getMultiplier() * fullScale - 3 * fullScale, game.getMultiplier() * fullScale - 3 * fullScale);

        // Dessine le corps du serpent
        int bodyIndex = 0;
        for (Point point : snake.getBody()) {
            drawBody(snake, point, bodyIndex);
            bodyIndex++;
        }

        //Dessine le serpent
        drawHead(snake);

        //Dessine les obstacles
        for (Obstacle obs : obstacle.getObstacles()) {
            gc.drawImage(
                    obs.getImage(), (obs.getPos().x * game.getMultiplier()) * fullScale,
                    (obs.getPos().y * game.getMultiplier()) * fullScale,
                    game.getMultiplier() * fullScale - 3 * fullScale,
                    game.getMultiplier() * fullScale - 3 * fullScale
            );
        }
    }

    private void drawGrid(Item item, Snake snake, GraphicsContext gc) {
        //Dessine l'arrière-plan
        gc.setFill(game.getColor());
        gc.fillRect(0, 0, gridCanvas.getWidth(), gridCanvas.getHeight());

        // Dessine la pomme
        gc.drawImage(item.getImage(), (item.getPos().x * game.getMultiplier()) * fullScale, (item.getPos().y * game.getMultiplier()) * fullScale, game.getMultiplier() * fullScale - 3 * fullScale, game.getMultiplier() * fullScale - 3 * fullScale);

        // Dessine le serpent (corp)
        int bodyIndex = 0;
        for (Point point : snake.getBody()) {
            drawBody(snake, point, bodyIndex);
            bodyIndex++;
        }
        //dessine le serpent (tête
        drawHead(snake);
    }

    /**
     * Dessine la tête et gère la rotation
     *
     * @param snake Serpent
     */
    private void drawHead(Snake snake) {
        snake.getImageViewHead().setRotate(snake.getSnakeDir());
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = snake.getImageViewHead().snapshot(params, null);
        graphicsContext.drawImage(rotatedImage, (snake.getHead().x * game.getMultiplier()) * fullScale, (snake.getHead().y * game.getMultiplier()) * fullScale, game.getMultiplier() * fullScale * fullScale, game.getMultiplier() * fullScale * fullScale);
    }

    /**
     * Cette partie dessine la queue du corps du serpent et quand il se tourne. La queue est placée à l'indice 0,
     * et tourne par rapport au reste du corps. Le serpent tourne à partir d'un tableau 2D
     *
     * @param snake  Serpent
     * @param point
     * @param index La partie du serpent à dessiner.
     */
    private void drawBody(Snake snake, Point point, int index) {
        Image rotatedImage;
        if (snake.getBody().size() <= 1) {
            snake.getImageViewTail().setRotate(snake.getSnakeDir());
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            rotatedImage = snake.getImageViewTail().snapshot(params, null);
        } else if (index == 0) {
            int rotateTail;
            if (snake.getBody().get(1).getX() == snake.getBody().get(0).getX() && snake.getBody().get(1).getY() == snake.getBody().get(0).getY() - 1) {
                rotateTail = 0;
            } else if (snake.getBody().get(1).getX() == snake.getBody().get(0).getX() && snake.getBody().get(1).getY() == snake.getBody().get(0).getY() + 1) {
                rotateTail = 180;
            } else if (snake.getBody().get(1).getX() == snake.getBody().get(0).getX() - 1 && snake.getBody().get(1).getY() == snake.getBody().get(0).getY()) {
                rotateTail = 270;
            } else {
                rotateTail = 90;
            }
            snake.getImageViewTail().setRotate(rotateTail);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            rotatedImage = snake.getImageViewTail().snapshot(params, null);
        } else if (index > 0 && (turnArray[(int) snake.getBody().get(index).getX()][(int) snake.getBody().get(index).getY()] != 0)) {
            snake.getImageViewTurn().setRotate(90 * turnArray[(int) snake.getBody().get(index).getX()][(int) snake.getBody().get(index).getY()]);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            rotatedImage = snake.getImageViewTurn().snapshot(params, null);
        } else {
            snake.getImageViewBody().setRotate(snake.getBodyDir(index));
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            rotatedImage = snake.getImageViewBody().snapshot(params, null);
        }

        if (turnArray[(int) snake.getBody().get(0).getX()][(int) snake.getBody().get(0).getY()] != 0) {
            turnArray[(int) snake.getBody().get(0).getX()][(int) snake.getBody().get(0).getY()] = 0;
        }

        graphicsContext.drawImage(rotatedImage, (point.x * game.getMultiplier()) * fullScale, (point.y * game.getMultiplier()) * fullScale, game.getMultiplier() * fullScale * fullScale, game.getMultiplier() * fullScale * fullScale);
    }

    /**
     * La méthode écrit une valeur dans un tableau 2D représentant l'endroit de la grille
     * où le serpent doit tourner et de quel côté il est tourné.
     * @param snake Serpent
     */
    public void turnDir(Snake snake) {
        if ((GameController.lastKey == KeyCode.LEFT && GameController.key == KeyCode.UP) || (GameController.lastKey == KeyCode.DOWN && GameController.key == KeyCode.RIGHT)) {
            turnArray[(int) snake.getHead().getX()][(int) snake.getHead().getY()] = 2;
        } else if ((GameController.lastKey == KeyCode.LEFT && GameController.key == KeyCode.DOWN) || (GameController.lastKey == KeyCode.UP && GameController.key == KeyCode.RIGHT)) {
            turnArray[(int) snake.getHead().getX()][(int) snake.getHead().getY()] = 3;
        } else if ((GameController.lastKey == KeyCode.RIGHT && GameController.key == KeyCode.UP) || (GameController.lastKey == KeyCode.DOWN && GameController.key == KeyCode.LEFT)) {
            turnArray[(int) snake.getHead().getX()][(int) snake.getHead().getY()] = 1;
        } else if ((GameController.lastKey == KeyCode.UP && GameController.key == KeyCode.LEFT) || (GameController.lastKey == KeyCode.RIGHT && GameController.key == KeyCode.DOWN)) {
            turnArray[(int) snake.getHead().getX()][(int) snake.getHead().getY()] = 4;
        } else turnArray[(int) snake.getHead().getX()][(int) snake.getHead().getY()] = 0;
    }

    /**
     * afficher label gameOver si la partie est perdu
     */
    public void gameOver(Snake snake) {
        if (snake.getDead() && !gameOverVis) {
            gameOverVis = true;
            gameOverLabel.setVisible(true);
            viewHighScore.setVisible(true);
            restartButton.setVisible(true);
            returnButton.setVisible(true);
        } else if (!snake.getDead() && gameOverVis) {
            gameOverLabel.setVisible(false);
            gameOverVis = false;
        }
    }

    /**
     *afficher label gameWon si la partie est gagné
     *
    */

    public void gameWon() {
        if (game.gameWon() && !gameWonVis) {
            gameWonVis = true;
            gameWonLabel.setVisible(true);
        } else if (!game.gameWon() && gameWonVis) {
            gameWonVis = false;
            gameWonLabel.setVisible(false);
        }
    }

    /**
     *Met à jour scoreLabel avec le dernier score
     */
    public void updateScoreLabel() {
        scoreLabel.setText("Score : " + game.getScorePoints()+" \n ");
    }

    /**
     *Met à jour gridPaneApple
     */
    public void updateAppleGridPane() {
        nbRedAppleImage.setText(" : "+Item.getNbItemEaten()+"  ");
        nbBlueAppleImage.setText(" : "+ScoreItem.getNbScoreEaten()+"  ");
        nbYellowAppleImage.setText(" : "+SpeedItem.getNbSpeedEaten()+"  ");
        nbRainbowAppleImage.setText(" : "+GhostWallItem.getNbGhostEaten()+"  ");
        nbBrownAppleImage.setText(" : "+ObstacleItem.getNbObstacleEaten()+"  ");
    }

    /**
     *Met à jour multiplicatorLabel avec le dernier multiplicator
     */
    public void updateMultiplicatorLabel() {
        if (Multiplicator.isActif())
            multiplicatorLabel.setText("point x "+Multiplicator.getCoef()+"\n ");
        else
            multiplicatorLabel.setText("point x 1\n ");
    }

    /**
     * Affiche visuellement le meilleur score lorsqu'un nom est entré dans le jeu.
     */
    private void showHighScore(boolean show) {
        if (!show) {
            highScoreVis = false;
            highScoreLabel.setVisible(false);
        } else if (show) {
            gameOverLabel.setVisible(false);
            gameWonLabel.setVisible(false);
            userName.setVisible(true);
            userName.setOnAction(event -> {
                userName.setVisible(false);
                returnButtonScore.setVisible(true);
                highScore.readHighScoreFromFile();
                highScore.addScore(game, userName.getText().toUpperCase());
                printHighScore();
            });
        }
    }

    /**
     * afficher le meilleur score;
     */
    private void printHighScore() {
        highScoreVis = true;
        StringBuilder scores = new StringBuilder();
        for (int i = 0; i < highScore.getTotalArray().length; i++) {
            scores.append(highScore.getTotalArray()[i][0]).append("      -      ").append(highScore.getTotalArray()[i][1]).append("\n");
        }
        highScoreLabel.setText("HIGH SCORE \n" + scores);
        highScoreLabel.setTextAlignment(TextAlignment.LEFT);
        ViewManager.getHighScoreLabel().setText("HIGH SCORE \n" + scores);
        highScoreLabel.setVisible(true);
    }

    /**
     * limite la saisie de l'utilisateur à la longueur sélectionnée
     *
     * @param field
     * @param textMaxLength taille maximum que le texte peut recevoir
     */
    private static void addTextLimiter(final TextField field, final int textMaxLength) {
        field.textProperty().addListener((ov, oldValue, newValue) -> {
            if (field.getText().length() > textMaxLength) {
                String s = field.getText().substring(0, textMaxLength);
                field.setText(s);
            }
        });
    }

    private void makeAppleImages(ImageView imageView, Image image) {
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
    }

    public Label getNbRedAppleImage() {
        return nbRedAppleImage;
    }

    public Label getNbBlueAppleImage() {
        return nbBlueAppleImage;
    }

    public Label getNbYellowAppleImage() {
        return nbYellowAppleImage;
    }

    public Label getNbRainbowAppleImage() {
        return nbRainbowAppleImage;
    }

    public Label getNbBrownAppleImage() {
        return nbBrownAppleImage;
    }
}