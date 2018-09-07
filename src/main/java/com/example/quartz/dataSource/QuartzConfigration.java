package com.example.quartz.dataSource;

import java.util.Properties;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Quartz配置
 */
@Configuration
public class QuartzConfigration {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private JobFactory jobFactory;


  private static final String PROPERTIES_NAME = "application.properties";
  private static final Logger LOGGER = LoggerFactory.getLogger(QuartzConfigration.class);

  public SchedulerFactoryBean schedulerFactoryBean() {
    SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
    schedulerFactoryBean.setQuartzProperties(quartzProperties());
    schedulerFactoryBean.setDataSource(dataSource);
    schedulerFactoryBean.setJobFactory(jobFactory);
    return schedulerFactoryBean;
  }

  private Properties quartzProperties() {
    PropertiesFactoryBean properties = new PropertiesFactoryBean();
    properties.setLocation(new ClassPathResource(PROPERTIES_NAME));
    try {
      properties.afterPropertiesSet();
      return properties.getObject();
    } catch (Exception e) {
      LOGGER.error("获取定时任务配置文件失败,{}", PROPERTIES_NAME);
      System.exit(-1);
      return null;
    }

  }


}
