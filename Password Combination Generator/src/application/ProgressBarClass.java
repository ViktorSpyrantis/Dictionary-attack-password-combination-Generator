package application;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProgressBarClass {

	private  static ProgressBar pb = new ProgressBar(0.0);
	private static Button button = new Button();
	private static VBox box = new VBox();
	private static Text text1 = new Text("");
	//private static Text text2 = new Text("");
	
	public void openPbWindow(){
		try {
			Stage newStage = new Stage();
			
			box.setAlignment(Pos.CENTER);
			box.setSpacing(20);
			
			
			
			text1.setText("Please wait while the file is being created");
			text1.setStyle("-fx-font: 14 arial; -fx-text-alignment: center;");
			
			//text2.setStyle("-fx-font: 14 arial; -fx-text-alignment: bottom;");
			
			button.setText("OK");
			button.setPrefSize(50, 40);
			button.setStyle(String.format("-fx-font-size: 14;"));
			button.setOnAction(e -> {
				newStage.close();
			});
			
			pb.setPrefWidth(190);
			
			
			box.getChildren().addAll(text1, pb, button);
			button.setVisible(false);
			
			Scene stageScene = new Scene(box, 400, 170);
			newStage.setScene(stageScene);
			newStage.setResizable(false);
			newStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fileCreated(){
		pb.setVisible(false);
		button.setVisible(true);
		text1.setText("File has been created");
	}
	
	public void changePbStatus(int cpw, int tpw){
		pb.setProgress((double)cpw/tpw);
	//	text2.setText(cpw + " of " + tpw + " passwords");
	}
	

}
