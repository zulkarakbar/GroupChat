

<%@ page isErrorPage="false" errorPage="error.jsp"
	import="java.util.Set,java.util.Iterator,java.util.Map,
chatroom.com.zulkar.chatroom.chatutility.*,
chatroom.com.zulkar.chatroom.model.*"%>
<% 
	
	String nickname = (String)session.getAttribute("uname");
	String key= (String)session.getAttribute("hash");
	if (nickname != null && nickname.length() > 0)
	{
		try{
		ChatRoomList roomList = (ChatRoomList)session.getAttribute("chatRoomList");
		ChatRoom chatRoom = roomList.getRoomOfChatter(key+nickname);
		if ( chatRoom != null)
		{
			String msg = request.getParameter("messagebox");
			
			if ( msg != null && msg.length() > 0)
			{
				msg = msg.trim();
				chatRoom.addMessage(new Message(nickname, msg, new java.util.Date().getTime()));
			}
			
%>
<HTML>
<HEAD>
<LINK rel="stylesheet" type="text/css" href="chat.css">
<META http-equiv="pragma" content="no-cache">

<SCRIPT language="JavaScript" type="text/javascript">
<!--

function winopen(path)
{
	chatterinfo = window.open(path,"chatterwin","scrollbars=no,resizable=yes, width=400, height=300, location=no, toolbar=no, status=no");
	chatterinfo.focus();

}

//-->
</SCRIPT>
</HEAD>
<BODY onLoad="document.msg.messagebox.focus();" bgcolor="#FFFFFF">
	<TABLE width="100%" cellpadding="3" cellspacing="0">
		<TR>
			<TD width="50%" align="left" valign="top">
				<TABLE>
					<TR>
						<FORM name="msg" action="/sendMessage" method="post">
							<TD width="100%"><INPUT type="text" name="messagebox"
								maxlength="300" size="35" onmessage="top.frames[1].location.reload()" > <INPUT type="hidden"
								name="nickname" value="<%=session.getAttribute("uname")%>">
								<INPUT name="submit" type="submit" value="Send" onClick="top.frames[0].location.reload()"></TD>
						</FORM>
					</TR>
				</TABLE>
			</TD>
			<TD width="50%">
				<TABLE border="1" cellpadding="3" cellspacing="0" class="panel">
					<TR align="left" valign="top">
						<FORM name="changeRoom" method="post" action="listrooms.jsp">
							<TD width="15%"><INPUT type="hidden" name="n"
								value="<%=nickname%>"> <INPUT name="ChangeRoom"
								type="submit" id="ChangeRoom" value="Change Room"></TD>
						</FORM>
						<FORM name="editinfo" action="editinfo" method="post">
							<TD width="20%"><INPUT type="button" value="Edit Profile"
								onClick='winopen("editInfo.jsp")'></TD>
						</FORM>
						<FORM name="find">
							<TD width="10%"><INPUT type="button" value="Find"
								onClick='winopen("find.jsp")'></TD>
						</FORM>
					</TR>
					<TR align="left" valign="top">
						<FORM name="refresh">
							<TD><INPUT type="Button" value="Refresh"
								onClick="top.frames[0].location.reload()"></TD>
						</FORM>
						<FORM name="logout" action="logout.jsp" method="post"
							target="_top">
							<TD width="10%"><INPUT type="Submit" value="Logout">
							</TD>
						</FORM>
						<TD>&nbsp;</TD>
					</TR>
				</TABLE>
			</TD>
		</TR>
	</TABLE>
</BODY>
</HTML>
<%
		}
		else
		{
			out.write("<h2 class=\"error\">Your room couldn't be found. You can't send message</h2>");
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	else
	{
		response.sendRedirect("login.jsp");
	}
%>
