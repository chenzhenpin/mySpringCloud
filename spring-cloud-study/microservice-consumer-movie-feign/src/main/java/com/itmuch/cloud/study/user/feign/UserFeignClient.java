package com.itmuch.cloud.study.user.feign;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itmuch.cloud.study.user.entity.User;

/**
 * 使用@FeignClient("microservice-provider-user")注解绑定microservice-provider-user服务，还可以使用url参数指定一个URL。
 * @author eacdy
 */
//调用的服务名 service,fallback指定容错处理类。
@FeignClient(name = "service",fallback = UserFeignBackClient.class)
public interface UserFeignClient {
  @RequestMapping("/{id}")
  public User findByIdFeign(@RequestParam("id") Long id);

  @RequestMapping("/instance-info") //远程调用的地址instance-info
  public ServiceInstance findInfoFeign();

}
