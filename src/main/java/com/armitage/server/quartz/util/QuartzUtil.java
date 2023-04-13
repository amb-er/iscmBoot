package com.armitage.server.quartz.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.quartz.model.BaseJob;
import com.armitage.server.quartz.model.ScmSystemTask;
import org.springframework.stereotype.Component;

/**
 * Quartz任务调度器工具类
 *
 * @author CatalpaFlat
 * @date Create in 10:25 2017/11/16
 */
public class QuartzUtil {
	private static Log log = LogFactory.getLog(QuartzUtil.class);
	
	public Scheduler clusterQuartzScheduler = (Scheduler) AppContextUtil.getBean("schedulerFactoryBean");
	/**
     * 调度器工厂
     */
    public static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    /**
     * 默认Job组名
     */
    public static String JOB_GROUP_NAME = "DEFAULT_JOB_GROUP_NAME";
    /**
     * 默认触发器组名
     */
    public static String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGER_GROUP_NAME";

    /**
     * 获取调度器
     *
     * @return Scheduler
     * @throws SchedulerException Scheduler获取异常
     */
    public static Scheduler getScheduler2() throws SchedulerException {
        return schedulerFactory.getScheduler();
    }
    
    /**
     * 获取调度器
     *
     * @return Scheduler
     * @throws SchedulerException Scheduler获取异常
     */
    public Scheduler getScheduler() throws SchedulerException {
        return clusterQuartzScheduler;
        
    }

