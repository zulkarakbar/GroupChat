<%@ page isErrorPage="true" import="java.io.PrintWriter" %>
<HTML>
<HEAD>
<TITLE>Error Occurred</TITLE>	

</HEAD>
<BODY bgcolor="#FFFFFF">
<h2>Some error occurred</h2>
<pre>
<%
if (exception != null)
{
	//System.out.println("Exception: " +exception.getMessage());
	out.write("<span class=\"error\">Exception: " +exception.getMessage() + "</span><br>");
}
%>
</pre>
<p>
<a href="/welcome.jsp"></a>
</p>
</BODY>
</HTML>