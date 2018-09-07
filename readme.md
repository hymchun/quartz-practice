# quartz框架练习

#pom.xml文件导入quartz依赖
   <dependency>
      <groupId>org.quartz-scheduler</groupId>
      <artifactId>quartz</artifactId>
      <version>2.3.0</version>
    </dependency>

## Quartz框架必须创建的数据库表，脚本在tables_mysql_innodb.sql中

### 每一个Job类都会实现Job接口execute()方法

####Quartz基本组成部分
     调度器：Scheduler
     任务：JobDetail
     触发器：Trigger，（包括SimpleTrigger和CronTrigger）：触发器Trigger最基本的功能是指定Job的执行时间，执行间隔，运行次数等。
     SimpleTrigger ：（精准指定间隔）可以实现在一个指定时间段内执行一次作业任务或一个时间段内多次执行作业任务。
     CroTrigger：是基于Cron表达式的 