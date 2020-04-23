package Presentation.View.Widget;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameSettings_widget extends VBox {
    private final String pauseBox =  "[     Pause      ]";
    private final String resumeBox = "[     Resume  ]";
    private final String startBux =  "[     Start         ]";
    private final String resetBox =  "[     Reset       ]";
    private final String valuesBoxChecked =   "Values\t[  X  ]";
    private final String valuesBoxUnchecked = "Values\t[       ]";
    private final String hintsBoxChecked =    "Hints \t[  X  ]";
    private final String hintsBoxUnchecked =  "Hints \t[       ]";
    private Label pauseAndResumeButton = new Label(pauseBox);
    private Label startAndResetButton = new Label(startBux);
    private Label showValuesButton = new Label(valuesBoxUnchecked);
    private Label showHintsButton = new Label(hintsBoxUnchecked);
    private DelaySettings_widget delaySettingsWidget = new DelaySettings_widget();

    GameSettings_widget() {
        Label separator1 = new Label();
        separator1.setPrefHeight(80);
        Label separator2 = new Label();
        separator2.setPrefHeight(80);
        setAlignment(Pos.CENTER);
        getChildren().addAll(startAndResetButton, pauseAndResumeButton, separator1, delaySettingsWidget, separator2, showHintsButton, showValuesButton);
        //setStyle("-fx-background-color: red");
    }

    private Color disabledColor;
    private Color enabledColor;
    public void setStyle(Font font, Color disableColor, Color enableColor) {
        this.disabledColor = disableColor;
        this.enabledColor = enableColor;

        delaySettingsWidget.setStyle(font, enableColor);

        pauseAndResumeButton.setFont(font);
        pauseAndResumeButton.setTextFill(disableColor);

        startAndResetButton.setFont(font);
        startAndResetButton.setTextFill(enableColor);

        showHintsButton.setFont(font);
        showHintsButton.setTextFill(enableColor);

        showValuesButton.setFont(font);
        showValuesButton.setTextFill(enableColor);
    }

    public Label getLowerSpeedLabel(){
        return delaySettingsWidget.getLowerSpeedLabel();
    }

    public Label getHigherSpeedLabel(){
        return delaySettingsWidget.getHigherSpeedLabel();
    }

    public void setSpeedLabel(int speed){
        delaySettingsWidget.setDelayLabel(speed);
    }

    public Label getShowValuesButton() {
        return showValuesButton;
    }

    public void checkValuesButton() {
        showValuesButton.setText(valuesBoxChecked);
    }

    public void uncheckValuesButton() {
        showValuesButton.setText(valuesBoxUnchecked);
    }

    public Label getShowHintsButton() {
        return showHintsButton;
    }

    public void checkHintsButton() {
        showHintsButton.setText(hintsBoxChecked);
    }

    public void uncheckHintsButton() {
        showHintsButton.setText(hintsBoxUnchecked);
    }

    public Label getPauseAndResumeButton() {
        return pauseAndResumeButton;
    }

    public void setPauseAndResumeButtonToPause() {
        pauseAndResumeButton.setText(pauseBox);
    }

    public void setPauseAndResumeButtonToResume() {
        pauseAndResumeButton.setText(resumeBox);
    }

    public Label getStartAndResetButton() {
        return startAndResetButton;
    }

    public void setStartAndResetButtonToStart() {
        startAndResetButton.setText(startBux);
    }

    public void setStartAndResetButtonToReset() {
        startAndResetButton.setText(resetBox);
    }

    public void enablePauseButton() {
        pauseAndResumeButton.setTextFill(enabledColor);
    }

    public void disablePauseButton(){
        pauseAndResumeButton.setTextFill(disabledColor);
    }


//    HBox menu = new HBox();
//    Button newGameButton = new Button("New Othello.Game");
//        newGameButton.setOnAction(new newGameButtonHandler());
//    CheckBox showValues = new CheckBox("Show Values");
//        showValues.setOnMouseClicked(new showValuesCheckBoxHandler());
//        showValues.setSelected(false);
//    CheckBox showHint = new CheckBox("Show Hints");
//        showHint.setOnMouseClicked(new showHintHandler());
//        showHint.setSelected(false);
//    Button newSimulation = new Button("New Simulation");
//        newSimulation.setOnAction(new newSimulationButtonHandler());
//        menu.getChildren().addAll(newGameButton, showValues, showHint, newSimulation);
//        menu.setSpacing(15);
//        menu.setAlignment(Pos.CENTER_LEFT);
}
