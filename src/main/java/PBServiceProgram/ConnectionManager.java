package PBServiceProgram;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager {

	private Connection sqlConnection;
	private String address;
	private String port;
	private String database;
	
	public ConnectionManager() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("C:/PBSerwis/pb.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		address = props.getProperty("address");
		port = props.getProperty("port");
		database = props.getProperty("database");
		sqlConnection = DriverManager.getConnection("jdbc:mysql://" + address + ":" + port + "/" + database, user, password);
	}

	public Connection getSqlConnection() {
		return sqlConnection;
	}

	public void setSqlConnection(Connection sqlConnection) {
		this.sqlConnection = sqlConnection;
	}
	@Override
	public String toString(){
		
		return "//"+ address + ":" + port + "/" + database;
	}
		
}
