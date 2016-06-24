<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>

</head>
<body class="home">


<!-- Page Content -->
<div id="page-wrapper">
    <br>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>用户信息</h3>
                </div>
                <div class="panel-body">
                    <form id="addForm" role="form" class="form-horizontal" action="/home/homeUserSubmit" method="post">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">姓名</label>
                                    <div class="col-sm-4">
                                        <input type="text" hidden name="id" id="id"/>
                                        <input type="text" class="form-control" name="name" id="name"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">工号</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="usrName" id="usrName"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">性别</label>
                                    <div class="col-sm-4">
                                        <select class="selectpicker" title="请选择..." name="gender" id="gender">

                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
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
                                        <select class="selectpicker" title="请选择..." name="usrDepartment"
                                                id="usrDepartment">

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
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Email</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="usrEmail" id="usrEmail"/>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <%--                                <div class="form-group">
                                                                    <label class="col-sm-2 control-label">角色</label>
                                                                    <div class="col-sm-4">
                                                                        <select class="selectpicker" title="请选择..." multiple name="roles" id="roles">

                                                                        </select>
                                                                    </div>
                                                                </div>--%>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">默认页面</label>
                                    <div class="col-sm-4">
                                        <select class="selectpicker" title="请选择..." name="usrUrl" id="usrUrl">
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <p>${Message}</p>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-primary">提交
                            </button>
                        </div>
                    </form>
                </div>
                <!-- /.container-fluid -->
            </div>
        </div>
    </div>    <!-- /#page-wrapper -->
    <script>
        var urlstr;
        getRoles();
        getUserEmployeeType();
        getUserGender();
        getUserDepartment();
        getUserActivity();

        $(function () {

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
            });

            $('#roles').on('changed.bs.select', function (e) {
                // do something...

                $('#usrUrl').empty();
                getUserRights($('#roles').val());
                $('#usrUrl').selectpicker('val', urlstr);
            });

            editForm(${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.id});
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

        function getUserRights(dev_roles) {
            $.ajax({
                url: '/home/userGetUserRights',
                async: false,
                dataType: 'json',
                type: 'PUT',
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify({roles: dev_roles}),
                success: function (data, textStatus) {
                    console.log("success");
                    $.each(data, function (i, item) {
                        if (item.parentId == null) {
                            $('#usrUrl').append(" <optgroup label='" + item.rightText + "'>");
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

        function editForm(dev_id) {
            $("#addForm").data('bootstrapValidator').resetForm(true);
            $.ajax({
                url: '/home/userGetUserById/' + dev_id,
                async: true,
                dataType: 'json',
                type: 'PUT',
                data: JSON.stringify({id: dev_id}),

                success: function (data, textStatus) {
                    console.log("success" + data.roleId);

                    $('#modalTitle').html("新增用户");
                    $('#id').val(data.id);
                    $('#name').val(data.name);
                    $('#gender').selectpicker('val', data.gender);
                    $('#usrName').val(data.usrName);
                    $('#usrType').selectpicker('val', data.usrType);
                    $('#usrDepartment').selectpicker('val', data.usrDepartment);
                    /*                    $('#usrFlag').selectpicker('val', data.usrFlag);*/
                    $('#roles').selectpicker('val', data.roles);
                    getUserRights(data.roles);
                    $('#roles').prop('disabled', true);
                    $('#usrName').prop("readonly", true);
                    $('#usrUrl').selectpicker('val', data.usrUrl);
                    $('#usrPhone').val(data.usrPhone);
                    $('#usrEmail').val(data.usrEmail);
                    urlstr = data.usrUrl;
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
        /*       function onAddUserClicked() {
         $('#modalTitle').html("新增");
         $('#id').val(null);
         $('#name').val('');
         $('#gender').val(null);
         $('#usrName').val('');
         $('#usrName').prop("readonly", false);
         $('#usrType').val(null);
         $('#usrDepartment').val(null);
         $('#usrFlag').val(null);
         $('#roles').val(null);
         }

         function queryParams(params) {
         $('#toolbar').find('input[name]').each(function () {
         params[$(this).attr('name')] = $(this).val();
         });

         params["status"] = $('#queryStatus').val();
         return params;
         }

         function responseHandler(res) {
         return res.rows;
         }

         function otherFormatter(value, row) {
         return ' <button type="button"  onclick="editForm(' + row.id + ')" class="btn btn-success btn-sm"  >编辑</button> <button type="button"  onclick="resetPwd(' + row.id + ')" class="btn btn-success btn-sm"  >重置密码</button>'
         }
         function usrDepartmentFormatter(value, row) {
         return $('#usrDepartment option[value="' + row.usrDepartment + '"]').text();
         }
         function usrTypeFormatter(value, row) {
         return $('#usrType option[value="' + row.usrType + '"]').text();
         }
         function usrFlagFormatter(value, row) {
         return $('#usrFlag option[value="' + row.usrFlag + '"]').text();
         }*/
        function onSubmitModal() {
            $("#addForm").submit();
            /*$.post(
             '
            <%=request.getContextPath()%>/home/userAdd',
             $("#addForm").serialize(),
             function (data) {
             if (data.flag == 1) {
             bootbox.alert(data.message);
             } else {
             bootbox.alert(data.message);
             }
             }
             );*/
        }

    </script>
</div>
</body>
