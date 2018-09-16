package chatroom.com.zulkar.chatroom.model;

import lombok.Data;

/**
 * Represents a Message sent by a user.
 */
@Data
public class Message {
	private String chatterName = null;
	private String message = null;
	private long timeStamp;

	public Message(String name, String message, long timeStamp) {
		this.chatterName = name;
		this.message = message;
		this.timeStamp = timeStamp;
	}

}