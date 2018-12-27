package com.itmuch.cloud.study.user.feign;

import com.itmuch.cloud.study.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class UserFeignBackClient implements UserFeignClient {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Override
    public User findByIdFeign(Long id) {
        User user=new User();
        user.setId((long)3);
        user.setAge(33);
        user.setUsername("error");
        return user;
    }

    @Override
    public ServiceInstance findInfoFeign() {
        System.out.println("findInfoFeign");
        ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
        return localServiceInstance;
    }
}
