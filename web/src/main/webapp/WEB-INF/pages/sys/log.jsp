<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>日志查询</title>
    <!-- ztree JavaScript -->
</head>

<body>
<div id="page-wrapper">
    <br>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>日志查询</h3>
                </div>
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <div id="toolbar">
                            <div class="form-inline" role="form">
                                <div class="form-group">
                                    <input name="url" class="form-control" type="text" placeholder="功能名称">
                                    <input name="username" class="form-control" type="text" placeholder="操作人">
                                    <input name="ip" class="form-control" type="text" placeholder="IP地址">
                                </div>
                                <button id="ok" type="submit" class="btn btn-info">查询</button>
                            </div>
                        </div>
                        <table id="table"
                               data-classes="table table-no-bordered"
                               data-toggle="table"
                               data-height="660"
                               data-toolbar="#toolbar"
                               data-show-toggle="true"
                               data-query-params="queryParams"
                               data-show-columns="true"
                               data-show-refresh="true"
                               data-url="/sys/getLogs"
                               data-height="400"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-page-list="[10]"
                               data-sort-name="createTime"
                               data-sort-order="desc">
                            <thead>
                            <tr>
                                <th data-field="url">功能</th>
                                <th data-field="username">操作人</th>
                                <th data-field="timeStr">时间</th>
                                <th data-field="role">角色名称</th>
                                <th data-field="ip">IP</th>
                            </tr>
                            </thead>
                        </table>
                        <%-- </div>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<script>
    var code, log, className = "dark";
    var modalDialog;
    var setDialog;
    var regiht;
    var $table = $('#table'),
            $ok = $('#ok');

    function statusFormatter(value, row) {
        if (row.roleFlag == 1) {
            return '有效';
        } else {
            return '无效';
        }
    }

    $(function () {


        $ok.click(function () {
            $table.bootstrapTable('refresh');

        });


    })




    function queryParams(params) {
        $('#toolbar').find('input[name]').each(function () {
            params[$(this).attr('name')] = $(this).val();
        });
        params["ttt"] = new Date().getTime();
        params["status"] = $('#queryStatus').val();
        return params;
    }

    function responseHandler(res) {
        return res.rows;
    }

    function otherFormatter(value, row) {
        return '<button type="button"  onclick="editForm(' + row.id + ')" class="btn btn-success btn-sm"  >编辑</button>'
                + '&nbsp;&nbsp;' + '<button type="button"  onclick="setAuthority(' + row.id + ')" class="btn btn-success btn-sm">设置权限</button>';
    }






    function beforeCheck(treeId, treeNode) {
        className = (className === "dark" ? "" : "dark");
        showLog("[ " + getTime() + " beforeCheck ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
        return (treeNode.doCheck !== false);
    }
    function onCheck(e, treeId, treeNode) {
        showLog("[ " + getTime() + " onCheck ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
    }

    function showLog(str) {
        if (!log) log = $("#log");
        log.append("<li class='" + className + "'>" + str + "</li>");
        if (log.children("li").length > 6) {
            log.get(0).removeChild(log.children("li")[0]);
        }
    }

    function getTime() {
        var now = new Date(),
                h = now.getHours(),
                m = now.getMinutes(),
                s = now.getSeconds(),
                ms = now.getMilliseconds();
        return (h + ":" + m + ":" + s + " " + ms);
    }


</script>
</body>
</html>
