package com.itmuch.cloud.study;

import com.itmuch.cloud.study.filter.AccessFilter1;
import com.itmuch.cloud.study.filter.AccessFilter2;
import com.itmuch.cloud.study.filter.AccessFilter3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 使用@EnableZuulProxy注解激活zuul。
 * 跟进该注解可以看到该注解整合了@EnableCircuitBreaker、@EnableDiscoveryClient，是个组合注解，目的是简化配置。
 * @author eacdy
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApiGatewayApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZuulApiGatewayApplication.class, args);
  }
 @Bean
  public AccessFilter1 accessFilter1() {
    return new AccessFilter1();
  }

  @Bean
  public AccessFilter2 accessFilter2() {
    return new AccessFilter2();
  }
  @Bean
  public AccessFilter3 accessFilter3() {
    return new AccessFilter3();
  }
}
