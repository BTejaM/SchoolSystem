package loginapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;

public class LoginController implements Initializable {
	LoginModel loginmodel = new LoginModel();

	@FXML
	private Label dbstatus;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private ComboBox<option> combobox;

	@FXML
	private Button loginbutton;

	@FXML
	private Label loginstatus;


	public static String uName;
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		if (this.loginmodel.isDataBaseConnected()) {

			this.dbstatus.setText("Connected to database");
		} else {
			this.dbstatus.setText("not connected to database");
		}

		this.combobox.setItems(FXCollections.observableArrayList(option.values()));
	}

	@FXML
	public void Login(ActionEvent event) {
		 uName = this.username.getText();
		if (this.username.getText().isEmpty() | this.password.getText().isEmpty()
				| this.combobox.getSelectionModel().isEmpty()) {
			this.loginstatus.setText("Please Enter all fields");
			return;
		}

		try {

			if (this.loginmodel.isLogin(this.username.getText(), this.password.getText(),
					this.combobox.getValue().toString())) {
				Stage stage = (Stage) this.loginbutton.getScene().getWindow();
				// stage.close();

				switch (this.combobox.getValue().toString()) {

				case "Admin":
					adminLogin();
					break;
				case "Student":
					studentLogin();
					break;
				}

			} else {
				this.loginstatus.setText("wrong credentials");
			}

			if (this.username.getText() == "" | this.password.getText() == "") {
				this.loginstatus.setText("Please Enter all fields");
			}

		} catch (Exception localException) {
			
			localException.printStackTrace();
		}

	}

	public void studentLogin() {
		try {

			Stage userStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = (Pane) loader.load(getClass().getResource("/students/studentFXML.fxml").openStream());

			StudentsController studentscontroller = (StudentsController) loader.getController();

			Scene scene = new Scene(root);
			userStage.setScene(scene);
			userStage.setTitle("Student Dashboard");
			userStage.setResizable(false);
			this.loginstatus.setText("success");
			userStage.show();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void adminLogin() {

		try {
			Stage adminStage = new Stage();
			FXMLLoader adminloader = new FXMLLoader();

			Pane adminroot = (Pane) adminloader.load(getClass().getResource("/admin/adminFXML.fxml").openStream());

			AdminController adminController = (AdminController) adminloader.getController();
			Scene adminscene = new Scene(adminroot);
			adminStage.setScene(adminscene);
			adminStage.setTitle("Admin Dashboard");

			adminStage.setResizable(false);
			this.loginstatus.setText("success");
			adminStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
		
	public static String getUsername() {
		
		return uName;
	}

}
