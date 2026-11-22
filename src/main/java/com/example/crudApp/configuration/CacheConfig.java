package com.example.crudApp.configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching


public class CacheConfig {
    @Bean
    public Config HazelCastConfig(){
        Config config= new Config();
        config.setInstanceName("crud_hazelcast");
        config.addMapConfig(new MapConfig("department").setTimeToLiveSeconds(300));
        config.addMapConfig(new MapConfig("department_list").setTimeToLiveSeconds(300));
        config.addMapConfig(new MapConfig("Student").setTimeToLiveSeconds(300));
        config.addMapConfig(new MapConfig("student_list").setTimeToLiveSeconds(300));
        config.addMapConfig(new MapConfig("mark").setTimeToLiveSeconds(300));
        config.addMapConfig(new MapConfig("mark_list").setTimeToLiveSeconds(300));


        return config;

    }
}
