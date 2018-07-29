package im.radis;

import im.ImWebsocketServer;
import im.connection.ImConnectionMap;
import io.netty.channel.group.ChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class RedisHandler implements Runnable {

    private final static Logger log = LoggerFactory.getLogger(ImWebsocketServer.class);


    private static final String key = "live";
    private static final long timeInterval = 10000;// 10s
    private final RedisPoolManager redisPoolManager = new RedisPoolManager();

    private final ConcurrentHashMap<Integer, ChannelGroup> viewerMap = ImConnectionMap.getViewerMap();

    @Override
    public void run() {
        while (true) {

            log.info("=========== 更新观众数量 ==============");
            viewerMap.forEach((roomid, channelGroup) -> {
                int viewers = channelGroup.size();

                redisPoolManager.getJedis().hset(key, roomid + "", viewers + "");
                log.info("直播间 : " + roomid + " , 观众 : " + viewers +" 人 ");

                if (viewers == 0) {
                    log.info("直播间"+roomid+"的观众为 0 ，从IM移除");
                    ImConnectionMap.removeRoom(roomid);
                }

            });

            try {
                Thread.sleep(timeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
