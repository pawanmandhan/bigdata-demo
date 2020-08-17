package com.demo.util.cron;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.demo.util.hive.HiveConnectionUtil;
import com.demo.watcher.FileWatcher;

public class LocalFSToStagingDataLoadJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {

		long partitionName = new Date().getTime() / 1000L;
		int newFilesCount = FileWatcher.NEW_FILES_TO_BE_LOADED.size();
		while (newFilesCount-- > 0) {

			long lastTableIndex = Long.parseLong(FileWatcher.NEW_FILES_TO_BE_LOADED.get(newFilesCount));
			System.out.println(
					"Long timestamp : " + lastTableIndex + " Last table Entry : " + FileWatcher.LAST_TABLE_ENTRY);
			if (lastTableIndex > Long.parseLong(FileWatcher.LAST_TABLE_ENTRY + "") && lastTableIndex <= partitionName) {

				String STAGING_DATALOAD_QUERY = extracted(partitionName, newFilesCount);
				System.out.println("Query : " + STAGING_DATALOAD_QUERY);

				try {
					HiveConnectionUtil.getConnection().createStatement().execute(STAGING_DATALOAD_QUERY);
				} catch (Exception e) {
					e.printStackTrace();
				}
				FileWatcher.LAST_TABLE_ENTRY = Long.parseLong(FileWatcher.NEW_FILES_TO_BE_LOADED.get(newFilesCount));
			}
		}

	}

	private String extracted(long partitionName, int newFilesCount) {
		return "INSERT OVERWRITE TABLE UsageTable3 PARTITION(loadTime='" + partitionName
				+ "') SELECT load_time, report_time, aoperator, boperator, a_mobile_number, b_mobile_number,"
				+ " a_imsi, b_imsi, a_imei, b_imei, call_type, failure_type, latitude, longitude, call_start_time, "
				+ "call_end_time FROM UsageTable2 WHERE loadTime='"
				+ FileWatcher.NEW_FILES_TO_BE_LOADED.get(newFilesCount) + "'";
	}

}
