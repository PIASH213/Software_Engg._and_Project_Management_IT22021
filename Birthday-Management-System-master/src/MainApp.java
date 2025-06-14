import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BirthdayController controller = new BirthdayController();
            Scene scene = new Scene(controller.getView(), 600, 500);
            primaryStage.setTitle("Birthday Management System");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            showError("Application Failed to Start", e);
            System.exit(1);
        }
    }

    private void showError(String header, Exception e) {
        System.err.println(header + ": " + e.getMessage());
        e.printStackTrace();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fatal Error");
        alert.setHeaderText(header);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
