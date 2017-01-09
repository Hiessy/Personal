package com.test.database;

import ar.com.personal.srvnews.pojo.Member;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapSessionImpl;
import com.mysql.jdbc.Connection;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("TableDate", "ClickDate");
		params.put("TableName", "oempro_stats_link");
		params.put("RelCampaignID", 845);

		List<MetaMember> e = (List<MetaMember>) smc.queryForList("Member.getDataIDforEmail", params);
		List<Member> memList = new ArrayList<Member>();

		Map<String, Object> param2s = new HashMap<String, Object>();
		param2s.put("SubscriberID", 0);
		param2s.put("TableName", "");
		for (MetaMember m : e) {
			param2s.replace("SubscriberID", m.getSubscriberID());
			param2s.replace("TableName", "oempro_subscribers_" + m.getListID());
			Member mem = (Member) smc.queryForObject("Member.getDataEmail", param2s);

			mem.getMailingCustomID().setMailingID(m.getSubscriberID());
			mem.getMailingCustomID().setDate(m.getDate());
			memList.add(mem);
		}

		if (memList != null) {
			for (Member m : memList){
				System.out.println("MemberID         = " + m.getMemberID());
				System.out.println("EmailAddress     = " + m.getEmail());
				System.out.println("Ip ID            = " + m.getMailingCustomID().getId());
				System.out.println("SubscriptionIP   = " + m.getMailingCustomID().getMailingID());
				System.out.println("Date             = " + m.getMailingCustomID().getDate());
			}
				
		}

	}
}
