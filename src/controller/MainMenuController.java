package src.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import src.Main;
import src.model.Item;
import src.model.Game;
import src.model.Obstacle;
import src.model.Snake;
import src.view.GameView;
import src.view.ViewManager;

public class MainMenuController {

    private static int xVALUE = 20;
    private static int yVALUE = 20;

    private Scene gameScene;

    private boolean powerUp;
    private char difficulty = 'N';

    private ViewManager viewManager = Main.getViewManager();

    /**
     * actions réalisées au moment du lancement de l'appli
     * lance le jeu
     */
    public void handleStart() {
        powerUp = viewManager.getPowerItems().isSelected();
        setGridSize(viewManager.getInputGridSizeX().getText(), viewManager.getInputGridSizeY().getText());
        setDifficulty();

        viewManager.initGameStage();

        Stage gameStage = viewManager.getGameStage();
        launchSnake(gameStage);
        gameStage.setScene(gameScene);
        viewManager.getPrimaryStage().close();
        gameStage.show();
    }

    /**
     * permet de revenir à la vue du  menu principal avec une animation
     */
    public void handleMain(){
        viewManager.getSettingSubScene().translateSubSceneIn();
        viewManager.getSkinSubScene().translateSubSceneIn();
        viewManager.getScoreSubScene().translateSubSceneIn();
        viewManager.getMainSubScene().translateSubSceneOutMain();
    }

    /**
     * permet d'accéder à la vue des paramètres avec une animation
     */
    public void handleSetting() {
        viewManager.getMainSubScene().translateSubSceneIn();
        viewManager.getSkinSubScene().translateSubSceneIn();
        viewManager.getScoreSubScene().translateSubSceneIn();
        viewManager.getSettingSubScene().translateSubSceneOut();
    }

    /**
     * permet d'accéder à la vue du score avec une animation
     */
    public void handleScore() {
        viewManager.getMainSubScene().translateSubSceneIn();
        viewManager.getSettingSubScene().translateSubSceneIn();
        viewManager.getSkinSubScene().translateSubSceneIn();
        viewManager.getScoreSubScene().translateSubSceneOut();
    }

    /**
     * permet d'accéder à la vue du choix des skins du serpent
     */
    public void handleSkin(){
        viewManager.getMainSubScene().translateSubSceneIn();
        viewManager.getSettingSubScene().translateSubSceneIn();
        viewManager.getScoreSubScene().translateSubSceneIn();
        viewManager.getSkinSubScene().translateSubSceneOut();
    }

    /**
     * quitte le jeu
     */
    public void handleQuit() {
        System.exit(0);
    }

    /**
     * lance le jeu avec tous les paramètres choisi avant dans la vue settings
     */
    private void launchSnake(Stage gameStage) {
        //Créer le jeu, obstacle , snake, pommeEnter
        Game game = new Game(xVALUE, yVALUE, powerUp, difficulty);
        Obstacle obstacle = new Obstacle(game.getWidth(), game.getHeight());
        Snake snake = new Snake(game.getWidth(), game.getHeight(), game.isNoBorder(), obstacle);
        Item item = new Item(game.getWidth(), game.getHeight(), snake, obstacle);

        //Instances de view et controller qui ont les mêmes instances que snake et item.
        GameView gameView = new GameView(game);
        GameController controller = new GameController(snake, item, game, gameView, obstacle, gameStage);

        StackPane gamePane = new StackPane();
        gameScene = new Scene(gamePane, game.getWidth() * game.getMultiplier(), game.getHeight() * game.getMultiplier()+100);

        gameScene.setOnKeyPressed(controller::handle);

        //créer la scene + lui ajoute les canvas

        gameView.makeScene(gameScene, snake, item);
        gamePane.getChildren().addAll(gameView.gridCanvas,gameView.scoreCanvas,gameView.gridPaneItem,gameView.tileMultiplicator, gameView.tileButtons, gameView.scoreLabel ,gameView.gameOverLabel, gameView.highScoreLabel,gameView.gameWonLabel,gameView.returnButtonScore, gameView.userName);

        gamePane.setPadding(new Insets(20));
        gamePane.setAlignment(gameView.gameOverLabel, Pos.TOP_CENTER);
        gamePane.setAlignment(gameView.gameWonLabel, Pos.TOP_CENTER);
        gamePane.setAlignment(gameView.highScoreLabel, Pos.CENTER);
        gamePane.setAlignment(gameView.scoreLabel, Pos.BOTTOM_LEFT);
        gamePane.setAlignment(gameView.tileMultiplicator, Pos.BOTTOM_RIGHT);
        gamePane.setAlignment(gameView.returnButtonScore,Pos.TOP_RIGHT);
        gamePane.setAlignment(gameView.gridPaneItem,Pos.BOTTOM_RIGHT);
    }

    /**
     * permet de choisir la taille de la grille
     * @param width largeur de la grille
     * @param height hauteur de la grille
     */
    private void setGridSize(String width, String height) {
        boolean validGridSize = false;
        if (width.matches("-?\\d+") || height.matches("-?\\d+")) {
            validGridSize = ((Integer.parseInt(width) >= 5) && (Integer.parseInt(width) <= 100));
            if (((Integer.parseInt(height) < 5) || (Integer.parseInt(height) > 100))) {
                validGridSize = false;
            }
        }
        if (validGridSize) {
            xVALUE = Integer.parseInt(width);
            yVALUE = Integer.parseInt(height);
        }
    }

    /**
     * permet d'affecter une difficulté au jeu grâce au menu
     */
    private void setDifficulty() {
        if (viewManager.getCheckEasy().isSelected()) {
            difficulty = 'E';
        } else if (viewManager.getCheckMedium().isSelected()) {
            difficulty = 'N';
        } else if (viewManager.getCheckHard().isSelected()) {
            difficulty = 'H';
        }
    }

    /**
     * Retourne le viewManager
     *
     * @return le viewManager
     */
    public ViewManager getViewManager() {
        return viewManager;
    }

    /**
     * Retourne la xValue
     *
     * @return la xValue
     */
    public static int getxValue() {
        return xVALUE;
    }

    /**
     * Retourne la yValue
     *
     * @return la yValue
     */
    public static int getyVALUE() {
        return yVALUE;
    }
}

