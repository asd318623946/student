package com.libinyn.base.service;

import com.libinyn.base.vo.ScheduleJob;

public interface ScheduleJobService {

	/**
	 * 数据库中插入定时任务，并返回插入的对象
	 * @param scheduleJob
	 * @return
	 * @throws Exception
	 */
	void createScheduleJob(ScheduleJob scheduleJob) throws Exception;
	
	/**
	 * 修改定时任务，并返回修改后的对象
	 * @param scheduleJob
	 * @return
	 * @throws Exception
	 */
	ScheduleJob updateScheduleJob(ScheduleJob scheduleJob) throws Exception;
	
	
	
}
