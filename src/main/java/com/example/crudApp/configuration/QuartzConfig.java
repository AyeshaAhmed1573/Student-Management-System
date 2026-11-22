package com.example.crudApp.configuration;

import com.example.crudApp.job.ReportJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail reportJobDetail(){
        return JobBuilder.newJob(ReportJob.class)
                .withIdentity("report job")
                .storeDurably()
                .build();

    }
    @Bean
    public Trigger reportTrigger(JobDetail reportJobDetail){
        return TriggerBuilder.newTrigger()
                .forJob(reportJobDetail)
                .withIdentity("report Trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 4 * * ?"))
                .build();

    }
}
