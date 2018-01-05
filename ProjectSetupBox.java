package sample;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

import static javafx.scene.paint.Color.*;

public class ProjectSetupBox {
    public static String projectName;

    public static Project display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create a new project");
        window.setMinWidth(400);
        window.setMinHeight(200);
        window.setResizable(false);

        Label label = new Label();
        label.setText("Please enter the name of your project:");

        TextField input = new TextField("New Project");
        input.setMaxWidth(300);

        window.setOnCloseRequest(e -> {
            e.consume();
            //return new Project(projectName, Color.TEAL);
        });

        input.setOnAction(e -> {
            if (input.getText().equals("")) {
                Popup.display("Error","Please enter a project name.");
            } else {
                projectName = input.getText();
                window.close();
            }
        });

        Button ok = new Button("OK");
        ok.setOnAction(e -> {
            if (input.getText().equals("")) {
                Popup.display("Error","Please enter a project name.");
            } else {
                projectName = input.getText();
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,input,ok);
        layout.setAlignment(Pos.CENTER);
        window.getIcons().add(new Image(Main.class.getResourceAsStream("data/tasks.png")));
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return new Project(projectName, TEAL);
    }
}
