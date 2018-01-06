package sample;

import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
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

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import static jdk.nashorn.internal.objects.Global.Infinity;

public class Main extends Application {

    Scene scene1;
    Stage window;

    @Override
    public void start(Stage primaryStage) {
        //Parent root = FXMLLoader.load(getClass().getResource("tasks.fxml"));
        window = primaryStage;
        //set title of main window
        primaryStage.setTitle("ProductivityApp");
        //make resizeable and set minimum height and width
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(845);

        //saves to file before closing
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //create labels
        Label p_label = new Label("Projects:");
        Label t_label = new Label("Tasks:");

        //create VBox which contains p_label
        VBox projects = new VBox();
        projects.getChildren().add(p_label);
        projects.setSpacing(12);
        projects.setPadding(new Insets(12));
        projects.setAlignment(Pos.TOP_CENTER);
        projects.setMargin(projects, new Insets(12,12,12,12));

        //create VBox which contains Rectangles
        VBox tasks = new VBox();
        tasks.setMinWidth(200);
        Rectangle t1 = new Rectangle(150,100,Color.BLUE);
        Rectangle t2 = new Rectangle(150,100,Color.GREEN);
        Rectangle t3 = new Rectangle(150,100,Color.ORANGE);
        tasks.getChildren().addAll(t_label,t1,t2,t3);
        tasks.setSpacing(12);
        tasks.setPadding(new Insets(12));
        tasks.setAlignment(Pos.TOP_CENTER);

        //create button that opens a popup to create a new Project
        Button addProject = new Button("Add Project");
        addProject.setOnAction(e -> {
            StackPane addedProject = ProjectSetupBox.display().getStackPane();
            projects.getChildren().add(addedProject);
        });

        //create wrapper VBox which contains VBox projects and Button addProject
        VBox projectsWrapper = new VBox();
        projectsWrapper.getChildren().addAll(projects,addProject);
        projectsWrapper.setAlignment(Pos.TOP_CENTER);
        projectsWrapper.setPadding(new Insets(12,0,12,0));

        //creates a ScrollPane and adds the wrapper VBox to it
        ScrollPane s1 = new ScrollPane();
        s1.setContent(projectsWrapper);

        //creates a GridPane and sets ColumnConstraints for ScrollPane s1 and VBox tasks
        GridPane divider = new GridPane();
        divider.addRow(0, s1, tasks);
        ColumnConstraints projectsConstraint = new ColumnConstraints(600, 600, Infinity, Priority.ALWAYS, HPos.LEFT, true);
        ColumnConstraints tasksConstraint = new ColumnConstraints(200);
        divider.getColumnConstraints().addAll(projectsConstraint,tasksConstraint);

        //set things to fill width so it looks good
        s1.setFitToWidth(true);
        projectsWrapper.setFillWidth(true);
        projects.setFillWidth(true);

        //TODO: please help. how to make the Project objects span the full width of projectsConstraint??

        //create 4 initial sample projects
        for (int k = 0; k < 4; k++) {
            Project p = new Project( "Project " + (k+1), Color.TEAL);
            StackPane addedProject = p.getStackPane();
            addedProject.prefWidthProperty().bind(projectsConstraint.prefWidthProperty());
            projects.getChildren().add(addedProject);
        }

        //create menu options with different functions
        final Menu menu1 = new Menu("File");
        MenuItem close = new MenuItem("Exit");
        MenuItem about = new MenuItem("About");

        close.setOnAction(e -> {
            closeProgram();
        });

        about.setOnAction(e -> {
            Popup.display("About","ProductivityApp 1.1\nCoded using JavaFX\nWritten by Ian Ong, 2018!");
        });

        //adds "About" and "Exit" under "File"
        menu1.getItems().addAll(about,close);

        //adds "Options" (currently no functionality)
        final Menu menu2 = new Menu("Options");

        //put menu options into a new MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2);

        //adds the main MenuBar and main GridPane into a BorderPane
        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(divider);

        //creating background colors
        Background bg = new Background(new BackgroundFill(Color.rgb(220,220,220), new CornerRadii(5),new Insets(0)));
        Background projects_bg = new Background(new BackgroundFill(Color.rgb(255,255,255), new CornerRadii(5),new Insets(0)));
        Background tasks_bg = new Background(new BackgroundFill(Color.rgb(255,255,255), new CornerRadii(5),new Insets(0)));

        //adding background colors
        divider.setBackground(bg);
        projects.setBackground(projects_bg);
        tasks.setBackground(tasks_bg);

        //creating a new Scene with BorderPane layout as its layout
        scene1 = new Scene(layout,800,600);

        //set the scene of the stage to scene1
        primaryStage.setScene(scene1);

        //set the icon of the window
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("data/tasks.png")));

        //set the stylesheet of the window
        setUserAgentStylesheet(STYLESHEET_MODENA);

        //adds rules in the user-defined stylesheet "mainstyle.css"
        StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("mainstyle.css").toString());

        //the application is displayed
        primaryStage.show();

    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Exit", "Are you sure you want to exit?");
        if(answer) { //if the user chose "yes"
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("save.txt", "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            writer.println("Save string goes here"); //write a save string to a file called "save.txt"
            writer.close();
            System.out.println("File saved!"); //pseudocode to represent saving the state of the program
            window.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
