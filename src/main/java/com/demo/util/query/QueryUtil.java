package com.demo.util.query;

public class QueryUtil {

	public static String getPartitionQuery(String fileName, String tableName) {
		return "LOAD DATA LOCAL INPATH '/home/npntraining/eclipse-workspace/data/current/" + fileName
				+ "' OVERWRITE INTO TABLE " + tableName + " PARTITION(loadTime='" + fileName + "')";
	}
	
	public static String getCreateTableQuery(String tableName) {
		return "CREATE TABLE "
				+ tableName +"("
				+ "load_time BIGINT, report_time BIGINT, aoperator STRING, "
				+ "Boperator STRING, A_Mobile_number BIGINT, B_Mobile_Number BIGINT, "
				+ "A_INSI BIGINT, B_IMSI BIGINT, A_IMEI BIGINT, "
				+ "B_IMEI BIGINT, call_type STRING, Failure_type Array<STRING>, "
				+ "latitude STRING, longitue STRING, city STRING, "
				+ "state STRING, call_start_time BIGINT, call_end_time BIGINT) "
				+ "PARTITIONED BY (loadTime STRING) "
				+ "ROW FORMAT DELIMITED " 
				+ "FIELDS TERMINATED BY ',' "
				+ "COLLECTION ITEMS TERMINATED BY ' ' "
				+ "STORED AS TextFile;";
	}
	

}
