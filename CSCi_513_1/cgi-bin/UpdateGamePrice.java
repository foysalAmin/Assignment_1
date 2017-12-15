import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;
import  java.sql.CallableStatement;
class  UpdateGamePrice {
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
    String message = "UPDATE PRICE SUCCESSFUL" ;  
    String[] asin=args[0].trim().split(",");
    String[] price=args[1].trim().split(",");
    String updatequery="";
    CallableStatement callableStatement = null;
    String updategame = "{call UPDATEPRICE(?,?)}";





    for (int i=0;i<asin.length;i++) {

      try {
        callableStatement = conn.prepareCall(updategame);

        callableStatement.setString(1,asin[i]);
        callableStatement.setInt(2,Integer.parseInt(price[i]));

        callableStatement.execute();

      } catch (SQLException e) {

       String output = "{\"error\": \""+ e +"\"}";
       System.out.println(output);

     }

        //  updatequery = "UPDATE games SET price="+Integer.parseInt(price[i])+"where asin = '"+ asin[i]+"'";
        //  rset = stmt.executeQuery( updatequery );

   }
                //UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;

   String output = "{\"success\": \""+ message +"\"}";
   System.out.println(output);
   conn.close( );
 }
}