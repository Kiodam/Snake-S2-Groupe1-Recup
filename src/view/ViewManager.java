package src.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import src.Main;
import src.model.SnakeSubScene;
import src.model.HighScore;
import src.model.Snake;

import java.io.IOException;

public class ViewManager {

    //Mainstage.
    private Stage primaryStage = new Stage();
    private AnchorPane root;

    //Gamestage
    private Stage gameStage;


    //mainSubScene
    private SnakeSubScene mainSubScene;
    private String css = "src/ressource/style/styling.css";
    private Image redApple = new Image("src/ressource/image/apple.png");
    private Image berry = new Image("src/ressource/image/berry.png");
    private Image ghost = new Image("src/ressource/image/ghost.png");
    private Image banana = new Image("src/ressource/image/banana.png");
    private Image brownApple = new Image("src/ressource/image/poison.png");
    private Image multiplicator = new Image("src/ressource/image/multiplicators.png");

    //ScoreSubScene
    private SnakeSubScene scoreSubScene;
    private static Label highScoreLabel;

    //SettingSubScene
    private SnakeSubScene settingSubScene;
    private CheckBox powerApples;
    private CheckBox checkEasy;
    private CheckBox checkMedium;
    private CheckBox checkHard;
    private TextField inputGridSizeX;
    private TextField inputGridSizeY;
    
    //SkinSubScene
    private SnakeSubScene skinSubScene;

    private CheckBox checkSkin1;
    private CheckBox checkSkin2;
    private CheckBox checkSkin3;
    private CheckBox checkSkin4;

    public ViewManager() {
    }

    /**
     * Initialisation du menu lors du Start
     */
    public void initalizeMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
            root = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        initStartSubScene();
        initScoreSubScene();
        initSettingSubScene();
        initSkinSubScene();
        root.getChildren().addAll(skinSubScene ,scoreSubScene, settingSubScene, mainSubScene);
        Scene mainScene = new Scene(root);

        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("PTUTS2 - Groupe 1 - Snake");
        primaryStage.show();
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Initialise l'onglet Start du menu
     */
    private void initStartSubScene() {
        mainSubScene = new SnakeSubScene("src/ressource/image/SubScene.png");
        mainSubScene.setLayoutX(85);
        AnchorPane mainRoot = (AnchorPane) mainSubScene.getRoot();

        Label welcomeText = new Label();
        welcomeText.setId("welcomeLabel");
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        welcomeText.setText("Bienvenue dans le jeu Snake \n" +
                "Séléctionner le niveau de difficulté dans les '⚙️ Settings', \n" +
                "Apprécier vos meilleurs scores dans '\uD83C\uDFC6 Score'.\n\n");
        welcomeText.getStylesheets().add(css);

        // Création des box contenant les images et les descriptions
        HBox applebox = new HBox();
        HBox labelbox = new HBox();

        ImageView redAppleImage = new ImageView();
        makeAppleImages(redAppleImage, redApple);

        ImageView blueAppleImage = new ImageView();
        makeAppleImages(blueAppleImage, berry);

        ImageView yellowAppleImage = new ImageView();
        makeAppleImages(yellowAppleImage, banana);

        ImageView rainbowAppleImage = new ImageView();
        makeAppleImages(rainbowAppleImage, ghost);

        ImageView brownAppleImage = new ImageView();
        makeAppleImages(brownAppleImage, brownApple);

        ImageView multiplicatorImage = new ImageView();
        makeAppleImages(multiplicatorImage, multiplicator);


        Label redAppleText = new Label();
        redAppleText.setText("5 points");
        makeAppleLabels(redAppleText);

        Label blueAppleText = new Label();
        blueAppleText.setText("15 points");
        makeAppleLabels(blueAppleText);

        Label yellowAppleText = new Label();
        yellowAppleText.setText("Soit plus\n" +
                "rapide\n" +
                "5 points");
        makeAppleLabels(yellowAppleText);

        Label rainbowAppleText = new Label();
        rainbowAppleText.setText("Traverser\n" +
                "les murs\n" +
                "5 points");
        makeAppleLabels(rainbowAppleText);

        Label brownAppleText = new Label();
        brownAppleText.setText("Place\n" +
                "des\n" +
                "obstacles\n" +
                "5 points");
        makeAppleLabels(brownAppleText);

        Label multiplicatorText = new Label();
        multiplicatorText.setText("Multiplie\n"+
                "les points\n"+
                "gagnes\n"+
                "par\n"+
                "2, 3 ou 5");


        labelbox.setSpacing(9);
        labelbox.setLayoutX(10);
        labelbox.setLayoutY(320);

        labelbox.getChildren().addAll(redAppleText, blueAppleText, yellowAppleText, rainbowAppleText, brownAppleText, multiplicatorText);

        applebox.setSpacing(60);
        applebox.setLayoutX(15);
        applebox.setLayoutY(270);

        applebox.getChildren().addAll(redAppleImage, blueAppleImage, yellowAppleImage, rainbowAppleImage, brownAppleImage, multiplicatorImage);

        mainRoot.getChildren().addAll(welcomeText, applebox, labelbox);
    }

