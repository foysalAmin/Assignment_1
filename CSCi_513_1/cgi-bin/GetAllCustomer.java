// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  GetAllCustomer {
    public static void  main( String args[ ] ) throws SQLException {
		String user     = "mamin";
		String password = "df56po9m";
		String database = "oracle1.aero.und.edu:1521/cs513.aero.und.edu";

		// Open an OracleDataSource and get a connection.
		OracleDataSource ods = new OracleDataSource( );
		ods.setURL     ( "jdbc:oracle:thin:@" + database );
		ods.setUser    ( user );
		ods.setPassword( password );
		Connection conn = ods.getConnection( );
		
		try
		{
			Statement state = conn.createStatement();
			String query = "SELECT FULLNAME , USERNAME FROM CUSTOMERS";
			ResultSet rset = state.executeQuery(query);
            
			
			StringBuilder builder = new StringBuilder();
		    builder.append("[");
			boolean firstTime = true;
			while(rset.next())
			{  
		         //System.out.println(rset.getString(1));
		       
				if(firstTime)
					firstTime = false;
				else
				builder.append(",");
				builder.append("{\"FULLNAME\":\"");
				builder.append(rset.getString(1));
				builder.append("\",\"USERNAME\":\"");
				builder.append(rset.getString(2));
				builder.append("\"}"); 
			}
			builder.append("]");

			System.out.println(builder.toString());

			rset.close();
			state.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}
