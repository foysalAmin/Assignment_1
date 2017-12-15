// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  IndexPage {
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
      String query = "select pass from customer  where usr_name = '"+args[0].trim()+"'";
      //System.out.println( "<h3><em>" + query + "</em>" );
      ResultSet rset = stmt.executeQuery( query );

	   if ( rset.next( ) ) {
		  
		  if (rset.getString("PASS").equals(args[1].trim()) ) {     
		  
                        status = "login is successful";

                        String output = "{\"success\": \""+status+"\",";
        	        output += "\"userid\":\""+args[0].trim()+"\"}";
		        System.out.println(output);
	
		   } else {

			   status = " PassWord did not match " ;
                           String output = "{\"error\": \""+status+"\"}";
		           System.out.println(output);
		   }
	   
	  }else {
		  
                   status = " User does not exist " ;
                   String output = "{\"error\": \""+status+"\"}";
		   System.out.println(output);
	  }
	  
      // Close the ResultSet and Statement.
      rset.close( );
      stmt.close( );
    }
    catch ( SQLException ex ) {
      System.out.println( ex );
    }
    // Close the Connection.
    conn.close( );
  }
}
