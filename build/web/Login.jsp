<%-- 
    Document   : Login
    Created on : 2018-10-11, 22:51:03
    Author     : fmg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/mapcss.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
          <form name="form1" action="LoginServlet" method="post">
                     <div id="logw">
                    <div id="logw-1"></div>
                    <div id="logw-2">
                        <input type="text" class="wintext" placeholder="Username..." name="username"></input>
                        <input type="password" class="wintext" placeholder="Password..." name="password"></input>
                        <input type="submit" name="Submit" class="winbt" value="确定"></input>
                    </div>
                    </div>
              </form>
    </body>
</html>