    /**
     * Set les images a afficher
     *
     * @param imageView l'imageView a set
     * @param image l'image a ajouter a l'imageView
     */
    private void makeAppleImages(ImageView imageView, Image image) {
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
    }

    /**
     * Set les labels associes aux images
     *
     * @param label le label a set
     */
    private void makeAppleLabels(Label label) {
        label.setId("applelabel");
        label.getStylesheets().add(css);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setPrefWidth(100);
    }

    /**
     * Initialise l'onglet Score du menu
     */
    private void initScoreSubScene() {
        scoreSubScene = new SnakeSubScene("src/ressource/image/SubScene.png");

        highScoreLabel = new Label();
        highScoreLabel.getStylesheets().add(css);
        highScoreLabel.setLayoutX(200);
        highScoreLabel.setLayoutY(5);

        // lis les meilleurs score dans le fichier HighScore.txt
        HighScore highScore = new HighScore();
        highScore.readHighScoreFromFile();
        StringBuilder scores = new StringBuilder();

        // ajoute les lignes a l'objet score
        for (int i = 0; i < highScore.getTotalArray().length; i++) {
            scores.append(highScore.getTotalArray()[i][0]).append("      -      ").append(highScore.getTotalArray()[i][1]).append("\n");
        }

        highScoreLabel.setText("\n     HIGH SCORE \n" + scores);
        highScoreLabel.setTextAlignment(TextAlignment.LEFT);
        highScoreLabel.setVisible(true);

        AnchorPane scoreRoot = (AnchorPane) scoreSubScene.getRoot();
        scoreRoot.getChildren().add(highScoreLabel);
    }

    /**
     * Initialise l'onglet Setting du menu
     */
    private void initSettingSubScene() {
        settingSubScene = new SnakeSubScene("src/ressource/image/SubScene.png");
        AnchorPane settingRoot = (AnchorPane) settingSubScene.getRoot();

        // creation des checkBox
        powerApples = new CheckBox("Power Ups");
        makeCheckBoxSetting(powerApples, true, 100, 200);
        checkEasy = new CheckBox("Easy");
        makeCheckBoxSetting(checkEasy, false, 100,250);
        checkMedium = new CheckBox("Normal");
        makeCheckBoxSetting(checkMedium, true, 100, 280);
        checkHard = new CheckBox("Hard");
        makeCheckBoxSetting(checkHard, false, 100,310);

        // creation des labels associes aux checkbox
        Label powerDescription = new Label("Activer ou désactiver les bonus");
        makeLabel(powerDescription, 250, 203);
        Label easyDescription = new Label("Vitesse de jeu normale sans murs");
        makeLabel(easyDescription,250, 253);
        Label normalDescription = new Label("Vitesse de jeu normal et murs");
        makeLabel(normalDescription, 250, 283);
        Label hardDescription = new Label("Vitesse de jeu rapide et murs ");
        makeLabel(hardDescription, 250, 313);

        // creation des texteFields permettant de modifier la taille du plateau
        inputGridSizeX = new TextField("");
        inputGridSizeX.setPromptText("largeur  (10 - 90)");
        inputGridSizeX.setLayoutX(100);
        inputGridSizeX.setLayoutY(100);
        inputGridSizeY = new TextField();
        inputGridSizeY.setPromptText("Hauteur (10 - 90)");
        inputGridSizeY.setLayoutX(100);
        inputGridSizeY.setLayoutY(140);
        inputGridSizeX.getStylesheets().add(css);
        inputGridSizeY.getStylesheets().add(css);

        settingRoot.getChildren().addAll(checkEasy, checkMedium, checkHard, powerApples, inputGridSizeX, inputGridSizeY, powerDescription, easyDescription, normalDescription, hardDescription);
    }

