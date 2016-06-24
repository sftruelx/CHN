<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>亲子游戏</title>
</head>
<body>
<div id="page-wrapper">
    <br>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>亲子游戏列表</h3>
                </div>
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <div id="toolbar">
                            <div class="form-inline" role="form">
                                <div class="form-group">
                                    <div class="btn-group" role="group" aria-label="...">
                                        <button onclick="onAddUserClicked()" type="button" class="btn btn-success">
                                            新增
                                        </button>
                                        <button type="button" class="btn btn-warning">删除</button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <select id="queryStatus" name="queryStatus" class="form-control">
                                        <option value="">全部</option>
                                        <option value="1">已挂号</option>
                                        <option value="2">已开单</option>
                                        <option value="3">已分诊</option>
                                        <option value="4">已完成</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input name="name" class="form-control" type="text" placeholder="姓名">
                                </div>
                                <button id="ok" type="submit" class="btn btn-info">查询</button>
                            </div>
                        </div>
                        <table id="table"
                               data-classes="table table-no-bordered"
                               data-toggle="table"
                               data-height="680"
                               data-toolbar="#toolbar"
                               data-show-toggle="true"
                               data-query-params="queryParams"
                               data-show-columns="true"
                               data-show-refresh="true"
                               data-url="/demo/getPatient"
                               data-height="400"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-page-list="[10]"
                               data-sort-name="createDateStr"
                               data-sort-order="desc">
                            <thead>
                            <tr>
                                <th data-field="no">门诊号码</th>
                                <th data-field="name">姓名</th>
                                <th data-field="genderStr">性别</th>
                                <th data-field="age">年龄</th>
                                <th data-field="birthdayStr">出生日期</th>
                                <th data-field="department">挂号科室</th>
                                <th data-field="statusStr">状态</th>
                                <th data-field="createDateStr" data-sortable="true">挂号日期</th>
                                <th data-field="other" data-formatter="otherFormatter">功能</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="margin: 150px auto;" id="user-dialog" class="modal modal_align fade bs-example-modal-lg" tabindex="-1"
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

                    <div class="form-group">
                        <label for="no" class="col-sm-2 control-label">门诊号码</label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="no" id="no"/>
                        </div>

                        <label for="name" class="col-sm-2 control-label">姓名</label>

                        <div class="col-sm-4">
                            <input name="name" type="text" class="form-control" id="name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>

                        <div class="col-sm-4">
                            <select name="gender" class="form-control" id="gender">
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>

                        <label for="birthdayStr" class="col-sm-2 control-label">出生年月</label>
                        <div class="input-append birthdayStr form_datetime col-sm-4">
                            <input name="birthdayStr" id="birthdayStr" class="form-control input-sm" size="16"
                                   type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="mobile" class="col-sm-2 control-label">手机号码</label>

                        <div class="col-sm-4">
                            <input name="mobile" type="text" class="form-control input-sm" id="mobile"/>
                        </div>

                        <label class="col-sm-2 control-label">挂号科室</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="department" id="department">
                                <option value="儿童健康科">儿童健康科</option>
                                <option value="幼儿保健科">幼儿保健科</option>
                            </select>
                        </div>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    var modalDialog;
    var $table = $('#table'),
            $ok = $('#ok');
    $(function () {
        modalDialog = $("#user-dialog").on('shown.bs.modal', function (e) {
            $('#name').focus();
            $("#addForm").data('bootstrapValidator').resetForm(true);
        })

        $('#birthdayStr').datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            autoclose: true,
            todayBtn: true,
            minuteStep: 10,
            minView: "month", //选择日期后，不会再跳转去选择时分秒
            format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
            language: 'zh-CN', //汉化
            autoclose: true //选择日期后自动关闭
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
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '请输入姓名'
                        }
                    }
                },
                mobile: {
                    validators: {
                        notEmpty: {
                            message: '请输入手机号码'
                        },
                        regexp: {
                            regexp: /^((\d3)|(\d{3}\-))?13[0-9]\d{8}|15[89]\d{8}/,
                            message: '请输入11位数的手机号码'
                        }
                    }
                },
                birthdayStr: {
                    validators: {
                        notEmpty: {
                            message: '请输入出生日期'
                        },
                        date: {
                            format: 'YYYY-MM-DD',
                            message: '出生日期格式必须是(YYYY-MM-DD)'
                        }
                    }
                }
            }
        }).on('success.form.bv', function (e) {
            $("#addForm").data('bootstrapValidator').resetForm();
            onSubmitModal();
        });
    })


    $('#dateClose').click(function () {
        $('#birthdayStr').val('');
    });

    function onAddUserClicked() {
        $('#modalTitle').html("新增挂号病人");
        $('#no').val('');
        getNO();
        $('#name').val('');
        $('#gender').val('1');
        $('#birthdayStr').val('');
        $('#mobile').val('');
        modalDialog.find("input[type='hidden']").prop("value", 0);
        modalDialog.modal('show');
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
        if (row.status == 1) {
            return '<button type="button" class="btn btn-success btn-sm" onclick="gotow(' + row.id + ' )" >分诊</button>'
        } else if (row.status == 2) {
            return '<button type="button" class="btn btn-success">指引单</button>'
        } else if (row.status == 3) {
            return '<button type="button" class="btn btn-success">收费</button>'
        } else {
            return '<button type="button" class="btn btn-success">其他</button>'
        }

        return row.name + row.status;
    }


    function gotow(id) {
//        alert(id);
        window.location.href = "/demo/guide?id=" + id;
    }

    function birthdayFormatter(value) {
        // 16777215 == ffffff in decimal
        var color = '#' + Math.floor(Math.random() * 6777215).toString(16);
        return '<div  style="color: ' + color + '">' +
                '<i class="glyphicon glyphicon-usd"></i>' +
                value +
                '</div>';
    }

    function onSubmitModal() {
        $.post(
                '<%=request.getContextPath()%>/demo/addPatient',
                $("#addForm").serialize(),
                function (data) {
                    if (data.id != null) {
                        modalDialog.modal('hide');
                        $table.bootstrapTable('refresh');
                    } else {
                        bootbox.alert("保存失败");
                    }
                }
        );
        /*
         $.ajax({
         url: '
        <%=request.getContextPath()%>/demo/addPatient',
         type: 'post',
         dataType: 'json',
         data: $("#addForm").serialize(),
         success: function (result) {
         if (result.id != null) {
         modalDialog.modal('hide');
         $table.bootstrapTable('refresh');
         } else {
         bootbox.alert("保存失败");
         }
         }
         });*/
    }

    function getNO() {
        $.post(
                '<%=request.getContextPath()%>/demo/getNO',
                null,
                function (data) {
                    if (data != null) {
                        $('#no').val(data);
                    } else {
                        bootbox.alert("保存失败");
                    }
                });
    }

</script>
</body>
</html>
