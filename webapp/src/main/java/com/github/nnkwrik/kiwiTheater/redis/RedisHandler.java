package com.github.nnkwrik.kiwiTheater.redis;

import com.github.nnkwrik.kiwiTheater.model.json.SrsSteam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Map;

@Component
public class RedisHandler {

    public static final String KEYLIVE = "live";

    @Autowired
    private RedisPoolManager redisPoolManager;

    public void registerLiveStream(SrsSteam srsSteam) {
        Jedis jedis = redisPoolManager.getJedis();
        if (!jedis.hexists("live", srsSteam.getStream())) {

            jedis.hset(KEYLIVE, srsSteam.getStream(), "0");
        }
    }

    public void deleteLiveStream(SrsSteam srsSteam) {
        Jedis jedis = redisPoolManager.getJedis();
        jedis.hdel(KEYLIVE, srsSteam.getStream());
    }

    public void updateViewer() {

    }

    public Map<String, String> getAllLiving() {
        Jedis jedis = redisPoolManager.getJedis();

        //{filed1=value1, filed2=value2}
        return jedis.hgetAll(KEYLIVE);

    }

    public String getLiveViewers(String roomId){

        return redisPoolManager.getJedis().hget(KEYLIVE,roomId);
    }

}
