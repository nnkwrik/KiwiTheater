<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>

<head>
    <meta charset="utf-8">

    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/front-resources/layui/css/layui.css" media="all">
    <link rel="icon" href="${pageContext.request.contextPath }/front-resources/img/logo/icons8-kiwi-64.png" type="image/x-icon" />
</head>


<body style=" color : #555;">

<!-- logo -->
<div class="layui-container">
    <div class="layui-row ">
        <br><br>
        <div class="layui-col-md3">
            <a href="${pageContext.request.contextPath }/home">
                <img src="${pageContext.request.contextPath }/front-resources/img/logo/logo.png" alt="" class="layui-col-md12">
            </a>
        </div>
    </div>


    <div class="layui-col-md4 layui-col-md-offset4"
         style="margin-top: 150px;border: 1px solid #ccc;padding:14px;border-radius: 16px;">
        <h2 style="text-align: center;">登录<br><br></h2>
        <form class="layui-form" action="${pageContext.request.contextPath }/signin" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">帐号</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" required lay-verify="required" placeholder="" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder=""
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <c:if test="${errorMsg!=null}">
                <p style="color: #FF5722;margin-left: 100px;margin-bottom: 20px">${errorMsg}</p>
            </c:if>
            <div class="layui-form-item">
              <div class="layui-input-block" style="height: 10px;">
                <input type="checkbox" name="remenberme" lay-skin="primary" title="记住我">
              </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-primary" lay-submit lay-filter="post">提交</button>
                    &nbsp没有帐号?
                    <a href="/signup" style="color:#01AAED">注册</a>
                </div>
            </div>

        </form>

    </div>


    <script src="${pageContext.request.contextPath }/front-resources/layui/layui.js" charset="utf-8"></script>
    <script>
        // Search
        //Demo
        layui.use('form', function () {
            var form = layui.form;

            //监听提交
            form.on('submit(post)', function (data) {
                // layer.msg(JSON.stringify(data.field));
                // return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
        });
    </script>

</body>

</html>
