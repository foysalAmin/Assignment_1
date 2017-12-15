// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  CustomerLogin {
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
    Statement stmt = conn.createStatement( );
	  String query = "select * from customers  where USERNAME = '"+args[0].trim()+"' and password ='"+ args[1].trim()+"'";
	  ResultSet rset = stmt.executeQuery( query );
	  
      if ( rset.next() ) {
		   
            String message = "Success" ;  
            String output = "{\"success\": \""+ args[0].trim() +"\"}";
	          System.out.println(output);
      } else{
           String message = "Fail login" ;  
            String output = "{\"error\": \""+ message +"\"}";
            System.out.println(output);
      }
	      
     
      // Close the ResultSet and Statement.
      rset.close( );
      stmt.close( );
    }
    catch ( SQLException ex ) {
      String output = "{\"error\": \""+ ex +"\"}";
	  System.out.println(output);
	  
    }
    // Close the Connection.
    conn.close( );
  }
}
