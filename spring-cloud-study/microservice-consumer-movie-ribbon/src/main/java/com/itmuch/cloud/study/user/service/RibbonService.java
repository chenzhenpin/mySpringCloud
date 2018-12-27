package com.itmuch.cloud.study.user.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.itmuch.cloud.study.user.entity.User;

@Service
public class RibbonService {
  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "handleBack") //容错处理函数handleBack
  public User findById(Long id) {
    // http://服务提供者的serviceId/url
    return this.restTemplate.getForObject("http://service/" + id, User.class);
  }
  public User handleBack(Long id){
     User user=new User();
     user.setId((long)3);
     user.setAge(33);
     user.setUsername("error");
     return user;
  }
}
