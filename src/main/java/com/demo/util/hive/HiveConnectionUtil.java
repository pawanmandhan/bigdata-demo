package com.demo.util.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class HiveConnectionUtil {

	private static Connection INSTANCE = null;

	static {
		try {
			Class.forName("org.apache.hive.jdbc.HiveDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private HiveConnectionUtil() {
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (INSTANCE == null) {
			System.out.println("fetching connection instance..");
			INSTANCE = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "npntraining",
					"npntraining");

		}
		return INSTANCE;
	}
}