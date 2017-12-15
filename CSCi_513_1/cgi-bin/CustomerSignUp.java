// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  CustomerSignUp {
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
	  String query = "select * from customers  where USERNAME = '"+args[1].trim()+"'";
	  ResultSet rset = stmt.executeQuery( query );
	  
      if ( rset.next() ) {
		   
            String message = "username already taken. choose another one." ;  
            String output = "{\"error\": \""+ message +"\"}";
	        System.out.println(output);
			
			
      } else { 
	  
		  String query2 = "insert into customers(FULLNAME,USERNAME,PASSWORD) values ( '"+args[0].trim()+"', '" +args[1].trim()+"','"+args[2].trim()+"')";
		  //System.out.println( query2 );
		  rset = stmt.executeQuery( query2 );
		
		  status = "Registration successful !";
		  String output = "{\"success\": \""+status+"\"}";
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