    /**
     * 获取CronTrigger
     *
     * @param jobName          任务名
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param time             crond格式时间
     * @return CronTrigger
     */
    public CronTrigger getCronTrigger(String jobName, String triggerGroupName, String time) {
        if (StringUtils.isBlank(triggerGroupName)) {
            triggerGroupName = TRIGGER_GROUP_NAME;
        }
        return TriggerBuilder.newTrigger().withIdentity(jobName, triggerGroupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
    }

    /**
     * 获取JobDetail
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名（为空使用默认）
     * @param cls          任务类
     * @param jobDataMap   附带参数
     * @return JobDetail
     */
    public JobDetail getJobDetail(String jobName, String jobGroupName, Class<? extends Job> cls, JobDataMap jobDataMap) {
        if (StringUtils.isBlank(jobGroupName)) {
            jobGroupName = JOB_GROUP_NAME;
        }

        if (jobDataMap != null) {
            return JobBuilder.newJob(cls).withIdentity(jobName, jobGroupName).usingJobData(jobDataMap).build();
        } else {
            return JobBuilder.newJob(cls).withIdentity(jobName, jobGroupName).build();
        }
    }

    /**
     * 设置JobDetail 和 CronTrigger 到 scheduler（已获取的调度器中，无需重复调用）
     *
     * @param cls              任务嘞
     * @param jobName          任务名
     * @param jobGroupName     任务组名（为空使用默认）
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param time             crond格式时间
     * @param jobDataMap       附带参数
     * @param scheduler        调度器
     * @return 设置成功与否
     * @throws SchedulerException 调度器异常
     */
    public boolean setJobDetailAndCronTriggerInScheduler(Class<? extends Job> cls, String jobName, String jobGroupName, String triggerGroupName,
                                                                 String time, JobDataMap jobDataMap, Scheduler scheduler) throws SchedulerException {
        if (!isJobKey(scheduler, jobName, jobGroupName)) {
            return false;
        }
        JobDetail jobDetail = getJobDetail(jobName, jobGroupName, cls, jobDataMap);
        CronTrigger trigger = getCronTrigger(jobName, triggerGroupName, time);
        scheduler.scheduleJob(jobDetail, trigger);
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
        return true;
    }

    /**
     * 从调度器中移除Job
     *
     * @param scheduler  调度器
     * @param triggerKey 触发器key（名，组）
     * @param jobKey     任务key（名，组）
     */
    public void removeJob(Scheduler scheduler, TriggerKey triggerKey, JobKey jobKey) {
        try {
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            //移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用CronTrigger类型添加任务
     *
     * @param scheduler        调度器
     * @param cls              任务嘞
     * @param jobName          任务名
     * @param jobGroupName     任务组名（为空使用默认）
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param time             crond格式时间
     * @param jobDataMap       附带参数
     * @return 是否添加成功
     */
    public boolean addJobByCronTrigger(Scheduler scheduler, Class<? extends Job> cls, String jobName, String jobGroupName,
                                               String triggerGroupName, String time, JobDataMap jobDataMap) {
        try {
            return setJobDetailAndCronTriggerInScheduler(cls, jobName, jobGroupName, triggerGroupName, time, jobDataMap, scheduler);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否存在JobKey
     *
     * @param scheduler    任务调度器
     * @param jobName      任务名
     * @param jobGroupName 任务组名
     * @return 是否存在JobKey
     */
    public boolean isJobKey(Scheduler scheduler, String jobName, String jobGroupName) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            return jobDetail == null;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 判断是否存在JobKey
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名
     * @return 是否存在JobKey
     */
    public boolean isJobKey(String jobName, String jobGroupName) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            JobDetail jobDetail = getScheduler().getJobDetail(jobKey);
            return jobDetail == null;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加定时任务
     *
     * @param cls              任务类
     * @param jobName          任务名
     * @param jobGroupName     任务组名（为空使用默认）
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param time             crond格式时间
     * @param jobDataMap       附带参数
     * @return 是否正常添加任务
     */
    public boolean addJobByCronTrigger(Class<? extends Job> cls, String jobName, String jobGroupName,
                                              String triggerGroupName, String time, JobDataMap jobDataMap) {
        try {
            if (StringUtils.isBlank(jobName)) {
                return false;
            }
            Scheduler scheduler = getScheduler();
            return setJobDetailAndCronTriggerInScheduler(cls, jobName, jobGroupName, triggerGroupName, time, jobDataMap, scheduler);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改任务时间
     *
     * @param jobName          任务名
     * @param time             crond格式时间
     * @param jobGroupName     任务组名（为空使用默认）
     * @param triggerGroupName 触发器组名（为空使用默认）
     * @param jobDataMap       附带参数
     * @return 是否修改成功
     */
    public boolean modifyJobTime(String jobName, String time, String jobGroupName,
                                        String triggerGroupName, JobDataMap jobDataMap) {
        try {
            if (StringUtils.isBlank(jobName)) {
                return false;
            }
            Scheduler scheduler = getScheduler();
            if (StringUtils.isBlank(triggerGroupName)) {
                triggerGroupName = TRIGGER_GROUP_NAME;
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return false;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                if (StringUtils.isBlank(jobGroupName)) {
                    jobGroupName = JOB_GROUP_NAME;
                }
                JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                Class<? extends Job> jobClass = jobDetail.getJobClass();
                removeJob(scheduler, triggerKey, jobKey);
                return addJobByCronTrigger(scheduler, jobClass, jobName, jobGroupName, triggerGroupName, time, jobDataMap);
            }
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 启动所有定时任务
     */
    public void startJobs() {
        try {
            Scheduler scheduler = getScheduler();
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            Scheduler scheduler = getScheduler();
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 停止一个job任务
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名（空位默认）
     * @return 是否停止
     */
    public boolean pauseJob(String jobName, String jobGroupName) {
        try {
            Scheduler scheduler = getScheduler();
            if (StringUtils.isBlank(jobGroupName)) {
                jobGroupName = JOB_GROUP_NAME;
            }
            scheduler.interrupt(JobKey.jobKey(jobName, jobGroupName));
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 恢复一个job任务
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名（空位默认）
     * @return 是否恢复
     */
    public boolean resumeJob(String jobName, String jobGroupName) {
        try {
            Scheduler scheduler = getScheduler();
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加定时任务
     *
     * @param cls  任务类
     * @param bJob 任务类属性
     * @return 是否添加成功
     */
    public boolean addJobByCronTrigger(Class<? extends Job> cls, BaseJob bJob) {
        if (bJob == null) {
            return false;
        }
        String jobName = bJob.getJobName();
        if (StringUtils.isBlank(jobName)) {
            return false;
        }
        try {
            Scheduler scheduler = getScheduler();
            return setJobDetailAndCronTriggerInScheduler(cls, jobName, bJob.getJobGroupName(), bJob.getTriggerGroupName(), bJob.getCronTime(), bJob.getJobDataMap(), scheduler);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改任务JobDateMap
     *
     * @param cls  任务类
     * @param bJob 任务类属性
     * @return 是否修改成功
     */
    public boolean modifyJobDateMap(Class<? extends Job> cls, BaseJob bJob) {
        if (bJob == null) {
            return false;
        }
        String jobName = bJob.getJobName();
        if (StringUtils.isBlank(jobName)) {
            return false;
        }
        String triggerGroupName = bJob.getTriggerGroupName();
        if (StringUtils.isBlank(triggerGroupName)) {
            triggerGroupName = TRIGGER_GROUP_NAME;
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, triggerGroupName);
        String jobGroupName = bJob.getJobGroupName();
        if (StringUtils.isBlank(jobGroupName)) {
            jobGroupName = JOB_GROUP_NAME;
        }
        try {
            Scheduler scheduler = getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
            if (jobDetail1 == null) {
                return false;
            }
            JobDataMap oldJobDataMap = jobDetail1.getJobDataMap();
            JobDataMap jobDataMap = bJob.getJobDataMap();
            if (!oldJobDataMap.equals(jobDataMap)) {
                Class<? extends Job> jobClass = jobDetail1.getJobClass();
                removeJob(scheduler, triggerKey, jobKey);
                return addJobByCronTrigger(scheduler, jobClass, jobName, jobGroupName, triggerGroupName, bJob.getCronTime(), jobDataMap);
            }
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    /**
     * 添加任务（餐饮成本）
     *
     */
    public boolean addJobBySystemTask(ScmSystemTask systemTask) {
    	if (!systemTask.isFlag()) {
			return false;
		}
		JobDataMap jobDataMap = new JobDataMap();
		BaseJob bJob = new BaseJob();
		bJob.setJobName(systemTask.getTaskName()+"_"+systemTask.getOrgUnitNo());
		bJob.setJobGroupName(systemTask.getTaskGroup());
		bJob.setTriggerGroupName(systemTask.getTaskGroup());
		bJob.setCronTime(systemTask.getCronExpression());
		bJob.setJobDataMap(jobDataMap);
		Class cla = null;
		try {
			cla = Class.forName(systemTask.getTaskClass());
		} catch (ArrayIndexOutOfBoundsException e) {
			log.info("没有指定类名称" + systemTask.getTaskClass());
		} catch (ClassNotFoundException e) {
			log.info("找不到指定的类" + systemTask.getTaskClass());
		}
		try {
		JobKey jobKey = JobKey.jobKey(bJob.getJobName(), bJob.getJobGroupName());
		TriggerKey triggerKey = TriggerKey.triggerKey(bJob.getJobName(), bJob.getTriggerGroupName());
			// 判断是否有job和trigger
			if(jobKey != null && triggerKey != null){
				removeJob(getScheduler(),triggerKey,jobKey);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return addJobByCronTrigger(cla, bJob);
    }
    
    /**
     * 修改任务时间（餐饮成本）
     *
     */
    public boolean updateJobBySystemTask(ScmSystemTask systemTask) {
    	JobDataMap jobDataMap = new JobDataMap();
    	return modifyJobTime(systemTask.getTaskName()+"_"+systemTask.getOrgUnitNo(), systemTask.getCronExpression(), systemTask.getTaskGroup(), systemTask.getTaskGroup(), jobDataMap);
    }
    
    /**
     * 移除任务（餐饮成本）
     *
     */
    public boolean removeJobBySystemTask(ScmSystemTask systemTask) {
		try {
			String jobName = systemTask.getTaskName()+"_"+systemTask.getOrgUnitNo();
			JobKey jobKey = JobKey.jobKey(jobName, systemTask.getTaskGroup());
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, systemTask.getTaskGroup());
			// 判断是否有job和trigger
			if (jobKey != null && triggerKey != null) {
				removeJob(getScheduler(), triggerKey, jobKey);
			}
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * 添加任务（单据）
     *
     */
    public boolean addJobByBillId(ScmSystemTask systemTask,long billId) {
    	if (!systemTask.isFlag()) {
			return false;
		}
		JobDataMap jobDataMap = new JobDataMap();
		BaseJob bJob = new BaseJob();
		bJob.setJobName(systemTask.getTaskName()+"_"+systemTask.getOrgUnitNo()+"_"+billId);
		bJob.setJobGroupName(systemTask.getTaskGroup());
		bJob.setTriggerGroupName(systemTask.getTaskGroup());
		bJob.setCronTime(systemTask.getCronExpression());
		bJob.setJobDataMap(jobDataMap);
		Class cla = null;
		try {
			cla = Class.forName(systemTask.getTaskClass());
		} catch (ArrayIndexOutOfBoundsException e) {
			log.info("没有指定类名称" + systemTask.getTaskClass());
		} catch (ClassNotFoundException e) {
			log.info("找不到指定的类" + systemTask.getTaskClass());
		}
		try {
		JobKey jobKey = JobKey.jobKey(bJob.getJobName(), bJob.getJobGroupName());
		TriggerKey triggerKey = TriggerKey.triggerKey(bJob.getJobName(), bJob.getTriggerGroupName());
			// 判断是否有job和trigger
			if(jobKey != null && triggerKey != null){
				removeJob(getScheduler(),triggerKey,jobKey);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return addJobByCronTrigger(cla, bJob);
    }
    
    /**
     * 移除任务（单据）
     *
     */
    public boolean removeJobByBillId(ScmSystemTask systemTask,long billId) {
		try {
			String jobName = systemTask.getTaskName()+"_"+systemTask.getOrgUnitNo()+"_"+billId;
			JobKey jobKey = JobKey.jobKey(jobName, systemTask.getTaskGroup());
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, systemTask.getTaskGroup());
			// 判断是否有job和trigger
			if (jobKey != null && triggerKey != null) {
				removeJob(getScheduler(), triggerKey, jobKey);
			}
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
    }
}

