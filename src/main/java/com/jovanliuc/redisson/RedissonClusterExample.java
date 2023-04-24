package com.jovanliuc.redisson;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.testng.annotations.Test;

public class RedissonClusterExample {

    @Test void test() {

        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://127.0.0.1:7000")
                .addNodeAddress("redis://127.0.0.1:7001")
                .addNodeAddress("redis://127.0.0.1:7002");

        RedissonClient redisson = Redisson.create(config);

        // Now you can use Redisson objects like RMap, RSet, RList, etc. to interact with the Redis cluster
        RMap<String, String> map = redisson.getMap("myMap");
        map.put("key1", "value1");
        map.put("key2", "value2");

        RSet<String> set = redisson.getSet("mySet");
        set.add("value1");
        set.add("value2");

        // Don't forget to shutdown the Redisson client when you're done
        redisson.shutdown();
    }
}
