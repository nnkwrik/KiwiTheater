<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>


<body>

<button onclick="sendJson()">json数据交互测试</button>
<script type="text/javascript">
    function sendJson() {
        alert("${pageContext.request.contextPath }");
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath }/json_test",
            data:'{"id":"1","name":"电冰箱","price":"1999"}',
            contentType:"application/json;charset=utf-8",
            success:function(data) {
                alert(data);
            }
        });
    }
</script>



</body>
</html>

