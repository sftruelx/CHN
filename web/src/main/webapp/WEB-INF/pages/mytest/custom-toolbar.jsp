<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>custom-toolbar</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${ctx}/scripts/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/scripts/assets/bootstrap-table/src/bootstrap-table.css">
    <link rel="stylesheet" href="${ctx}/scripts/assets/examples.css">
    <style>
        .w70 {width: 70px !important;}
    </style>
    <script src="${ctx}/scripts/assets/jquery.min.js"></script>
    <script src="${ctx}/scripts/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${ctx}/scripts/assets/bootstrap-table/src/bootstrap-table.js"></script>
</head>
<body>
    <div class="container">
        <h1>用户列表</h1>

        <div id="buttonbar">
            <button type="button" class="btn btn-success">新增用户</button>
            <button type="button" class="btn btn-danger">删除</button>
        </div>
        <br>
        <p>点击查询按钮查询用户信息</p>
        <div id="toolbar">
            <div class="form-inline" role="form">
<%--                <div class="form-group">
                    <span>Offset: </span>
                    <input name="offset" class="form-control w70" type="number" value="0">
                </div>
                <div class="form-group">
                    <span>Limit: </span>
                    <input name="limit" class="form-control w70" type="number" value="5">
                </div>--%>
                <div class="form-group">
                    <input name="search" class="form-control" type="text" placeholder="省市">
                </div>
                <div class="form-group">
                    <input name="name" class="form-control" type="text" placeholder="用户名">
                </div>
                <div class="form-group">
                    <input name="address" class="form-control" type="text" placeholder="地址">
                </div>
                <button id="ok" type="submit" class="btn btn-info">查询</button>
            </div>
        </div>
        <table id="table"
               data-toggle="table"
               data-height="460"
               data-toolbar="#toolbar"
               data-show-refresh="true"
               data-show-toggle="true"
               data-show-columns="true"
               data-query-params="queryParams"
               data-response-handler="responseHandler"
               data-url="/user/list">
            <thead>
            <tr>
                <th data-field="id">ID</th>
                <th data-field="usrName">Item Name</th>
                <th data-field="price">Item Price</th>
            </tr>
            </thead>
        </table>
    </div>

<script>
    var $table = $('#table'),
        $ok = $('#ok');

    $(function () {
        $ok.click(function () {
            $table.bootstrapTable('refresh');
        });
    });

    function queryParams() {
        var params = {};
        $('#toolbar').find('input[name]').each(function () {
            params[$(this).attr('name')] = $(this).val();
        });
        return params;
    }

    function responseHandler(res) {
        return res.rows;
    }
</script>
</body>
</html>