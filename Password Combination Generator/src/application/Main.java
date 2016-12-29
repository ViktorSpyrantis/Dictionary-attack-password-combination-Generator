package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			root.getStyleClass().clear();
			//root.getStyleClass().add("application.css");
			primaryStage.setTitle("Password Generator");
			primaryStage.setScene(new Scene(root,600,650));
			primaryStage.setResizable(false);

			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
