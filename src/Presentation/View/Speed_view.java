package Presentation.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Speed_view extends VBox {
    private Slider speedSlider = new Slider();
    private Label speedLabel = new Label();
    private Button pauseButton = new Button();
    private Button playButton = new Button();
    private HBox buttonsBox = new HBox();
    private HBox speedBox = new HBox();

    public Speed_view() {
        setupSpeedSlider();
        setupSpeedLabel();
        setupButtonsBox();
        setupSpeedBox();
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(10,10,10,10));
        getChildren().addAll(buttonsBox, speedBox);
    }

    private void setupSpeedSlider() {
        speedSlider.setMin(0);
        speedSlider.setMax(2000);
        speedSlider.setValue(1000);
        speedSlider.setShowTickLabels(false);
        speedSlider.setShowTickMarks(false);
        speedSlider.setBlockIncrement(100);
        speedSlider.setSnapToTicks(true);
    }

    private void setupSpeedLabel() {
        speedLabel = new Label(2.5 + " seconds");
        speedLabel.setTextFill(Color.BLACK);
    }

    private void setupSpeedBox(){
        speedBox.getChildren().addAll(speedSlider, speedLabel);
    }

    private void setupButtonsBox(){
        setupPauseButton();
        setupPlayButton();
        buttonsBox.setSpacing(10);
        buttonsBox.getChildren().addAll(playButton, pauseButton);
    }

    private void setupPauseButton() {
        playButton.setText("Play");
    }

    private void setupPlayButton() {
        pauseButton.setText("Pause");
    }
}