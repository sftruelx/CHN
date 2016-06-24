<%@ page import="org.springframework.security.core.context.SecurityContext" %>
<%@ page import="com.mycompany.model.sys.SysUser" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.mycompany.model.sys.SysRightComparator" %>
<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><decorator:title/><fmt:message key="webapp.name"/></title>

    <!-- Bootstrap Core CSS -->
    <link href="${ctx}/scripts/demo/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/scripts/demo/css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="${ctx}/scripts/demo/css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="${ctx}/scripts/bootselect/css/bootstrap-select.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="${ctx}/scripts/demo/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${ctx}/scripts/demo/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${ctx}/scripts/demo/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">

    <link rel="stylesheet" href="${ctx}/scripts/assets/bootstrap-table/src/bootstrap-table.css">

    <link rel="stylesheet" href="${ctx}/scripts/assets/examples.css">


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>

    <![endif]-->
    <!-- jQuery -->
    <script src="${ctx}/scripts/demo/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/scripts/demo/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/scripts/demo/js/bootstrapValidator.min.js"></script>
    <script src="${ctx}/scripts/demo/js/language/zh_CN.js"></script>
    <script src="${ctx}/scripts/bootselect/js/bootstrap-select.min.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="${ctx}/scripts/demo/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${ctx}/scripts/demo/dist/js/sb-admin-2.js"></script>

    <!-- 日期控件 JavaScript -->
    <script src="${ctx}/scripts/demo/js/bootstrap-datetimepicker.js"></script>
    <script src="${ctx}/scripts/demo/js/bootstrap-datetimepicker.zh-CN.js"></script>

    <script src="${ctx}/scripts/bootbox.min.js"></script>
    <script src="${ctx}/scripts/assets/bootstrap-table/src/bootstrap-table.js"></script>
    <%--中文资源包--%>
    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- 日期格式 JavaScript -->
    <script src="${ctx}/scripts/demo/js/timeUtils.js"></script>

    <decorator:head/>
</head>
<body>
<div id="wrapper">
    <div id="reset-dialog" class="modal modal_align" style="display:none;"
         tabindex="-1"
         role="dialog"
         aria-labelledby="myLargeModalLabel" style="display:none;">
        <div class="modal-dialog modal-lg" style="width:45%">

            <div class="modal-content">
                <form id="resetForm" role="form" class="form-horizontal">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="resetModalTitle"></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">旧密码</label>
                                    <div class="col-sm-7">
                                        <input type="text" hidden name="userId" id="userId" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.id}"/>
                                        <input type="password" class="form-control" name="oldPassword" id="oldPassword"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">新密码</label>
                                    <div class="col-sm-7">
                                        <input type="password" class="form-control" name="newPassword" id="newPassword"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">再次确认</label>
                                    <div class="col-sm-7">
                                        <input type="password" class="form-control" name="reconfirm" id="reconfirm"/>
                                    </div>
                                </div>
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
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <%--<img src="${ctx}/images/logo-01.png" style="width: 20%"/>--%>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-messages">
                    <li>
                        <img src="/images/erweima.png"/>
                    </li>
                    <li class="divider"></li>
                </ul>
                <!-- /.dropdown-messages -->
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-tasks">
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 1</strong>
                                    <span class="pull-right text-muted">40% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                        <span class="sr-only">40% Complete (success)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 2</strong>
                                    <span class="pull-right text-muted">20% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                        <span class="sr-only">20% Complete</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 3</strong>
                                    <span class="pull-right text-muted">60% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                        <span class="sr-only">60% Complete (warning)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 4</strong>
                                    <span class="pull-right text-muted">80% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                        <span class="sr-only">80% Complete (danger)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Tasks</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-tasks -->
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-alerts">
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-comment fa-fw"></i> New Comment
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                <span class="pull-right text-muted small">12 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-envelope fa-fw"></i> Message Sent
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-tasks fa-fw"></i> New Task
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Alerts</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-alerts -->
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username} <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <security:authorize ifNotGranted="ROLE_USERsdfsd">
                    <li><a href="/home/homeUserEdit"><i class="fa fa-user fa-fw"></i> 用户信息</a>
                    </li>
                    </security:authorize>
                    <li><a href="#" onclick="showResetClicked()"><i class="fa fa-gear fa-fw"></i> 修改密码 </a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="/logout"><i class="fa fa-sign-out fa-fw"></i> 注销</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="#"><i class="fa fa-dashboard fa-fw"></i><b> 功能列表</b></a>
                    </li>
                    <c:forEach var="ri"  items="${sessionScope.MY_MENU}">
                   <%-- <c:forEach var="ri"  items="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.sysRole.sysRights}">--%>
                        <c:if test="${ri.parentId ==null && ri.menuCode != ''}" >
                            <li>
                                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> ${ri.rightText} <span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">

                                    <c:forEach var="item" items="${sessionScope.MY_MENU}">
                                        <c:if test="${item.parentId == ri.id && item.menuCode != '' }">
                                            <li>
                                                <a href="${item.rightUrl}">${item.rightText} </a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                 </ul>
                            </li>
                        </c:if>
                    </c:forEach>

                    <li>
                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 护士站 <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li class="active">
                                <a href="/children/childrenManagePage">儿童档案管理</a>
                            </li>
                            <li>
                                <a href="/demo/dispatch">分诊台</a>
                            </li>
                            <li>
                                <a href="/sys/user">用户管理</a>
                            </li>
                            <li>
                                <a href="/sys/role">角色管理</a>
                            </li>
                            <li>
                                <a href="/sys/right">权限管理</a>
                            </li>
                            <li>
                                <a href="/dictionary/list">字典管理</a>
                            </li>
                            <li>
                                <a href="/demo/trainning">智护训练</a>
                            </li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>

                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <!-- Page Content -->


    <decorator:body/>


</div>
<!-- /#wrapper -->
<script>

    var resetDialog;
    $(function () {
        resetDialog = $("#reset-dialog").on('hide.bs.modal', function () {
            $("#resetForm").data('bootstrapValidator').resetForm(true);
        });

        $("#resetForm").bootstrapValidator({
            message: 'This value is not valid',
            submitButtons: 'button[type="submit"]',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                newPassword: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        identical: {
                            field: 'reconfirm',
                            message: '请确认再次输入的密码是否一致'
                        }
                    }
                },
                reconfirm: {
                    validators: {
                        notEmpty: {
                            message: '请再次输入密码'
                        },
                        identical: {
                            field: 'newPassword',
                            message: '输入的内容必须和密码一致'
                        }
                    }
                },
                oldPassword: {
                    validators: {
                        notEmpty: {
                            message: '请输入旧密码'
                        }
                    }
                }
            }
        }).on('success.form.bv', function (e) {
            onSubmitReset();
            $("#resetForm").data('bootstrapValidator').resetForm();
        });

    })

    function onSubmitReset() {
        $.post(
                '<%=request.getContextPath()%>/sys/userUpdatePassword',
                $("#resetForm").serialize(),
                function (data) {
                    if (data.id != null) {
                        resetDialog.modal('hide');

                    } else {
                        bootbox.alert("输入的密码不正确");
                    }
                }
        );
    }

    function showResetClicked() {
//        resetDialog.find("input[type='hidden']").prop("value", 0);
        $('#resetModalTitle').html("修改密码");
        resetDialog.modal('show');
    }

</script>

</body>
</html>
