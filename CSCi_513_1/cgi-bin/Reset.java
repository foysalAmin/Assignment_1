// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  Reset {
  public static void  main( String args[ ] ) throws SQLException { 
   
    String status = "error";
  
    String user     = "mamin";
    String password = "df56po9m";
    String database = "oracle1.aero.und.edu:1521/cs513.aero.und.edu";

    // Open an OracleDataSource and get a connection.
    OracleDataSource ods = new OracleDataSource( );
    ods.setURL     ( "jdbc:oracle:thin:@" + database );
    ods.setUser    ( user );
    ods.setPassword( password );
    Connection conn = ods.getConnection( ); 
    try {
      // Create, compose, and execute a statement.
      
	        Statement stmt = conn.createStatement();

			
			String query = "delete from customers";
			stmt.executeUpdate(query);
			
			query = "delete from developers";
			stmt.executeUpdate(query);
			
			query = "delete from games";
			stmt.executeUpdate(query);
			
			stmt.close();
			
    }
    catch ( SQLException ex ) {
      System.out.println( ex );
    }
    // Close the Connection.
    conn.close( );
  }
}
