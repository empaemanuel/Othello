package Presentation.View;

import Presentation.Controller.Game_controller;
import Presentation.Model.Game_model;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * Handling primary stage/main window, starting new game, switching between games and so on.
 * Not handling any events during gameplay, but only for the buttons in the menu.
 */
public class Main_view extends Application {

    // Override the start method in the Application class
    @Override
    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        primaryStage.setTitle("Othello");

        //Othello.Game window

        Game_view game_view = new Game_view();
        Game_model game_model = new Game_model();
        Game_controller game_controller = new Game_controller(game_view);


        //Add to root
        root.setCenter(game_view);

        //Init stage
        primaryStage.setResizable(false);
        Scene scene = new Scene(root,800,640);


        primaryStage.setScene(scene);
        //primaryStage.setMinWidth(500);
        //primaryStage.setMinHeight(500);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}



