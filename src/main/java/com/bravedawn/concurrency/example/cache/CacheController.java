package com.bravedawn.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private RedisClient redisClient;

    @RequestMapping("/set")
    public String set(@RequestParam("k") String k, @RequestParam("v") String v) throws Exception{
        redisClient.set(k, v);
        return "SUCCESS";
    }

    @RequestMapping("/get")
    public String get(@RequestParam("k") String k) throws Exception{
        String value = redisClient.get(k);
        return value;
    }

}
