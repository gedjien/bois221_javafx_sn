package com.example.game_of_bean_bags;

import com.example.game_of_bean_bags.handlers.ActionChain;
import com.example.game_of_bean_bags.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import javafx.scene.control.Control;

public class HelloController {
    @FXML
    private Label balanceLabel;
    @FXML
    private ImageView bag1;
    @FXML
    private ImageView bag2;
    @FXML
    private ImageView bag3;
    @FXML
    private Button informationButton;

    private Player player1;
    private ActionChain action;

    @FXML
    public void initialize() {
        player1 = new Player("Player1", 0);
        updateBalanceLabel();
        loadImages();
        setupTooltips();
    }

    private void setupTooltips() {
        if (informationButton != null) {
            installTooltip(informationButton, "Правила игры");
        }
    }

    private void installTooltip(Control control, String text) {
        if (control == null) return;
        
        Tooltip tooltip = new Tooltip(text);
        tooltip.setStyle("");
        tooltip.setOpacity(1.0);
        tooltip.setAutoHide(true);
        tooltip.getStyleClass().add("custom-tooltip");
        tooltip.setShowDelay(Duration.millis(100));
        tooltip.setHideDelay(Duration.millis(100));
        
        Tooltip.uninstall(control, control.getTooltip());
        Tooltip.install(control, tooltip);
    }

    private void loadImages() {
        Image bagImage = new Image(getClass().getResourceAsStream("/images/bag.png"));
        bag1.setImage(bagImage);
        bag2.setImage(bagImage);
        bag3.setImage(bagImage);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Баланс: " + player1.getNumber() + " монет");
    }

    @FXML
    public void onPay() {
        player1.addNumber(1);
        updateBalanceLabel();
    }

    @FXML
    public void onBagClick(MouseEvent event) {
        if (action == null) {
            action = new ActionChain();
        }
        
        // Проверяем, есть ли у игрока монеты для игры
        if (!player1.pay(1)) {
            showNotEnoughMoneyAlert();
            return;
        }
        
        updateBalanceLabel();
        
        // Обрабатываем результат игры
        boolean result = action.process();
        System.out.println("DEBUG: Результат игры: " + (result ? "Выигрыш" : "Проигрыш"));
        
        if (result) {
            // Выигрыш
            player1.addNumber(5);
            updateBalanceLabel();
            showWinAlert();
        } else {
            // Проигрыш
            showLoseAlert();
        }
        
        // Сбрасываем цепочку для следующей игры
        action = null;
    }

    private void showNotEnoughMoneyAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Недостаточно средств");
        alert.setHeaderText("У вас недостаточно монет!");
        alert.setContentText("Добавьте монету, чтобы продолжить игру.");
        alert.showAndWait();
    }

    private void showWinAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Поздравляем!");
        alert.setHeaderText("Вы выиграли!");
        alert.setContentText("Вы получили 5 монет!");
        alert.showAndWait();
    }

    private void showLoseAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Увы!");
        alert.setHeaderText("Вы проиграли");
        alert.setContentText("Вы потеряли 1 монету. Попробуйте еще раз!");
        alert.showAndWait();
    }

    @FXML
    public void OnActioninformationButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("Правила игры");
        alert.setContentText("Выберите один из трех мешочков. Если в мешочке есть монетки - вы выиграли 5 монет! " +
                           "Вы можете забрать выигрыш или продолжить игру. " +
                           "Если мешочек пуст - вы проиграли 1 монету, но можете попробовать снова!");
        alert.showAndWait();
    }
}