package br.com.bmo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() throws SQLException {
		
		// 1 - sudo docker run --name mysql_localhost -e MYSQL_ROOT_PASSWORD=dqm50vnc -p 3306:3306 -d mysql:8.0.18
		// 2 - sudo docker start mysql_localhost
		// 3 - sudo docker exec -it mysql_localhost bash	
		return DriverManager
		.getConnection("jdbc:mysql://localhost/online_store?userTimezone=true&serverTimezone=UTC", 
				"root", "dqm50vnc");
	}
}
