<%-- 
    Document   : posting
    Created on : 2018-10-18, 9:10:03
    Author     : yyznb
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/mapcss.css" rel="stylesheet" type="text/css"/>
         <script type="text/javascript" src="js/vue.js"></script>
          <script type="text/javascript" src="js/1.9.1jquery.min.js"></script>
          <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <title>fatie</title>
    </head>
 <%
    String city="北京";
    if(request.getParameter("city")!=null)
        city=request.getParameter("city");
   Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now = df.format(d);
%>
    <body>
            <form name="form1" method="post" action="TopicServlet">
        <div id="fatie" v-show="postshow">
            <div id="post-title">
                <input name="city" type="hidden"  value="<%=city%>">
                <input name="topicDate" type="hidden" value="<%=now%>">
                <textarea cols="40" rows="1" name="title" placeholder="Title..."></textarea>
                <div class="close" v-on:click="postclose()"></div>
            </div>
            <div id="post-area">
        <textarea cols="74" rows="18" placeholder="Context..." name="content"></textarea>
            </div>
            <div id="post-bt">
        <input type="submit" value="发布"></input>
            </div>
        </div>
            </form>
    </body>
</html>
