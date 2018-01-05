package sample;

import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Popup {

    public static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(200);
        window.setResizable(false);

        Label label = new Label();
        label.setText(message);

        Button ok = new Button("OK");
        ok.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,ok);
        layout.setAlignment(Pos.CENTER);
        window.getIcons().add(new Image(Main.class.getResourceAsStream("data/tasks.png")));
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
