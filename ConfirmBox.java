package sample;

import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    static boolean answer;

    //displays a generic popup box with a title, message, and choice between "yes" or "no"
    //returns true or false depending on whether the user chose "yes" or "no"
    public static boolean display(String title, String message) {
        Stage window = new Stage();
        //makes the window modal
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(200);
        window.setResizable(false);

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.getIcons().add(new Image(Main.class.getResourceAsStream("data/tasks.png")));
        window.setScene(scene);

        //wait until boolean is returned
        window.showAndWait();

        //returns false by default
        return answer;
    }
}
