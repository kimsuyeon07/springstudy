package com.koreait.integration.batch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BoardJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		// 
		System.out.println("BoardJob 동작 완료!");

	}

}
