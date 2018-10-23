<%-- 
    Document   : regResult
    Created on : 2016-11-8, 16:29:03
    Author     : win8
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String result = (String)request.getAttribute("result");
            if(result.equals("注册成功")){
        %>
        注册成功！三秒后自动跳转到登陆页面。
        <meta http-equiv="refresh" content="3;url=Login.jsp"/>
        <%
        }
        else if(request.equals("注册失败"))
        {
        %>
        注册失败！三秒后重新注册。
        <meta http-equiv="refresh" content="3;url=Reg.jsp"/>
        <%
        }
        else
        {
        %>
        用户名被占用！三秒后重新注册。
        <meta http-equiv="refresh" content="3;url=Reg.jsp"/>
        <%
        }
        %>
    </body>
</html>
