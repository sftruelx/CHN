<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>检查项目管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <SCRIPT>
        var modalDialog;
        $(document).ready(function () {
            $('#bizItemForm').bootstrapValidator().on('success.form.bv', function (e) {
                onSubmitModal();
                $("#bizItemForm").data('bootstrapValidator').resetForm();
            });;
            modalDialog = $("#bizItem-dialog").on('hide.bs.modal', function () {
                $(this).removeData("bs.modal");
                $("#bizItemForm").data('bootstrapValidator').resetForm();
            });


            $("#addBizItem").click(function () {
                    $("input[type=reset]").trigger("click");
                    $('#modalTitle').html("新增检查项目");
                    $('#id').val('');
                    $('#itemCode').val('');
                    $('#itemCode').removeAttr("readonly");
                    $('#itemName').val('');
                    $('#itemName').removeAttr("readonly");
                    $('#itemAddress').val('');
                    $('#feeCode').val('');
                    $('#feePrice').val('');
                    $('#typeItems').val('');
                    $('#itemtype').val('0');
                    $('#itemClass').val('1');
                    $('#suggestion').val('');
                    $('#itemGuide').val('');
                    $('#remark').val('');
                    $('#submitType').val("add");
                    modalDialog.modal('show');
            });

            $("#search").click(function () {
                //$('#table').bootstrapTable('refresh', {url: "${ctx}/dictionary/listDetail?itemCode=" + $('#pCode').val()+"&itemName="+$('#pName').val()});
                $('#table').bootstrapTable('refresh');
            });

        });

        function onSubmitModal() {
            $.ajax({
                url: '${ctx}/bizItem/saveBizItem',
                type: 'post',
                dataType: 'json',
                data: $("#bizItemForm").serialize(),
                success: function (result) {
                    if (result.success == true) {
                        console.log(result)
                        $('#table').bootstrapTable('refresh');
                    } else {
                        bootbox.alert(result.msg+"");
                    }
                    if(result.closed == true) {
                        modalDialog.modal('hide');
                    }
                }
            });
        }

        function typeFormatter(value, row) {
            if (row.itemType == 1) {
                return '<span style="color: green"><span class="glyphicon glyphicon-ok"></span></span>';
            } else {
                return '<span style="color: red"><span class="glyphicon glyphicon-remove"></span></span>';
            }
        }

        function classFormatter(value, row) {
            if (row.itemClass == 1) {
                return '<span style="color:green "><span class="glyphicon glyphicon-user">医生</span></span>';
            } else {
                return '<span style="color: red"><span class="glyphicon glyphicon-user">护士</span></span>';
            }
        }

        function otherFormatter(value, row) {
            var btnparam = "'" + row.id + "','" + row.itemCode +"'";
            return '<button type="button"  onclick="editDetailClicked(' + btnparam + ')" class="btn btn-success btn-sm"  >修改</button>';
        }

        function editDetailClicked(id,itemCode) {
            $.ajax({
                url: '${ctx}/bizItem/getBizItem',
                type: 'post',
                dataType: 'json',
                data: {itemCode:itemCode},
                success: function (result) {
                    if (result.success == true) {
                        console.log(result)

                        $('#modalTitle').html("修改检查项目");
                        $('#id').val(result.data.bizItem.id);
                        $('#itemCode').val(result.data.bizItem.itemCode);
                        $('#itemCode').prop("readonly", true);
                        $('#itemName').val(result.data.bizItem.itemName);
                        $('#itemName').prop("readonly", true);
                        $('#itemAddress').val(result.data.bizItem.itemAddress);
                        $('#feeCode').val(result.data.bizItem.feeCode);
                        $('#feePrice').val(result.data.bizItem.feePrice);
                        $('#typeItems').val(result.data.bizItem.typeItems);
                        $('#itemtype').val(result.data.bizItem.itemType);
                        $('#itemClass').val(result.data.bizItem.itemClass);
                        $('#suggestion').val(result.data.bizItem.suggestion);
                        $('#itemGuide').val(result.data.bizItem.itemGuide);
                        $('#remark').val(result.data.bizItem.remark);
                        $('#submitType').val("edit");
                        modalDialog.modal('show');
                    } else {
                        bootbox.alert(result.msg+"");
                    }
                }
            });
        }

        function queryParams(params) {
            params["itemValue"] = $('#pCode').val();
            return params;
        }

    </SCRIPT>
