package ssm.model.json;

import lombok.Data;

/**
 * 从srs RTMP Server传json回调
 * 推流/拉流地址 : rtmp://127.0.0.1:1935/live/房间号
 */
@Data
public class SrsSteam {
//HTTP callback from srs server (RTMP server)
//https://github.com/ossrs/srs/wiki/v3_CN_HTTPCallback

//    on_publish	{
//        "action": "on_publish",
//                "client_id": 1985,
//                "ip": "192.168.1.10",
//                "vhost": "video.test.com",
//                "app": "live",
//                "stream": "livestream"
//    }	当客户端发布流时，譬如flash/FMLE方式推流到服务器

//  在docker hub 中找到的版本多包含了以下项
//            "tcUrl":"rtmp://192.168.0.6:1935/live",

//    on_unpublish	{
//        "action": "on_unpublish",
//                "client_id": 1985,
//                "ip": "192.168.1.10",
//                "vhost": "video.test.com",
//                "app": "live",
//                "stream": "livestream"
//    }	当客户端停止发布流时

//    on_play	{
//        "action": "on_play",
//                "client_id": 1985,
//                "ip": "192.168.1.10",
//                "vhost": "video.test.com",
//                "app": "live",
//                "stream": "livestream",
//                "pageUrl": "http://www.test.com/live.html"
//    }	当客户端开始播放流时

//    on_stop	{
//        "action": "on_stop",
//                "client_id": 1985,
//                "ip": "192.168.1.10",
//                "vhost": "video.test.com",
//                "app": "live",
//                "stream": "livestream"
//    }	当客户端停止播放时。备注：停止播放可能不会关闭连接，还能再继续播放。


    private String action;

    private Integer client_id;

    private String ip;

    private String vhost;

    private String app;

    private String stream;//房间号

    private String tcUrl;

    private String pageUrl;

}
