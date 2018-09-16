<%@ page isErrorPage="false" errorPage="error.jsp" 
import="java.util.Set,
java.util.Iterator,
java.util.Map,
java.util.Date,java.text.DateFormat,
chatroom.com.zulkar.chatroom.chatutility.*,
chatroom.com.zulkar.chatroom.model.*"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String roonName = null;
	String nickname = (String)session.getAttribute("uname");
	ChatRoomList roomList = null;
	ChatRoom chatRoom = null;
	Chatter chatter = null;
	Message[] messages = null;

	if (nickname != null)
	{
		try
		{
			roomList = (ChatRoomList) session.getAttribute("chatRoomList");
			roonName = (String)session.getAttribute("roomName");
			if (roonName != null && roonName != "")
			{
				chatRoom = roomList.getRoom(roonName);
				chatter = chatRoom.getChatter(nickname);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception: "+ e.getMessage());
			throw new ServletException("Unable to get handle to ServletContext");
		}	
	
%>
<HTML>
<HEAD>
<link rel="stylesheet" type="text/css" href="http://127.0.0.1:8887/chat.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script  type="text/javascript">

<%-- function reload()
{
	window.location.reload(true);
}

setInterval('reload()', <%=refreshAfter%>);

function winopen(path)
{
	chatterinfo = window.open(path,"chatterwin","scrollbars=no,resizable=yes, width=400, height=300, location=no, toolbar=no, status=no");
	chatterinfo.focus();
} --%>
function doit_onkeypress(event){
    if (event.keyCode == 13 || event.which == 13){
    	document.getElementById("sendmessage").click();
    }
}
function addMessageOnReload(){
	 setInterval(function(){}, 1000);
	
	var count =0;
	var message = document.getElementById("messagebox").value;
	document.getElementById("messagebox").value='';
	var userName=document.getElementById("logger").textContent;
	if(message!=null){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var response=this.responseText;
				var responseArr=JSON.parse(this.responseText);
				for(count=0;count<responseArr.length;count++){
					var messageArr=responseArr[count];
					var chatterName=messageArr.chatterName;
					if(chatterName==userName){
						var div = document.createElement('div');
						var msg=messageArr.message;
						 div.textContent = msg;
						 div.setAttribute('class', 'container darker');
						 document.body.appendChild(div);
						 window.scrollTo(0, document.body.scrollHeight);
					}else{
						var div = document.createElement('div');
						var timeStamp=messageArr.timeStamp;
						var msg=messageArr.message;
						div.textContent = msg;
						 div.setAttribute('class', 'container_user');
						 document.body.appendChild(div);
						 window.scrollTo(0, document.body.scrollHeight);
					}
					
				}	
				
			}
		};
		xhttp.open("GET", "http://localhost:8080/sendMessage1?data="+message, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send();
	}
	
}
function addMessage(){
	
	window.scrollBy(0, 300);
	var div = document.createElement('div');
	var count =0;
	var message = document.getElementById("messagebox").value;
	document.getElementById("messagebox").value='';
	var userName=document.getElementById("logger").textContent;
	if(message!=null){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var response=this.responseText;
				var responseArr=JSON.parse(this.responseText);
				for(count=0;count<responseArr.length;count++){
					var messageArr=responseArr[count];
					var chatterName=messageArr.chatterName;
					if(chatterName==userName){
						var msg=messageArr.message;
						 div.textContent = msg;
						 div.setAttribute('class', 'container_darker');
						 document.body.appendChild(div);
						 window.scrollTo(0, document.body.scrollHeight);
					}else{
						var timeStamp=messageArr.timeStamp;
						var msg=messageArr.message;
						div.textContent = msg;
						 div.setAttribute('class', 'container_user');
						 document.body.appendChild(div);
						 window.scrollTo(0, document.body.scrollHeight);
					}
					
				}	
				
			}
		};
		xhttp.open("GET", "http://localhost:8080/sendMessage1?data="+message, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send();
	}
	
}

//-->
</script>
</HEAD>
<BODY onLoad="addMessageOnReload()" bgcolor="#FFFFFF">

<table style="width:100%; border:0">
<tr>
<td width="100%" valign="top">
<%@ include file="header.jsp" %>
<table>
<tr>
<td>

<div class="left_div">
<p style="left:30px;" id="logger"  ><b><%=(String)session.getAttribute("uname")%> </b></p>
<p style="left:70px;"><b>you are in room <%=roonName%></b></p>
</div>

</td>
</tr>
</table>
	</td>
	<td width="30%" valign="top">
	<table class="users">
		<tr>
			<td>
			<table >
		<tr>
			<td>
	<span class="show_users"><%=chatRoom.getNoOfChatters()%> people in this room</span><br>
	</td>
	</tr>
	</table>
	<%
	Chatter[] chatters = chatRoom.getChattersArray();
	for(int i = 0; i < chatters.length; i++)
	{	
	%>
	<span class="show_users"><%= chatters[i].getName() + " (" +chatters[i].getSex() +")<br>"%></span>
	<%
		
	}

}
else
{
	response.sendRedirect("login.jsp");
}
%>
		</td>
	</tr>
</table>
</td>
</tr>
</table>
  			<div class="type_msg">
            <div class="input_msg_write">
              <input type="text"  placeholder="Type a message" id="messagebox" onkeypress="javascript:doit_onkeypress(event);"/>
            </div>
            </div>
             <button class="msg_send_btn" type="button" value="Send" id="sendmessage" onClick="addMessage()"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>

								
</BODY>
</HTML>