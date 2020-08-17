package com.demo.util.hive;

import java.sql.SQLException;

import com.demo.constant.QueryConstants;
import com.demo.util.query.QueryUtil;

public class HiveUtils {

	public static void createPartitionTable(String fileName) throws ClassNotFoundException, SQLException {
		String LOAD_DATA_QUERY = QueryUtil.getPartitionQuery(fileName, QueryConstants.STAGING_TABLE);
		System.out.println("partition sql to be executed : " + LOAD_DATA_QUERY);
		HiveConnectionUtil.getConnection().createStatement().execute(LOAD_DATA_QUERY);
		System.out.println("Loaded data into the table successfully");
	}

	public void createUsageTable() throws ClassNotFoundException, SQLException {
		final String CREATE_TABLE_QUERY = QueryUtil.getCreateTableQuery(QueryConstants.LEVEL1_TABLE);
		System.out.println("load data sql to be executed : " + CREATE_TABLE_QUERY);
		HiveConnectionUtil.getConnection().createStatement().execute(CREATE_TABLE_QUERY);
		System.out.println("Loaded data into the table successfully");
	}

}
