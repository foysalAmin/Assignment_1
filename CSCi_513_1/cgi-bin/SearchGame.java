// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;
import  java.sql.CallableStatement;
class  SearchGame {
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

	/*		String query="";
			if(args[0].equals("")){
			String query = "SELECT * FROM GAMES where asin='"+ args[0]+"'";
			}else{
				String query = "SELECT * FROM GAMES where asin='"+ args[0]+"'";
			}*/

			String query ="";
			String dev= args[0].trim();
			String[] devs=dev.split("\\s+");
			String subquery="";

			if(args[0].equals("")){
              query="SELECT asin,title ,price FROM GAMES";
			}else{
				for (String d :devs ) {
					subquery=subquery+"'"+d+"',";
					
				}

				//select t.name, tt.asin, tt.price from "DEVELOPERS" t, table(t."GAMES") tt where t.name in ('sajib','sayem');
			  
			  query="select tt.asin, tt.title, tt.price from \"DEVELOPERS\" t, table(t.\"GAMES\") tt where t.name in("+ subquery.substring(0,subquery.length()-1)+")";
			// System.out.println(query); 
			}
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
				builder.append(rset.getInt(3));

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
