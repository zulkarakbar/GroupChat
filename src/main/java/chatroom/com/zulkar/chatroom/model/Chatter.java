package chatroom.com.zulkar.chatroom.model;

import lombok.Data;

/**
 * This class represents a chatter in the chat room. For each Chatter object a
 * name, age and login time is required.
 */
@Data
public class Chatter {
	private String name = null;
	private String sex = null;
	private String email = null;
	 private long loginTime = -1;
	private String age = null;
	private String key;

	
	public Chatter(String name, String sex, String age, String key) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.key = key+name;
		loginTime = System.currentTimeMillis();
	}

	public Chatter() {

	}

}