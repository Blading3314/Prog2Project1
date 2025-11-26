import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AppStart extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Enter the movie name: ");
        TextField textField = new TextField();
        Button button = new Button("Search");
        VBox root = new VBox(10, label, textField, button);
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Movie Rental System");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
