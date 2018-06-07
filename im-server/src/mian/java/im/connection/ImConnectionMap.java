package im.connection;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protobuf.generate.webapp.ImPto;

import java.util.concurrent.ConcurrentHashMap;

public class ImConnectionMap {

    private final static Logger log = LoggerFactory.getLogger(ImConnectionMap.class);

    private static ConcurrentHashMap<Integer, ChannelGroup> viewerMap = new ConcurrentHashMap<>();

    //测试用
//    static {
//        for (int i = 9; i <= 17; i++) {
//
//            viewerMap.put(i, new DefaultChannelGroup("room" + i, GlobalEventExecutor.INSTANCE));
//        }
//    }

    public static void addRoom(int roomId) {
        ChannelGroup channelGroup = new DefaultChannelGroup("room" + roomId, GlobalEventExecutor.INSTANCE);
        viewerMap.put(roomId, channelGroup);
    }

    public static void addViewer(int roomId, Channel channel) {
        viewerMap.get(roomId).add(channel);
    }

    public static ConcurrentHashMap<Integer, ChannelGroup> getViewerMap(){return viewerMap;}

    public static void removeRoom(int roomId) {

        ChannelGroup channelGroup = viewerMap.remove(roomId);
        ImPto.Im.Builder builder = ImPto.Im.newBuilder();
        builder.setImType(ImPto.ImType.ImChClose);
        builder.setRoomid(roomId);
        ImPto.Im im = builder.build();

        ChannelGroupFuture c = channelGroup.writeAndFlush(im);
        c.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                channelGroup.close();
            }
        });

    }

    public static void broadcast(int roomId, ImPto.Im im) {
        viewerMap.get(roomId).writeAndFlush(im);
    }

    public static void broadcastAll(ImPto.Im im) {
        viewerMap.forEach((key, value) -> {
            value.writeAndFlush(im);
        });


    }


}
