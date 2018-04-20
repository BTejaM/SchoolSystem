package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData {
	
	private final StringProperty ID;
	private final StringProperty FirstName;
	private final StringProperty LastName;
	private final StringProperty email;
	private final StringProperty DOB;
	
	public StudentData(String id, String firstname, String lastname, String email, String dob) {
		
		
		this.ID= new SimpleStringProperty(id);
		this.FirstName= new SimpleStringProperty(firstname);
		this.LastName= new SimpleStringProperty(lastname);
		this.email= new SimpleStringProperty(email);
		this.DOB= new SimpleStringProperty(dob);
		
		
		
	}


	public final StringProperty IDProperty() {
		return this.ID;
	}
	


	public final java.lang.String getID() {
		return this.IDProperty().get();
	}
	


	public final void setID(final java.lang.String ID) {
		this.IDProperty().set(ID);
	}
	


	public final StringProperty firstNameProperty() {
		return this.FirstName;
	}
	


	public final java.lang.String getFirstName() {
		return this.firstNameProperty().get();
	}
	


	public final void setFirstName(final java.lang.String firstName) {
		this.firstNameProperty().set(firstName);
	}
	


	public final StringProperty lastNameProperty() {
		return this.LastName;
	}
	


	public final java.lang.String getLastName() {
		return this.lastNameProperty().get();
	}
	


	public final void setLastName(final java.lang.String lastName) {
		this.lastNameProperty().set(lastName);
	}
	


	public final StringProperty emailProperty() {
		return this.email;
	}
	


	public final java.lang.String getEmail() {
		return this.emailProperty().get();
	}
	


	public final void setEmail(final java.lang.String email) {
		this.emailProperty().set(email);
	}
	


	public final StringProperty DOBProperty() {
		return this.DOB;
	}
	


	public final java.lang.String getDOB() {
		return this.DOBProperty().get();
	}
	


	public final void setDOB(final java.lang.String DOB) {
		this.DOBProperty().set(DOB);
	}
	


}