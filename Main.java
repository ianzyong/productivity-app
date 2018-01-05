package sample;

import com.sun.javafx.scene.layout.region.BackgroundSizeConverter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

import javax.swing.*;
import java.awt.*;

public class Main extends Application {

    Scene scene1;
    Stage window;

    @Override
    public void start(Stage primaryStage) {
        //Parent root = FXMLLoader.load(getClass().getResource("tasks.fxml"));
        window = primaryStage;
        primaryStage.setTitle("ProductivityApp");
        primaryStage.setResizable(false);
        
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        Label p_label = new Label("Projects:");
        Label t_label = new Label("Tasks:");

//        GridPane layout = new GridPane();
//        ColumnConstraints column1 = new ColumnConstraints();
//        column1.setPercentWidth(65);
//        ColumnConstraints column2 = new ColumnConstraints();
//        column2.setPercentWidth(35);
//        layout.getColumnConstraints().addAll(column1,column2);

        VBox projects = new VBox();
        projects.getChildren().add(p_label);
        for (int k = 0; k < 4; k++) {
            projects.getChildren().add(Project.display("Project " + (k+1), Color.TEAL));
        }

        Button addProject = new Button("Add Project");
        addProject.setOnAction(e -> {
            projects.getChildren().add(ProjectSetupBox.display().display());
        });

        projects.setSpacing(12);
        projects.setPadding(new Insets(12));
        projects.setAlignment(Pos.TOP_CENTER);
        projects.setMargin(projects, new Insets(12,12,12,12));

        VBox projectsWrapper = new VBox();
        projectsWrapper.getChildren().addAll(projects,addProject);
        projectsWrapper.setAlignment(Pos.TOP_CENTER);
        projectsWrapper.setPadding(new Insets(12,0,12,0));

        ScrollPane s1 = new ScrollPane();
        //s1.setPrefSize(120, 120);
        s1.setContent(projectsWrapper);

        VBox tasks = new VBox();
        tasks.setMinWidth(200);
        Rectangle t1 = new Rectangle(150,100,Color.BLUE);
        Rectangle t2 = new Rectangle(150,100,Color.GREEN);
        Rectangle t3 = new Rectangle(150,100,Color.ORANGE);
        tasks.getChildren().addAll(t_label,t1,t2,t3);
        tasks.setSpacing(12);
        tasks.setPadding(new Insets(12));
        tasks.setAlignment(Pos.TOP_CENTER);

        BorderPane layout = new BorderPane();
        layout.setCenter(s1);
        BorderPane.setAlignment(projects, Pos.CENTER);
        BorderPane.setMargin(projects, new Insets(12,12,12,12));
        layout.setRight(tasks);
        BorderPane.setAlignment(tasks, Pos.CENTER);
        BorderPane.setMargin(tasks, new Insets(12,12,12,12));

        final Menu menu1 = new Menu("File");
        MenuItem close = new MenuItem("Exit");
        MenuItem about = new MenuItem("About");
        close.setOnAction(e -> {
            closeProgram();
        });
        about.setOnAction(e -> {
            Popup.display("About","ProductivityApp 1.0\nCoded using JavaFX\nWritten by Ian Ong, 2018!");
        });
        menu1.getItems().addAll(about,close);

        final Menu menu2 = new Menu("Options");

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2);
        layout.setTop(menuBar);

        Background bg = new Background(new BackgroundFill(Color.rgb(220,220,220), new CornerRadii(5),new Insets(0)));
        Background projects_bg = new Background(new BackgroundFill(Color.rgb(255,255,255), new CornerRadii(5),new Insets(0)));
        Background tasks_bg = new Background(new BackgroundFill(Color.rgb(255,255,255), new CornerRadii(5),new Insets(0)));

        layout.setBackground(bg);
        projects.setBackground(projects_bg);
        tasks.setBackground(tasks_bg);

        scene1 = new Scene(layout,800,600);
        //scene2 = new Scene(layout2,300,600);
        primaryStage.setScene(scene1);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("data/tasks.png")));
        primaryStage.show();

//        Group g = new Group();
//        Scene scene = new Scene(g,200,100);
//        Rectangle r = new Rectangle(40,40);
//        r.setX(80);
//        r.setY(30);
//        r.setFill(Color.GREEN);
//        r.setStroke(Color.BLACK);
//        r.setStrokeWidth(5);
//        g.getChildren().add(r);
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Exit", "Are you sure you want to exit?");
        if(answer) {
            System.out.println("File saved!");
            window.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
