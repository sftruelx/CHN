<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>字典管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- ztree JavaScript -->
    <link href="${ctx}/scripts/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <script src="${ctx}/scripts/zTree/jquery.ztree.all.min.js"></script>
    <SCRIPT type="text/javascript">
        var setting = {
            view: {
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom,
                selectedMulti: false
            },
            callback: {
                onClick: zTreeOnClick
            }

        };

        var zNodes =${dictMain};

        var modalDialog;

        var newCount = 1;
        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#editBtn_" + treeNode.tId).length > 0) return;
            /*<span class='button add' id='addBtn_" + treeNode.tId
             + "' title='增加字典类型' onfocus='this.blur();'></span>*/
            var addStr = "" +
                    "<span class='button edit' id='editBtn_" + treeNode.tId
                    + "' title='修改字典类型' onfocus='this.blur();'></span>" +
                    "<span class='button remove' id='removeBtn_" + treeNode.tId
                    + "' title='删除字典类型' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            /*var btn = $("#addBtn_" + treeNode.tId);
            if (btn) btn.bind("click", function () {
                $('#modalTitle').html("新增主字典");
                $('#id').val('');
                $('#dict_key').val('');
                $('#dict_key').removeAttr("readonly");
                $('#dict_value').val('');
                $('#dict_parent').val('');
                $('#dict_type').val('0');
                $('#dict_describe').val('');
                $('#dict_remark').val('');
                $('#submitType').val("add");
                modalDialog.modal('show');
            });*/
            var btnEdit = $("#editBtn_" + treeNode.tId);
            if (btnEdit) btnEdit.bind("click", function () {
                console.log(treeNode.id + treeNode.code + treeNode.name + treeNode.codeParent + treeNode.describe + treeNode.remark + treeNode.delabled);
                $('#modalTitle').html("修改主字典: " + treeNode.name);
                $('#id').val(treeNode.id);
                $('#dict_key').val(treeNode.code);
                $('#dict_key').prop("readonly", true);
                $('#dict_value').val(treeNode.name);
                $('#dict_value').prop("readonly", true);
                $('#dict_type').val('0');
                $('#dict_parent').val(treeNode.codeParent);
                $('#dict_describe').val(treeNode.describe);
                $('#dict_remark').val(treeNode.remark);
                $('#submitType').val("edit");
                $('input:radio[name="delabled"]').removeAttr('checked');
                $("input[name=delabled][value=" + treeNode.delabled + "]").prop("checked", true);
                modalDialog.modal('show');
            });
            var btnRemove = $("#removeBtn_" + treeNode.tId);
            if (btnRemove) btnRemove.bind("click", function () {
                console.log(treeNode.id + treeNode.code + treeNode.name + treeNode.codeParent + treeNode.describe + treeNode.remark + treeNode.delabled);
                if (treeNode.delabled === 1) {
                    bootbox.alert("标准字典不允许删除!");
                    return;
                } else {
                    $.ajax({
                        url: '${ctx}/dictionary/delDict',
                        type: 'post',
                        dataType: 'json',
                        data: {
                            id: treeNode.id,
                            dict_key: treeNode.code,
                            dict_value: treeNode.name,
                            dict_type: treeNode.type
                        },
                        success: function (req) {
                            console.log(req)
                            if (req.success == true) {
                                var treeObj = $.fn.zTree.getZTreeObj("tree");
                                treeObj.removeNode(treeNode);
                                $('#mainKey').val('');
                                $('#mainValue').val('');
                            } else {
                                bootbox.alert(req.msg);
                            }
                            modalDialog.modal('hide');
                        }
                    });
                }
            });

        }
        ;
        function removeHoverDom(treeId, treeNode) {
            /*$("#addBtn_" + treeNode.tId).unbind().remove();*/
            $("#editBtn_" + treeNode.tId).unbind().remove();
            $("#removeBtn_" + treeNode.tId).unbind().remove();
        }
        ;

        $(document).ready(function () {
            $.fn.zTree.init($("#tree"), setting, zNodes);
            var treeObj = $.fn.zTree.getZTreeObj("tree");
            var nodes = treeObj.getNodes();
            if (nodes.length > 0) {
                treeObj.selectNode(nodes[0]);
            }

            modalDialog = $("#dict-dialog").on('hide.bs.modal', function () {
                $("#dictForm").data('bootstrapValidator').resetForm();
            });

            $("#dictForm").bootstrapValidator({
                message: 'This value is not valid',
                submitButtons: 'button[type="submit"]',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    dict_key: {
                        validators: {
                            notEmpty: {
                                message: '请输入字典代码'
                            }
                        }
                    },
                    dict_value: {
                        validators: {
                            notEmpty: {
                                message: '请输入字典名称'
                            }
                        }
                    }
                }
            }).on('success.form.bv', function (e) {
                onSubmitModalMainDict();
                $("#dictForm").data('bootstrapValidator').resetForm();
            });

            $("#addDetail").click(function () {
                console.log($('#mainKey').val());
                if ($('#mainKey').val() == null || $('#mainKey').val() == "") {
                    bootbox.alert("请选择要增加的左边树的字典类型！");
                    return;
                } else {
                    $('#modalTitle').html("新增字典明细:[" + $('#mainValue').val() + "]");
                    $('#id').val('');
                    $('#dict_key').val('');
                    $('#dict_key').removeAttr("readonly");
                    $('#dict_value').val('');
                    $('#dict_value').removeAttr("readonly");
                    $('#dict_parent').val($('#mainKey').val());
                    $('#dict_type').val('1');
                    $('#dict_describe').val('');
                    $('#dict_remark').val('');
                    $('#submitType').val("add");
                    modalDialog.modal('show');
                }

            });

            $("#addMain").click(function () {
                $('#modalTitle').html("新增主字典");
                $('#id').val('');
                $('#dict_key').val('');
                $('#dict_key').removeAttr("readonly");
                $('#dict_value').val('');
                $('#dict_value').removeAttr("readonly");
                $('#dict_parent').val('');
                $('#dict_type').val('0');
                $('#dict_describe').val('');
                $('#dict_remark').val('');
                $('#submitType').val("add");
                modalDialog.modal('show');
            });


        });


        function onSubmitModalMainDict() {
            $.ajax({
                url: '${ctx}/dictionary/saveDict',
                type: 'post',
                dataType: 'json',
                data: $("#dictForm").serialize(),
                success: function (result) {
                    if (result.success == true) {
                        console.log(result)
                        if ($('#dict_type').val() == "0") {
                            if ($('#submitType').val() == "add") {
                                var treeObj = $.fn.zTree.getZTreeObj("tree");
                                var newNode = result.data.newDict;
                                console.log(result.data.newDict);

                                newNode = treeObj.addNodes(null, newNode);
                            } else {
                                var treeObj = $.fn.zTree.getZTreeObj("tree");
                                var node = treeObj.getSelectedNodes()[0];
                                node.code = result.data.newDict.code;
                                node.name = result.data.newDict.name;
                                node.describe = result.data.newDict.describe;
                                node.remark = result.data.newDict.remark;
                                node.delabled = result.data.newDict.delabled
                                treeObj.updateNode(node);
                            }
                        } else {
                            $('#table').bootstrapTable('refresh');
                        }
                    } else {
                        bootbox.alert(result.msg);
                    }
                    if(result.closed == true) {
                        modalDialog.modal('hide');
                    }
                }
            });
        }

        function zTreeOnClick(event, treeId, treeNode) {
            $('#table').bootstrapTable('refresh', {url: "${ctx}/dictionary/listDetail?dict_parent=" + treeNode.code});
            $('#mainKey').val(treeNode.code);
            $('#mainValue').val(treeNode.name);
        }
        ;

        function delabledFormatter(value, row) {
            if (row.delabled == 1) {
                return '<span style="color: green"><span class="glyphicon glyphicon-ok"></span></span>';
            } else {
                return '<span style="color: red"><span class="glyphicon glyphicon-remove"></span></span>';
            }
        }

        function otherFormatter(value, row) {
            var btnparam = "'" + row.id + "','" + row.dict_key + "','" + row.dict_value + "','" + row.dict_describe + "','" + row.dict_remark + "','" + row.delabled + "','" + row.dict_parent + "'";
            return '<button type="button"  onclick="editDetailClicked(' + btnparam + ')" class="btn btn-success btn-sm"  >修改</button>' +
                    '<span> </span><button type="button"  onclick="removeDetailClicked(' + btnparam + ')" class="btn btn-danger btn-sm"  >删除</button>';
        }

        function editDetailClicked(id, dict_key, dict_value, dict_describe, dict_remark, delabled, dict_parent) {
            console.log(id + dict_key + dict_value + dict_describe + dict_remark + delabled + "edit");
            $('#modalTitle').html("修改字典明细: " + $("#mainValue").val() + "：" + dict_value);
            $('#id').val(id);
            $('#dict_key').val(dict_key);
            $('#dict_key').prop("readonly", true);
            $('#dict_value').val(dict_value);
            $('#dict_value').prop("readonly", true);
            $('#dict_type').val('1');
            $('#dict_parent').val(dict_parent);
            $('#dict_describe').val(dict_describe !== 'null' ? dict_describe : "");
            $('#dict_remark').val(dict_remark !== 'null' ? dict_remark : "");
            $('#submitType').val("edit");
            $('input:radio[name="delabled"]').removeAttr('checked');
            $("input[name=delabled][value=" + delabled + "]").prop("checked", true);
            modalDialog.modal('show');
        }

        function removeDetailClicked(id, dict_key, dict_value, dict_describe, dict_remark, delabled) {
            console.log(id + dict_key + dict_value + dict_describe + dict_remark + delabled + "del");
            if (delabled === "1") {
                bootbox.alert("标准字典不允许删除!");
                return;
            } else {
                $.ajax({
                    url: '${ctx}/dictionary/delDict',
                    type: 'post',
                    dataType: 'json',
                    data: {id: id, dict_key: dict_key, dict_value: dict_value, dict_type: 1},
                    success: function (req) {
                        console.log(req)
                        if (req.success == true) {
                            $('#table').bootstrapTable('refresh');
                        } else {
                            bootbox.alert(req.msg);
                        }
                    }
                });
            }


        }

        function queryParams(params) {
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
                    <h3>字典管理</h3>
                </div>
                <div class="panel-body">
                    <div class="col-xs-2"
                         style="border: 1px; ;height: auto;width:200px;overflow:auto">
                        <button id="addMain" type="button" class="btn btn-info" style="margin-left: 20px;">新增字典</button>
                        <ul id="tree" class="ztree" style="height: auto;max-height: 500px;"></ul>
                    </div>

                    <div id="dict-dialog" class="modal modal_align fade bs-example-modal in"
                         tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" style="display:none;">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form id="dictForm" role="form" class="form-horizontal">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="modalTitle"></h4>
                                    </div>

                                    <div class="modal-body">
                                        <input type="hidden" id="submitType" name="submitType" value="add"/>
                                        <input type="hidden" id="dict_type" name="dict_type" value="0"/>
                                        <input type="hidden" id="id" name="id" value=""/>
                                        <input type="hidden" id="dict_parent" name="dict_parent" value=""/>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <!-- Text input-->
                                                    <label class="col-sm-3  control-label">字典代码</label>
                                                    <div class="col-sm-6 controls">
                                                        <input placeholder="请输入字典代码" class="form-control input-xlarge"
                                                               type="text" id="dict_key" name="dict_key">
                                                        <p class="help-block"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <!-- Text input-->
                                                    <label class="col-sm-3  control-label">字典名称</label>
                                                    <div class="col-sm-6 controls">
                                                        <input placeholder="请输入字典名称" class="form-control input-xlarge"
                                                               type="text" id="dict_value" name="dict_value">
                                                        <p class="help-block"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <!-- Text input-->
                                                    <label class="col-sm-3  control-label">字典描述</label>
                                                    <div class="col-sm-6 controls">
                                                        <input placeholder="请输入字典描述" class="form-control input-xlarge"
                                                               type="text" id="dict_describe" name="dict_describe">
                                                        <p class="help-block"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <!-- Text input-->
                                                    <label class="col-sm-3  control-label">字典备注</label>
                                                    <div class="col-sm-6 controls">
                                                        <input placeholder="请输入字典备注" class="form-control input-xlarge"
                                                               type="text" id="dict_remark" name="dict_remark">
                                                        <p class="help-block"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">标准字典</label>
                                                    <div class="controls col-sm-6 ">
                                                        <!-- Inline Radios -->
                                                        <label class="radio inline col-sm-3">
                                                            <input checked="checked" value="1" name="delabled"
                                                                   type="radio">
                                                            是
                                                        </label>
                                                        <label class="radio inline col-sm-3">
                                                            <input value="0" name="delabled" type="radio">
                                                            否
                                                        </label>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
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

                    <div class="col-xs-9">
                        <div id="toolbar">
                            <div class="form-inline" role="form">
                                <button id="addDetail" type="button" class="btn btn-info">新增明细</button>
                                <%--<button id="editDetail" type="button" class="btn btn-info">修改</button>
                                <button id="delDetail" type="button" class="btn btn-info">删除</button>--%>
                                <input type="hidden" id="mainKey" name="mainKey" value="${defaultDictParent}"/>
                                <input type="hidden" id="mainValue" name="mainValue" value="${defaultDictValue}"/>
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
                               data-url="${ctx}/dictionary/listDetail?dict_parent=${defaultDictParent}"
                               data-height="400"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-page-list="[10]"
                               data-sort-name="id"
                               data-sort-order="asc">
                            <thead>
                            <tr>
                                <th data-field="dict_key">字典代码</th>
                                <th data-field="dict_value">字典名称</th>
                                <th data-field="dict_describe">字典描述</th>
                                <th data-field="dict_remark">字典备注</th>
                                <th data-field="dict_parent">主字典代码</th>
                                <th data-field="delabled" data-formatter="delabledFormatter">标准字典</th>
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