    /**
     * Initialise l'onglet Skin du menu
     */
    private void initSkinSubScene() {
        skinSubScene = new SnakeSubScene("src/ressource/image/SubScene.png");

        Label skinLabel = new Label("Choisis ton skin");
        makeLabel(skinLabel, 250, 203);

        // creation des checkbox
        checkSkin1 = new CheckBox("Snake vert");
        makeCheckBoxSkin(checkSkin1, true, 100, 50);
        checkSkin2 = new CheckBox("Snake Mr.Hankey");
        makeCheckBoxSkin(checkSkin2, false, 100,150);
        checkSkin3 = new CheckBox("Snake Akatsuki");
        makeCheckBoxSkin(checkSkin3, false, 100, 250);
        checkSkin4 = new CheckBox("Nuée de noiraudes");
        makeCheckBoxSkin(checkSkin4, false, 100,350);

        // creation des image associees aux checkbox
        ImageView skin1 = new ImageView("src/ressource/image/Skin/Green/fullSnake.png");
        Pane paneSkin1 = new Pane();
        paneSkin1.getChildren().add(skin1);
        paneSkin1.setLayoutX(300);
        paneSkin1.setLayoutY(30);

        ImageView skin2 = new ImageView("src/ressource/image/Skin/Hankey/fullSnake.png");
        skin2.setPreserveRatio(true);
        skin2.setFitWidth(240);
        Pane paneSkin2 = new Pane();
        paneSkin2.getChildren().add(skin2);
        paneSkin2.setLayoutX(300);
        paneSkin2.setLayoutY(130);

        ImageView skin3 = new ImageView("src/ressource/image/Skin/Aka/fullSnake.png");
        Pane paneSkin3 = new Pane();
        paneSkin3.getChildren().add(skin3);
        paneSkin3.setLayoutX(300);
        paneSkin3.setLayoutY(230);

        ImageView skin4 = new ImageView("src/ressource/image/Skin/Noiraude/fullSnake.png");
        skin4.setPreserveRatio(true);
        skin4.setFitWidth(240);
        Pane paneSkin4 = new Pane();
        paneSkin4.getChildren().add(skin4);
        paneSkin4.setLayoutX(300);
        paneSkin4.setLayoutY(330);

        AnchorPane skinRoot = (AnchorPane) skinSubScene.getRoot();
        skinRoot.getChildren().addAll(checkSkin1, checkSkin2, checkSkin3, checkSkin4, paneSkin1, paneSkin2, paneSkin3 , paneSkin4);
    }

