package Presentation.View.Widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Weight_widget extends VBox {
    private Label weightLabel = new Label();

    public Weight_widget() {
        weightLabel.setText("" + 0);

        getChildren().addAll(weightLabel);
        setupLayout();
    }

    public void setupStyle(Font font, String color){
        weightLabel.setFont(font);

        weightLabel.setTextFill(Color.valueOf(color));

        //setStyle("-fx-background-color: red");
    }

    private void setupLayout(){
        setAlignment(Pos.CENTER);
        setPrefWidth(150);
    }

    public void setWeight(int weight) {
        weightLabel.setText("" + weight);
    }
}
