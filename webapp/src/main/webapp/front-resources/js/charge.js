
var curPath=window.document.location.href;
var pathName=window.document.location.pathname;
var pos=curPath.indexOf(pathName);
var localhostPath=curPath.substring(0,pos);
var path=localhostPath + pathName.substring(0,pathName.substr(1).indexOf('/')+1);

function charge() {
    layer.closeAll('dialog');
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['420px', '200px'], //宽高
        content: '<div style="text-align:center; margin-top:20px">' +
        '<h2 style="margin-bottom:30px">选择充值金额</h2>' +
        '<button class="layui-btn layui-btn-primary" onclick="postCharge(10);">10块</button>' +
        '<button class="layui-btn layui-btn-primary" onclick="postCharge(5);">5块</button>' +
        '<button class="layui-btn layui-btn-primary" onclick="postCharge(3);">3块</button>' +
        '<button class="layui-btn layui-btn-primary" onclick="postCharge(1);">1块</button>' +
        '<button class="layui-btn layui-btn-primary" onclick="postCharge(0.5);">5毛</button>' +
        '</div>'
    });


}

function postCharge(amount) {
    layer.closeAll();
    layer.load(2);
    $.ajax({
        type: "post",
        url: "/charge",
        data: '{"amount":' + amount + '}',
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            setTimeout(function () {
                layer.closeAll('loading');
            }, 2000);
            // $("#coin").html('<i class="layui-icon" style="color: #FF5722;">&#xe65e;</i>余额：'+data);
            $("#coin").html(data);
            layer.msg('充值成功! 当前余额:'+data, {icon: 1});

        }
    });
}