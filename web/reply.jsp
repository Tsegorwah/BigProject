<%-- 
    Document   : reply
    Created on : 2018-10-23, 12:10:24
    Author     : yyznb
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/mapcss.css" rel="stylesheet" type="text/css"/>
         <script type="text/javascript" src="js/vue.js"></script>
          <script src="https://webapi.amap.com/maps?v=1.4.10&key=69c70ee2c4d2e932f9a8b4ac0b5afb29"></script>
          <script type="text/javascript" src="js/1.9.1jquery.min.js"></script>
          <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    </head>
    <%
     int topicId = Integer.parseInt(request.getParameter("topicId"));
   Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now = df.format(d);
%>
    <body>
        <jsp:include flush="true" page="head.jsp"></jsp:include>
        <form name="form1" method="post" action="ReplyServlet?topicId=<%=topicId%>">
        <div id="huitie">
            <div id="post-title">
                <input name="replyDate" type="hidden" value="<%=now%>">
            </div>
            <div id="post-area">
        <textarea cols="74" rows="13" placeholder="Context..." name="replyContent"></textarea>
            </div>
            <div id="post-bt">
        <input type="submit" value="回复"></input>
            </div>
        </div>
            </form>
                <jsp:include flush="true" page="tail.jsp"></jsp:include>
    </body>
</html>
