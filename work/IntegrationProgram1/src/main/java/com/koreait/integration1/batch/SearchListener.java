package com.koreait.integration1.batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Application Lifecycle Listener implementation class SearchListener
 *
 */
@WebListener
public class SearchListener implements ServletContextListener {

	// field
	private Scheduler scheduler;
	private SchedulerFactory factory;
	
	// constructor
    public SearchListener() {

    	try {
    		factory = new StdSchedulerFactory();
    		scheduler = factory.getScheduler();
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    	}
    	
    }

    // 종료
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
         try {
     		scheduler.shutdown();
     	} catch (SchedulerException e) {
     		e.printStackTrace();
     	}
         
    }

	// 시작
    public void contextInitialized(ServletContextEvent sce)  { 
         
    	try {
    		JobDetail job = JobBuilder.newJob(SearchJob.class)
					  				  .withIdentity("mySearchJob", "myGroup")
					  				  .build(); 
    		CronTrigger trigger = TriggerBuilder.newTrigger()
				    .withIdentity("mySearchTrigger", "myGroup")
				    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
				    .build();
    		scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
    	
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    	}
    	
    }
	
}
