package com.jovanliuc.jedis;

import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public class JedisStandaloneExample {

    @Test
    void test() {
        JedisPool pool = new JedisPool("localhost", 6379);

        try (Jedis jedis = pool.getResource()) {
            // Store & Retrieving a sample string
            jedis.set("foo", "bar");
            System.out.println(jedis.get("foo")); // prints bar

            // Store & Retrieving a HashMap
            Map<String, String> hash = new HashMap<>();
            hash.put("name", "Jovan");
            hash.put("surname", "Liu");
            hash.put("company", "Redis");
            hash.put("age", "34");
            jedis.hset("user-session:123", hash);
            System.out.println(jedis.hgetAll("user-session:123"));
        }
    }
}
