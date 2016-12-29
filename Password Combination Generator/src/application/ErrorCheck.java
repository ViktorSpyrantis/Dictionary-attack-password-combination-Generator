package application;

import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorCheck {
	public static void checkForMistakes(boolean caps, boolean small, boolean nums, boolean syms, String exactly, String from, String to, boolean exacRad, boolean mulRad, boolean setPos, String beggining, String end) throws IOException{
		
		//Checks if no checkbox is selected
		if(!caps && !small && !nums && !syms){
			popUpWindow("Please select at least one type of characters");
			
		//Checks if password length's entry is not an integer
		}else if((exacRad && !isNumeric(exactly)) || (mulRad && (!isNumeric(from) || !isNumeric(to)))){
			popUpWindow("Incorect input at \"Password length\" entry");
		}else if(setPos && (beggining == "" || end == "")){ 						//(beggining.length() >= from.length()   || end.length() >= from.length() || (beggining.length() + end.length()) >= from.length())){
			popUpWindow("Incorect input at \"Known positions\" entry");
		}
		
	}
	
	private static void popUpWindow(String tx) throws IOException{
		try {
			Stage newStage = new Stage();
			VBox comp = new VBox();
			comp.setAlignment(Pos.CENTER);
			comp.setSpacing(30);
			Text t = new Text();
			t.setText(tx);
			t.setStyle("-fx-font: 14 arial; -fx-text-alignment: center;");
			
			Button button = new Button();
			button.setText("OK");
			button.setPrefSize(50, 40);
			button.setStyle(String.format("-fx-font-size: 14;"));
			button.setOnAction(e -> {
				newStage.close();
			});
			
			comp.getChildren().add(t);
			comp.getChildren().add(button);
			Scene stageScene = new Scene(comp, 400, 150);
			newStage.setScene(stageScene);
			newStage.setResizable(false);
			newStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static boolean isNumeric(String str) {  
		try {  
			int d = Integer.parseInt(str);  
		}catch(NumberFormatException nfe){  
			return false;
		}  
			return true;  
		}


}
