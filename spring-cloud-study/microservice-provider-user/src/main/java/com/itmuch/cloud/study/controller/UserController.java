package com.itmuch.cloud.study.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.study.domain.User;
import com.itmuch.cloud.study.repository.UserRepository;

import java.util.Date;

/**
 * 作用：
 * ① 测试服务实例的相关内容
 * ② 为后来的服务做提供
 * @author eacdy
 */
@RestController
public class UserController {
  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private UserRepository userRepository;

  /**
   * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
   * @RequestMapping(value = "/id", method = RequestMethod.GET)
   * 类似的注解还有@PostMapping等等
   * @param id
   * @return user信息
   */
  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    System.out.println("----------------------------------------------------------------------");
    System.out.println(id);
    System.out.println(new Date(System.currentTimeMillis()));
    User findOne=null;
    try {
//       Thread.sleep(1*1000);
       findOne= this.userRepository.findOne(id);
       ObjectMapper objectMapper = new ObjectMapper();
       String jsonStr=objectMapper.writeValueAsString(findOne);
       System.out.println(jsonStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(new Date(System.currentTimeMillis()));
    return findOne;
  }

  /**
   * 本地服务实例的信息
   * @return
   */
  @GetMapping("/instance-info")
  public ServiceInstance showInfo() {
    System.out.println("showInfo");
    //如果通过其他微服务调用，执行到下面语句则报错
    ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
    return localServiceInstance;
  }
}
