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

public class ProjectSetupBox {

    static Color desiredColor;
    public static String projectName;

    public static Project display() {
        Stage window = new Stage();
        //makes the window modal
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create a new project");
        window.setMinWidth(400);
        window.setMinHeight(200);
        window.setResizable(false);

        //text initialized
        Label label = new Label();
        label.setText("Please enter the name of your project:");

        Label label2 = new Label();
        label2.setText("Choose a color:");

        //TextField created with default text "New Project"
        TextField input = new TextField("New Project");
        input.setMaxWidth(300);

        //ColorPicker created with default color teal
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.TEAL);
        desiredColor = colorPicker.getValue();

        //do nothing when user presses the "x" button to close
        window.setOnCloseRequest(e -> {
            e.consume();
        });

        //set desired color to the chosen color
        colorPicker.setOnAction(e -> {
            desiredColor = colorPicker.getValue();
        });

        //check to make sure the project name isn't empty when "enter" is pressed
        input.setOnAction(e -> {
            if (input.getText().equals("")) {
                Popup.display("Error","Please enter a project name.");
            } else {
                projectName = input.getText();
                window.close();
            }
        });

        //check to make sure the project name isn't empty when the OK button is pressed
        Button ok = new Button("OK");
        ok.setOnAction(e -> {
            if (input.getText().equals("")) {
                Popup.display("Error","Please enter a project name.");
            } else {
                projectName = input.getText();
                window.close();
            }
        });

        //creates a VBox and adds all elements
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,input,label2,colorPicker,ok);
        layout.setAlignment(Pos.CENTER);
        window.getIcons().add(new Image(Main.class.getResourceAsStream("data/tasks.png")));
        Scene scene = new Scene(layout);
        window.setScene(scene);

        //show the window and wait until something is returned
        window.showAndWait();

        return new Project(projectName, desiredColor);
    }
}
