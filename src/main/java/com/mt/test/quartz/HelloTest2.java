package com.mt.test.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class HelloTest2 {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
        // String[] groups = sched.getTriggerGroupNames();
        // System.out.println("==" + sched.getTriggerNames("cronGroup"));

    }
}
