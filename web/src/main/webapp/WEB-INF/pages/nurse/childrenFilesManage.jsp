<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="page-wrapper">
    <br>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>儿童信息列表</h3>
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
                               data-url="/children/getPatient"
                               data-height="400"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-page-list="[10]"
                               data-sort-name="createDateStr"
                               data-sort-order="desc">
                            <thead>
                            <tr>
                                <th data-field="outpatientNo">门诊号码</th>
                                <th data-field="name">姓名</th>
                                <th data-field="gender" data-formatter="genderFormatter">性别</th>
                                <th data-field="birthdayStr">出生日期</th>
                                <th data-field="cardId">身份证号</th>
                                <th data-field="medicareCard">医保卡号</th>
                                <th data-field="mobile">手机号码</th>
                                <th data-field="nation">民族</th>
                                <th data-field="status" data-formatter="statusFormatter">状态</th>
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
<div id="children-dialog" class="modal modal_align fade bs-example-modal-lg" tabindex="-1"
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
                                <label class="col-sm-2 control-label">门诊号码</label>
                                <div class="col-sm-4">
                                    <input type="text" hidden name="id" id="id"/>
                                    <input type="text" placeholder="请输入门诊号码" class="form-control" name="outpatientNo"
                                           id="outpatientNo"/>
                                </div>


                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" placeholder="请输入姓名" class="form-control" name="name"
                                           id="name"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="gender" id="gender">
                                        <option value="9">未知</option>
                                        <option value="1">男</option>
                                        <option value="2">女</option>
                                    </select>
                                </div>
                                <label class="col-sm-2 control-label">出生日期</label>
                                <div class="input-append birthdayStr form_datetime col-sm-4 date">
                                    <input name="birthdayStr" id="birthdayStr" class="form-control input-sm" size="16"
                                           value="2015-01-01" placeholder="请选择出生日期" type="text">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="请输入身份证号" name="cardId"
                                           id="cardId"/>
                                </div>
                                <label class="col-sm-2 control-label">医保卡号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="请输入医保卡号" name="medicareCard"
                                           id="medicareCard"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">手机号码</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="请输入手机号码" name="mobile"
                                           id="mobile"/>
                                </div>
                                <label class="col-sm-2 control-label">民族</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="请输入民族" name="nation"
                                           id="nation"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">居住地址</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="请输入手机号码" name="address"
                                           id="address"/>
                                </div>
                                <label class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="status" id="status">
                                        <option value="1">正常</option>
                                        <option value="0">作废</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="detail" style="display: none">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">创建日期</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control"
                                               name="createDateStr"
                                               id="createDateStr"/>
                                    </div>
                                    <label class="col-sm-2 control-label">创建人</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="createUser"
                                               id="createUser"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">修改日期</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control"
                                               name="updateDateStr"
                                               id="updateDateStr"/>
                                    </div>
                                    <label class="col-sm-2 control-label">修改人</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="updateUser"
                                               id="updateUser"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <input type="reset" style="display:none;"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button id="submit" type="submit" class="btn btn-primary">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    var modalDialog;
    var $table = $('#table'),
            $ok = $('#ok');

    $(document).ready(function () {
        commonClean();
        modalDialog = $("#children-dialog").on('hide.bs.modal', function (e) {
            if (e.namespace != "") {
                $("#addForm").data('bootstrapValidator').resetForm(true);
            }
        })


        $('#birthdayStr').datetimepicker({
            minView: "month", //选择日期后，不会再跳转去选择时分秒
            format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
            language: 'zh-CN', //汉化
            autoclose: true //选择日期后自动关闭
        })

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
                        regexp: {
                            regexp: /^((\d3)|(\d{3}\-))?13[0-9]\d{8}|15[89]\d{8}/,
                            message: '请输入正确的手机号码'
                        }
                    }
                },
                cardId: {
                    validators: {
                        regexp: {
                            regexp: /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/,
                            message: '请输入正确的身份证号码'
                        }
                    }
                },
                birthdayStr: {
                    validators: {
                        notEmpty: {
                            message: '请输入出生日期'
                        }, date: {
                            format: 'YYYY-MM-DD',
                            message: '日期正确格式为yyyy-mm-dd'
                        }
                    }
                }
            }
        }).on('success.form.bv', function (e) {
            $("#addForm").data('bootstrapValidator').resetForm();
            onSubmitModal();
        });
    })

    function genderFormatter(value, row) {
        if (row.gender == 1) {
            return '男';
        } else if (row.gender == 2) {
            return '女';
        } else {
            return '未知';
        }
    }

    function statusFormatter(value, row) {
        if (row.status == 1) {
            return '正常';
        } else {
            return '作废';
        }
    }

    $('#dateClose').click(function () {
        $('#birthdayStr').val('');
    });

    function onAddUserClicked() {
        $('#modalTitle').html("新增儿童信息");
        getNO();
        commonClean();

        modalDialog.modal('show');
    }

    function editForm(dev_id, cur_status) {
        $('#modalTitle').html("编辑儿童信息");
        commonClean();
        showInfo(dev_id);
    }

    function commonClean() {
        $('input').attr("readonly", false);//去除input元素的readonly属性
        $("input[type=reset]").trigger("click");
        $('#status').removeAttr("disabled");
        $('#gender').removeAttr("disabled");
        $('#detail').css('display', 'none');
        $("#submit").show();
        $('#outpatientNo').attr("readonly", true);
        $('#birthdayStr').attr("readonly", true);
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
        return '<button type="button"  onclick="detail(' + row.id + ')" class="btn btn-success btn-sm">详情</button>'
                + '&nbsp;&nbsp;' + '<button type="button"  onclick="editForm(' + row.id + ')" class="btn btn-success btn-sm">编辑</button>';
    }


    function onSubmitModal() {
        $.post(
                '<%=request.getContextPath()%>/children/addPatient',
                $("#addForm").serialize(),
                function (data) {
                    if (data) {
                        modalDialog.modal('hide');
                        $table.bootstrapTable('refresh');
                    } else {
                        bootbox.alert("保存失败");
                    }
                }
        );

    }

    function getNO() {
        $.post(
                '<%=request.getContextPath()%>/children/getOutpatientNO',
                function (data) {
                    if (data != null) {
                        $('#outpatientNo').val(data);
                    } else {
                        bootbox.alert("生成门诊号码失败，请重新增加");
                    }
                });
    }

    function detail(dev_id) {
        $('#modalTitle').html("查看儿童信息");

        $('#detail').css('display', 'block');
        $("#submit").hide();
        $('#gender').attr("disabled", "desabled");
        $('#status').attr("disabled", "desabled");
        $('#addForm').find('input').attr("readonly", true);

        showInfo(dev_id);
    }

    function showInfo(dev_id) {
        $.ajax({
            url: '/children/getChildrenById/' + dev_id,
            async: true,
            dataType: 'json',
            success: function (data, textStatus) {
                $('#id').val(data.id);
                $('#outpatientNo').val(data.outpatientNo);
                $('#name').val(data.name);
                $('#gender').val(data.gender);
                $('#cardId').val(data.cardId);
                $('#medicareCard').val(data.medicareCard);
                $('#mobile').val(data.mobile);
                $('#birthdayStr').val(data.birthdayStr);
                $('#nation').val(data.nation);
                $('#medicareCard').val(data.medicareCard);
                $('#mobile').val(data.mobile);
                $('#birthdayStr').val(data.birthdayStr);
                $('#address').val(data.address);
                $('#status').val(data.status);

                $('#createDateStr').val(data.createDateStr);
                $('#createUser').val(data.createUser);
                $('#updateDateStr').val(data.updateDateStr);
                $('#updateUser').val(data.updateUser);
                modalDialog.modal('show');
            }, error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }
</script>
</body>
</html>
