import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;
import  java.sql.CallableStatement;

class  CreateGames {
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

     String query = "select * from games  where asin = '"+args[0].trim()+"'";
     ResultSet rset = stmt.executeQuery( query );
     if ( rset.next() ) {

      String message = "UPDATE DATA SUCCESSFUL" ;  
      
      int price = Integer.parseInt(args[2].trim());
      String developerid =args[3].trim();
      String[] developerids = developerid.split(",");
      String developername=args[4].trim();
      String[] developernames=developername.split(",");
      String subquery="";
      for (int i=0;i<developerids.length;i++) {
       subquery=subquery+"developer_temp("+Integer.parseInt(developerids[i])+",'"+developernames[i]+"'),";

     }
                //UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;
     String query2 = "UPDATE games SET title='"+args[1].trim()+"',price="+ price +",developers= developers_list("+ subquery.substring(0,subquery.length()-1) +") where asin = '"+args[0].trim()+"'";
     rset = stmt.executeQuery( query2 );
     String output = "{\"success\": \""+ message +"\"}";
     System.out.println(output);

     
     
   } else { 

    int price = Integer.parseInt(args[2].trim());
    String developerid =args[3].trim();
    String[] developerids = developerid.split(",");
    String developername=args[4].trim();
    String[] developernames= developername.split(",");
    String subquery="";
   // String[] developerquery= new String[developerids.length];
            // System.out.println(developernames);
    for (int i=0;i<developerids.length;i++) {
      
     if(i==developerids.length-1){
      subquery=subquery+"developer_temp("+Integer.parseInt(developerids[i])+",'"+ developernames[i]+"')";
      
       }else{

      subquery=subquery+"developer_temp("+Integer.parseInt(developerids[i])+",'"+ developernames[i]+"'),";
      
      }
     
  }

  String query2 = "INSERT INTO GAMES VALUES ('"+args[0].trim()+"','" +args[1].trim()+"'," + price +", DEVELOPERS_LIST( "+ subquery +"))";

           // System.out.println(query2);
    rset = stmt.executeQuery( query2 );
    
    CallableStatement callableStatement = null;
    
    String updatedev = "{call UPDATEDEVELOPER(?,?,?,?)}";
    callableStatement = conn.prepareCall(updatedev);
    callableStatement.setString(1,args[0].trim());
    callableStatement.setString(2,args[1].trim());
    callableStatement.setInt(3,price);
    callableStatement.setString(4,args[3].trim());
    callableStatement.execute();

  // System.out.println(status+developerids.length+developerquery.length);

/*  for (int i=0;i<developerquery.length;i++) {
        System.out.println(i);
        System.out.println(developerquery[i]);
        stmt.executeQuery(developerquery[i]);
  }*/

/*INSERT INTO GAMES VALUES('1234','coc', 30 ,DEVELOPERS_LIST(
                                                        developer_temp(1,'aaaa'),
                                                        developer_temp(2,'era'),
                                                        developer_temp(3,'abba')
                                                        ));*/


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
