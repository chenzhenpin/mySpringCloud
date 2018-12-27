package com.itmuch.cloud.study.user.controller;

import com.itmuch.cloud.study.user.message.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.study.user.entity.User;
import com.itmuch.cloud.study.user.feign.UserFeignClient;

@RestController
public class FeignController {
  @Autowired
  private UserFeignClient userFeignClient;
  @Autowired
  private Sender sender;

  @ResponseBody
  @GetMapping("feign/{id}")
  public User findByIdFeign(@PathVariable Long id) {
    sender.send();
    User user = this.userFeignClient.findByIdFeign(id);
    return user;
  }
  @GetMapping("feign/instance-info")
  public ServiceInstance findInfoFeign() {
    ServiceInstance serviceInstance = this.userFeignClient.findInfoFeign();
    return serviceInstance;
  }
}
