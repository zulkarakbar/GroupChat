package chatroom.com.zulkar.chatroom.chatutility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import chatroom.com.zulkar.chatroom.model.ChatRoom;
import chatroom.com.zulkar.chatroom.model.Chatter;

public class ChatterUtility {
	private Map<String, Chatter> chatters = new HashMap<>();

	public synchronized void addChatter(Chatter chatter) {
		chatters.put(chatter.getName(), chatter);
	}

	public synchronized Object removeChatter(String chatterName) {
		return chatters.remove(chatterName);
	}

	public Chatter getChatter(String chatterName) {
		return chatters.get(chatterName);
	}

	public boolean chatterExists(String chatterName) {
		return chatters.containsKey(chatterName);
	}
	public boolean chatterExists(ChatRoom room,String chatterName) {
		
		return chatters.containsKey(chatterName);
	}

	public int getNoOfChatters() {
		return chatters.size();
	}

	public Set<Entry<String, Chatter>> getChatters() {
		return chatters.entrySet();
	}

	public Chatter[] getChattersArray() {
		Chatter[] chattersArray = new Chatter[chatters.size()];
		Set<Entry<String, Chatter>> chatters = getChatters();
		Iterator<Entry<String, Chatter>> chattersit = chatters.iterator();
		int i = 0;
		while (chattersit.hasNext()) {
			Map.Entry<String, Chatter> me = chattersit.next();
			String key = (String) me.getKey();
			chattersArray[i] = (Chatter) me.getValue();
			i++;
		}
		return chattersArray;
	}
}
