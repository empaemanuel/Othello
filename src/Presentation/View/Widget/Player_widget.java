package Presentation.View.Widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Player_widget extends HBox {
    private Label nameLabel = new Label();
    private Label scoreLabel = new Label();
    private Circle brick = new Circle(15);

    public Player_widget() {
        //setStyle("-fx-background-color: red");
        setupNameLabel();
        setupScoreLabel();

        setAlignment(Pos.CENTER);

        setPrefHeight(80);

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

        setPadding(new Insets(10,0,10,0));
        setMaxWidth(300);
        setAlignment(Pos.CENTER);
        getChildren().addAll(nameLabel, region1, brick,region2, scoreLabel);
    }

    public void setupStyle(Font font, String color){
        nameLabel.setFont(font);
        scoreLabel.setFont(font);
        setColor(Color.valueOf(color));
    }

    public void setToBlack(){
        brick.setFill(Color.BLACK);
    }

    public void setToWhite(){
        brick.setFill(Color.WHITE);
    }

    private void setupNameLabel(){
        nameLabel.setText("...");
        nameLabel.setTextAlignment(TextAlignment.LEFT);
        nameLabel.setAlignment(Pos.CENTER_LEFT);
    }

    private void setupScoreLabel(){
        scoreLabel.setText("" + 0);
        scoreLabel.setTextAlignment(TextAlignment.RIGHT);
        scoreLabel.setAlignment(Pos.CENTER_RIGHT);
    }

    public void setScore(int score){
        scoreLabel.setText("" + score);
    }

    public void setToDefault() {
        setColor(Color.LIGHTGRAY);
    }

    private void setColor(Color c){
        nameLabel.setTextFill(c);
        scoreLabel.setTextFill(c);
    }

    public void setName(String name) {
        nameLabel.setText(name);
    }
}
