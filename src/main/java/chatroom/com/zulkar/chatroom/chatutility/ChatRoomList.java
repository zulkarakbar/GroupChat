package chatroom.com.zulkar.chatroom.chatutility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Repository;

import chatroom.com.zulkar.chatroom.model.ChatRoom;
@Repository
public class ChatRoomList {
	
	private Map<String,ChatRoom> roomList;

	public ChatRoomList() {
		roomList = new HashMap<>();
	}

	public synchronized void addRoom(ChatRoom room) {
		roomList.put(room.getName(), room);
	}

	public synchronized void removeRoom(String name) {
		roomList.remove(name);
	}

	public ChatRoom getRoom(String name) {
		return (ChatRoom) roomList.get(name);
	}

	public ChatRoom getRoomOfChatter(String key) {
		ChatRoom[] rooms = this.getRoomListArray();

		for (int i = 0; i < rooms.length; i++) {
			boolean chatterexists = rooms[i].chatterExists(key);
			if (chatterexists) {
				return rooms[i];
			}
		}
		return null;
	}



	public Set<Entry<String, ChatRoom>> getRoomList() {
		return roomList.entrySet();
	}

	public ChatRoom[] getRoomListArray() {
		ChatRoom[] roomListArray = new ChatRoom[roomList.size()];
		Set<Entry<String, ChatRoom>> roomlist = getRoomList();
		Iterator<Entry<String, ChatRoom>> roomlistit = roomlist.iterator();
		int i = 0;
		while (roomlistit.hasNext()) {
			Map.Entry<String, ChatRoom> me =  roomlistit.next();
			roomListArray[i] = (ChatRoom) me.getValue();
			i++;
		}
		return roomListArray;
	}

	public boolean chatterExists(String nickname) {
		boolean chatterexists = false;
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++) {
			chatterexists = rooms[i].chatterExists(nickname);

			if (chatterexists) {
				break;
			}
		}
		return chatterexists;
	}
}