<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>分诊管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <SCRIPT>
        var modalDialog;
        getBizStatus();
        getDepartment();
        var itemModalDialog;
        var countPrice;

        $(document).ready(function () {
            $('#triageForm').bootstrapValidator({
                message: 'This value is not valid',
                submitButtons: 'button[type="submit"]',
                /*feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },*/
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: '请输入姓名'
                            }
                        }
                    },
                    /*outpatientNo: {
                        validators: {
                            notEmpty: {
                                message: '门诊号码不能为空'
                            }
                        }
                    },*/
                    departmentCode: {
                        message: 'The username is not valid',
                        validators: {
                            notEmpty: {
                                message: '请选择科室'
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
                    birthday: {
                        validators: {
                            notEmpty: {
                                message: '请输入出生日期'
                            }, date: {
                                format: 'YYYY-MM-DD',
                                message:'日期正确格式为yyyy-mm-dd'
                            }
                        }
                    },
                    regDate: {
                        validators: {
                            notEmpty: {
                                message: '请输入挂号日期'
                            }, date: {
                                format: 'YYYY-MM-DD HH:ii:ss',
                                message:'日期正确格式为yyyy-mm-dd HH:ii:ss'
                            }
                        }
                    }
                }
            }).on('success.form.bv', function (e) {
                onSubmitModal();
            });
            modalDialog = $("#triage-dialog").on('hide.bs.modal', function () {
                $("#triageForm").data('bootstrapValidator').resetForm();
            });

            $('#searchStartDate').datetimepicker({
                minView: "month", //选择日期后，不会再跳转去选择时分秒
                format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                todayBtn: true,
                autoclose: true //选择日期后自动关闭
            }).on('changeDate', function(ev) {
                $('#table').bootstrapTable('refresh');
            });

            itemModalDialog = $("#item-dialog").on('hide.bs.modal', function () {});

            $('#searchEndDate').datetimepicker({
                minView: "month", //选择日期后，不会再跳转去选择时分秒
                format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                todayBtn: true,
                autoclose: true //选择日期后自动关闭
            }).on('changeDate', function(ev) {
                $('#table').bootstrapTable('refresh');
            });

            $('#birthday').datetimepicker({
                minView: "month", //选择日期后，不会再跳转去选择时分秒
                format: "yyyy-mm-dd HH:ii:ss", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                todayBtn: true,
                autoclose: true //选择日期后自动关闭
            });

            $('#regDate').datetimepicker({
                minView: "month", //选择日期后，不会再跳转去选择时分秒
                format: "yyyy-mm-dd HH:ii:ss", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                todayBtn: true,
                autoclose: true //选择日期后自动关闭
            });

            $('#addTriage').click(function () {
                $("input[type=reset]").trigger("click");
                modalDialog.modal('show');
            });

            $('#addPatient').click(function () {
                $("input[type=reset]").trigger("click");
            });

            $('#searchPatient').click(function () {
                if($('#searchNo').val()==null||$('#searchNo').val()==""){
                    bootbox.alert("单号不能为空");
                    return;
                }
                $.ajax({
                    url: '${ctx}/triage/getPatient?searchNo='+$('#searchNo').val(),
                    type: 'post',
                    dataType: 'json',
                    success: function (result) {
                        if (result.success == true) {
                            console.log(result)
                            $('#name').val(result.data.patient.name);
                            $('#gender').val(result.data.patient.gender);
                            if(result.data.patient.birthday!=null){
                            $('#birthday').val(GetStringLocalDate(new Date(result.data.patient.birthday)).substring(0,10));}
                            $('#cardId').val(result.data.patient.cardId);
                            $('#medicareCard').val(result.data.patient.medicareCard);
                            $('#mobile').val(result.data.patient.mobile);
                            $('#outpatientNo').val(result.data.patient.outpatientNo);
                            if(result.data.patient.regDate!=null){
                            $('#regDate').val(GetStringLocalDate(new Date(result.data.patient.regDate)).substring(0,10));}
                            $('#departmentCode').val(result.data.patient.departmentCode);
                            $('#mobile').val(result.data.patient.mobile);
                        } else {
                            bootbox.alert(result.msg+"");
                        }
                    }
                });
            });

            $("input:checkbox").change(function() {
                countPrice=0.00;
                $('input:checkbox[name=item]:checked').each(function(i){
                    countPrice = (Number(countPrice)+Number($(this).attr("price"))).toFixed(2);
                });
                $("#countPrice").text(countPrice);
            });


            $('#saveTriageDetailBtn').click(function () {
                var item =new Array();
                $('input:checkbox[name=item]:checked').each(function(i){
                    item.push($(this).val());
                });
                if(item.length == 0){
                    bootbox.alert("请选择至少一项项目！");
                    return;
                }
                $.ajax({
                    url: '${ctx}/triage/saveTriageItem',
                    dataType: 'json',
                    data: {triageNo:$("#ttriageNo").val(),item:item.toString(),outpatientNo:$("#toutpatientNo").val(),gender:$("#tgender").val(),name:$("#tname").val(),submitType:$("#itemSubmitType").val()},
                    success: function (result) {
                        if (result.success == true) {
                            //console.log(result);
                            itemModalDialog.modal('hide');
                            $('#table').bootstrapTable('refresh');
                            //打开打印页面打印 todo
                        } else {
                            bootbox.alert(result.msg+"");
                        }
                        /*if(result.closed == true) {
                         modalDialog.modal('hide');
                         }*/
                    }
                });
            });


        });

        function onSubmitModal() {
            console.log("hide");
            $('#triage-dialog').modal('hide')
            $.ajax({
                url: '${ctx}/triage/saveTriageData',
                type: 'post',
                dataType: 'json',
                data: $("#triageForm").serialize(),
                success: function (result) {
                    if (result.success == true) {
                        console.log(result)
                        modalDialog.modal('hide');
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

        function getBizStatus() {
            $.ajax({
                url: '/triage/getBizStatus',
                async: true,
                dataType: 'json',
                type: 'PUT',
                success: function (data, textStatus) {
                    $.each(data, function (i, item) {
                        $('#searchStatus').append("<option value='" + item.dict_key + "'>" + item.dict_value + "</option>");
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error");
                }
            });
        }

        function getDepartment() {
            $.ajax({
                url: '/triage/getDepartment',
                async: true,
                dataType: 'json',
                type: 'PUT',
                success: function (data, textStatus) {
                    $.each(data, function (i, item) {
                        $('#departmentCode').append("<option value='" + item.dict_key + "'>" + item.dict_value + "</span></option>");
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error");
                }
            });
        }

        function statusFormatter(value, row) {
            if (row.status == 1) {
                return '<span style="color:#337ab7"><span class="glyphicon glyphicon-sort-by-attributes"> 已挂号</span></span>';
            } else if (row.status == 2) {
                return '<span style="color: #337ab7"><span class="glyphicon glyphicon-pencil"> 已开单</span></span>';
            } else if (row.status == 3) {
                return '<span style="color: #5bc0de"><span class="glyphicon glyphicon-indent-left"> 已分诊</span></span>';
            } else if (row.status == 4) {
                return '<span style="color: #5bc0de"><span class="glyphicon glyphicon-plus-sign"> 就诊中</span></span>';
            } else if (row.status == 5) {
                return '<span style="color: #5cb85c"><span class="glyphicon glyphicon-saved"> 已完成</span></span>';
            } else if (row.status == 6) {
                return '<span style="color: #5cb85c"><span class="glyphicon glyphicon-list-alt"> 已报告</span></span>';
            } else if (row.status == 0) {
                return '<span style="color: red"><span class="glyphicon glyphicon-trash"> 作废</span></span>';
            }

        }

        function selectItem(id,triageNo,outpatientNo,name,gender) {
            $("input[type=reset]").trigger("click");
            $('[name=item]:checkbox').attr("checked", false);
            $("#countPrice").text("");
            $('#ttriageNo').val(triageNo);
            $('#toutpatientNo').val(outpatientNo);
            $('#tname').val(name);
            $('#tgender').val(gender);
            $("#itemSubmitType").val("add")
            $('#itemModalTitle').html("开检查项目");
            itemModalDialog.modal('show');
        }

        function cancelTriage(id,triageNo) {
            $.ajax({
                url: '/triage/cancelTriage?id='+id+"&triageNo="+triageNo,
                async: true,
                dataType: 'json',
                type: 'POST',
                success: function (result) {
                    if (result.success == true) {
                        console.log(result)
                        $('#table').bootstrapTable('refresh');
                    } else {
                        bootbox.alert(result.msg+"");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error");
                }
            });
        }

        function doTriage(id,triageNo) {
            $.ajax({
                url: '/triage/doTriage?id='+id+"&triageNo="+triageNo,
                async: true,
                dataType: 'json',
                type: 'POST',
                success: function (result) {
                    if (result.success == true) {
                        console.log(result)
                        $('#table').bootstrapTable('refresh');
                    } else {
                        bootbox.alert(result.msg+"");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error");
                }
            });
        }

        function reSelectItem(id,triageNo,outpatientNo,name,gender) {
            $("input[type=reset]").trigger("click");
            $('[name=item]:checkbox').attr("checked", false);
            $("#countPrice").text("");
            $('#ttriageNo').val(triageNo);
            $('#toutpatientNo').val(outpatientNo);
            $('#tname').val(name);
            $('#tgender').val(gender);
            $("#itemSubmitType").val("reAdd");//重开单标志
            $('#itemModalTitle').html("重开检查项目");
            itemModalDialog.modal('show');
        }

        function searchTriage(id,triageNo) {

        }

        function printTriage(id,triageNo) {

        }

        function reBackTriage(id,triageNo) {
            $.ajax({
                url: '/triage/reBackTriage?id='+id+"&triageNo="+triageNo,
                async: true,
                dataType: 'json',
                type: 'POST',
                success: function (result) {
                    if (result.success == true) {
                        console.log(result)
                        $('#table').bootstrapTable('refresh');
                    } else {
                        bootbox.alert(result.msg+"");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error");
                }
            });
        }

        function otherFormatter(value, row) {
            var btnparam = "'" + row.id + "','" + row.triageNo +"'";
            var btnparam2 = "'" + row.id + "','" + row.triageNo+ "','" + row.outpatientNo+ "','" + row.name+ "','" + row.gender +"'";
            if (row.status == 1) {
                return '<button type="button"  onclick="selectItem(' + btnparam2 + ')" class="btn btn-primary btn-sm"  ><span><span class="glyphicon glyphicon-pencil"> 开单</button>  ' +
                        '<button type="button"  onclick="cancelTriage(' + btnparam + ')" class="btn btn-danger btn-sm"  ><span><span class="glyphicon glyphicon-trash"> 作废</button>';
            } else if (row.status == 2) {
                return '<button type="button"  onclick="doTriage(' + btnparam + ')" class="btn btn-primary btn-sm"  ><span><span class="glyphicon glyphicon-indent-left"> 分诊</button>  ' +
                        '<button type="button"  onclick="reSelectItem(' + btnparam2 + ')" class="btn btn-warning btn-sm"  ><span><span class="glyphicon glyphicon-pencil"> 重开单</button>';
            } else if (row.status == 3||row.status==4) {
                return '<button type="button"  onclick="searchTriage(' + btnparam + ')" class="btn btn-info btn-sm"  ><span><span class="glyphicon glyphicon-heart-empty"> 查询</button>';
            } else if (row.status == 5||row.status==6) {
                return '<button type="button"  onclick="printTriage(' + btnparam + ')" class="btn btn-success btn-sm"  ><span><span class="glyphicon glyphicon-print"> 打印</button>';
            }else if (row.status == 0) {
                return '<button type="button"  onclick="reBackTriage(' + btnparam + ')" class="btn btn-danger btn-sm"  ><span><span class="glyphicon glyphicon-share-alt"> 撤回</button>';
            }

        }

        function departmentChange() {
            $("#departmentName").val($("#departmentCode option:selected").text());
        }

        function queryParams(params) {
            params["regStartDate"] = $('#searchStartDate').val();
            params["regEndDate"] = $('#searchEndDate').val();
            params["status"] = $('#searchStatus').val();
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
                    <h3>分诊台</h3>
                </div>
                <div class="panel-body">
                      <div id="triage-dialog" class="modal modal_align fade bs-example-modal-lg"
                         tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" style="display:none;">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content" style="width: 112%">
                                <form id="triageForm" role="form" class="form-horizontal"
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
                                                <div class="list-group">


                                                    <a class="list-group-item">
                                                        <div class="form-group">
                                                            <label class="col-sm-1  control-label" style="font-size: 12px;">就诊号:</label>
                                                            <div class="col-sm-3">
                                                                <input placeholder="挂号单号/门诊号码" class="form-control input-xlarge"  type="text" id="searchNo" name="searchNo"
                                                                       data-bv-notempty="true" data-bv-notempty-message="">
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <button type="button" class="btn btn-default" id="searchPatient" name="searchPatient">
                                                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 获取信息</button>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <button type="button" class="btn btn-default" id="addPatient" name="searchPatient">
                                                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增信息</button>
                                                            </div>
                                                        </div>
                                                    </a>
                                                    <a href="#" class="list-group-item active">
                                                        <h4 class="list-group-item-heading">
                                                            基本信息
                                                        </h4>
                                                    </a>
                                                      <a class="list-group-item">
                                                          <div class="form-group">
                                                          <!-- Text input-->
                                                              <label class="col-sm-1  control-label" style="font-size: 12px;">姓名:</label>
                                                              <div class="col-sm-3">
                                                                  <input placeholder="" class="form-control input-xlarge"  type="text" id="name" name="name"
                                                                         data-bv-notempty="true" data-bv-notempty-message="姓名不允许为空！">
                                                              </div>
                                                              <label class="col-sm-1  control-label" style="font-size: 12px;">性别:</label>
                                                              <div class="col-sm-3">
                                                                  <select name="gender" class="form-control" id="gender">
                                                                      <option value="1">男</option>
                                                                      <option value="2">女</option>
                                                                      <option value="9" selected>未知</option>
                                                                  </select>
                                                              </div>
                                                              <label for="birthday" class="col-sm-1 control-label" style="font-size: 12px;">出生年月:</label>
                                                              <div class="input-append birthday form_datetime col-sm-3">
                                                                  <input name="birthday" id="birthday" class="form-control "  type="text">
                                                              </div>

                                                          </div>
                                                          <div class="form-group">
                                                              <!-- Text input-->
                                                              <label class="col-sm-1  control-label" style="font-size: 12px;">身份证:</label>
                                                              <div class="col-sm-3">
                                                                  <input placeholder="" class="form-control input-xlarge"  type="text" id="cardId" name="cardId">
                                                              </div>
                                                              <label class="col-sm-1  control-label" style="font-size: 12px;">医保卡:</label>
                                                              <div class="col-sm-3">
                                                                  <input placeholder="" class="form-control input-xlarge"  type="text" id="medicareCard" name="medicareCard">
                                                              </div>
                                                              <label class="col-sm-1  control-label" style="font-size: 12px;">手机号:</label>
                                                              <div class="col-sm-3">
                                                                  <input placeholder="" class="form-control input-xlarge"  type="text" id="mobile" name="mobile">
                                                              </div>

                                                          </div>
                                                      </a>
                                                    <a href="#" class="list-group-item active">
                                                        <h4 class="list-group-item-heading">
                                                            挂号信息
                                                        </h4>
                                                    </a>
                                                    <a class="list-group-item">
                                                        <div class="form-group">
                                                            <label class="col-sm-1  control-label" style="font-size: 12px;">门诊号:</label>
                                                            <div class="col-sm-3">
                                                                <input placeholder="" class="form-control input-xlarge"  type="text" id="outpatientNo" name="outpatientNo" readonly>
                                                            </div>
                                                            <label class="col-sm-1  control-label" style="font-size: 12px;">挂号科室:</label>
                                                            <div class="col-sm-3">
                                                                <input type="hidden" id="departmentName" name="departmentName" value=""/>
                                                                <select name="departmentCode" class="form-control" id="departmentCode" onchange="departmentChange()">
                                                                    <option value="" selected></option>
                                                                </select>
                                                            </div>
                                                            <label for="regDate" class="col-sm-1 control-label" style="font-size: 12px;">挂号日期:</label>
                                                            <div class="input-append regDate form_datetime col-sm-3">
                                                                <input name="regDate" id="regDate" class="form-control "  type="text">
                                                            </div>
                                                        </div>
                                                    </a>

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
                        <div id="toolbar" class="col-sm-12">
                            <div class="form-inline" role="form" >
                                <button id="addTriage" type="button" class="btn btn-info">新增</button>
                                <div class="form-group">
                                    <div class="date col-sm-5" data-date-format="yyyy-mm-dd">
                                        <label class="control-label" >开始日期:</label>
                                        <input id="searchStartDate"  name="searchStartDate" class="form-control input-xlarge" type="text" value="${searchDate}" readonly>
                                    </div>
                                    <div class="date col-sm-5" data-date-format="yyyy-mm-dd">
                                        <label class="control-label" >结束日期:</label>
                                        <input id="searchEndDate"  name="searchEndDate" class="form-control input-xlarge" type="text" value="${searchDate}" readonly>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="searchStatus" name="searchStatus" class="form-control" onchange="$('#table').bootstrapTable('refresh');">
                                            <option value="" selected>全部</option>
                                        </select>
                                    </div>
                                </div>
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
                               data-url="${ctx}/triage/listDetail"
                               data-height="400"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-page-list="[10]"
                               data-sort-name="id"
                               data-sort-order="desc">
                            <thead>
                                <tr>
                                    <th data-field="triageNo">就诊流水号</th>
                                    <th data-field="outpatientNo">门诊号码</th>
                                    <th data-field="name">姓名</th>
                                    <th data-field="gender">性别</th>
                                    <th data-field="departmentName">挂号科室</th>
                                    <th data-field="regDateStr">挂号日期</th>
                                    <th data-field="triageDateStr">分诊日期</th>
                                    <th data-field="operatorName">操作员</th>
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

    <div id="item-dialog" class="modal modal_align fade bs-example-modal-lg"
         tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" style="display:none;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content" style="width: 112%">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="itemModalTitle">检查项目指引申请单</h4>
                    </div>

                    <div class="modal-body form-horizontal">
                        <input type="hidden" id="itemSubmitType" name="itemSubmitType" value="add"/>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="list-group">
                                    <a href="#" class="list-group-item active">
                                        <h4 class="list-group-item-heading">
                                            基本信息
                                        </h4>
                                    </a>
                                    <a class="list-group-item">

                                        <div class="form-group">
                                            <!-- Text input-->
                                            <label class="col-sm-2  control-label" style="font-size: 12px;">流水号:</label>
                                            <div class="col-sm-3">
                                                <input placeholder="" class="form-control input-xlarge"  type="text" id="ttriageNo" name="ttriageNo" readonly>
                                            </div>
                                            <label class="col-sm-2  control-label" style="font-size: 12px;">门诊号码:</label>
                                            <div class="col-sm-3">
                                                <input placeholder="" class="form-control input-xlarge"  type="text" id="toutpatientNo" name="toutpatientNo" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2  control-label" style="font-size: 12px;">姓名:</label>
                                            <div class="col-sm-3">
                                                <input placeholder="" class="form-control input-xlarge"  type="text" id="tname" name="tname" readonly
                                                       data-bv-notempty="true" data-bv-notempty-message="姓名不允许为空！">
                                            </div>
                                            <label class="col-sm-2  control-label" style="font-size: 12px;">性别:</label>
                                            <div class="col-sm-3">
                                                <select name="tgender" class="form-control" id="tgender" disabled>
                                                    <option value="1">男</option>
                                                    <option value="2">女</option>
                                                    <option value="9" selected>未知</option>
                                                </select>
                                            </div>

                                        </div>
                                    </a>
                                    <a href="#" class="list-group-item active">
                                        <h4 class="list-group-item-heading">
                                            检查项
                                        </h4>
                                    </a>
                                    <a class="list-group-item">
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <c:forEach var="bizItems" items="${bizItem}" varStatus="status">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" value="${bizItems.itemCode}" price="${bizItems.feePrice}" name="item">${bizItems.feeCode}:${bizItems.itemName}
                                                        <span style="color: red">  ${bizItems.feePrice}</span>
                                                    </label>
                                                </div>
                                                <c:if test="${status.count % 8 eq 0||status.count eq 8}">
                                            </div>
                                            <div class="col-lg-4">
                                                </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </a>
                                    <a class="list-group-item">
                                        <div class="row">
                                            <div class="col-lg-3">总额：<span style="color: red" id="countPrice"></span></div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>


                        <div class="modal-footer">
                            <input type="reset" style="display: none">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消
                            </button>
                            <button id="saveTriageDetailBtn" class="btn btn-primary">提交
                            </button>
                        </div>

                    </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
