// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;
import  java.sql.CallableStatement;
class  DeveloperDetails {
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
		Statement state = conn.createStatement();
		String query = "select  tt.asin, tt.title, tt.price from \"DEVELOPERS\" t, table(t.\"GAMES\") tt WHERE t.ID="+args[0]+"";
		try
		{
		
			ResultSet rset = state.executeQuery(query);

		StringBuilder builder = new StringBuilder();
		builder.append("[");
		boolean firstTime = true;
			while(rset.next())
			{  
	           if(firstTime)
					firstTime = false;
				else
				builder.append(",");

				builder.append("{\"ASIN\":\"");
				builder.append(rset.getString(1));
				builder.append("\",\"TITLE\":\"");
				builder.append(rset.getString(2));
				builder.append("\",\"PRICE\":\"");
				builder.append(rset.getDouble(3));
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
