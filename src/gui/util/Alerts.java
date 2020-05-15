package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {

	public static void showAlert(String title, String header, String content, AlertType type)
	
	{
		Alert alerts= new Alert(type);
		
		alerts.setTitle(title);
		alerts.setHeaderText(header);
		alerts.setContentText(content);
		alerts.show();
		
		
		
	}

}
