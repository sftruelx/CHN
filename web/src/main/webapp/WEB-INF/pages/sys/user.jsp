<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
<div id="page-wrapper">
    <br>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>用户列表</h3>
                </div>
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <div id="toolbar">
                            <div class="form-inline" role="form">
                                <button type="button" onclick="onAddUserClicked()" class="btn btn-success btn-sm">新增
                                </button>
                                <div class="form-group">
                                    <input name="usrName" class="form-control" type="text" placeholder="工号">
                                    <input name="name" class="form-control" type="text" placeholder="姓名">
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
                               data-url="/home/userGetUsers"
                               data-side-pagination="server"
                               data-height="400"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-page-list="[5, 10, 20]"
                               data-sort-name="usrName"
                               data-sort-order="desc">
                            <thead>
                            <tr>
                          <%--      <th data-field="id">ID</th>--%>
                                <th data-field="usrName" data-sortable="true">工号</th>
                                <th data-field="name">姓名</th>
                                <th data-field="usrType" data-formatter="usrTypeFormatter">类型</th>
                                <th data-field="usrDepartment" data-formatter="usrDepartmentFormatter">科室</th>
                                <th data-field="usrFlag" data-formatter="usrFlagFormatter">状态</th>
                                <%--<th data-field="createDateStr" data-sortable="true">挂号日期</th>--%>
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
<div id="user-dialog" class="modal modal_align bs-example-modal-lg"
     tabindex="-1"
     role="dialog"
     aria-labelledby="myLargeModalLabel" style="display:none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form id="addForm" role="form" class="form-horizontal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="modalTitle"></h4>
                </div>
                <div class="modal-body">

                    <input type="hidden" id="submit-type" value="0"/>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" hidden name="id" id="id"/>
                                    <input type="text" class="form-control" name="name" id="name"/>
                                </div>


                                <label class="col-sm-2 control-label">性别</label>
                                <div class="col-sm-4">
                                    <select class="selectpicker" title="请选择..." name="gender" id="gender">

                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">工号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="usrName" id="usrName"/>
                                </div>
                                <label class="col-sm-2 control-label">类型</label>
                                <div class="col-sm-4">
                                    <select class="selectpicker" title="请选择..." name="usrType" id="usrType">

                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">科室</label>
                                <div class="col-sm-4">
                                    <select class="selectpicker" title="请选择..." name="usrDepartment" id="usrDepartment">

                                    </select>
                                </div>
                                <label class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-4">
                                    <select class="selectpicker" title="请选择..." name="usrFlag" id="usrFlag">

                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系电话</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="usrPhone" id="usrPhone"/>
                                </div>
                                <label class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="usrEmail" id="usrEmail"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">角色</label>
                                <div class="col-sm-4">
                                    <select class="selectpicker" title="请选择..." multiple name="roles" id="roles">

                                    </select>
                                </div>
                                <%--<label class="col-sm-2 control-label">默认页面</label>
                                <div class="col-sm-4">
                                    <select class="selectpicker" title="请选择..."  name="usrUrl" id="usrUrl">

                                    </select>
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
<script>
    var modalDialog;
    var $table = $('#table'),
            $ok = $('#ok');
    getRoles();
    getUserEmployeeType();
    getUserGender();
    getUserDepartment();
    getUserActivity();
    getUserRights();
    $(function () {
        modalDialog = $("#user-dialog").on('hide.bs.modal', function () {
            $("#addForm").data('bootstrapValidator').resetForm(true);
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
                name: {
                    validators: {
                        notEmpty: {
                            message: '请输入姓名'
                        }
                    }
                },
                gender: {
                    validators: {
                        notEmpty: {
                            message: '请选择性别'
                        }
                    }
                },
                usrName: {
                    validators: {
                        notEmpty: {
                            message: '请选择性别'
                        }
                    }
                },
                usrType: {
                    validators: {
                        notEmpty: {
                            message: '请选择类型'
                        }
                    }
                },
                usrDepartment: {
                    validators: {
                        notEmpty: {
                            message: '请选择性别'
                        }
                    }
                },
                usrFlag: {
                    validators: {
                        notEmpty: {
                            message: '请选择状态'
                        }
                    }
                },
                usrPhone: {
                    validators: {
                        numeric: {
                            message: '请输入正确的联系电话'
                        }
                    }
                },
                usrEmail: {
                    validators: {
                        emailAddress: {
                            message: '请输入正确的email'
                        }
                    }
                },
                roles: {
                    validators: {
                        notEmpty: {
                            message: '请选择角色'
                        }
                    }
                }
            }
        }).on('success.form.bv', function (e) {
            onSubmitModal();
            $("#addForm").data('bootstrapValidator').resetForm();
        });

    })

    function getRoles() {
        $.ajax({
            url: '/home/roleGetAllRoles',
            async: true,
            dataType: 'json',
            type: 'PUT',

            success: function (data, textStatus) {
                console.log("success");
                $.each(data, function (i, item) {
                    $('#roles').append("<option value='" + item.id + "'>" + item.roleName + "</option>");
                });
                $('#roles').selectpicker('refresh');
            },

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }

    function getUserEmployeeType() {
        $.ajax({
            url: '/home/userGetUserEmployeeType',
            async: true,
            dataType: 'json',
            type: 'PUT',

            success: function (data, textStatus) {
                console.log("success");
                $.each(data, function (i, item) {
                    $('#usrType').append("<option value='" + item.dict_key + "'>" + item.dict_value + "</option>");
                });
                $('#usrType').selectpicker('refresh');
            },

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }

    function getUserDepartment() {
        $.ajax({
            url: '/home/userGetUserDepartment',
            async: true,
            dataType: 'json',
            type: 'PUT',

            success: function (data, textStatus) {
                console.log("success");
                $.each(data, function (i, item) {
                    $('#usrDepartment').append("<option value='" + item.dict_key + "'>" + item.dict_value + "</option>");
                });
                $('#usrDepartment').selectpicker('refresh');
            },

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }

    function getUserGender() {
        $.ajax({
            url: '/home/userGetUserGender',
            async: true,
            dataType: 'json',
            type: 'PUT',

            success: function (data, textStatus) {
                console.log("success");
                $.each(data, function (i, item) {
                    $('#gender').append("<option value='" + item.dict_key + "'>" + item.dict_value + "</option>");
                });
                $('#gender').selectpicker('refresh');
            },

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }
    function getUserActivity() {
        $.ajax({
            url: '/home/userGetUserActivity',
            async: true,
            dataType: 'json',
            type: 'PUT',

            success: function (data, textStatus) {
                console.log("success");
                $.each(data, function (i, item) {
                    $('#usrFlag').append("<option value='" + item.dict_key + "'>" + item.dict_value + "</option>");
                });
                $('#usrFlag').selectpicker('refresh');
            },

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }

    function getUserRights() {
        $.ajax({
            url: '/home/userGetUserRights',
            async: true,
            dataType: 'json',
            type: 'GET',
            success: function (data, textStatus) {
                console.log("success");
                $.each(data, function (i, item) {
                    if (item.parentId == null) {
                        $('#usrUrl').append(" <optgroup label='"+  item.rightText +"'>");
                    } else {
                        $('#usrUrl').append("<option value='" + item.rightUrl + "'>" + item.rightText + "</option>");
                    }
                });
                $('#usrUrl').selectpicker('refresh');
            },

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }

    function editForm(dev_id, cur_status) {
        $("#addForm").data('bootstrapValidator').resetForm(true);
        $.ajax({
            url: '/home/userGetUserById/' + dev_id,
            async: true,
            dataType: 'json',
            type: 'PUT',
            data: JSON.stringify({status: cur_status}),

            success: function (data, textStatus) {
                console.log("success" + data.roleId);

                $('#modalTitle').html("编辑用户");
                $('#id').val(data.id);
                $('#name').val(data.name);
                $('#gender').selectpicker('val',data.gender);
                $('#usrName').val(data.usrName);
                $('#usrType').selectpicker('val',data.usrType);
                $('#usrDepartment').selectpicker('val',data.usrDepartment);
                $('#usrFlag').selectpicker('val',data.usrFlag);
                $('#usrPhone').val(data.usrPhone);
                $('#usrEmail').val(data.usrEmail);
                $('#roles').selectpicker('val', data.roles);
                $('#usrName').prop("readonly", true);
                modalDialog.find("input[type='hidden']").prop("value", 0);
                modalDialog.modal('show');
            },

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }

    function resetPwd(dev_id, cur_status) {
        $.ajax({
            url: '/home/userResetPassword',
            async: true,
            dataType: 'json',
            type: 'POST',
            data: JSON.stringify({userId: dev_id}),
            contentType: 'application/json;charset=UTF-8',
            success: function (data, textStatus) {
                console.log("success" + data.roleId);
                bootbox.alert("重置成功");
            },

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
                bootbox.alert("重置失败");
            },

        });
    }
    function onAddUserClicked() {
        $('#modalTitle').html("新增");
        $('#id').val(null);
        $('#name').val('');
        $('#gender').val(null);
        $('#usrName').val('');
        $('#usrName').prop("readonly", false);
        $('#usrType').val(null);
        $('#usrDepartment').val(null);
        $('#usrFlag').val(null);
        $('#usrPhone').val('');
        $('#usrEmail').val('');
        $('#roles').val(null);
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
        return '<div class="btn-group btn-group-sm">   <button type="button"  onclick="editForm(' + row.id + ')" class="btn btn-success btn-sm"  >编辑</button> <button type="button"  onclick="resetPwd(' + row.id + ')" class="btn btn-success btn-sm"  >重置密码</button></div>'
    }
    function usrDepartmentFormatter(value, row) {
        return $('#usrDepartment option[value="' + row.usrDepartment + '"]').text();
    }
    function usrTypeFormatter(value, row) {
        return $('#usrType option[value="' + row.usrType + '"]').text();
    }
    function usrFlagFormatter(value, row) {
        return $('#usrFlag option[value="' + row.usrFlag + '"]').text();
    }
    function onSubmitModal() {
        $.post(
                '<%=request.getContextPath()%>/home/userAdd',
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


</script>
</body>
</html>
