package com.libinyn.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libinyn.base.dao.ScheduleJobDao;
import com.libinyn.base.service.ScheduleJobService;
import com.libinyn.base.vo.ScheduleJob;

@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
	
	@Autowired
	private ScheduleJobDao scheduleJobDao;

	@Override
	public void createScheduleJob(ScheduleJob scheduleJob) throws Exception {
		
		 scheduleJobDao.createScheduleJob(scheduleJob);
	}

	@Override
	public ScheduleJob updateScheduleJob(ScheduleJob scheduleJob) throws Exception {
		
		return scheduleJobDao.updateScheduleJob(scheduleJob);
	}
   
	
	
}
