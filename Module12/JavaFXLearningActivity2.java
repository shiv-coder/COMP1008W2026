import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaFXLearningActivity2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // =========================
        // Painter App
        // =========================
        VBox painterRoot = new VBox(10);
        painterRoot.setStyle("-fx-padding: 10;");
        Label painterLabel = new Label("Painter App");
        RadioButton circleBtn = new RadioButton("Circle");
        RadioButton rectBtn = new RadioButton("Rectangle");
        ToggleGroup shapeGroup = new ToggleGroup();
        circleBtn.setToggleGroup(shapeGroup);
        rectBtn.setToggleGroup(shapeGroup);
        circleBtn.setSelected(true);

        Canvas canvas = new Canvas(400, 300);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Mouse event to draw shapes
        canvas.setOnMousePressed(e -> {
            double x = e.getX();
            double y = e.getY();
            if (circleBtn.isSelected()) {
                gc.setFill(Color.BLUE);
                gc.fillOval(x - 20, y - 20, 40, 40);
            } else if (rectBtn.isSelected()) {
                gc.setFill(Color.GREEN);
                gc.fillRect(x - 20, y - 20, 40, 40);
            }
        });

        painterRoot.getChildren().addAll(painterLabel, circleBtn, rectBtn, canvas);
        Scene painterScene = new Scene(painterRoot, 500, 400);

        // =========================
        // Color Chooser App
        // =========================
        VBox colorRoot = new VBox(10);
        colorRoot.setStyle("-fx-padding: 10;");
        Label colorLabel = new Label("Color Chooser App");
        Canvas colorCanvas = new Canvas(400, 200);
        ColorPicker colorPicker = new ColorPicker(Color.RED);

        // Bind ColorPicker to canvas fill
        GraphicsContext colorGc = colorCanvas.getGraphicsContext2D();
        colorGc.setFill(colorPicker.getValue());
        colorGc.fillRect(0, 0, colorCanvas.getWidth(), colorCanvas.getHeight());

        colorPicker.setOnAction(e -> {
            colorGc.setFill(colorPicker.getValue());
            colorGc.fillRect(0, 0, colorCanvas.getWidth(), colorCanvas.getHeight());
        });

        colorRoot.getChildren().addAll(colorLabel, colorPicker, colorCanvas);
        Scene colorScene = new Scene(colorRoot, 500, 300);

        // =========================
        // Cover Viewer App
        // =========================
        VBox coverRoot = new VBox(10);
        coverRoot.setStyle("-fx-padding: 10;");
        Label coverLabel = new Label("Cover Viewer App");

        // Sample images (replace with local image files if desired)
        ObservableList<ImageView> images = FXCollections.observableArrayList();
        images.add(new ImageView(new Image("https://via.placeholder.com/100x150.png?text=Book+1")));
        images.add(new ImageView(new Image("https://via.placeholder.com/100x150.png?text=Book+2")));
        images.add(new ImageView(new Image("https://via.placeholder.com/100x150.png?text=Book+3")));

        ListView<ImageView> listView = new ListView<>(images);
        listView.setPrefHeight(200);

        coverRoot.getChildren().addAll(coverLabel, listView);
        Scene coverScene = new Scene(coverRoot, 300, 300);

        // =========================
        // Scene Switching with Tabs
        // =========================
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Painter", painterRoot);
        Tab tab2 = new Tab("Color Chooser", colorRoot);
        Tab tab3 = new Tab("Cover Viewer", coverRoot);
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);

        tabPane.getTabs().addAll(tab1, tab2, tab3);

        Scene mainScene = new Scene(tabPane, 600, 450);
        primaryStage.setTitle("Module 12: JavaFX Apps");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}