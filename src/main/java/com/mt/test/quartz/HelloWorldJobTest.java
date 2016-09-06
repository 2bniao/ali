package com.mt.test.quartz;

import java.text.ParseException;

import org.quartz.SchedulerException;

public class HelloWorldJobTest {
    public static void main(String[] args) throws ParseException, SchedulerException {
        /*
         * JobDetail job = new JobDetail("helloWorldJob", Scheduler.DEFAULT_GROUP, HelloJob.class); CronTrigger trigger
         * = new CronTrigger("helloWorldTrigger", "cronGroup", "0/10 * * * * ?"); Scheduler scheduler =
         * StdSchedulerFactory.getDefaultScheduler(); String s = scheduler.getSchedulerInstanceId(); scheduler.start();
         * scheduler.scheduleJob(job, trigger);
         */

        System.out.println("==");
    }
}
