package im;

import im.connection.ImConnectionMap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protobuf.generate.webapp.ImPto;


public class ImWebSocketServerHandler extends SimpleChannelInboundHandler<ImPto.Im> {

    private static final Logger logger = LoggerFactory.getLogger(ImWebSocketServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ImPto.Im im) throws Exception {
        int roomid = im.getRoomid();

        switch (im.getImType()) {
            case ImHandShake:
                if (!ImConnectionMap.getViewerMap().containsKey(roomid)){
                    logger.info("新的IM roomid ："+roomid);
                    ImConnectionMap.addRoom(roomid);
                }
                ImConnectionMap.addViewer(roomid,ctx.channel());
                logger.info("观众进入roomid"+roomid);
                break;
            case Danmaku:
                ImConnectionMap.broadcast(roomid,im);
                logger.info("弹幕" +
                        "[ 房间号 : "+roomid+" , 用户 : "+im.getSendername()+" , 内容 : "+im.getContent()+" ]");
                break;
            case Gift:
                ImConnectionMap.broadcast(roomid,im);
                logger.info("礼物" +
                        "[ 房间号 : "+roomid+" , 用户 : "+im.getSendername()+" , 内容 : "+im.getContent()+" ]");
                break;

        }


    }

    //channel be close 刷新or关闭窗口
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("观众离开直播间");
    }


}
