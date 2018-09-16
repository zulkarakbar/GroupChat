<!DOCTYPE html>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="http://127.0.0.1:8887/loginchat.css">
<style>

</style>
</head>
<body>
<div>
<%@ include file="header.jsp" %>
</div>
	<div class="login_div">
<p>From pictograms carved into stone to the latest chat apps, humans always find better ways to communicate</p>
<p>Chat simplifies that away. Type a quick message, get an equally short reply or perhaps just an emoji, 
and get on with your work.</p><p> There &#39;s nothing complicated or time-consuming. It's how we talk to friends and family 
today, and increasingly, it's also how work gets done.</p>
</div>
	<button onclick="document.getElementById('id01').style.display='block'"
		class="login_button">Login</button>

	<div id="id01" class="modal hide fade">

		<form class="modal-content animate" action="showview" method="post">
			<div class="imgcontainer">
				<span onclick="document.getElementById('id01').style.display='none'"
					class="close" title="Close Modal">&times;</span> 
			</div>

			<div class="container">
				<label for="uname"><b>Username</b></label> <input type="text"
					placeholder="Username" name="uname" id="uname" required> 
					<label for="mail"><b>Email</b></label> <input type="text"
					placeholder="Email" name="mail" id="mail" required> 
					
					<label for="room"><b>Enter Room</b></label><input type="text"
					placeholder="Room" name="roomName" id="roomName" class ="simple" required>
					
					<label for="room"><b>Gender</b></label><input type="text"
					placeholder="Gender" name="sex" id="sex" class ="simple" required>

				
				
					<label for="age"><b>Age</b></label> <input type="text"
					placeholder="Age" name="age" id="age" class ="simple" required>
				
				<button type="button" style="width: 200px" id="generateotp" onclick="generateOTP()">Generate
					OTP</button>
				<input id="otp" type="text" placeholder="Enter OTP" name="otp"
					required style="width: 150px">
				<button type="submit">Login</button>
			</div>

			<div class="container" style="background-color: #f1f1f1">
				<button type="button"
					onclick="document.getElementById('id01').style.display='none'"
					class="cancelbtn">Cancel</button>
				
			</div>
		</form>
	</div>

	<script>
		// Get the modal
		var modal = document.getElementById('id01');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}

		function generateOTP() {
			document.getElementById("generateotp").disabled = true;
			var email = document.getElementById("mail").value;
			var name = document.getElementById("uname").value;
			if (name == null)
				alert("Name is Null,Please enter valid username");
			if (mail == null)
				alert("Email cannot be null,Please enter a valid email for recieving OTP");
			var chatter = {
				name : name,
				email : email
			};
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("generateotp").disabled = true;
					alert("OTP sent");
				}
			};
			xhttp.open("POST", "http://localhost:8080/authenticate/OTP", true);
			xhttp.setRequestHeader("Content-type", "application/json");
			xhttp.send(JSON.stringify(chatter));
		}
	</script>

</body>
</html>
