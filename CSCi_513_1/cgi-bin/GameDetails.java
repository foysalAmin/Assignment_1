// Import the following packages to use JDBC.
import java.util.Arrays;
import java.util.List;
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;
import  java.sql.CallableStatement;
class  GameDetails {
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
		Statement state_developers = conn.createStatement();
		Statement state_games = conn.createStatement();
		String query = "SELECT d.id, d.name FROM GAMES g, DEVELOPERS d WHERE g.asin = '"+args[0]+"' AND d.id IN (SELECT e.ID FROM table(g.developers) e)";
		String query_1 = "SELECT title, price FROM games WHERE asin = '" + args[0] + "'";
       
		try{

			ResultSet rset = state_developers.executeQuery(query);
            ResultSet rset_1 = state_games.executeQuery(query_1);
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			boolean firstTime = true;
			
			if(rset_1.next())
			{
				builder.append("\"Title\":\"");
				builder.append(rset_1.getString(1));
				builder.append("\",\"Price\":\"");
				builder.append(String.format("%.3f",Float.parseFloat(rset_1.getString(2))));
				builder.append("\",");
			}
			
			builder.append("\"Devs\":[");
			
			while(rset.next())
			{  
		       
				if(firstTime)
					firstTime = false;
				else
					builder.append(",");

				builder.append("{\"ID\":\"");
				builder.append(rset.getInt(1));
				builder.append("\",\"DEVELOPER\":\"");
				builder.append(rset.getString(2));
				builder.append("\"}"); 
				
			}



			builder.append("]}");

			System.out.println(builder.toString());

			rset.close();
			rset_1.close();
			state_developers.close();
			state_games.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		
		/*try
		{
			
		
			ResultSet rset = state.executeQuery(query);

			
			StringBuilder builder = new StringBuilder();
		  //  builder.append("[");
			boolean firstTime = true;
			CallableStatement callableStatement = null;
			String getdev = "{? = call finddevelopers(?)}";
			
			callableStatement = conn.prepareCall(getdev);

			callableStatement.registerOutParameter(1,java.sql.Types.VARCHAR);
			callableStatement.setString(2, args[0]);
			callableStatement.execute();
			String developer = callableStatement.getString(1);

            //System.out.println(callableStatement.getString(1));


			while(rset.next())
			{  
		         //System.out.println(rset.getString(1));

				//if(firstTime)
				//	firstTime = false;
			//	else
			//	builder.append(",");

				builder.append("{\"ASIN\":\"");
				builder.append(rset.getString(1));
				builder.append("\",\"TITLE\":\"");
				builder.append(rset.getString(2));
				builder.append("\",\"PRICE\":\"");
				builder.append(rset.getString(3));

				builder.append("\",\"DEVELOPERS\":\"");

				builder.append(developer.substring(0,developer.length()-1));
				builder.append("\"}"); 
			}



		//	builder.append("]");

			System.out.println(builder.toString());

			rset.close();
			state.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}*/
		conn.close();
	}
}
