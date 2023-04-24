package com.jovanliuc.redisson;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.testng.annotations.Test;

public class RedissonStandaloneExample {

    @Test
    void test() {

        // Create Redisson client configuration
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379")
                .setDatabase(0);

        // Create Redisson client instance
        RedissonClient redisson = Redisson.create(config);

        // Get a Redisson distributed map
        RMap<String, String> map = redisson.getMap("myMap");

        // Add some key-value pairs to the map
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        // Retrieve a value from the map by key
        String value = map.get("key2");
        System.out.println("Value for key2: " + value);

        // Remove a key-value pair from the map
        map.remove("key3");

        // Shutdown the Redisson client
        redisson.shutdown();
    }
}
