package com.libinyn.base.dao;

import java.util.List;

import com.libinyn.base.vo.ScheduleJob;

public interface ScheduleJobDao {
   
	/**
	 * 创建定时任务
	 * @param scheduleJob
	 * @return ScheduleJob ScheduleJob
	 * @throws Exception
	 */
	public void createScheduleJob(ScheduleJob scheduleJob) throws Exception;
	
	/**
	 * 修改定时任务
	 * @param scheduleJob
	 * @return ScheduleJob ScheduleJob
	 * @throws Exception
	 */
	public ScheduleJob updateScheduleJob(ScheduleJob scheduleJob) throws Exception;
	
	/**
	 * 修改定时任务的状态
	 * @return ScheduleJob ScheduleJob
	 * @throws Exception
	 */
	public ScheduleJob updateScheduleJobStatus(String status) throws Exception;
	
	/**
	 * 查询任务
	 * @return List<ScheduleJob> ScheduleJob
	 * @throws Exception
	 */
	public List<ScheduleJob> queryScheduleJobList(ScheduleJob scheduleJob) throws Exception;
	
	/**
	 * 根据唯一健查询单个任务，如主键（jobId）、任务名称（jobName）
	 * @param scheduleJob
	 * @return ScheduleJob ScheduleJob
	 * @throws Exception
	 */
	public ScheduleJob findScheduleJob(ScheduleJob scheduleJob) throws Exception;
	
}
