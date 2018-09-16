package chatroom.com.zulkar.chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
	public class ChatRoomStart extends SpringBootServletInitializer {

		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(ChatRoomStart.class);
		}

		public static void main(String[] args) throws Exception {
			SpringApplication.run(ChatRoomStart.class, args);
		}
	}

