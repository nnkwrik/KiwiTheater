<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/front-resources/layui/css/layui.css" media="all">
    <link rel="icon" href="${pageContext.request.contextPath }/front-resources/img/logo/icons8-kiwi-64.png"
          type="image/x-icon"/>
    <script src="${pageContext.request.contextPath }/front-resources/js/charge.js" charset="utf-8"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/front-resources/protobuf/imPto.js"></script>
    <!-- <%--必须使用videojs 5.x版，最新版没有video-js.swf--%> -->
    <link href="${pageContext.request.contextPath }/front-resources/videojs-5.20.5/video-js.css" rel="stylesheet"
          type="text/css">
    <script src="${pageContext.request.contextPath }/front-resources/videojs-5.20.5/video.js"></script>
    <script>
        videojs.options.flash.swf = "${pageContext.request.contextPath }/front-resources/videojs-5.20.5/video-js.swf";
    </script>
</head>

<body style=" color : #555;" class="layui-container">
<div class="layui-layout-right" style=" margin-top:20px;margin-right:40px">
    <c:if test="${activeUser==null}">您还没有登录,<a href="${pageContext.request.contextPath }/signin" style="color:#01AAED">登录</a>or<a href="/signup" style="color:#01AAED">注册</a></c:if>
    <c:if test="${activeUser!=null}">
        <a href="${pageContext.request.contextPath }/editUserInfo">
            <img src="${pageContext.request.contextPath }/${activeUser.avatar}"
                 style="height:30px;width: 30px" class="layui-circle">
        </a> ${activeUser.nickname}
        <a href="${pageContext.request.contextPath }/signout" style="color:#01AAED">注销</a>
        <br>
        <button onclick="charge()"
                class="layui-col-md-offset4 layui-btn layui-btn  layui-btn-primary layui-btn layui-btn-xs">
            <i class="layui-icon" style="color: #FF5722;">&#xe65e;</i>余额：<span id="coin">${activeUser.coin}</span>
        </button>
    </c:if>
</div>
<!-- logo -->
<div class="layui-row ">
    <br>
    <br>
    <div class="layui-col-md3">
        <a href="${pageContext.request.contextPath }/home">
            <img src="${pageContext.request.contextPath }/front-resources/img/logo/logo.png" alt=""
                 class="layui-col-md12">
        </a>
    </div>
