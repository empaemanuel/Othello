package Presentation.View.Widget;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DelaySettings_widget extends VBox {
    private HBox delayBox = new HBox();
    private Label titleLabel = new Label("Delay");
    private Label lowerLabel = new Label("<<  ");
    private Label higherLabel = new Label("  >>");
    private Label delayLabel = new Label("0");

    DelaySettings_widget(){
        delayBox.getChildren().addAll(lowerLabel, delayLabel,higherLabel);
        getChildren().addAll(titleLabel, delayBox);
        setAlignment(Pos.CENTER);
        delayBox.setAlignment(Pos.CENTER);
        //setStyle("-fx-background-color: red");
    }

    public void setStyle(Font font, Color color) {
        titleLabel.setFont(font);
        titleLabel.setTextFill(color);

        lowerLabel.setFont(font);
        lowerLabel.setTextFill(color);

        higherLabel.setFont(font);
        higherLabel.setTextFill(color);

        delayLabel.setFont(font);
        delayLabel.setTextFill(color);
    }

    public Label getLowerSpeedLabel() {
        return lowerLabel;
    }

    public Label getHigherSpeedLabel() {
        return higherLabel;
    }

    public void setDelayLabel(int delayInMillis){
        double d = (double) delayInMillis/1000;
        delayLabel.setText("" + d);
    }

}
