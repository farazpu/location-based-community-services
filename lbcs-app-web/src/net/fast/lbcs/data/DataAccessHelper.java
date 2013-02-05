package net.fast.lbcs.data;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

import java.util.ArrayList;
import java.util.Hashtable;


class DataAccessHelper {

    private static Connection conn;

	/*
	 * Method to execute a SELECT kind of query
	 */
	public static ResultSet executeQuery(String sql)
	{
		openConnection();
		ResultSet rs = null;

		try
		{
			Statement stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);
		}
		catch (SQLException sqlEx)
		{

		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}

		return rs;
	}

        public static void UpdateQuery(String sql)
	{

		openConnection();
		ResultSet rs = null;

		try
		{
			Statement stmt = conn.createStatement();
		        stmt.executeUpdate(sql);
		}
		catch (SQLException sqlEx)
		{

		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			try {
				//conn.close();
			}
			catch(Exception ex)
			{

			}
		}

	//	return rs ;
	}

	/*
	 * Method to execute DML statements
	 */
	public static int execute(String sql,ArrayList<Object> params,ArrayList<String> paramTypes)
	{
		openConnection();
		int recordsAffected = 0;

		try
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
                        setParameters(stmt, params, paramTypes);
			recordsAffected = stmt.executeUpdate();
		}
		catch (SQLException sqlEx)
		{
			System.out.println(sqlEx.toString());
		}
		finally
		{
			closeConnection();
		}

		return recordsAffected;
	}

        private static void setParameters(PreparedStatement stmt, ArrayList<Object> params,ArrayList<String> paramTypes)
            throws SQLException
        {
            for(int i=0; i < params.size(); i++){
                if(paramTypes.get(i).equals("String")){
                    stmt.setString(i+1, (String) params.get(i));
                }
                else if (paramTypes.get(i).equals("Integer")) {
                    stmt.setInt(i+1, (Integer) params.get(i));
                }
                else if (paramTypes.get(i).equals("Double")){
                    stmt.setDouble(i+1, (Double) params.get(i));
                }
                else if (paramTypes.get(i).equals("Boolean")){
                    stmt.setBoolean(i+1, (Boolean) params.get(i));
                }
            }
        }

	/*
	 * Method to establish connection with the database
	 */
	private static void openConnection()
	{
            try{
                if(conn == null || conn.isClosed()){

                	String url = "jdbc:mysql://localhost/lbcs";
                	String user = "root";
                	String password = "";

                	// Load the Connector/J driver
                	try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	// Establish connection to MySQL
                	conn = DriverManager.getConnection(url, user, password);
                	System.out.println("------" + conn);
                }
            }
            catch(Exception ex){
            	System.out.println(ex.getMessage());
            }

	}

        public static void closeConnection(){
            try{
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException ex){

            }
        }

}