    /**
     * Set les checkbox setting
     *
     * @param checkBox la checkbox a set
     * @param selected boolean pour designer si la checkbox est check ou non
     * @param x position en x de la checkbox
     * @param y position en y de la checkbox
     */
    private void makeCheckBoxSetting(CheckBox checkBox, boolean selected, int x, int y) {
        checkBox.setSelected(selected);
        checkBox.getStylesheets().add(css);
        checkBox.setLayoutX(x);
        checkBox.setLayoutY(y);
        checkBox.setOnAction(e -> {
            // if permettant de check une seule des checkbox setting
            if (e.getSource().equals(checkEasy)) {
                checkEasy.setSelected(true);
                checkMedium.setSelected(false);
                checkHard.setSelected(false);
            } else if (e.getSource().equals(checkMedium)) {
                checkEasy.setSelected(false);
                checkMedium.setSelected(true);
                checkHard.setSelected(false);
            } else if (e.getSource().equals(checkHard)) {
                checkEasy.setSelected(false);
                checkMedium.setSelected(false);
                checkHard.setSelected(true);
            }
        });
    }

    /**
     * Set les checkbox skin
     *
     * @param checkBox la checkbox a set
     * @param selected boolean pour designer si la checkbox est check ou non
     * @param x position en x de la checkbox
     * @param y position en y de la checkbox
     */
    private void makeCheckBoxSkin(CheckBox checkBox, boolean selected, int x, int y) {
        checkBox.setSelected(selected);
        checkBox.getStylesheets().add(css);
        checkBox.setLayoutX(x);
        checkBox.setLayoutY(y);
        checkBox.setOnAction(e -> {
            // if permettant de check une seule des checkbox skin et de modifier le skin en jeu
            if (e.getSource().equals(checkSkin1)) {
                checkSkin1.setSelected(true);
                checkSkin2.setSelected(false);
                checkSkin3.setSelected(false);
                checkSkin4.setSelected(false);
                Snake.cheminSkin="src/ressource/image/Skin/Green/";
            } else if (e.getSource().equals(checkSkin2)) {
                checkSkin1.setSelected(false);
                checkSkin2.setSelected(true);
                checkSkin3.setSelected(false);
                checkSkin4.setSelected(false);
                Snake.cheminSkin="src/ressource/image/Skin/Hankey/";
            } else if (e.getSource().equals(checkSkin3)) {
                checkSkin1.setSelected(false);
                checkSkin2.setSelected(false);
                checkSkin3.setSelected(true);
                checkSkin4.setSelected(false);
                Snake.cheminSkin="src/ressource/image/Skin/Aka/";
            } else if (e.getSource().equals(checkSkin4)) {
                checkSkin1.setSelected(false);
                checkSkin2.setSelected(false);
                checkSkin3.setSelected(false);
                checkSkin4.setSelected(true);
                Snake.cheminSkin="src/ressource/image/Skin/Noiraude/";
            }
        });
    }

    /**
     * Set le label
     *
     * @param label label a set
     * @param x position en x du label
     * @param y position en y du label
     */
    private void makeLabel(Label label, int x, int y) {
        label.getStylesheets().add(css);
        //label.setAlignment(Pos.CENTER_LEFT);
        label.setStyle("-fx-font-size: 15;");
        label.setLayoutX(x);
        label.setLayoutY(y);
    }

    public SnakeSubScene getScoreSubScene() {
        return scoreSubScene;
    }

    public SnakeSubScene getSettingSubScene() {
        return settingSubScene;
    }

    public SnakeSubScene getSkinSubScene() {
        return skinSubScene;
    }

    public SnakeSubScene getMainSubScene() { return mainSubScene; }

    public CheckBox getPowerApples() {
        return powerApples;
    }

    public CheckBox getCheckEasy() {
        return checkEasy;
    }

    public CheckBox getCheckMedium() {
        return checkMedium;
    }

    public CheckBox getCheckHard() {
        return checkHard;
    }

    public TextField getInputGridSizeX() {
        return inputGridSizeX;
    }

    public TextField getInputGridSizeY() {
        return inputGridSizeY;
    }

    public void initGameStage() {
        gameStage = new Stage();
        gameStage.setTitle("PTUTS2 - Groupe 1 - Snake");
        gameStage.setResizable(false);
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public static Label getHighScoreLabel() {
        return highScoreLabel;
    }
}
