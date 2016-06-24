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
                    <h3>权限列表</h3>
                </div>
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <div id="toolbar">
                            <div class="form-inline" role="form">
<%--                                <button type="button" onclick="onAddUserClicked()" class="btn btn-success btn-sm">新增
                                </button>--%>
                                <div class="form-group">
                                    <input name="rightText" class="form-control" type="text" placeholder="权限名称">
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
                               data-url="/sys/rightGetRights"
                               data-height="400"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-page-list="[5, 10, 20]"
                               data-sort-name="menuCode"
                               data-sort-order="asc">
                            <thead>
                            <tr>
                   <%--             <th data-field="id">ID</th>--%>
                                <th data-field="rightText">权限名称</th>
                               <th data-field="rightDesc">权限说明</th>
  <%--                               <th data-field="parentId">父节点</th>
                                <th data-field="menuCode"  data-sortable="true">编码</th>--%>
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
                                <label class="col-sm-2 control-label">权限名称</label>
                                <div class="col-sm-4">
                                    <input type="text" hidden name="id" id="id"/>
                                    <input type="text" class="form-control" name="rightText" id="rightText"/>
                                </div>


                                <%--<label class="col-sm-2 control-label">URL</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="rightUrl" id="rightUrl"/>
                                </div>--%>
                            </div>
                        </div>
                    </div>
                    <%--<div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">权限说明</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="rightDesc" id="rightDesc"/>
                                </div>

                               &lt;%&ndash; <label class="col-sm-2 control-label">编码</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="menuCode" id="menuCode"/>
                                </div>&ndash;%&gt;
                            </div>
                        </div>
                    </div>--%>
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
                rightText: {
                    validators: {
                        notEmpty: {
                            message: '请输入权限名称'
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
        $("#addForm").data('bootstrapValidator').resetForm(true);
        $.ajax({
            url: '/sys/rightGetRightById/' + dev_id,
            async: true,
            dataType: 'json',
            type: 'PUT',
            data: JSON.stringify({status: cur_status}),

            success: function (data, textStatus) {
                console.log("success" + data.name + data.age);
                $('#modalTitle').html("修改权限");
                $('#id').val(data.id);
                $('#rightText').val(data.rightText);
                $('#rightDesc').val(data.rightDesc);
 /*               $('#parentId').val(data.parentId);
                $('#menuCode').val(data.menuCode);*/
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
        $('#rightText').val('');
        $('#rightDesc').val('');
/*        $('#parentId').val(null);
        $('#menuCode').val('');*/
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
        return '<div class="btn-group btn-group-sm">  <button type="button"  onclick="editForm(' + row.id + ')" class="btn btn-success btn-sm"  >编辑</button></div>'
    }


    function onSubmitModal() {
        $.post(
                '<%=request.getContextPath()%>/sys/rightAddRight',
                $("#addForm").serialize(),
                function (data) {
                    if (data.flag == 1) {
                        modalDialog.modal('hide');
                        $table.bootstrapTable('refresh');
                    } else {
                        bootbox.alert(data.message)
                    }
                }
        );
    }


</script>
</body>
</html>
