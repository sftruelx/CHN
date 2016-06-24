<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>绑定用户信息</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <link href="${ctx}/scripts/demo/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/scripts/demo/bower_components/jquery/dist/jquery.min.js"></script>


    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/scripts/demo/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/scripts/bootbox.min.js"></script>
</head>
<body>
<div>
    <img src="/images/logo-01.png" width="90%">
    <input hidden id="openId" value="${openId}"/>
    <div style="padding-top: 5%;padding-left: 3%" class="input-group">
        <span class="input-group-addon">姓名：</span>
        <input id="name" type="text" value="${bizPatient.name}" style="width: 90%;" class="form-control" placeholder="请输入姓名"
               aria-describedby="basic-addon1">
    </div>

    <div style="padding-top: 5%;padding-left: 3%" class="input-group">
        <span class="input-group-addon">手机：</span>
        <input id="num" type="text" value="${bizPatient.mobile}" style="width: 90%" class="form-control" placeholder="请输入手机号"
               aria-describedby="basic-addon1">
    </div>
    <div style="padding-top: 10%;padding-left: 35%">
        <button id="bindUser" type="submit" class="btn btn-primary">绑定用户</button>
    </div>

</div>
<script type="text/javascript">
    var openId = $('#openId').val();
    $('#bindUser').click(function () {
        var name = $('#name').val();
        var num = $('#num').val();
        if (name.trim() == "" || name == null) {
            $("#name").parent().addClass("has-error");
            $("#name").focus();
            return false;
        } else {
            $("#name").parent().removeClass("has-error");
        }

        if (num.trim() == "" || num == null) {
            $("#num").parent().addClass("has-error");
            $("#num").focus();
            return false;
        } else {
            $("#num").parent().removeClass("has-error");
        }

        if (!(/^1[3|4|5|7|8]\d{9}$/.test(num))) {
            $("#num").parent().addClass("has-error");
            $("#num").focus();
            return false;
        } else {
            $("#num").parent().removeClass("has-error");
        }

        $.ajax({
            url: '<%=request.getContextPath()%>/demo/addBind',
            type: 'post',
            data: {
                openId: openId,
                name: name,
                num: num
            },
            success: function (result) {
                if (result.code == 0) {
                    bootbox.alert("绑定成功");
                } else {
                    bootbox.alert("绑定失败");
                }
            }
        });
    })

</script>
</body>
</html>