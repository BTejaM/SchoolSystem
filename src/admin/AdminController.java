package admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController implements Initializable{
	//StudentData loginmodel = new StudentData();
	@FXML
	private TextField id;
	@FXML
	private TextField fname;
	@FXML
	private TextField lastname;
	@FXML
	private TextField email;
	@FXML
	private DatePicker dob;
	@FXML
	private Button clearButton;	
	

@FXML
private TableView<StudentData> studenttable;

@FXML
private TableColumn<StudentData,String> idColumn;

@FXML
private TableColumn<StudentData,String> fnamecolumn;


@FXML
private TableColumn<StudentData,String> lnamecolumn;


@FXML
private TableColumn<StudentData,String> emailcolumn;

@FXML
private TableColumn<StudentData,String> dobcolumn;


private dbConnection dc;

private ObservableList<StudentData> data;


@Override
public void initialize(URL url, ResourceBundle rb) {
	
	this.dc = new dbConnection();
	
		
}
private void loaddata(){
try{
		
		Connection conn=dbConnection.getConnection();
		this.data =FXCollections.observableArrayList();
		ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Students");
		
		while(rs.next()){
			this.data.add(new StudentData(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
		}
		
	}catch(SQLException e){
		e.printStackTrace();
	}
	System.out.println(data.get(0).getEmail());
	
	this.idColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("ID"));
	this.dobcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("DOB"));
	this.fnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("FirstName"));
	//this.fnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("FirstName"));
	this.lnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("LastName"));
	this.emailcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("email"));
	
	this.studenttable.setItems(this.data);
	
}

@FXML
private void loadStudentData(ActionEvent event){
this.loaddata();
	
}

@FXML
private void addStudent(ActionEvent event){
	String sqlInsert ="INSERT INTO students(id,fname,lname,email,DOB) VALUES(?,?,?,?,?)";
	try{
		
		Connection conn= dbConnection.getConnection();
		PreparedStatement stmt= conn.prepareStatement(sqlInsert);
		
		stmt.setString(1, this.id.getText());
		stmt.setString(2, this.fname.getText());
		stmt.setString(3, this.lastname.getText());
		stmt.setString(4, this.email.getText());
		stmt.setString(5, this.dob.getEditor().getText());
		stmt.execute();
		this.loaddata();
		conn.close();
		
		
		
	}catch(SQLException se){se.printStackTrace();}
}



 @FXML
 private void clearFields(ActionEvent event){
	 
	 this.id.setText("");
	 this.fname.setText("");
	 this.lastname.setText("");
	 this.email.setText("");
	 this.dob.setValue(null);
	 //this.firstname.setText("");
 }
}



