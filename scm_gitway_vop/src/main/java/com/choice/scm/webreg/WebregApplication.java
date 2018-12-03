package com.choice.scm.webreg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ServletComponentScan
@EnableEurekaClient
@EnableFeignClients
public class WebregApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebregApplication.class, args);
    }
}
