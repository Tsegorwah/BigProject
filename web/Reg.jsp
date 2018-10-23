<%-- 
    Document   : Reg
    Created on : 2018-10-11, 22:53:25
    Author     : fmg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/mapcss.css" rel="stylesheet" type="text/css"/>
        <title>Reg</title>
               <script>
            function check(){
                var username=document.getElementById("username").value;
                if(username==null||username==""){
                    alert("请输入用户名！");
                    return false;
                }
                var password1=document.getElementById("password1").value;
                     if(password1==null||password1==""){
                    alert("请输入密码！");
                    return false;
                }
                var password2=document.getElementById("password2").value;
                     if(password2==null||password2==""){
                    alert("请输入确认密码！");
                    return false;
                }
                if(password1!=password2){
                    alert("密码与确认密码不一致！");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
                <form name="form1" method="post" action="RegServlet">
        <div id="regw">
        <div id="logw-1-2"></div>
                 <div id="logw-3">
                        <input type="text" class="wintext" placeholder="Username..." name="username" ></input>
                        <input type="password" class="wintext" placeholder="Password..." name="password1" ></input>
                        <input type="password" class="wintext" placeholder="Confirm Password..." name="password2"></input>
                        <input type="submit" name="Submit" class="winbt" value="确定" onclick="return check()"></input>
                    </div>
        </div>
                </form>
    </body>
</html>
