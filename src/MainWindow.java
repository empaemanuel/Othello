import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * Handling primary stage/main window, starting new game, switching between games and so on.
 * Not handling any events during gameplay, but only for the buttons in the menu.
 */
public class MainWindow extends Application {
    GameDisplay gameDisplay;

    // Override the start method in the Application class
    @Override
    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        primaryStage.setTitle("Othello");

        //Game window
        gameDisplay = new GameDisplay();


        //Status bar
        Label status = new Label("status: ");

        //menu
        HBox menu = new HBox();
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(new newGameButtonHandler());
        CheckBox showValues = new CheckBox("Show Values");
        showValues.setOnMouseClicked(new showValuesCheckBoxHandler());
        showValues.setSelected(false);
        CheckBox showHint = new CheckBox("Show Hints");
        showHint.setOnMouseClicked(new showHintHandler());
        showHint.setSelected(false);
        Button newSimulation = new Button("New Simulation");
        newSimulation.setOnAction(new newSimulationButtonHandler());
        menu.getChildren().addAll(newGameButton, showValues, showHint, newSimulation);
        menu.setSpacing(15);
        menu.setAlignment(Pos.CENTER_LEFT);

        //Add to root
        root.setTop(menu);
        root.setBottom(status);
        root.setCenter(gameDisplay);

        //Init stage
        primaryStage.setResizable(false);
        Scene scene = new Scene(root,500,600);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(500);
        primaryStage.show();
    }

    class showValuesCheckBoxHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            gameDisplay.values(((CheckBox)e.getSource()).isSelected());
        }
    }

    class newGameButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            gameDisplay.newGame();
        }
    }

    class newSimulationButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            gameDisplay.newSimulation();
        }
    }

    //alhasi kusi

    //SHOW HINT
    class showHintHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent e) {
            gameDisplay.hints(((CheckBox)e.getSource()).isSelected());
        }
    }

    public static void main(String[] args) {

        launch(args);
    }

}



