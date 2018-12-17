package com.choice.scm.webreg.health;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangmsh on 2018/12/5
 */
@RestController
@RequestMapping("/")
public class HealthController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("ping")
    public String checkHealth() {
        return "pong";
    }

    @RequestMapping("getAvailableService")
    public String getAvailableService() {
        return JSONObject.toJSONString(discoveryClient.getServices());
    }
}
