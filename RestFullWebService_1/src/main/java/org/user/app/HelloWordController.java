package org.user.app;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.user.app.model.HelloWord;

@RestController
public class HelloWordController {
	
	@Autowired
	private MessageSource messageSource;
	@GetMapping(path="/hello-word")
	public String getHelloWord() {
		return "Hello Word";
	}
	@GetMapping(path="/hello-word-bean/user/{name}")
	public HelloWord getHelloWordBean(@PathVariable String name) {
		return new HelloWord(String.format("Hello Mr %s", name));
	}
	@GetMapping("/wishes")
	public String getWish(@RequestHeader(name="Accept-Language", required = false) Locale local) {
		return messageSource.getMessage("message.good.morning", null, local);
	}
}
