package application;


import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainWindowController {
	public ToggleGroup group = new ToggleGroup();

	@FXML
	private   Button submitButt = new Button();
	@FXML
	private  RadioButton exactlyRad;
	@FXML
	private  RadioButton multiRad;
	@FXML
	private  HBox multilengthBox;
	@FXML
	private  HBox exactlyBox;
	@FXML
	private  Label fromLabel;
	@FXML
	private  Label toLabel;
	@FXML
	private  TextField fromTF;
	@FXML
	private  TextField toTF;
	@FXML
	private  TextField exactlyTF;
	@FXML
	private  CheckBox allCaps;// = new CheckBox();
	@FXML
	private  CheckBox allSmall;// = new CheckBox();
	@FXML
	private  CheckBox allNum;// = new CheckBox();
	@FXML
	private  CheckBox symbols;// = new CheckBox();
	@FXML
	private TextField begginingTF;
	@FXML
	private TextField endTF;
	@FXML
	private CheckBox enterPos;// = new CheckBox();
	@FXML
	private VBox positionsBox;// = new VBox();
	
	int from;
	int to;
	FileCreator fc = new FileCreator();
	private ProgressBarClass pbc = new ProgressBarClass();
	
	@FXML
	private  void initialize(){
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		      public void changed(ObservableValue<? extends Toggle> ov,
		          Toggle old_toggle, Toggle new_toggle) {
		        if (group.getSelectedToggle() == exactlyRad) {
		        	multilengthBox.setDisable(true);
		        	exactlyBox.setDisable(false);      	
		        }else{
		        	exactlyBox.setDisable(true);
		        	multilengthBox.setDisable(false);
		        }
		      }
		    });
		
		enterPos.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		    	if(!enterPos.isSelected()){
		    		positionsBox.setDisable(true);
		    	}else{
		    		positionsBox.setDisable(false);
		    	}
		    }
		});
		
		exactlyRad.setSelected(true);
		exactlyRad.setToggleGroup(group);
		multiRad.setToggleGroup(group);
		enterPos.setSelected(false);
		positionsBox.setDisable(true);
	}
	
	public void submitButtClicked() throws IOException{
		ErrorCheck.checkForMistakes(allCaps.isSelected(), allSmall.isSelected(), allNum.isSelected(), symbols.isSelected(), exactlyTF.getText(), fromTF.getText(), toTF.getText(), exactlyRad.isSelected(), multiRad.isSelected(), enterPos.isSelected(), begginingTF.getText(), endTF.getText());
		
		if(exactlyRad.isSelected()){
			from = Integer.parseInt(exactlyTF.getText());
			to = Integer.parseInt(exactlyTF.getText()) +1;
			
		}else{
			from = Integer.parseInt(fromTF.getText());
			to = Integer.parseInt(toTF.getText()) + 1;
		}
		
		fc.setCharactersArray(allCaps.isSelected(), allSmall.isSelected(), allNum.isSelected(), symbols.isSelected());
		fc.setValues(from, to, begginingTF.getText(), endTF.getText());
		(new Thread(fc)).start();
		pbc.openPbWindow();
		submitButt.setDisable(true);
	}
	
	public int getFrom(){
		return from;
	}
	
	public int getTo(){
		return to;
	}
	
	public  String getBeggining(){
		return begginingTF.getText();
	}
	
	public String getEnd(){
		return endTF.getText();
	}
	
	public  void initializeStage(){
		submitButt.setDisable(false);
	}
}
