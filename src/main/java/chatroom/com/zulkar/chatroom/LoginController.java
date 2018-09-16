package chatroom.com.zulkar.chatroom;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import chatroom.com.zulkar.chatroom.chatutility.ChatRoomList;
import chatroom.com.zulkar.chatroom.model.ChatRoom;
import chatroom.com.zulkar.chatroom.model.Chatter;
import chatroom.com.zulkar.chatroom.model.Message;

@Controller
public class LoginController {
	
	@Autowired
	private ChatRoomList chatRoomList;
	

	@RequestMapping("/login")
	public String welcome(Map<String, Object> model,HttpSession session) {
		if(session!=null) {
			String nickname = (String)session.getAttribute("uname");
			String key = (String)session.getAttribute("hash");
			if(nickname!=null && key!=null) {
				ChatRoom room=chatRoomList.getRoomOfChatter(key+nickname);
				room.removeChatter(key+nickname);
				session.invalidate();
			}
			
		}
		
		
		return "welcome";
	}
	

	/*@PostMapping("/showview")
	public String showView(Map<String, Object> model, @RequestParam String uname, @RequestParam String mail,
			@RequestParam String otp, @RequestParam String roomName, @RequestParam String sex, @RequestParam String age,
			ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		session.setAttribute("uname", uname);
		session.setAttribute("roomName", roomName);
		session.setAttribute("hash", otp);
		
		
		ChatRoom room = null;
		room = chatRoomList.getRoom(roomName);
		if (room == null) {
			room = new ChatRoom(roomName);
			chatRoomList.addRoom(room);
		}
		session.setAttribute("chatRoomList", chatRoomList);
		if (!room.chatterExists(otp+uname)) {
			Chatter chatter = new Chatter(uname, sex, age, otp);
			room.addChatter(chatter,otp);
		} else {
			return "welcome";
		}
		return "chat";
	}

	@RequestMapping("/displayMessages")
	public String displayChat(Map<String, String> model, HttpServletRequest request, HttpSession session) {
		return "displayMessages";
	}

	@RequestMapping("/sendMessage")
	public String sendMessage(Map<String, String> model, HttpServletRequest request, HttpSession session) {
		return "sendMessage";
	}
*/
	@RequestMapping("/getMessages")
	@ResponseBody
	public Message[] getMessages(HttpServletRequest request, HttpSession session, @RequestParam("data") String message) {
		String nickname = (String) session.getAttribute("uname");
		String otp = (String) session.getAttribute("hash");
		ChatRoom chatRoom = null;
		Message[] messages = null;
		if (nickname != null && nickname.length() > 0) {
			try {
				
				chatRoom = chatRoomList.getRoomOfChatter(otp+nickname);
				if (chatRoom != null) {
					String msg = message;

					if (msg != null && msg.length() > 0) {
						msg = msg.trim();
						chatRoom.addMessage(new Message(nickname, msg, new java.util.Date().getTime()));

					}
				}
			} catch (Exception e) {
				
			}
		}
		Chatter chatter = chatRoom.getChatter(otp+nickname);
		long enteredAt = chatter.getLoginTime();
		if (enteredAt != -1) {
			messages = chatRoom.getMessages(enteredAt);
		} else {
			messages = chatRoom.getMessages(chatter.getLoginTime());
		}
		return messages;
	}

}