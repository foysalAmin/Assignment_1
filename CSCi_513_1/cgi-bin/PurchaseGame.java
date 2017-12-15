// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;
import  java.sql.CallableStatement;
class  PurchaseGame {
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
		
		if( args.length!=0){
			Statement state = conn.createStatement();

			String query ="";
			String changequery="";
			String[] asins= args[0].trim().split(",");
			String[] costs= args[1].trim().split(",");
			String[] quantities= args[2].trim().split(",");

			String username=args[3].trim();
			ResultSet rset=null;

			for(int i=0;i<asins.length;i++){
				query="select t.\"FULLNAME\", tt.asin, tt.quantity from \"CUSTOMERS\" t, table(t.\"ACCOUNT\") tt WHERE t.USERNAME='"+username+"' and tt.asin='"+asins[i]+"'";

				try
				{
					rset=state.executeQuery(query);

				}
				catch(SQLException e)
				{
					System.out.println(e);
				}

				if(rset.next()){
					
					changequery="update table(select c.\"ACCOUNT\" from CUSTOMERS c WHERE c.\"USERNAME\"='"+username+"') i set i.\"QUANTITY\" ="+quantities[i]+"where i.\"ASIN\" ='"+asins[i]+"'";

                   try
					{
						state.executeQuery(changequery);

					}
					catch(SQLException e)
					{
						System.out.println(e);
					}
				
				}else{
					changequery="INSERT INTO TABLE ( SELECT account FROM CUSTOMERS WHERE USERNAME = '"+username+"' ) VALUES ( customer_games('"+asins[i]+"',"+Integer.parseInt(quantities[i])+","+Integer.parseInt(costs[i])+"))";
					System.out.println(changequery);
					System.out.println(changequery);
				
					try
					{
						state.executeQuery(changequery);

					}
					catch(SQLException e)
					{
						System.out.println(e);
					}

						
				}
			}
		rset.close();
		state.close();

           System.out.println("Purchase Complete");
		}else{
			System.out.println("Choose Product");
		}

		conn.close();
	}
}
