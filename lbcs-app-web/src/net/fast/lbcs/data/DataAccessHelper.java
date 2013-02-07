/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fast.lbcs.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccessHelper {

	Statement stmt = null;
	ResultSet rs = null;
	private static Connection con;

	private static void openConnection(){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:MYSQL64");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Connected!");
	}

	private static void closeConnection(){
		try{
			if(con != null && !con.isClosed()){
				con.close();
			}
		}
		catch(SQLException ex){
		}
	}

	public static ResultSet executeQuery(String sql){
		openConnection();
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DataAccessHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
		closeConnection();
		return rs;
	}
    
	public static void UpdateQuery(String sql)
	{
		openConnection();
		try
		{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		closeConnection();
	}    
}
