<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
</head>
<body>
<div id="page-wrapper">
    <br>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>系统参数列表</h3>
                </div>
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <div id="toolbar">
                            <div class="form-inline" role="form">
                                <button type="button" onclick="onAddUserClicked()" class="btn btn-success btn-sm">新增
                                </button>
                                <div class="form-group">
                                    <input name="name" class="form-control" type="text" placeholder="权限名称">
                                    <%-- <input name="department" hidden type="text" value="幼儿保健科">--%>
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
                               data-url="/sysParam/getSysParam"
                               data-height="400"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-page-list="[10]"
                               data-sort-name="remarks"
                               data-sort-order="asc">
                            <thead>
                            <tr>
                                <th data-field="code">代码</th>
                                <th data-field="name">名称</th>
                                <th data-field="value">值</th>
                                <th data-field="remarks">备注</th>
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
<div id="sysParam-dialog" class="modal modal_align fade bs-example-modal-lg"
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
                                <label class="col-sm-2 control-label">代码</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="请输入代码" name="code" id="code"/>
                                </div>

                                <label class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-4">
                                    <input type="text" hidden name="id" id="id"/>
                                    <input type="text" class="form-control" placeholder="请输入名称" name="name" id="name"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">值</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="请输入值" name="value" id="value"/>
                                </div>

                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="请输入备注" name="remarks" id="remarks"/>
                                </div>
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

    $(function () {
        modalDialog = $("#sysParam-dialog").on('hide.bs.modal', function () {
            $("#addForm").data('bootstrapValidator').resetForm(true);
            $('#name').attr("readonly", false);
            $('#code').attr("readonly", false);
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
                            message: '请输入名称'
                        }
                    }
                },code: {
                    validators: {
                        notEmpty: {
                            message: '请输入代码'
                        }
                    }
                }
            }
        }).on('success.form.bv', function (e) {
            onSubmitModal();
            $("#addForm").data('bootstrapValidator').resetForm();

        });
    })


    function editForm(dev_id) {
        $('#name').attr("readonly", true);
        $('#code').attr("readonly", true);
        $.ajax({
            url: '/sysParam/getSysParamById/' + dev_id,
            async: true,
            dataType: 'json',
            success: function (data) {
                $('#modalTitle').html("新增权限");
                $('#id').val(data.id);
                $('#name').val(data.name);
                $('#code').val(data.code);
                $('#value').val(data.value);
                $('#remarks').val(data.remarks);
                modalDialog.find("input[type='hidden']").prop("value", 0);
                modalDialog.modal('show');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            },

        });
    }
    function onAddUserClicked() {
        $('#modalTitle').html("新增");
        $('#id').val(null);
        $('#name').val('');
        $('#code').val('');
        $('#value').val('');
        $('#remarks').val('');
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
        return '<button type="button"  onclick="editForm(' + row.id + ')" class="btn btn-success btn-sm"  >编辑</button>'
    }


    function onSubmitModal() {
        $.post(
                '<%=request.getContextPath()%>/sysParam/addSysParam',
                $("#addForm").serialize(),
                function (data) {
                    if (data.success) {
                        modalDialog.modal('hide');
                        $table.bootstrapTable('refresh');
                    } else {
                        bootbox.alert(data.msg);
                    }
                }
        );
    }


</script>
</body>
</html>
