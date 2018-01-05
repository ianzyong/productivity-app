package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Project {

    static int r_w = 535;

    String pn = "New Project";
    Paint pf = Color.TEAL;

    public Project(String projectName, Color fill) {
        pn = projectName;
        pf = fill;
    }

    public StackPane display() {
        StackPane proj = new StackPane();
        Label n = new Label(pn);
        n.setTextFill(Color.WHITE);
        proj.getChildren().addAll(new Rectangle(r_w,100, pf), n);
        return proj;
    }

    public static StackPane display(String name, Paint fill) {
        StackPane proj = new StackPane();
        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        proj.getChildren().addAll(new Rectangle(r_w,100, fill), n);
        return proj;
    }
}
