package com.demo.util.cron;

import static com.demo.constant.QueryConstants.LEVEL1_TABLE;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.demo.util.hive.HiveConnectionUtil;

public class StagingToLevel1DataLoadJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {

		long partitionName = new Date().getTime() / 1000L;
		String STAGING_DATALOAD_QUERY = extracted(partitionName);
		System.out.println("Query : " + STAGING_DATALOAD_QUERY);
		try {
			HiveConnectionUtil.getConnection().createStatement().execute(STAGING_DATALOAD_QUERY);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String extracted(long partitionName) {
		return "INSERT TABLE " + LEVEL1_TABLE + " PARTITION(loadTime='" + partitionName
				+ "') SELECT id,load_time,mobile_no FROM staging_table WHERE loadTime='" + 1 + "'";
	}

}
