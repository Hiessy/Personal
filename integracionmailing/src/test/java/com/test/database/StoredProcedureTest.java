package com.test.database;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapSessionImpl;
import com.ibatis.sqlmap.engine.transaction.TransactionConfig;
import com.ibatis.sqlmap.engine.transaction.TransactionManager;
import com.ibatis.sqlmap.engine.transaction.external.ExternalTransactionConfig;
import com.mysql.jdbc.Connection;

import java.io.*;
import java.sql.SQLException;









import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class StoredProcedureTest {

	public static void main(String[] args) throws IOException, SQLException {
	
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/oempro");
		dataSource.setUsername("root");
		dataSource.setPassword("V5pC6GG#Oii6");

		Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");			
		Connection paramConnection = (Connection) dataSource.getConnection();		
		SqlMapSessionImpl smc = (SqlMapSessionImpl) SqlMapClientBuilder.buildSqlMapClient(rd).openSession(paramConnection);
		System.out.println("Going to read employee name.....");

		List<Procedure> e = (List<Procedure>)smc.queryForList("Procedure.getTestClass");

		System.out.println("First Name:  " + e.get(0).getCampaignID());
		System.out.println("Record name Successfully ");

	}

}
