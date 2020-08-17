package com.demo.util.cron;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class LocalFSToStagingJobScheduler {

	public static void startScheduling() {
		try {
			JobDetail job = JobBuilder.newJob(LocalFSToStagingDataLoadJob.class).build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("Each_Minute_Job_1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();

			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (Exception e) {
			System.out.println("Exception occured in QuartzJobScheduler");
			e.printStackTrace();
		}
	}
}
