package chatroom.com.zulkar.chatroom.view;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import chatroom.com.zulkar.chatroom.chatutility.ChatRoomList;
import chatroom.com.zulkar.chatroom.model.ChatRoom;
import chatroom.com.zulkar.chatroom.model.Chatter;

@Controller
public class ViewController {
	@Autowired
	private ChatRoomList chatRoomList;

	@RequestMapping("/showview")
	public String showView(Map<String, Object> model, @RequestParam String uname, @RequestParam String mail,
			@RequestParam String otp, @RequestParam String roomName, @RequestParam String sex, @RequestParam String age,
			ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		
		HttpSession session = httpRequest.getSession(true);
		session.setAttribute("uname", uname);
		session.setAttribute("roomName", roomName);
		session.setAttribute("hash", otp);
		if(uname==null||roomName==null||otp==null)
			return "welcome";
		ChatRoom room = null;
		room = chatRoomList.getRoom(roomName);
		if (room == null) {
			room = new ChatRoom(roomName);
			chatRoomList.addRoom(room);
		}
		session.setAttribute("chatRoomList", chatRoomList);
		if (!room.chatterExists(otp + uname)) {
			Chatter chatter = new Chatter(uname, sex, age, otp);
			room.addChatter(chatter, otp);
		} 
		return "displayMessages";
	}

	@RequestMapping("/displayMessages")
	public String displayChat(Map<String, String> model, HttpServletRequest request, HttpSession session) {
		return "displayMessages";
	}

	@RequestMapping("/sendMessage")
	public String sendMessage(Map<String, String> model, HttpServletRequest request, HttpSession session) {
		return "sendMessage";
	}
//
	@RequestMapping("/errorPage")
	public String error(Map<String, Object> model) {
		System.out.println("Hello");
		return "error";
	}
	@RequestMapping("/listrooms")
	public String listRooms(Map<String, Object> model) {
		System.out.println("Hello");
		return "error";
	}
	@RequestMapping("/logout")
	public String logout(Map<String, Object> model,HttpSession session) {
		String nickname = (String)session.getAttribute("uname");
		String key = (String)session.getAttribute("hash");
		
		ChatRoom room=chatRoomList.getRoomOfChatter(key+nickname);
		room.removeChatter(key+nickname);
		return "welcome";
	}
}
