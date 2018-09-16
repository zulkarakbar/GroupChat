<%@ page session="true" import="chatroom.com.zulkar.chatroom.chatutility.ChatRoomList, chatroom.com.zulkar.chatroom.model.ChatRoom" errorPage="error.jsp"%>
<%
	String nickname = (String)session.getAttribute("uname");
	if (nickname != null && nickname.length() > 0)
	{
		String roomname = (String)session.getAttribute("roomName");
%>
	
<HTML>
<HEAD>
<TITLE>S2R Chat - <%out.print(nickname);%> (<%out.print(roomname);%>) </TITLE>
</HEAD>
<FRAMESET rows="93%,7%">displayMessages
<FRAME SRC="displayMessages" name="MessageWin">
<FRAME SRC="sendMessage" name="TypeWin">
</FRAMESET>
<NOFRAMES>
<H2>This chat rquires a browser with frames support</h2>
</NOFRAMES>
</HTML>
<%
}
else
{
	response.sendRedirect("login.jsp");
}
%>