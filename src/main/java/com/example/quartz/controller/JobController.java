package com.example.quartz.controller;

import com.example.quartz.scheduler.PrintWordsJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quartz")
public class JobController {

  @RequestMapping(value = "/timejob", method = RequestMethod.GET)
  public void quartz() throws SchedulerException {
    //1.创建调度器
    SchedulerFactory factory = new StdSchedulerFactory();
    Scheduler scheduler = factory.getScheduler();
    //2.创建JobDetail实例，并与PrintWordsJob类绑定
    JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class).withIdentity("job1", "group1")
        .build();
    //3.构建Trigger实例，每隔10分钟执行一次
    Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroups")
        .startNow().withSchedule(
            SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
        .build();
    scheduler.scheduleJob(jobDetail, trigger);
    scheduler.start();
  }

}
