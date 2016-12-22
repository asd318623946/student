package com.libinyn.base.job;

import org.springframework.stereotype.Component;

@Component("jobOneJob")
public class JobOneJob extends AbstractJob {

	JobOneJob() {
		super("每5秒打印一次欢迎！", JobOneJob.class);
	}

	@Override
	protected void doTask(Object args) throws Exception {
		if(args != null){
			String username = (String)args;
			System.err.println("欢迎！"+ username);
		}
		
	}


}
