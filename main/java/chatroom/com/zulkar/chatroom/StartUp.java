package chatroom.com.zulkar.chatroom;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.google.gson.Gson;

import chatroom.com.zulkar.chatroom.chatutility.ChatRoomList;
import chatroom.com.zulkar.chatroom.model.Message;

public class StartUp {
	private Gson gson;
	private ChatRoomList chatRoomList;
	private WelcomeController welcome;
	private List<Message> messages;

	@PostConstruct
	public void init() {
		gson = new Gson();
		chatRoomList=new ChatRoomList();
		welcome=new WelcomeController();
		messages=new LinkedList<>();
	}

	@Bean
	@Scope("single")
	public Gson getGson() {
		return gson;
	}
	@Bean
	@Scope("single")
	public List<Message> getMessages() {
		return messages;
	}
	@Bean
	@Scope("single")
	public ChatRoomList getChatRoomList() {
		return chatRoomList;
	}
	@Bean
	@Scope("single")
	public WelcomeController getWelcomeController() {
		return welcome;
	}
}
