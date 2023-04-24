package com.jovanliuc.jedis;

import org.testng.annotations.Test;
import redis.clients.jedis.ConnectionPool;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JedisClusterExample {

    @Test
    void test() {
        Set<HostAndPort> jedisClusterNodes = new HashSet();
        jedisClusterNodes.add(new HostAndPort("localhost", 7000));
        jedisClusterNodes.add(new HostAndPort("localhost", 7001));
        jedisClusterNodes.add(new HostAndPort("localhost", 7002));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
        jedisCluster.set("key1", "val1");
        jedisCluster.set("key2", "val2");
        jedisCluster.set("key3", "val3");

        System.out.println(jedisCluster.get("key1"));
        System.out.println(jedisCluster.get("key2"));
        System.out.println(jedisCluster.get("key3"));

        Map<String, ConnectionPool> clusterNodes = jedisCluster.getClusterNodes();
        System.out.println(clusterNodes);
    }
}
