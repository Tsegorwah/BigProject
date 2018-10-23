package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Reply;
import java.util.ArrayList;
import model.ReplyDaoImpl;
import model.TopicBean;

public final class content_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");

TopicBean tb = (TopicBean)request.getAttribute("topic");
int Id = 0;
if(request.getAttribute("Id")!=null){
    String s = request.getAttribute("Id").toString();
    Id = Integer.parseInt(s);
}

      out.write('\n');

    int currentPage = 1;
    if(request.getParameter("currentPage")!=null)
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
    ReplyDaoImpl rba = new ReplyDaoImpl();
    ArrayList al = rba.getReplyContentByPage(6, currentPage, Integer.parseInt(request.getAttribute("Id").toString()));
    int pageCount = rba.getRowCount()/6+1;

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link href=\"css/mycss.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"lt\">\n");
      out.write("            <table align=\"center\" class=\"hovertable\">\n");
      out.write("<tr>\n");
      out.write("    <th colspan=\"5\" height=\"160\">  \n");
      out.write("    <input id=\"fanhui\" type=\"button\" name=\"Submit\" title=\"返回首页\" onclick=\"location.href='index.jsp'\"/>\n");
      out.write("  </tr>\n");
      out.write("<tr height=\"120\">\n");
      out.write("    <td width=\"33%\" id=\"noborder\"><div  class=\"STYLE1\">作者</div></td>\n");
      out.write("    <td width=\"33%\" id=\"noborder\"><div  class=\"STYLE1\">作者</div></td>\n");
      out.write("    <td width=\"33%\" id=\"noborder\"><div  class=\"STYLE1\">发表日期</div></td>\n");
      out.write("</tr>\n");
      out.write("        ");

        for(int n=0;n<al.size();n++)
        {
            Reply rb=(Reply)al.get(n);
            
      out.write("\n");
      out.write("<tr class=\"yellow\" height=\"50\">\n");
      out.write("\t <td>");
      out.print(rb.getUsername());
      out.write("</td>\n");
      out.write("        <td>");
      out.print(rb.getReplyDate());
      out.write("</td>\n");
      out.write("        <td>");
      out.print(rb.getReplycontent());
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("      ");

        }
      
      out.write(" \n");
      out.write("<tr>\n");
      out.write("    <td colspan=\"2\"><div align=\"center\">\n");
      out.write("        <input type=\"submit\" name=\"Submit\" value=\"回复\" onclick=\"location.href='Reply.jsp?topicId=");
      out.print(tb.getId());
      out.write("'\"/>\n");
      out.write("    </div></td>\n");
      out.write("  </tr> \n");
      out.write("<tr class=\"yellow\">\n");
      out.write("    <th colspan=\"4\" height=\"22\">\n");
      out.write("              ");

           if(currentPage<pageCount&&currentPage>0)
                {
            
      out.write("\n");
      out.write("           <a href=\"ContentServlet?Id=");
      out.print(tb.getId());
      out.write("&currentPage=");
      out.print(currentPage+1);
      out.write("\">下一页</a>\n");
      out.write("            ");

                    }
            if(currentPage>1)
            {
              
      out.write("\n");
      out.write("              <a href=\"ContentServlet?Id=");
      out.print(tb.getId());
      out.write("&currentPage=");
      out.print(currentPage-1);
      out.write("\">上一页</a>\n");
      out.write("              ");

            }
            
      out.write("\n");
      out.write("           总页数:");
      out.print(pageCount);
      out.write(" 当前页:");
      out.print(currentPage);
      out.write("</div>\n");
      out.write("     </th>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
