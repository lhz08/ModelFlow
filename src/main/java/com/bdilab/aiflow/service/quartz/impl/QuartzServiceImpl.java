package com.bdilab.aiflow.service.quartz.impl;

import com.bdilab.aiflow.common.utils.UTCDateUtils;
import com.bdilab.aiflow.service.quartz.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

//import com.andya.selfcode.quartz.exception.BaseException;


/**
 * @author Andya
 * @date 2021/4/01
 */
@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void startScheduler() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass
     *            任务实现类
     * @param jobName
     *            任务名称
     * @param jobGroupName
     *            任务组名
     * @param jobTime
     *            时间表达式 (这是每隔多少秒为一次任务)
     * @param jobTimes
     *            运行的次数 （<0:表示不限次数）
     * @param jobData
     *            参数
     */
    @Override
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, int jobTime,
                       int jobTimes, Map jobData) {
        try {
            // 任务名称和组构成任务key
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName)
                    .build();
            // 设置job参数
            if(jobData!= null && jobData.size()>0){
                jobDetail.getJobDataMap().putAll(jobData);
            }
            // 使用simpleTrigger规则
            Trigger trigger = null;
            if (jobTimes < 0) {
                trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withIntervalInSeconds(jobTime))
                        .startNow().build();
            } else {
                trigger = TriggerBuilder
                        .newTrigger().withIdentity(jobName, jobGroupName).withSchedule(SimpleScheduleBuilder
                                .repeatSecondlyForever(1).withIntervalInSeconds(jobTime).withRepeatCount(jobTimes))
                        .startNow().build();
            }
            log.info("jobDataMap: {}", jobDetail.getJobDataMap().getWrappedMap());
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
           // throw new BaseException("add job error!");
        }
    }

    /**
     * 增加一个job-periodic
     *
     * @param jobClass
     *            任务实现类
     * @param jobName
     *            任务名称(建议唯一)
     * @param jobGroupName
     *            任务组名
     * @param jobData
     *            参数
     */

    @Override
    public boolean addPeriodic(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, Map jobData,Integer interval) {
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            // 任务名称和组构成任务key
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).usingJobData("runSecond",30)
                    .build();
            // 设置job参数
            if(jobData!= null && jobData.size()>0){
                jobDetail.getJobDataMap().putAll(jobData);
            }
            // 定义调度触发规则
            // 使用cornTrigger规则
            // 触发器key
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .startAt(UTCDateUtils.adddateInvert((Date) jobData.get("startTime"),interval*2))
                    .endAt(UTCDateUtils.adddateInvert((Date) jobData.get("endTime"),interval*2))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(1).withIntervalInSeconds(interval))//设置每次运行的间隔时间
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("jobDataMap: {}", jobDetail.getJobDataMap());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass
     *            任务实现类
     * @param jobName
     *            任务名称(建议唯一)
     * @param jobGroupName
     *            任务组名
     * @param jobTime
     *            时间表达式 （如：0/5 * * * * ? ）
     * @param jobData
     *            参数
     */
    @Override
    public boolean addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, String jobTime, Map jobData) {
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            // 任务名称和组构成任务key
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName)
                    .build();
            // 设置job参数
            if(jobData!= null && jobData.size()>0){
                jobDetail.getJobDataMap().putAll(jobData);
            }
            // 定义调度触发规则
            // 使用cornTrigger规则
            // 触发器key
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("jobDataMap: {}", jobDetail.getJobDataMap());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
          //  throw new BaseException("add job error!");
        }
    }

    /**
     * 修改 一个job的 时间表达式
     *
     * @param jobName
     * @param jobGroupName
     * @param jobTime
     */
    @Override
    public void updateJob(String jobName, String jobGroupName, String jobTime) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            log.info("new jobTime: {}", jobTime);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).build();
            // 重启触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
           // throw new BaseException("update job error!");
        }
    }

    /**
     * 删除任务一个job
     *
     * @param jobName
     *            任务名称
     * @param jobGroupName
     *            任务组名
     */
    @Override
    public void deleteJob(String jobName, String jobGroupName) {
        try {
            scheduler.deleteJob(new JobKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.printStackTrace();
            //throw new BaseException("delete job error!");
        }
    }

    /**
     * 暂停一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    @Override
    public boolean pauseJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.pauseJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
           // throw new BaseException("pause job error!");
            return false;
        }
    }

    /**
     * 恢复一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    @Override
    public boolean resumeJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.resumeJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
      //      throw new BaseException("resume job error!");
        }
    }

    /**
     * 立即执行一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    @Override
    public void runAJobNow(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
           // throw new BaseException("run a job error!");
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryAllJob() {
        List<Map<String, Object>> jobList = null;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            jobList = new ArrayList<Map<String, Object>>();
            for (JobKey jobKey : jobKeys) {
                log.info("maps: {}", scheduler.getJobDetail(jobKey).getJobDataMap().getWrappedMap());
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("jobName", jobKey.getName());
                    map.put("jobGroupName", jobKey.getGroup());
                    map.put("description", "触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    map.put("jobStatus", triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        map.put("jobTime", cronExpression);
                    }
                    jobList.add(map);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
           // throw new BaseException("query all jobs error!");
        }
        return jobList;
    }

    /**
     * 获取所有正在运行的job
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryRunJob() {
        List<Map<String, Object>> jobList = null;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<Map<String, Object>>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                Map<String, Object> map = new HashMap<String, Object>();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                map.put("jobName", jobKey.getName());
                map.put("jobGroupName", jobKey.getGroup());
                map.put("description", "触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put("jobStatus", triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put("jobTime", cronExpression);
                }
                jobList.add(map);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
          //  throw new BaseException("query run jobs error!");
        }
        return jobList;
    }

}

