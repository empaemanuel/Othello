package Presentation.View.Widget;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PlayerSettings_widget extends VBox {
    private final String checkedBox =   "[  X  ]";
    private final String uncheckedBox = "[       ]";
    private final String titleComputer = "Computer\t";
    private final String titlePlayer = "Player\t\t";
    private Label computerRadioButton = new Label(titleComputer + uncheckedBox);
    private Label playerRadioButton = new Label(titlePlayer + uncheckedBox);

    PlayerSettings_widget() {
        getChildren().addAll(playerRadioButton,computerRadioButton);
        //setStyle("-fx-background-color: red");
    }

    public void setStyle(Font font, Color color) {
        computerRadioButton.setFont(font);
        computerRadioButton.setTextFill(color);

        playerRadioButton.setFont(font);
        playerRadioButton.setTextFill(color);
    }

    public void checkComputerRadioButton(){
        computerRadioButton.setText(titleComputer + checkedBox);
    }

    public void uncheckComputerRadioButton(){
        computerRadioButton.setText(titleComputer + uncheckedBox);
    }

    public void checkPlayerRadioButton(){
        playerRadioButton.setText(titlePlayer + checkedBox);
    }

    public void uncheckPlayerRadioButton(){
        playerRadioButton.setText(titlePlayer + uncheckedBox);
    }

    public Label getComputerRadioButton() {
        return computerRadioButton;
    }

    public Label getPlayerRadioButton() {
        return playerRadioButton;
    }
}
