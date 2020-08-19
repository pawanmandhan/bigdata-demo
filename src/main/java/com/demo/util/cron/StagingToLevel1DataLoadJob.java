package com.demo.util.cron;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.demo.util.hive.HiveConnectionUtil;
import com.demo.watcher.FileWatcher;

public class StagingToLevel1DataLoadJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {

		long partitionName = new Date().getTime() / 1000L;
		int newFilesCount = FileWatcher.NEW_FILES_TO_BE_LOADED.size();
		while (newFilesCount-- > 0) {

			System.out.println("Long timestamp : " + lastTableIndex + " Last table Entry : " + FileWatcher.LAST_TABLE_ENTRY);
			if (lastTableIndex > Long.parseLong(FileWatcher.LAST_TABLE_ENTRY + "") && lastTableIndex <= partitionName) {

				String STAGING_DATALOAD_QUERY = extracted(partitionName, newFilesCount);
				System.out.println("Query : " + STAGING_DATALOAD_QUERY);
				try {
					HiveConnectionUtil.getConnection().createStatement().execute(STAGING_DATALOAD_QUERY);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	private String extracted(long partitionName, int newFilesCount) {
		return "INSERT OVERWRITE TABLE level1_table PARTITION(loadTime='" + partitionName
				+ "') SELECT id,load_time,mobile_no FROM staging_table WHERE loadTime='"
				+ FileWatcher.NEW_FILES_TO_BE_LOADED.get(newFilesCount) + "'";
	}

}
