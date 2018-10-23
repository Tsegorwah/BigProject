<%-- 
    Document   : content
    Created on : 2018-10-22, 16:48:00
    Author     : yyznb
--%>
<%@page import="model.Reply"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ReplyDaoImpl"%>
<%@page import="model.TopicBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
TopicBean tb = (TopicBean)request.getAttribute("topic");
int Id = 0;
if(request.getAttribute("Id")!=null){
    String s = request.getAttribute("Id").toString();
    Id = Integer.parseInt(s);
}
%>
<%
    int currentPage = 1;
    if(request.getParameter("currentPage")!=null)
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
    ReplyDaoImpl rba = new ReplyDaoImpl();
    ArrayList al = rba.getReplyContentByPage(6, currentPage, Integer.parseInt(request.getAttribute("Id").toString()));
    int pageCount = rba.getRowCount()/6+1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/mapcss.css" rel="stylesheet" type="text/css"/>
         <script type="text/javascript" src="js/vue.js"></script>
          <script src="https://webapi.amap.com/maps?v=1.4.10&key=69c70ee2c4d2e932f9a8b4ac0b5afb29"></script>
          <script type="text/javascript" src="js/1.9.1jquery.min.js"></script>
          <script type="text/javascript" src="js/jquery-ui.min.js"></script>
          <script>
            $(function() {
    $( document ).tooltip();
  });</script>
    </head>
    <body>
         <jsp:include flush="true" page="head.jsp"></jsp:include>
         <table align="center" class="hovertable">
  <tr height="80" >
    <td width="100%" id="noborder"><div class="STYLE1" align="center">内容</div></td>
  </tr>
   <tr width="100%" class="yellow" height="50">
	 <td align="center"><%=tb.getContent()%></td>       
    </tr>
    <tr>
    <th colspan="5" height="160">  
    <input id="fanhui" type="button" name="Submit" title="返回首页" onclick="location.href='index.jsp'"/>
  </tr>
</table>
        <div id="lt">
            <table align="center" class="hovertable">
<tr height="120" >
    <td  id="noborder"><div  class="STYLE1">回复</div></td>
    <td  id="noborder"><div  class="STYLE1">作者</div></td>
    <td  id="noborder"><div  class="STYLE1">发表日期</div></td>
</tr>
        <%
        for(int n=0;n<al.size();n++)
        {
            Reply rb=(Reply)al.get(n);
            %>
<tr class="yellow" height="50">
	 <td><%=rb.getReplycontent()%></td>
        <td><%=rb.getUsername()%></td>
        <td><%=rb.getReplyDate()%></td>
</tr>
      <%
        }
      %> 
<tr>
    <td colspan="3"><div align="center">
        <input class="bt" type="submit" name="Submit" value="回复" onclick="location.href='reply.jsp?topicId=<%=tb.getId()%>'"/>
    </div></td>
  </tr> 
<tr class="yellow">
    <th colspan="4" height="22">
              <%
           if(currentPage<pageCount&&currentPage>0)
                {
            %>
           <a href="ContentServlet?Id=<%=tb.getId()%>&currentPage=<%=currentPage+1%>">下一页</a>
            <%
                    }
            if(currentPage>1)
            {
              %>
              <a href="ContentServlet?Id=<%=tb.getId()%>&currentPage=<%=currentPage-1%>">上一页</a>
              <%
            }
            %>
           总页数:<%=pageCount%> 当前页:<%=currentPage%></div>
     </th>
</tr>
 <jsp:include flush="true" page="tail.jsp"></jsp:include>
</table>
        </div>
    </body>
</html>
