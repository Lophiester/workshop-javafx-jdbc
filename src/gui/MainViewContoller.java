package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.servies.DepartmentService;

public class MainViewContoller implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;

	@FXML

	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	};

	@FXML
	public void onMenuItemDepartmentAction() {
		LoadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	};

	@FXML
	public void onMenuItemAboutAction() {
		LoadView("/gui/About.fxml", x -> {
		});
	};

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	private synchronized <T> void LoadView(String absoluteName, Consumer<T> acaoDeInicializao) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));

		try {
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			Node mainMenu = mainVbox.getChildren().get(0);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainMenu);
			mainVbox.getChildren().addAll(newVBox.getChildren());
			T controller = loader.getController();
			acaoDeInicializao.accept(controller);
		} catch (IOException e) {
			Alerts.showAlert("IOException", null, e.getMessage(), AlertType.ERROR);
		}

	}
}
