package com.koreait.integration.batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

// [ Listener 파일 ]
// (Run of Server) 실행 할 때, 같이 실행된다.
@WebListener
public class BoardListener implements ServletContextListener {

    
	/* 필드 생성 */ 
	// Scheduler, SchedulerFactory : quartz
	private Scheduler scheduler;
	private SchedulerFactory factory;
	
	
	/* 생성자 생성 */
    public BoardListener() {
        // Constructor (생성자)
    	// BoardListener가 동자갛면 factory와 scheduler 생성
    	try {
    		factory = new StdSchedulerFactory();
    		scheduler = factory.getScheduler();
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    	}
    		
    }

	/* 실행 종료 */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("=== IntegrationProject 종료  ===");
    	// scheduler.shutdown()
    	try {
    		scheduler.shutdown();
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    	}
    }

	/* 실행 시작 */ 
    public void contextInitialized(ServletContextEvent sce)  {

    	System.out.println("=== IntegrationProject 시작  ===");
    	
    	// 프로젝트 실행과 동시에 작동되는 메서드
    	// Scheduler 동작에 필요한 2가지
    	// 1. Job 	  : 무슨 일을 -		: Job 인터페이스를 구현한 클래스
    	// 2. Trigger : - 언제 하겠다.	: CronTrigger를 이용한다. [ http://www.cronmaker.com/ ]
    	
    	try {
    		
    		// Job 생성
	    	JobDetail job = JobBuilder.newJob(BoardJob.class)
	    							  .withIdentity("myBoardJob", "myGroup")
	    							  .build();
	    	// Trigger 생성
	    	CronTrigger trigger = TriggerBuilder.newTrigger()
	    									    .withIdentity("myBoardTrigger", "myGroup")
	    									    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
	    									    .build();
	    	
	    	// start() 
	    	scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
    	
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    	}
    	
         
    }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
