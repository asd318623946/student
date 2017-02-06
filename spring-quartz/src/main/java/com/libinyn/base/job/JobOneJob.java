package com.libinyn.base.job;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.libinyn.base.service.ScheduleJobService;
import com.libinyn.base.vo.ScheduleJob;

@Component("jobOneJob")
public class JobOneJob extends AbstractJob {

	@Resource
	private ScheduleJobService scheduleJobService;
	
	JobOneJob() {
		super("每5秒打印一次欢迎！", JobOneJob.class);
	}

	@Override
	protected void doTask(Object args) throws Exception {
		if(args != null){
			String username = (String)args;
			System.err.println("欢迎！"+ username);
			ScheduleJob scheduleJob = new ScheduleJob();
			scheduleJob.setJobName("testJob1");
			scheduleJob.setDescJob("测试插入任务");
			scheduleJob.setJobStatus("2");
			scheduleJob.setCronExpression("0/5 * * * * ?");
			scheduleJobService.createScheduleJob(scheduleJob);
			System.err.println("JobId = " + scheduleJob.getJobId());
		}
	}


}