</div>
<!--title-->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend style="font-size: 30px;">正在热映 : ${roomVo.room.roomname}</legend>
</fieldset>
<div class="layui-row layui-col-space10" style="margin-top:40px">
    <div class="layui-col-md9">
        <div class="layui-col-md12" style="height: 700px;border: 1px solid #ccc;border-radius: 16px;padding:10px ">
            <div class="layui-col-md12" style=" ">
                <!--videojs-->
                <video id="livestream" class="video-js vjs-default-skin vjs-big-play-centered layui-col-md12"
                       height="560px" controls autoplay preload="auto"
                       data-setup='{"aspectRatio":"640:267","techorder" : ["flash","html5] }'>
                    <!--RTMP测试地址： rtmp://live.hkstv.hk.lxdns.com/live/hks-->
                    <source src="rtmp://127.0.0.1:1935/live/${roomVo.room.id}" type="rtmp/flv">
                </video>
            </div>
            <div class="layui-row layui-col-md12" style="margin-top: 10px">
                <a href="javascript:void(0)" onclick="gift(10,'橘子');">
                    <div class="layui-col-md2 layui-col-md-offset1 ">
                        <img src="${pageContext.request.contextPath }/front-resources/img/fruit-icons/icons8-orange-2-100.png"
                             alt=""
                             class="layui-col-md5 layui-col-md-offset3">
                        <p class="layui-col-md5 layui-col-md-offset3 " style="text-align: center;">
                            <b style="font-size: 15px; color: #676666">送橘子</b>
                            <br>
                            <i class="layui-icon" style="color:#FF5722">&#xe65e;</i> 10 块
                        </p>
                    </div>
                </a>
                <a href="javascript:void(0)" onclick="gift(5,'葡萄');">
                    <div class="layui-col-md2 ">
                        <img src="${pageContext.request.contextPath }/front-resources/img/fruit-icons/icons8-grapes-100.png"
                             alt=""
                             class="layui-col-md5 layui-col-md-offset3">
                        <p class="layui-col-md5 layui-col-md-offset3" style="text-align: center;">
                            <b style="font-size: 15px; color: #676666">送葡萄</b>
                            <br>
                            <i class="layui-icon" style="color:#FF5722">&#xe65e;</i> 5 块
                        </p>
                    </div>
                </a>
                <a href="javascript:void(0)" onclick="gift(1,'香蕉');">
                    <div class="layui-col-md2">
                        <img src="${pageContext.request.contextPath }/front-resources/img/fruit-icons/icons8-sweet-banana-100.png"
                             alt=""
                             class="layui-col-md5 layui-col-md-offset3">
                        <p class="layui-col-md5 layui-col-md-offset3" style="text-align: center;">
                            <b style="font-size: 15px; color: #676666">送香蕉</b>
                            <br>
                            <i class="layui-icon" style="color:#FF5722">&#xe65e;</i> 1 块
                        </p>
                    </div>
                </a>
                <a href="javascript:void(0)" onclick="gift(0.5,'樱桃');">
                    <div class="layui-col-md2 ">
                        <img src="${pageContext.request.contextPath }/front-resources/img/fruit-icons/icons8-cherry-100.png"
                             alt=""
                             class="layui-col-md5 layui-col-md-offset3">
                        <p class="layui-col-md5 layui-col-md-offset3" style="text-align: center;">
                            <b style="font-size: 15px; color: #676666">送樱桃</b>
                            <br>
                            <i class="layui-icon" style="color:#FF5722">&#xe65e;</i> 5 毛
                        </p>
                    </div>
                </a>
                <a href="javascript:void(0)" onclick="gift(0.1,'菠菜');">
                    <div class="layui-col-md2 ">
                        <img src="${pageContext.request.contextPath }/front-resources/img/fruit-icons/icons8-greens-100.png"
                             alt=""
                             class="layui-col-md5 layui-col-md-offset3">
                        <p class="layui-col-md5 layui-col-md-offset3" style="text-align: center;">
                            <b style="font-size: 15px; color: #676666">送菠菜</b>
                            <br>
                            <i class="layui-icon" style="color:#FF5722">&#xe65e;</i> 1 毛
                        </p>
                    </div>
                </a>
            </div>
        </div>
        <div class="layui-col-md12" style="margin-top: 50px;border: 1px solid #ccc;border-radius: 16px; padding: 30px">
            <!--<div class="layui-row layui-col-md12">-->
            <h1> 直播间介绍</h1>
            <hr>
            <p>
                ${roomVo.room.description}

            </p>
            <!--</div>-->
        </div>
    </div>
    <div class="layui-col-md3">
        <div class="layui-col-md12" style="border: 1px solid #ccc;padding:14px;border-radius: 16px;height: 700px;">
            <div class="layui-col-xs12" style="padding:14px;">
                <div class="layui-col-md4">
                    <img src="${pageContext.request.contextPath }/${roomVo.user.avatar}"
                         style="height:60px;width: 60px" class="layui-circle">
                </div>
                <div class="layui-col-md8">
                    <font size=4px>${roomVo.user.nickname}</font>
                    <br>
                    <i class="layui-icon" style=" color: #ccc;">&#xe756; ${roomVo.viewer} 人观看</i>
                </div>
            </div>
            <hr style="margin-top: 30px;">
            <div id="wsdiv" class="layui-col-xs12" style="overflow-y:auto; height: 520px"></div>
            <div class="layui-row layui-col-md12" style="margin-top: 10px">
                <div class="layui-col-md9">
                    <input id="msg" type="text" name="title" placeholder="" autocomplete="off" class="layui-input">
                </div>
                <button id="sendws" class="layui-btn layui-btn-primary" lay-submit>
                    <i class="layui-icon" style="color: #91b9a4;">&#xe611;</i>
                </button>
            </div>
        </div>
    </div>
</div>
<!--FOOTER-->
<br>
<br>
<br>
<fieldset class="layui-elem-field layui-field-title">
    <legend style="text-align:center;">
        <a href="https://github.com/nnkwrik">
            <i class="layui-icon" style="color:#c2c2c2;font-size:18px;">&#xe66a; Github : nnkwrik</i>
        </a>
    </legend>
