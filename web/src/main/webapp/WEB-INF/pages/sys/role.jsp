<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>角色管理</title>
    <!-- ztree JavaScript -->
    <link href="${ctx}/scripts/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <script src="${ctx}/scripts/zTree/jquery.ztree.all.min.js"></script>
</head>

<body>
<div id="page-wrapper">
    <br>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>角色列表</h3>
                </div>
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <div id="toolbar">
                            <div class="form-inline" role="form">
                                <button type="button" onclick="onAddUserClicked()" class="btn btn-success btn-sm">新增
                                </button>
                                <div class="form-group">
                                    <input name="roleName" class="form-control" type="text" placeholder="角色名称">
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
                               data-url="/sys/roleGetRoles"
                               data-height="400"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-page-list="[10]"
                               data-sort-name="createDateStr"
                               data-sort-order="desc">
                            <thead>
                            <tr>
                                <th data-field="roleName">名称</th>
                                <th data-field="roleDesc">描述</th>
                                <th data-field="roleFlag" data-formatter="statusFormatter">状态</th>
                                <%--<th data-field="roleSecurity">角色名称</th>--%>
                                <th data-field="other" data-formatter="otherFormatter">功能</th>
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
<div id="user-dialog" class="modal modal_align fade bs-example-modal-lg"
     tabindex="-1"
     role="dialog"
     aria-labelledby="myLargeModalLabel" style="display:none;">
    <div class="modal-dialog modal-lg" style="width: 50%">
        <div class="modal-content">
            <form id="addForm" role="form" class="form-horizontal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="modalTitle"></h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-6">
                                    <input type="text" hidden name="id" id="id"/>
                                    <input type="text" placeholder="请输入名称" class="form-control" name="roleName"
                                           id="roleName"/>
                                </div>
                            </div>
                            <div class="form-group">

                                <label class="col-sm-2 control-label">描述</label>
                                <div class="col-sm-6">
                                    <input type="text" placeholder="请输入描述" class="form-control" name="roleDesc"
                                           id="roleDesc"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="roleFlag" id="roleFlag">
                                        <option value="1">有效</option>
                                        <option value="0">无效</option>
                                    </select>
                                </div>
                                <%--<label class="col-sm-2 control-label">角色名称</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="请输入角色名称" name="roleSecurity" id="roleSecurity"/>
                                </div>--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">提交
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="set-dialog" class="modal modal_align fade bs-example-modal-lg"
     tabindex="-1"
     role="dialog"
     aria-labelledby="myLargeModalLabel" style="display:none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="setTitle"></h4>
            </div>

            <div class="col-xs-12" style="border: 1px;height: 500px;overflow:auto">
                <ul id="tree" class="ztree" style="height: 500px"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" onclick="addAuthority()" class="btn btn-primary">提交
                </button>
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
        modalDialog = $("#user-dialog").on('hide.bs.modal', function () {
            $("#addForm").data('bootstrapValidator').resetForm(true);
        });

        setDialog = $("#set-dialog").on('hide.bs.modal', function () {
        });

        $ok.click(function () {
            $table.bootstrapTable('refresh');

        });

        $("#addForm").bootstrapValidator({
            message: 'This value is not valid',
            submitButtons: 'button[type="submit"]',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                roleName: {
                    validators: {
                        notEmpty: {
                            message: '请输入姓名'
                        }
                    }
                }
            }
        }).on('success.form.bv', function (e) {
            onSubmitModal();
            $("#addForm").data('bootstrapValidator').resetForm();

        });
    })


    function editForm(dev_id, cur_status) {
        $('#addForm')[0].reset();
        $("#addForm").data('bootstrapValidator').resetForm(true);
        $.ajax({
            url: '/sys/roleGetRoleById/' + dev_id,
            async: true,
            dataType: 'json',
            type: 'PUT',
            data: JSON.stringify({status: cur_status}),

            success: function (data, textStatus) {
                $('#modalTitle').html("编辑用户权限");
                $('#id').val(data.id);
                $('#roleName').val(data.roleName);
                $('#roleDesc').val(data.roleDesc);
                $('#roleSecurity').val(data.roleSecurity);
                $('#roleFlag').val(data.roleFlag);

                modalDialog.find("input[type='hidden']").prop("value", 0);
                modalDialog.modal('show');
            },

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }
    function onAddUserClicked() {
        $('#modalTitle').html("新增角色");
        $('#addForm')[0].reset();
        modalDialog.find("input[type='hidden']").prop("value", 0);
        modalDialog.modal('show');
    }

    function queryParams(params) {
        $('#toolbar').find('input[name]').each(function () {
            params[$(this).attr('name')] = $(this).val();
        });

        params["status"] = $('#queryStatus').val();
        params["ttt"] = new Date().getTime();
        return params;
    }

    function responseHandler(res) {
        return res.rows;
    }

    function otherFormatter(value, row) {
        return '<div class="btn-group btn-group-sm">  <button type="button"  onclick="editForm(' + row.id + ')" class="btn btn-success btn-sm"  >编辑</button>'
                + '&nbsp;&nbsp;' + '<button type="button"  onclick="setAuthority(' + row.id + ')" class="btn btn-success btn-sm">设置权限</button></div>';
    }


    function onSubmitModal() {
        $.post(
                '<%=request.getContextPath()%>/sys/roleAddRole',
                $("#addForm").serialize(),
                function (data) {
                    if (data.flag == 1) {
                        modalDialog.modal('hide');
                        $table.bootstrapTable('refresh');
                    } else {
                        bootbox.alert(data.message);
                    }
                }
        );
    }

    function setAuthority(dev_id) {
        $('#id').val(dev_id);
        var flag = false;
        $.ajax({
            url: '/sys/roleGetIsAuthority',
            dataType: 'json',
            async: false,
            data: {
                roleId: dev_id
            },
            success: function (data) {
                regiht = data;
            }
        });
        $.ajax({
            url: '/sys/rightGetRights',
            dataType: 'json',
            async: false,
            data: {
                limit: 10000,
                offset: 0,
                order: 'desc',
                sort: 'menuCode'
            },
            success: function (data, textStatus) {
                $('#setTitle').html("设置权限");
                setDialog.modal('show');
                var setting = {
                    view: {
                        selectedMulti: false
                    },
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        beforeCheck: beforeCheck,
                        onCheck: onCheck
                    }
                };
                var arr = [];
                var newArr = {};
                $.each(data.rows, function (index, value) {
                    if (regiht.length > 0) {
                        $.each(regiht, function (name, reg) {
                            if (value.id == reg.id) {
                                flag = true;
                                return false;
                            } else {
                                flag = false;
                            }
                        })
                    }
                    newArr =
                    {
                        "id": value.id,
                        "name": value.rightText,
                        "pId": value.parentId == null ? 0 : value.parentId,
                        "checked": flag
                    }
                    arr.push(newArr);
                });
                var treeNodes = arr;

                var treeObj = $.fn.zTree.init($("#tree"), setting, treeNodes);
                treeObj.expandAll(true);
                $.each(data.rows, function (index, value) {
                    $("#list-left").append("<option value=" + value.id + ">" + value.rightText + "</option>");
                })


            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }

    //添加权限的方法
    function addAuthority() {
        var roleId = $('#id').val();

        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getCheckedNodes(true);

        var arr = [];
        for (var i = 0; i < nodes.length; i++) {
            arr.push(nodes[i].id);
        }
        $.ajax({
            url: '/sys/rightAddRoleRight',
            dataType: 'json',
            async: false,
            data: {
                roleId: roleId,
                nodes: arr.toString()
            },
            success: function (data) {
                if (data != null) {
                    setDialog.modal('hide');
                } else {
                    bootbox.alert("设置权限失败,请重新设置");
                }
            }
        })
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

    function checkNode(e) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                type = e.data.type,
                nodes = zTree.getSelectedNodes();
        if (type.indexOf("All") < 0 && nodes.length == 0) {
            alert("请先选择一个节点");
        }

        if (type == "checkAllTrue") {
            zTree.checkAllNodes(true);
        } else if (type == "checkAllFalse") {
            zTree.checkAllNodes(false);
        } else {
            var callbackFlag = $("#callbackTrigger").attr("checked");
            for (var i = 0, l = nodes.length; i < l; i++) {
                if (type == "checkTrue") {
                    zTree.checkNode(nodes[i], true, false, callbackFlag);
                } else if (type == "checkFalse") {
                    zTree.checkNode(nodes[i], false, false, callbackFlag);
                } else if (type == "toggle") {
                    zTree.checkNode(nodes[i], null, false, callbackFlag);
                } else if (type == "checkTruePS") {
                    zTree.checkNode(nodes[i], true, true, callbackFlag);
                } else if (type == "checkFalsePS") {
                    zTree.checkNode(nodes[i], false, true, callbackFlag);
                } else if (type == "togglePS") {
                    zTree.checkNode(nodes[i], null, true, callbackFlag);
                }
            }
        }
    }
</script>
</body>
</html>
