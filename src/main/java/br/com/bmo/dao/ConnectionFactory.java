package br.com.bmo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		
		// using C3P0 
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/online_store?userTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("dqm50vnc");
		
		comboPooledDataSource.setMaxPoolSize(15);
		
		this.dataSource = comboPooledDataSource;
	}

	public Connection getConnection()  {
		
		// 1 - sudo docker run --name mysql_localhost -e MYSQL_ROOT_PASSWORD=dqm50vnc -p 3306:3306 -d mysql:8.0.18
		// 2 - sudo docker start mysql_localhost
		// 3 - sudo docker exec -it mysql_localhost bash	
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
