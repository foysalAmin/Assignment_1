
// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;

class  RemoveDev {
    public static void  main( String args[ ] ) throws SQLException {
		if(args.length == 0)
			return; //only do stuff if there is people to delete

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
			
			 String sql = "DELETE FROM developers where id = ?";
             PreparedStatement prest = conn.prepareStatement(sql);
			 
			 for(int i = 0; i < args.length; i++)
			 {
                prest.setInt(1, Integer.valueOf(args[i]));
                prest.executeUpdate();
			 }
             
			 prest.close();
			/*
			Statement state = conn.createStatement();
			StringBuilder query = new StringBuilder();
			//query.append("DELETE FROM developers WHERE");
			query.append("BEGIN ");
			for(int i = 0; i < args.length; i++)
			{
				System.out.println( "data : " + args[i]); 
				/*if(i != 0)
					query.append(" OR");
				query.append(" id = ");
				query.append(args[i]);
				query.append("delete_dev(");
				query.append(args[i]);
				query.append("); ");
			}
			query.append("END;");
			System.out.println(query.toString());
			state.executeUpdate(query.toString());
			state.close();
			*/
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		conn.close();
	}
}
