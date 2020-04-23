package Presentation.Controller;

import Presentation.View.Game_view;
import Presentation.View.Widget.Settings_widget;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Settings_controller {
    private Settings_widget settings_widget;
    private Game_controller game_controller;
    private Game_view game_view;

    public Settings_controller(Game_controller game_controller, Settings_widget settings_widget, Game_view game_view) {
        this.game_controller = game_controller;
        this.game_view = game_view;
        this.settings_widget = settings_widget;

//
//        setupPlayerOneSettingsHandler();
//        setupPlayerTwoSettingsHandler();
        setupHintsHandler();
        setupValuesHandler();
        setupPauseAndResumeButtonHandler();
        setupStartAndResetButtonHandler();
        setupLowerSpeedButtonHandler();
        setupHigherSpeedButtonHandler();
    }

    private int timeInMillis = 0;

    /**
     * SPEED INCREASE
     */
    private void setupHigherSpeedButtonHandler() {
        settings_widget.getHigherSpeedLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (timeInMillis < 3000) {
                    timeInMillis += 500;
                }
                settings_widget.setDelayLabel(timeInMillis);
                game_controller.setDelay(timeInMillis);
            }
        });
    }

    /**
     * SPEED DECREASE
     */
    private void setupLowerSpeedButtonHandler() {
        settings_widget.getLowerSpeedLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (timeInMillis > 0) {
                    timeInMillis -= 500;
                }
                settings_widget.setDelayLabel(timeInMillis);
                game_controller.setDelay(timeInMillis);
            }
        });
    }

    /**
     * START AND RESET
     */
    private void setupStartAndResetButtonHandler() {
        settings_widget.getStartAndResetButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            private boolean isRunning = false;
            @Override
            public void handle(MouseEvent mouseEvent) {
                isRunning = !isRunning;
                if (isRunning) {
                    game_controller.startGame();
                    settings_widget.setStartAndResetButtonToReset();
                    pauseButtonEnabled = true;
                    settings_widget.setPauseButtonToEnabled();
                } else {
                    game_controller.resetGame();
                    settings_widget.setStartAndResetButtonToStart();
                    game_controller.setDelay(timeInMillis);
                    pauseButtonEnabled = false;
                    settings_widget.setPauseButtonToDisabled();
                    settings_widget.setPauseAndResumeButtonToPause();
                    isOnPause = true;
                }
            }
        });
    }

    private boolean pauseButtonEnabled = false;
    private boolean isOnPause = true;

    /**
     * PAUSE AND RESUME
     */
    private void setupPauseAndResumeButtonHandler() {
        settings_widget.getPauseAndResumeButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!pauseButtonEnabled) return;
                isOnPause = !isOnPause;
                if (isOnPause) {
                    settings_widget.setPauseAndResumeButtonToPause();
                    game_controller.resumeGame();
                } else {
                    settings_widget.setPauseAndResumeButtonToResume();
                    game_controller.pauseGame();
                }
            }
        });
    }

    /**
     * HINTS
     */
    private void setupHintsHandler() {
        settings_widget.getShowHintsButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            boolean isChecked = false;
            @Override
            public void handle(MouseEvent mouseEvent) {
                isChecked = !isChecked;
                if (isChecked) {
                    settings_widget.checkHintsButton();
                    game_view.showHints();
                } else {
                    settings_widget.uncheckHintsButton();
                    game_view.hideHints();
                }
            }
        });

    }


    /**
     * VALUES
     */
    private void setupValuesHandler() {
        settings_widget.getShowValuesButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            boolean isChecked = false;
            @Override
            public void handle(MouseEvent mouseEvent) {
                isChecked = !isChecked;
                if (isChecked) {
                    settings_widget.checkValuesButton();
                    game_view.showValues();
                } else {
                    settings_widget.uncheckValuesButton();
                    game_view.hideValues();
                }
            }
        });
    }



//    /**
//     * PLAYER ONE
//     */
//    private void setupPlayerOneSettingsHandler() {
//        settings_widget.getPlayerRadioButton_playerOne().setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                settings_widget.checkPlayerRadioButton_playerOne();
//                settings_widget.uncheckComputerRadioButton_playerOne();
//                game_controller.setPlayerOneToPlayer();
//
//            }
//        });
//
//        settings_widget.getComputerRadioButton_playerOne().setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                settings_widget.uncheckPlayerRadioButton_playerOne();
//                settings_widget.checkComputerRadioButton_playerOne();
//                game_controller.setPlayerOneToComputer();
//            }
//        });
//    }
//
//    /**
//     * PLAYER TWO
//     */
//    private void setupPlayerTwoSettingsHandler() {
//        settings_widget.getPlayerRadioButton_playerTwo().setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                settings_widget.checkPlayerRadioButton_playerTwo();
//                settings_widget.uncheckComputerRadioButton_playerTwo();
//                game_controller.setPlayerTwoToPlayer();
//            }
//        });
//
//        settings_widget.getComputerRadioButton_playerTwo().setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                settings_widget.uncheckPlayerRadioButton_playerTwo();
//                settings_widget.checkComputerRadioButton_playerTwo();
//                game_controller.setPlayerTwoToComputer();
//            }
//        });
//    }
//

}