</head>
<body>
<div id="page-wrapper">
    <br>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>检查项目管理</h3>
                </div>
                <div class="panel-body">
                      <div id="bizItem-dialog" class="modal modal_align fade bs-example-modal-lg"
                         tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" style="display:none;">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <form id="bizItemForm" role="form" class="form-horizontal"
                                      data-bv-message="校验不通过"
                                      data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
                                      data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
                                      data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="modalTitle"></h4>
                                    </div>

                                    <div class="modal-body">
                                        <input type="hidden" id="submitType" name="submitType" value="add"/>
                                        <input type="hidden" id="id" name="id" value=""/>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <!-- Text input-->
                                                    <label class="col-sm-2  control-label">项目代码</label>
                                                    <div class="col-sm-3">
                                                        <input placeholder="" class="form-control input-xlarge"  type="text" id="itemCode" name="itemCode"
                                                               data-bv-notempty="true" data-bv-notempty-message="项目代码不允许为空！">
                                                        <p class="help-block"></p>
                                                    </div>
                                                    <label class="col-sm-2  control-label">项目名称</label>
                                                    <div class="col-sm-4">
                                                        <input placeholder="" class="form-control input-xlarge"  type="text" id="itemName" name="itemName"
                                                               data-bv-notempty="true" data-bv-notempty-message="项目名称不允许为空！">
                                                        <p class="help-block"></p>
                                                    </div>

                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2  control-label">收费编码 </label>
                                                    <div class="col-sm-3">
                                                        <input placeholder="" class="form-control input-xlarge"  type="text" id="feeCode" name="feeCode">
                                                        <p class="help-block"></p>
                                                    </div>
                                                    <label class="col-sm-2  control-label">收费价格 </label>
                                                    <div class="col-sm-4">
                                                        <input placeholder="" class="form-control input-xlarge"  type="text" id="feePrice" name="feePrice"
                                                               pattern="^\d+(\.\d+)?$"
                                                               data-bv-regexp-message="只能输入数字金额" >
                                                        <p class="help-block"></p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2  control-label">项目类别 </label>
                                                    <div class="col-sm-3">
                                                        <select id="itemClass" name="itemClass" class="form-control">
                                                            <option value="1">医生项目</option>
                                                            <option value="2" selected>护士项目</option>
                                                        </select>
                                                        <p class="help-block"></p>
                                                    </div>
                                                    <label class="col-sm-2  control-label">诊室号码 </label>
                                                    <div class="col-sm-4">
                                                        <input placeholder="" class="form-control input-xlarge"  type="text" id="itemAddress" name="itemAddress">
                                                        <p class="help-block"></p>
                                                    </div>

                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2  control-label">是否套餐 </label>
                                                    <div class="col-sm-3">
                                                        <select id="itemType" name="itemType" class="form-control">
                                                            <option value="0" selected>项目</option>
                                                            <option value="1">套餐</option>
                                                        </select>
                                                        <p class="help-block"></p>
                                                    </div>
                                                    <label class="col-sm-2  control-label">包含项目 </label>
                                                    <div class="col-sm-4">
                                                        <input placeholder="录入项目代码，多个以 , 号隔开" class="form-control input-xlarge"  type="text" id="typeItems" name="typeItems">
                                                        <p class="help-block"></p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2  control-label">项目指引 </label>
                                                    <div class="col-sm-9">
                                                        <input placeholder="项目指引说明" class="form-control input-xlarge"  type="text" id="itemGuide" name="itemGuide">
                                                        <p class="help-block"></p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2  control-label">建议 </label>
                                                    <div class="col-sm-9">
                                                        <input placeholder="录入项目异常时的一般建议意见" class="form-control input-xlarge"  type="text" id="suggestion" name="suggestion">
                                                        <p class="help-block"></p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2  control-label">备注 </label>
                                                    <div class="col-sm-9">
                                                        <input placeholder="" class="form-control input-xlarge"  type="text" id="remark" name="remark">
                                                        <p class="help-block"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="modal-footer">
                                            <input type="reset" style="display: none">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">取消
                                            </button>
                                            <button type="submit" class="btn btn-primary">提交
                                            </button>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12">
                        <div id="toolbar">
                            <div class="form-inline" role="form">
                                <button id="addBizItem" type="button" class="btn btn-info">新增</button>
                                <%--<button id="editDetail" type="button" class="btn btn-info">修改</button>
                                <button id="delDetail" type="button" class="btn btn-info">删除</button>--%>
                                <div class="form-group">
                                    <input name="pCode" id="pCode" class="form-control" type="text" placeholder="输入编码或名称">
                                </div>
                                <button id="search" type="submit" class="btn btn-info">查询</button>
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
                               data-url="${ctx}/bizItem/listDetail"
                               data-height="400"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-page-list="[10]"
                               data-sort-name="id"
                               data-sort-order="asc">
                            <thead>
                                <tr>
                                    <th data-field="itemCode">项目编码</th>
                                    <th data-field="itemName">项目名称</th>
                                    <th data-field="feeCode">收费编码</th>
                                    <th data-field="feePrice">收费价格</th>
                                    <th data-field="itemAddress">诊室</th>
                                    <th data-field="itemType" data-formatter="typeFormatter">是否套餐</th>
                                    <th data-field="itemClass" data-formatter="classFormatter">项目类别</th>
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
</body>
</html>
