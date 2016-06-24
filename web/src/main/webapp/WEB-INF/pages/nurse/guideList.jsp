<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>打印检查指引单</title>
    <style>
        .input_text {
            background-color: #efefef;
            border-color: #000000;
            border-style: solid;
            border-top-width: 0px;
            border-right-width: 0px;
            border-bottom-width: 1px;
            border-left-width: 0px;
        }
    </style>
    <link href="${ctx}/scripts/demo/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/scripts/demo/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/scripts/demo/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div id="page-wrapper">
    <!-- /.row -->
    <form:form action="#" method="post" commandName="bizPatient" class="form-horizontal">
        <div class="text-center">
            <img src="/images/logo-01.png">
        </div>
        <div class="row text-center">
            <div>
                <h3 class="page-header">打印检查指引单</h3>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel-heading">
                    <div class="form-group">
                        <label class="col-xs-4 col-sm-4 control-label">姓名：</label>
                        <div class="col-xs-2 col-sm-2">
                            <label class="control-label">${bizPatient.name}</label>
                        </div>
                        <label class="col-xs-2 col-sm-2 control-label">性别：</label>
                        <div class="col-xs-2 col-sm-2">
                            <label class="control-label">${bizPatient.genderStr}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-4 col-sm-4 control-label">年龄：</label>
                        <div class="col-xs-2 col-sm-2">
                            <label class="control-label"> ${bizPatient.age}</label>
                        </div>
                        <label class="col-xs-2 col-sm-2 control-label">挂号科室：</label>
                        <div class="col-xs-2 col-sm-4">
                            <label class="control-label"> ${bizPatient.department}</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-4 col-sm-4 control-label">状态：</label>
                        <div class="col-sm-2">
                            <label class="control-label">${bizPatient.statusStr}</label>
                        </div>
                        <label class="col-xs-2 col-sm-2 control-label">日期：</label>
                        <div class="col-sm-4">
                            <label class="control-label">${bizPatient.createDateStr}</label>
                        </div>
                    </div>

                    <div style="height: 50px"></div>
                    <div class="form-group">
                        <label class="col-xs-4 col-sm-4 control-label">项目：</label>
                        <div class="col-xs-2 col-sm-2">
                            <label class="control-label"><input type="checkbox" checked="checked">智护训练</label>
                        </div>
                        <label class="col-xs-2 col-sm-2 control-label">诊室：</label>
                        <div class="col-xs-4 col-sm-4">
                            <label class="control-label">212诊室</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-6 col-sm-4 control-label">护士：</label>
                        <div class="col-xs-6 col-sm-4">
                            <label class="control-label">赵敏</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-6 col-sm-4 control-label">备注：</label>
                        <div class="col-xs-6 col-sm-6">
                            <label class="control-label">212诊室在本栋楼2楼212房，无特殊事项</label>
                        </div>
                    </div>


                    <div class="form-group">
                        <div style="height: 50px"></div>
                        <label class="col-xs-4 col-sm-4 control-label">申请人：</label>
                        <div class="col-xs-2 col-sm-2">
                            <label class="control-label">小昭</label>
                        </div>
                        <label class="col-xs-2 col-sm-2 control-label">时间：</label>
                        <div class="col-xs-4 col-sm-4">
                            <label class="control-label">${dateStr}</label>
                        </div>
                    </div>
                    <div style="height: 50px"></div>
                </div>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>
