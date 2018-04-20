package students;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import admin.*;
import admin.StudentData;
import dbUtil.dbConnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import loginapp.*;

public class StudentsController implements Initializable {
	@FXML
	private TextField id;
	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField email;
	@FXML
	private TextField dob;

	private dbConnection dc;

	private ObservableList<StudentData> data;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		load();

		// }
	}

	public void load() {
		String uName = LoginController.getUsername();
//		String query = "SELECT * FROM Students  WHERE username='" + uName+"'" ;
		String query = "SELECT * FROM Students  WHERE username=?" ;
		//Statement stmt=null;
		try {
			Connection conn = dbConnection.getConnection();
			//stmt=conn.createStatement();
			PreparedStatement stmt= conn.prepareStatement(query);
			System.out.println(uName);
			stmt.setString(1, uName);
			
			ResultSet rs = stmt.executeQuery();
		//	ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Students WHERE username='"+uName+"'");

			System.out.println(rs.getString(3));

			this.id.setText(rs.getString(1));
			this.fname.setText(rs.getString(2));
			this.lname.setText(rs.getString(3));
			this.email.setText(rs.getString(4));
			this.dob.setText(rs.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
