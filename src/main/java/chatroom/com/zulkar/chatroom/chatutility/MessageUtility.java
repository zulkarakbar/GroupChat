package chatroom.com.zulkar.chatroom.chatutility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import chatroom.com.zulkar.chatroom.model.Message;
import lombok.Data;

@Data
public class MessageUtility {
	private List<Message> messages = new LinkedList<>();
	private int numberOFMessages = 25;

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
