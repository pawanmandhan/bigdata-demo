package com.demo.util.query;

import static com.demo.constant.FileLocationConstants.INPUT_DIRECTORY_PATH;
import static com.demo.constant.QueryConstants.DATABASE_NAME;

public class QueryUtil {

	public static String getLoadLocalFileToStagingQuery(String fileName, String tableName) {
		return "LOAD DATA LOCAL INPATH '" + INPUT_DIRECTORY_PATH + fileName + "' INTO TABLE " + DATABASE_NAME + "."
				+ tableName;
	}

//	public static String getCreateTableQuery(String tableName) {
//		return "CREATE TABLE " + tableName + "(" + "load_time BIGINT, report_time BIGINT, aoperator STRING, "
//				+ "Boperator STRING, A_Mobile_number BIGINT, B_Mobile_Number BIGINT, "
//				+ "A_INSI BIGINT, B_IMSI BIGINT, A_IMEI BIGINT, "
//				+ "B_IMEI BIGINT, call_type STRING, Failure_type Array<STRING>, "
//				+ "latitude STRING, longitue STRING, city STRING, "
//				+ "state STRING, call_start_time BIGINT, call_end_time BIGINT) " + "PARTITIONED BY (loadTime STRING) "
//				+ "ROW FORMAT DELIMITED " + "FIELDS TERMINATED BY ',' " + "COLLECTION ITEMS TERMINATED BY ' ' "
//				+ "STORED AS TextFile;";
//	}

}
