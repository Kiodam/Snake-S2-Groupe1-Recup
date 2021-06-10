package src;

import javafx.application.Application;
import javafx.stage.Stage;
import src.view.ViewManager;

/**
 A partir de là, le jeu est lancé et le viewManager est initialisé.
 */
public class Main extends Application {

    private static ViewManager viewManager;

    @Override
    public void start(Stage primaryStage) {
        viewManager = new ViewManager();
        viewManager.initalizeMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static ViewManager getViewManager() {
        return viewManager;
    }
}

