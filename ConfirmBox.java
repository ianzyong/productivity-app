package sample;

import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(200);
        window.setResizable(false);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("Yes");
        closeButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        Button backButton = new Button("No");
        backButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,closeButton, backButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.getIcons().add(new Image(Main.class.getResourceAsStream("data/tasks.png")));
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
