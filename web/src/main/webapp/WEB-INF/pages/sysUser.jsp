<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
<button type="button" onclick="onAddUserClicked()" class="btn btn-xs btn-default"><span
        class="icon-plus-sign-alt">增加用户</span></button>
<table id="grid-command-buttons" class="table table-condensed table-hover table-striped">
    <thead>
    <tr>
        <th data-column-id="id">ID</th>
        <th data-column-id="usrName">usrName</th>
        <th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
<div id="user-dialog" class="modal modal_align fade bs-example-modal-lg" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel" style="display:none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle"></h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <input type="hidden" id="submit-type" value="0"/>

                    <div class="form-group">
                        <label for="t-username" class="col-sm-2 control-label">用户名</label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control input-sm" id="t-username"/>
                        </div>

                        <label for="t-email" class="col-sm-2 control-label"><spring:message code="user.email"/></label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control input-sm" id="t-email"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="onSubmitModal();">提交
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    var modalDialog;

    $(function () {
        init();
        modalDialog = $("#user-dialog");
    })

    function onAddUserClicked() {
        $('#modalTitle').html("增加用户");
        modalDialog.find("input[type='hidden']").prop("value", 0);
        modalDialog.modal('show');
    }

    function init() {
        var grid = $("#grid-command-buttons").bootgrid({
            ajax: true,
            post: function () {
                return {
                    id: "b0df282a-0d67-40e5-8558-c9e93b7befed"
                };
            },
            url: "/user/list",
            formatters: {
                "commands": function (column, row) {
                    return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"icon-edit\"></span></button> " +
                            "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\"><span class=\"icon-trash\"></span></button>";
                }
            }
        }).on("loaded.rs.jquery.bootgrid", function () {
            /* Executes after data is loaded and rendered */
            grid.find(".command-edit").on("click", function (e) {
                alert("You pressed edit on row: " + $(this).data("row-id"));
            }).end().find(".command-delete").on("click", function (e) {
                deleteUser($(this).data("row-id"));
            });
        });
    }

    function deleteUser(userId) {
        bootbox.confirm({
            buttons: {
                confirm: {
                    label: '确认',
                    className: 'btn btn-primary'
                },
                cancel: {
                    label: '取消',
                    className: 'btn-default'
                }
            },
            message: '确定要删除此用户吗？',
            callback: function (result) {
                if (result) {
                    $.ajax({
                        url: '<%=request.getContextPath()%>/service/user/delete',
                        type: 'post',
                        dataType: 'json',
                        data: {
                            userId: userId
                        },
                        success: function (result) {
                            buttonAdd.removeAttr('disabled');
                            if (0 == result.code) {
                                bootbox.alert("删除成功");
                                init();
                            }
                        },
                        error: function () {
                        }
                    });
                }
            },
        });
    }

    function onSubmitModal() {
        $.ajax({
            url: '<%=request.getContextPath()%>/service/user/delete',
            type: 'post',
            dataType: 'json',
            data: {
                userId: userId
            },
            success: function (result) {
                buttonAdd.removeAttr('disabled');
                if (0 == result.code) {
                    bootbox.alert("删除成功");
                    init();
                }
            },
            error: function () {
            }
        });
    }

</script>
</body>
</html>