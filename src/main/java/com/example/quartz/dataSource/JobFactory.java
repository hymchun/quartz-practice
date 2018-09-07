package com.example.quartz.dataSource;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义任务工厂类，解决SPringle不能在Quartz注入Bean
 */
@Component
public class JobFactory extends AdaptableJobFactory {

  @Autowired
  private AutowireCapableBeanFactory autowireCapableBeanFactory;

  @Override
  protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
    Object jobInstance = super.createJobInstance(bundle);
    //这一步解决不能spring注入bean的问题
    autowireCapableBeanFactory.autowireBean(jobInstance);
    return jobInstance;
  }
}

