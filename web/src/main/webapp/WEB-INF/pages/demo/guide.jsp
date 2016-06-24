<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="page-wrapper">
    <br>
    <form:form action="/nurse/dispatch" name="form" method="post" target="_blank" commandName="bizPatient" class="form-horizontal">
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3>检查指引单</h3>
                    </div>
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">姓名</label>
                                <div class="col-sm-2">
                                    <form:input cssClass="form-control input-sm" path="name"/>
                                    <input name="userId" hidden type="text" value="${bizPatient.id}">
                                </div>
                                <label class="col-sm-1 control-label">性别</label>
                                <div class="col-sm-2">
                                    <form:input cssClass="form-control input-sm" path="genderStr"/>
                                </div>
                                <label class="col-sm-1 control-label">出生日期</label>
                                <div class="col-sm-2">
                                    <form:input cssClass="form-control input-sm" path="birthdayStr"/>
                                </div>
                                <label class="col-sm-1 control-label">年龄</label>
                                <div class="col-sm-2">
                                    <form:input cssClass="form-control input-sm" path="age"/>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-1 control-label">挂号科室</label>
                                <div class="col-sm-2">
                                    <form:input cssClass="form-control input-sm" path="department"/>
                                </div>
                                <label class="col-sm-1 control-label">状态</label>
                                <div class="col-sm-2">
                                    <form:input cssClass="form-control input-sm" path="statusStr"/>
                                </div>
                                <label class="col-sm-1 control-label">日期</label>
                                <div class="col-sm-2">
                                    <form:input cssClass="form-control input-sm" path="createDateStr"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-1"></div>
                                <div class="col-lg-3">

                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="1" name="item">满日体检
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="2" name="item">普通体检
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="3" name="item">骨龄测评
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="4" name="item">辅食制作
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="5" name="item">儿童早期发展
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="6" name="item">一岁儿童综合发展项目
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="7" name="item">步态分析检查
                                        </label>
                                    </div>
                                </div>
                                <div class="col-lg-3">

                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="" name="item">智护训练
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="" name="item">皮肤黄疸测定
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="" name="item">听力筛查
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="" name="item">神经行为测试
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="" name="item">骨强度检查
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="" name="item">儿童气质评定
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="">贝利婴儿量表测评
                                        </label>
                                    </div>
                                </div>
                                <div class="col-lg-3">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="">饮食行为筛查
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="">ASQ儿童发育筛查
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="">ASQ儿童行为筛查
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="">亲子游戏
                                        </label>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-lg-12">

                <button type="submit" class="btn btn-primary"
                        onclick="new_window = window.open(); gotow(${bizPatient.id} )"> 确定
                </button>
                <button type="reset" class="btn btn-success" onclick="backHome()"> 返回</button>

            </div>
            <!-- /.col-lg-12 -->
        </div>
    </form:form>
</div>
<script>

    function backHome() {
        window.location.href = '/demo/dispatch';
    }
    function gotow(id) {
        $('#form').submit;
    }
</script>
</body>
</html>
