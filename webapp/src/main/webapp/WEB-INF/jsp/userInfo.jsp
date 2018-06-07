<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="utf-8">
    <title>kiwi-theater-编辑资料</title>

    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/front-resources/layui/css/layui.css" media="all">
    <link rel="icon" href="${pageContext.request.contextPath }/front-resources/img/logo/icons8-kiwi-64.png"
          type="image/x-icon"/>
    <script src="${pageContext.request.contextPath }/front-resources/js/charge.js" charset="utf-8"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<body style=" color : #555;">
<!-- logo -->
<div class="layui-container">
    <div class="layui-row ">
        <br><br>
        <div class="layui-col-md3">
            <a href="${pageContext.request.contextPath }/home">
                <img src="${pageContext.request.contextPath }/front-resources/img/logo/logo.png" alt=""
                     class="layui-col-md12">
            </a>
        </div>
    </div>

    <!--title-->

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend style="font-size: 30px;">编辑个人资料</legend>
    </fieldset>


    <div class="">
        <img class="" style="height:200px; width:200px" src="${pageContext.request.contextPath }/${activeUser.avatar}"
             id="preview">
        <button type="button" class="layui-btn layui-btn-primary " style="margin-left: 30px;margin-top:150px"
                id="uploadImg"><i class="layui-icon">&#xe67c;</i>上传图片
        </button>
        <p id="uploadText"></p>
    </div>

    <!-- <div class=" " style="margin-top:100px"> -->


    <form class="layui-form layui-form-pane" style="margin-top:50px" action="${pageContext.request.contextPath }/editUserInfo" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="nickname" value="${activeUser.nickname}" required
                       autocomplete="off" class="layui-input">
                <input type="hidden" name="avatar" value="${activeUser.avatar}"><!--TODO 支持上传图片-->
            </div>
            <div class="">
                <button class="layui-btn layui-btn-primary " lay-submit="">提交修改</button>
            </div>
        </div>

    </form>
    <!-- </div> -->


    <a href="${pageContext.request.contextPath }/editRoomInfo">
        <button class="layui-btn layui-btn-primary " lay-submit="">编辑我的直播间</button>
    </a>
    <button class="layui-btn layui-btn-primary " onclick="charge()" lay-submit="">
        <i class="layui-icon" style="color: #FF5722;">&#xe65e;</i>
        充值 <font color=#888>(剩余:<span id="coin">${activeUser.coin}</span>金币)</font></button>

</div>


<script src="${pageContext.request.contextPath }/front-resources/layui/layui.js" charset="utf-8"></script>
<script>
    // Search
    //Demo
    layui.use('form', function () {
        var form = layui.form;

        // //监听提交
        // form.on('submit(sub)', function (data) {
        //     layer.msg(JSON.stringify(data.field));
        //     return false;
        // });
    });

    layui.use('upload', function () {
        var $ = layui.jquery,
            upload = layui.upload;
        var preSrc = $('#preview')[0].src;
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#uploadImg',
            url: '/upload/',
            before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {

                    $('#preview').attr('src', result); //图片链接（base64）
                });
            },
            done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
            },
            error: function () {
                //演示失败状态，并实现重传
                var uploadText = $('#uploadText');
                uploadText.html('<span style="color: #FF5722;">上传失败</span>');
                $('#preview').attr('src', preSrc);
            }
        });
    });
</script>

</body>

</html>