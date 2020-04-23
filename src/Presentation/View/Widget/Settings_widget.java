package Presentation.View.Widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Settings_widget extends VBox {
    private PlayerSettings_widget playerOneSettingsView;
    private PlayerSettings_widget playerTwoSettingsView;
    private GameSettings_widget gameSettingsWidget = new GameSettings_widget();

    public Settings_widget(){
        //this.playerOneSettingsView = new PlayerSettings_widget();
        //this.playerTwoSettingsView = new PlayerSettings_widget();

        Region region1 = new Region();
        VBox.setVgrow(region1, Priority.ALWAYS);

        Region region2 = new Region();
        VBox.setVgrow(region2, Priority.ALWAYS);

        setAlignment(Pos.CENTER);
        //getChildren().addAll(playerTwoSettingsView, region1, gameSettingsWidget, region2, playerOneSettingsView);
        getChildren().addAll(gameSettingsWidget);
    }

    public void setupStyle(String disableColor, String enableColor, Font font){
        setPrefWidth(150);
        setPadding(new Insets(0,10,0,10));

        gameSettingsWidget.setStyle(font, Color.valueOf(disableColor), Color.valueOf(enableColor));
        //playerOneSettingsView.setStyle(font, color);
        //playerTwoSettingsView.setStyle(font,color);

        setStyle("-fx-background-color: #344b33");
        //setStyle("-fx-background-color: blue");
    }

    public Label getLowerSpeedLabel(){
        return gameSettingsWidget.getLowerSpeedLabel();
    }

    public Label getHigherSpeedLabel(){
        return gameSettingsWidget.getHigherSpeedLabel();
    }

    public void setDelayLabel(int speed){
        gameSettingsWidget.setSpeedLabel(speed);
    }

    public Label getShowValuesButton() {
        return gameSettingsWidget.getShowValuesButton();
    }

    public void checkValuesButton() {
        gameSettingsWidget.checkValuesButton();
    }

    public void uncheckValuesButton() {
        gameSettingsWidget.uncheckValuesButton();
    }

    public Label getShowHintsButton() {
        return gameSettingsWidget.getShowHintsButton();
    }

    public void checkHintsButton() {
        gameSettingsWidget.checkHintsButton();
    }

    public void uncheckHintsButton() {
        gameSettingsWidget.uncheckHintsButton();
    }

    public Label getPauseAndResumeButton() {
        return gameSettingsWidget.getPauseAndResumeButton();
    }

    public void setPauseAndResumeButtonToPause() {
        gameSettingsWidget.setPauseAndResumeButtonToPause(); }

    public void setPauseAndResumeButtonToResume() {
        gameSettingsWidget.setPauseAndResumeButtonToResume();
    }

    public Label getStartAndResetButton() {
        return gameSettingsWidget.getStartAndResetButton();
    }

    public void setStartAndResetButtonToStart() {
        gameSettingsWidget.setStartAndResetButtonToStart();
    }

    public void setStartAndResetButtonToReset() {
        gameSettingsWidget.setStartAndResetButtonToReset();
    }


    public void checkComputerRadioButton_playerOne(){
        playerOneSettingsView.checkComputerRadioButton();}

    public void uncheckComputerRadioButton_playerOne(){
        playerOneSettingsView.uncheckComputerRadioButton();}

    public void checkPlayerRadioButton_playerOne(){
        playerOneSettingsView.checkPlayerRadioButton();
    }

    public void uncheckPlayerRadioButton_playerOne(){
        playerOneSettingsView.uncheckPlayerRadioButton();
    }

    public Label getComputerRadioButton_playerOne() {
        return playerOneSettingsView.getComputerRadioButton();
    }

    public Label getPlayerRadioButton_playerOne() {
        return playerOneSettingsView.getPlayerRadioButton();
    }


    public void checkComputerRadioButton_playerTwo(){
        playerTwoSettingsView.checkComputerRadioButton();
    }

    public void uncheckComputerRadioButton_playerTwo(){
        playerTwoSettingsView.uncheckComputerRadioButton();
    }

    public void checkPlayerRadioButton_playerTwo(){
        playerTwoSettingsView.checkPlayerRadioButton();
    }

    public void uncheckPlayerRadioButton_playerTwo(){
        playerTwoSettingsView.uncheckPlayerRadioButton();
    }

    public Label getComputerRadioButton_playerTwo() {
        return playerTwoSettingsView.getComputerRadioButton();
    }

    public Label getPlayerRadioButton_playerTwo() {
        return playerTwoSettingsView.getPlayerRadioButton();
    }


    public void setPauseButtonToEnabled() {
        gameSettingsWidget.enablePauseButton();
    }

    public void setPauseButtonToDisabled() {
        gameSettingsWidget.disablePauseButton();
    }
}
