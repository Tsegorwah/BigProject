<%-- 
    Document   : luntan
    Created on : 2018-10-8, 16:17:12
    Author     : yyznb
--%>

<%@page import="model.TopicBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.TopicBeanAction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<%
    int currentPage=1;
    String city="北京";
    if(request.getParameter("city")!=null)
        city=request.getParameter("city");
    if(request.getParameter("currentPage")!=null)
        currentPage=Integer.parseInt(request.getParameter("currentPage"));
        TopicBeanAction tba=new TopicBeanAction();
        ArrayList al=tba.getTopicByPage(6, currentPage,city);
        int pageCount=tba.getRowCount()/6+1;//总页数
    %>
          <script>
  $(function() {
    $( document ).tooltip();
    city="<%=city%>";
    citycenter(city);
  });
  </script>
        <div id="lt">
            <table align="center" class="hovertable">
<tr>
    <th colspan="5" height="160">  
    <input class="ltbt" type="button" name="Submit" title="发帖" v-on:click="postblock()"/>
    <input class="ltbt" type="button" name="Submit" title="查看热力图"  v-on:click="hotblock()"/>
    <input class="ltbt" type="button" name="Submit" title="查看我的贴子" onclick="location.href='mytiezi.jsp'"/>
    <input class="ltbt" type="button" name="Submit2" title="修改个人信息" onclick="location.href='Changeuser.jsp'"/>
    <input class="ltbt" type="button" name="Submit2" title="热帖" onclick="location.href='retie.jsp'"/> 
    
    <div id="citydiv"><div class="city"><%=city%>论坛</div></div></th>  
  </tr>
<tr height="120">
    <td width="50%" id="noborder"><div align="center" class="STYLE1">标题</div></td>
    <td width="20%" id="noborder"><div  class="STYLE1">作者</div></td>
    <td width="20%" id="noborder"><div  class="STYLE1">发表日期</div></td>
    <td width="10%" id="noborder"><div  class="STYLE1">回复数</div></td>
</tr>
        <%
        for(int n=0;n<al.size();n++)
        {
            TopicBean tb=(TopicBean)al.get(n);
            %>
<tr class="yellow" height="50">
	 <td><a href="ContentServlet?Id=<%=tb.getId()%>"><%=tb.getTitle()%></a></td>
        <td><%=tb.getUsername()%></td>
        <td><%=tb.getTopicDate()%></td>
        <td><%=tb.getReplycount()%></td>
</tr>
      <%
        }
      %> 

<tr class="yellow">
    <th colspan="4" height="22">
              <%
           if(currentPage<pageCount&&currentPage>0)
                {
            %>
            <a href="index.jsp?currentPage=<%=currentPage+1%>&city=<%=city%>">下一页</a>
            <%
                    }
            if(currentPage>1)
            {
              %>
              <a href="index.jsp?currentPage=<%=currentPage-1%>&city=<%=city%>">上一页</a>
              <%
            }
            %>
           总页数:<%=pageCount%> 当前页:<%=currentPage%></div>
     </th>
</tr>
</table>
        </div>
    </body>
     <script type="text/javascript" src="js/hot.js"></script> 
</html>