</fieldset>
<script src="${pageContext.request.contextPath }/front-resources/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery,
            layer = layui.layer; //独立版的layer无需执行这一句
    });

    function gift(giftValue, giftName) {
        var activeUser = "${activeUser}";
        if (activeUser == "") {
            layer.confirm('要登录才能送礼物。马上去登录吧！', {
                btn: ['嗯！', '算了'] //按钮
            }, function () {
                window.location.href = "${pageContext.request.contextPath }/signin"
            });
            return;
        }

        var coin = "${activeUser.coin}";
        if (coin < giftValue) {
            layer.confirm('只剩 ' + coin + ' 块了！不够买' + giftName + '哦..要去充值吗？', {
                btn: ['嗯！', '算了'] //按钮
            }, function () {
                charge()
            });
        } else {
            // 询问框
            layer.confirm('确定要投喂' + giftName + '吗？', {
                btn: ['嗯！', '算了'] //按钮
            }, function () {
                sendGift(giftValue, giftName)
            });
        }

    }

    function sendGift(giftValue, giftName) {
        layer.closeAll();
        layer.load(2);
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath }/send_gift",
            data: '{"anchorid":${roomVo.user.id},"giftValue":' + giftValue + '}',
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                setTimeout(function () {
                    layer.closeAll('loading');
                }, 2000);
                layer.msg('投喂成功!', {icon: 1});
                $("#coin").html(data);
                // 传im
                var gift = new proto.Im();
                gift.setImtype(proto.ImType.GIFT);
                gift.setSenderid(${activeUser.id})
                gift.setSendername("${activeUser.nickname}")
                gift.setRoomid(${roomVo.room.id});
                gift.setContent(giftName);
                websocket.send(gift.serializeBinary());
            }

        });

    }


    // Search
    //Demo
    layui.use('form', function () {
        var form = layui.form;

        //监听提交
        form.on('submit(sub)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
<!-- <%--src="http://localhost:8080/resources/sockjs.min.js"></script>--%> -->
<script>
    var websocket;

    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://" + window.location.hostname + ":2333/ws");
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://" + window.location.hostname + ":2333/ws");
    } else {
        websocket = new SockJS("http://" + window.location.hostname + ":2333/sockjs/ws");
    }
    //如果没有配置默认返回的是个Blob对象，protobuf解析时会报错
    websocket.binaryType = "arraybuffer";

    //建立连接
    websocket.onopen = function (evnt) {
        var handshake = new proto.Im();
        handshake.setImtype(proto.ImType.IMHANDSHAKE);
        handshake.setRoomid(${roomVo.room.id});
        websocket.send(handshake.serializeBinary());
    };
    //接受信息
    websocket.onmessage = function (evnt) {

        if (evnt.data instanceof ArrayBuffer) {
            var msg = proto.Im.deserializeBinary(evnt.data);

            switch (msg.getImtype()) {
                case proto.ImType.DANMAKU:
                    var senderid = msg.getSenderid();
                    var sendername = msg.getSendername();
                    var content = msg.getContent();
                    $("#wsdiv").html($("#wsdiv").html() + "<br><font color='#01AAED'>" + sendername + "</font> : " + content);
                    $('#msg').val("");
                    break;
                case proto.ImType.GIFT:
                    var senderid = msg.getSenderid();
                    var sendername = msg.getSendername();
                    var content = msg.getContent();
                    $("#wsdiv").html($("#wsdiv").html() +
                        "<br>感谢 <font color='#009688'>" + sendername + "</font> 老板赠送的 <font color='#009688'>" + content + "</font>");
                    break;
                case proto.ImType.SYSANNOUNCEMENT:
                    break;
                case proto.ImType.IMCHCLOSE:
                    break;
            }
            var div = document.getElementById('wsdiv');
            div.scrollTop = div.scrollHeight;

        }

    };

    websocket.onerror = function (evnt) {
        alert("服务器连接失败,请刷新页面")
    };
    websocket.onclose = function (evnt) {
    }

    //发送信息
    $('#sendws').click(function () {
        if ("${activeUser.nickname}" == "") {
            alert("请登录");
            return;
        }
        if (websocket.readyState != websocket.OPEN) {
            alert("连接失败!websocket.readyState : " + websocket.readyState);
            return;
        }

        var msg = $('#msg').val();
        if (msg.trim() != '') {

            var danmaku = new proto.Im();
            danmaku.setImtype(proto.ImType.DANMAKU);
            danmaku.setSendername("${activeUser.nickname}");
            danmaku.setSenderid(${activeUser.id});
            danmaku.setRoomid(${roomVo.room.id});
            danmaku.setContent(msg);
            //调用后台handleTextMessage方法
            websocket.send(danmaku.serializeBinary());

        } else {
            alert("输入内容为空");
        }

    });
</script>

</body>

</html>