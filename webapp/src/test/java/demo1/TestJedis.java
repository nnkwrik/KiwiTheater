package demo1;

import com.github.nnkwrik.kiwiTheater.redis.RedisPoolManager;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestJedis {


    @Test
    public void testHgetAll(){

        Jedis jedis = new RedisPoolManager().getJedis();
        jedis.hset("key","filed1","value1");
        jedis.hset("key","filed2","value2");
        System.out.println(jedis.hgetAll("key"));

    }
}
