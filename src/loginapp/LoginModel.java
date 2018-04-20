package loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class LoginModel {

	Connection connection;

	public LoginModel() {

		try {
			this.connection = dbConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (this.connection == null) {
			System.exit(1);
		}
	}

	public boolean isDataBaseConnected() {

		try {
			this.connection = dbConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (this.connection!=null)?true:false;
	}

	public boolean isLogin(String user, String pass, String opt) throws Exception {

		PreparedStatement pr = null;
		ResultSet rs = null;
		String sql = null;
		sql="SELECT * FROM login where username=? and password=? and division=?";
		try {
			pr = this.connection.prepareStatement(sql);
			
			pr.setString(1, user);
			pr.setString(2, pass);
			pr.setString(3, opt);

			rs = pr.executeQuery();

			boolean bool1;
			if (rs.next()) {
				return true;
			}
			return false;

		} catch (Exception ex) {
			return false;

		} finally {
			if(pr!=null)
			pr.close();
			if(rs!=null)
			rs.close();
		}
	}

}