package chatroom.com.zulkar.chatroom.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import chatroom.com.zulkar.chatroom.chatutility.ChatterUtility;
import lombok.Data;

@Data
public class ChatRoom {

	private String name = null;
	private ChatterUtility chatterUtility;
	private Chatter chatter;
	private Map<String, Chatter> chatters = new HashMap<>();
	private int numberOFMessages=25;
	
	
	private List<Message> messages=new LinkedList<>();

	public ChatRoom(String name) {
		this.name = name;
	//	this.chatterUtility= chatterUtility;
	}
	
	public synchronized void addChatter(Chatter chatter,String otp) {
		chatters.put(otp+chatter.getName(), chatter);
	}
	
	public boolean chatterExists(String key) {
		return chatters.containsKey(key);
	}
	public synchronized Object removeChatter(String chatterName) {
		return chatters.remove(chatterName);
	}

	public Chatter getChatter(String chatterName) {
		return chatters.get(chatterName);
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

	public synchronized void addMessage(Message msg) {
		if (messages.size() == numberOFMessages) {
			((LinkedList<Message>) messages).removeFirst();
		}
		messages.add(msg);
	}

	public ListIterator<Message> getMessages() {
		return messages.listIterator();
	}

	public Message[] getMessages(long afterTimeStamp) {
		ListIterator<Message> li = messages.listIterator();
		List<Message> temp = new ArrayList<>();
		Message m;
		while (li.hasNext()) {
			m = (Message) li.next();
			if (m.getTimeStamp() >= afterTimeStamp) {
				temp.add(m);
			}
		}
		Object o[] = temp.toArray();
		Message[] arr = new Message[o.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (Message) o[i];
		}
		return arr;
	}

	public int getNoOfMessages() {
		return messages.size();
	}

	public void setMaximumNoOfMessages(int size) {
		numberOFMessages = size;
	}

	public int getMaxiumNoOfMessages() {
		return numberOFMessages;
	}
}