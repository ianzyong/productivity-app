package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Project extends StackPane {

    static double r_w = 535;

    String pn;
    Paint pf;

    //initializes a Project object with a name and a color
    public Project(String projectName, Color fill) {
        pn = projectName;
        pf = fill;
    }

    //returns a StackPane representation of a Project object
    public StackPane getStackPane() {
        StackPane proj = new StackPane();
        Label n = new Label(pn);
        n.setTextFill(Color.WHITE);
        Rectangle rec = new Rectangle(r_w,100, pf);
        //rec.getStyleClass().add("highlight");
        proj.getChildren().addAll(rec, n);
        return proj;
    }

}
