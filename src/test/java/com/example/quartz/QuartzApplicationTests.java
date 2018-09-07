package com.example.quartz;

import com.example.quartz.controller.JobController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.example.quartz.QuartzApplication.class)
public class QuartzApplicationTests {

  @Autowired
  private JobController job;

  @Test
  public void test() throws SchedulerException {
    job.quartz();
  }

}
