package com.libinyn.base.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.libinyn.base.utils.DateUtil;

/**
 * 抽象job
 *
 * @fileName: AbstractJob.java
 * @author: WeiHui.Zhang
 * @date: 2016-09-01  9:37
 * @version: v1.0.0
 */
public abstract class AbstractJob {

	private Logger LOGGER;
	private String jobName;

	/**
	 * 构造方法
	 *
	 * @param jobName 任务名
	 */
	AbstractJob(String jobName, Class<? extends AbstractJob> clazz) {
		this.jobName = jobName;
		LOGGER = LoggerFactory.getLogger(clazz);
	}
	
	/**
	 * 干定时任务
	 */
	protected void doJob(Object args) {
		Date start = new Date();
		String startTime = DateUtil.formatYMDHMSDateStr(start);
		LOGGER.info("任务[{}]在[{}]开始运行", jobName, startTime);
		try {
			doTask(args);
		} catch (Exception e) {
			LOGGER.info("任务[{}]执行错误", jobName, e);
		}
		Date end = new Date();
		String endTime = DateUtil.formatYMDHMSDateStr(end);
		LOGGER.info("任务[{}]在[{}]结束运行", jobName, endTime);
	}

	/**
	 * 真正的干定时任务业务
	 */
	protected abstract void doTask(Object args) throws Exception;
}
