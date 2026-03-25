import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class JavaFXLearningActivity extends Application {

    @Override
    public void start(Stage stage) {

        // STEP 1
        Label title = new Label("This is a GUI Application");

        // STEP 4
        Label nameLabel = new Label("Enter Name:");
        TextField nameField = new TextField();
        Button submitBtn = new Button("Submit");
        Label outputLabel = new Label();

        // STEP 5 & 9 (Event Handling)
        submitBtn.setOnAction(e -> {
            String name = nameField.getText();

            if (name.isEmpty()) {
                outputLabel.setText("Name cannot be empty");
            } else {
                outputLabel.setText("Hello " + name);
            }
        });

        // STEP 8 (GridPane)
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        // STEP 7 (HBox)
        HBox buttonBox = new HBox(10, submitBtn);

        // STEP 6 (VBox)
        VBox root = new VBox(10, title, grid, buttonBox, outputLabel);
        root.setPadding(new Insets(15));

        // STEP 11 & 12
        Scene scene = new Scene(root, 350, 250);
        stage.setScene(scene);
        stage.setTitle("GUI vs CLI Demo");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}