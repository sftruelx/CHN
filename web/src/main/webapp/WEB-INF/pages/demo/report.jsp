<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结果</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${ctx}/scripts/demo/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/scripts/demo/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/scripts/demo/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>
<!-- Page Content -->
<img src="/images/logo-01.png" width="90%">
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">报告内容</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h4>训练内容</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h4>${trainning.content}</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
               <h4>训练结果</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h4>${trainning.result}</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h4>建议</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h4>${trainning.advise}</h4>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>
</body>
</html>
