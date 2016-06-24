<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=utf-8"%>
<head>
    <title><fmt:message key="login.title"/></title>
    <meta name="menu" content="Login"/>
</head>
<body id="login">
<table id="tab">
    <th>name</th>
</table>
</body>
<%@ include file="/scripts/jquery-1.6.2.js"%>
<script>
    $.ajax({
        type: "GET",
        url: "userform/select",
        data: {username:$("#username").val(), content:$("#content").val()},
        dataType: "json",
        success: function(data){
            $('#resText').empty();   //清空resText里面的所有内容
            var html = '';
            $.each(data, function(commentIndex, comment){
                html += '<div class="comment"><h6>' + comment['username']
                        + ':</h6><p class="para"' + comment['content']
                        + '</p></div>';
            });
            $('#resText').html(html);
        }
    });
</script>