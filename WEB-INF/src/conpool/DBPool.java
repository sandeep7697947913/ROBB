package conpool;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBPool{
	public static Connection getConnection(){
		Connection con = null;
		try{
			InitialContext inx = new InitialContext();
			Context envCtx = (Context) inx.lookup("java:comp/env");
			DataSource dtsc = (DataSource) envCtx.lookup("jdbc/bookrent");
			con = dtsc.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(NamingException e){
			e.printStackTrace();
		}
		return con;
	}
}