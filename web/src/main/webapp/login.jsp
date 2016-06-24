<!DOCTYPE html>
<html lang="en">
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=utf-8" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>登录首页</title>

    <!-- Bootstrap Core CSS -->
    <link href="${ctx}/scripts/demo/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${ctx}/scripts/demo/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${ctx}/scripts/demo/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${ctx}/scripts/demo/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <!--<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>-->
    <!--<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
    <%--<![endif]-->--%>

</head>

<body>
<div><img src="${ctx}/images/logo-01.png"/></div>
<div style='background-image: url("images/indexBg.jpg");padding-top: 150px; width: 100%;height: 100%' class="container">
    <div class="row">
        <div class="col-md-2 col-md-offset-5">
           <%-- <div class="login-panel panel panel-default">--%>
                <div class="panel-heading">
                    <h2 class="form-signin-heading">
                        <fmt:message key="login.heading"/>
                    </h2>
                </div>
                <div class="panel-body">

                        <form role="form" method="post" id="loginForm" action="<c:url value='/j_security_check'/>"
                              onsubmit="saveUsername(this);return validateForm(this)" class="form-signin"
                              autocomplete="off">
                            <fieldset>
                                <div class="form-group">
                                    <c:if test="${param.error != null}">
                                        <div class="alert alert-danger alert-dismissable">
                                            <fmt:message key="errors.password.mismatch"/>
                                        </div>
                                    </c:if>
                                    <input type="text" name="j_username" id="j_username" class="form-control"
                                           placeholder="<fmt:message key="label.username"/>" required tabindex="1">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" name="j_password" id="j_password"
                                           tabindex="2"
                                           placeholder="<fmt:message key="label.password"/>" required>
                                </div>
                                <c:if test="${appConfig['rememberMeEnabled']}">
                                    <label for="rememberMe" class="checkbox">
                                        <input type="checkbox" name="_spring_security_remember_me" id="rememberMe"
                                               tabindex="3"/>
                                        <fmt:message key="login.rememberMe"/></label>
                                </c:if>
                                <!-- Change this to a button or input when using this as a form -->
                                <button type="submit" class="btn btn-lg btn-success btn-block" name="login"
                                        tabindex="4">
                                    <fmt:message key='button.login'/>
                                </button>
                            </fieldset>
                        </form>

                </div>
           <div style="height: 300px"></div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="${ctx}/scripts/demo/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${ctx}/scripts/demo/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${ctx}/scripts/demo/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${ctx}/scripts/demo/dist/js/sb-admin-2.js"></script>
<script type="text/javascript">

    $("#j_username").focus();

</script>
</body>

</html>
