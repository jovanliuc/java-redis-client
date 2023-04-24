package com.jovanliuc.lettuce;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class LettuceClusterExample {

    @Test void test() {
        RedisURI node1 = RedisURI.create("localhost", 7000);
        RedisURI node2 = RedisURI.create("localhost", 7001);
        RedisURI node3 = RedisURI.create("localhost", 7002);

        RedisClusterClient clusterClient = RedisClusterClient.create(Arrays.asList(node1, node2, node3));

        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                .enableAdaptiveRefreshTrigger(ClusterTopologyRefreshOptions.RefreshTrigger.MOVED_REDIRECT, ClusterTopologyRefreshOptions.RefreshTrigger.PERSISTENT_RECONNECTS)
                .adaptiveRefreshTriggersTimeout(30, TimeUnit.SECONDS)
                .build();

        clusterClient.setOptions(ClusterClientOptions.builder()
                .topologyRefreshOptions(topologyRefreshOptions)
                .build());

        StatefulRedisClusterConnection<String, String> connection = clusterClient.connect();
        RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();

        // Use the Redis commands to interact with the cluster
        syncCommands.set("key", "value");
        String value = syncCommands.get("key");
        System.out.println("Value for key 'key': " + value);

        // Close the connection and the client
        connection.close();
        clusterClient.shutdown();
    }
}
