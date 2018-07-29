<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="utf-8">
    <title>kiwi-theater-首页</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/front-resources/layui/css/layui.css" media="all">
    <link rel="icon" href="${pageContext.request.contextPath }/front-resources/img/logo/icons8-kiwi-64.png"
          type="image/x-icon"/>
    <script src="${pageContext.request.contextPath }/front-resources/js/charge.js" charset="utf-8"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<body class="layui-container" style="color: #555">
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
    <div class="layui-col-md5 layui-col-md-offset3" style="margin-top: 100px;">
        <img src="${pageContext.request.contextPath }/front-resources/img/logo/logo.png" alt="" class="layui-col-md12">
    </div>
</div>
<br><br><br><br>


<!-- Search -->

<form class="layui-form" action="">
    <div class="layui-row ">
        <form id="" action="${pageContext.request.contextPath }/home" method="get">
            <div class="layui-col-md8 layui-col-md-offset1">

                <input type="text" name="keyword" required lay-verify="required" autocomplete="off" placeholder=""
                       class="layui-input">
            </div>
            <div>
                <button class="layui-btn layui-btn-primary layui-col-md1" lay-submit lay-filter="search">
                    <i class="layui-icon">&#xe615;</i>
                </button>
            </div>
        </form>
    </div>
    <br><br>
    <div class="layui-row ">
        <!-- <div class="layui-col-md6 layui-col-md-offset3"> -->
        <div class="layui-btn-container layui-col-md-offset3">
            <a href="${pageContext.request.contextPath }/home" class="layui-btn layui-btn-radius layui-btn-primary "
               lay-submit lay-filter="search">
                <i class="layui-icon" style="color: #FF5722">&#xe756;</i> 热门
            </a>
            <a href="${pageContext.request.contextPath }/home?category=1"
               class="layui-btn layui-btn-radius layui-btn-primary">
                喜剧
            </a>
            <a href="${pageContext.request.contextPath }/home?category=2"
               class="layui-btn layui-btn-radius layui-btn-primary">
                动画
            </a>
            <a href="${pageContext.request.contextPath }/home?category=3"
               class="layui-btn layui-btn-radius layui-btn-primary">
                动作
            </a>
            <a href="${pageContext.request.contextPath }/home?category=4"
               class="layui-btn layui-btn-radius layui-btn-primary">
                爱情
            </a>
            <a href="${pageContext.request.contextPath }/home?category=5"
               class="layui-btn layui-btn-radius layui-btn-primary">
                恐怖
            </a>

        </div>
    </div>


</form>
<br><br><br><br>

<div class="layui-row ">

    <c:forEach items="${liveList}" var="vo">
        <!--card-->
        <div class="layui-col-md4" style="margin-top: 40px;">
            <a href="/play?roomid=${vo.room.id}">
                <div class="layui-tab-card layui-col-md9 layui-col-md-offset1 layui-anim layui-anim-upbit"
                     style="height:255px">
                    <div class="layui-tab-content"
                         onmouseout="defalutImg(this,'${vo.room.img}')"
                         onmouseover="changeImg(this)">
                        <img class="layui-col-md12" src="${vo.room.img}">
                        <hr>
                        <font size="3" color="#555">${vo.room.roomname}</font>
                        <br>
                        <i class="layui-icon" style=" color: #c2c2c2;">&#xe756;${vo.viewer}人观看</i>
                        <i class="layui-icon" style=" color: #c2c2c2;">&#xe66f;${vo.user.nickname}</i>
                    </div>
                </div>
            </a>
        </div>
        <!--end card-->
    </c:forEach>


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
<!--导航-->
<script src="${pageContext.request.contextPath }/front-resources/layui/layui.js" charset="utf-8"></script>
<script>
    function changeImg(card) {

        var cardImg = card.getElementsByTagName("img")[0];
        cardImg.src = "${pageContext.request.contextPath }/front-resources/img/play2.png";


    }

    function defalutImg(card, soucrce) {
        var cardImg = card.getElementsByTagName("img")[0];
        cardImg.src = soucrce;

    }

    // Search
    //Demo
    layui.use('form', function () {
        var form = layui.form;

        // //监听提交
        // form.on('submit(search)', function (data) {
        //     layer.msg(JSON.stringify(data.field));
        //     return false;
        // });
    });
</script>
</body>

</html>