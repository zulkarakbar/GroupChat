package chatroom.com.zulkar.chatroom;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chatroom.com.zulkar.chatroom.model.Chatter;

@RestController
@RequestMapping("/")
public class DemoController {
	@PostMapping(value="/showview1")
	public String showWelcomeView(ModelMap modelMap, Chatter chatter) {
		modelMap = new ModelMap();
		modelMap.put("message", "Welcome");
		return "viewpage";
	}
}
