package com.example.ai.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AiClientController {
	private final ChatClient chatClient;
	private List<Message> memMessage = new ArrayList<>();
	
	@GetMapping("/memchat")
	public String chat(@RequestParam String prompt) {
		memMessage.add(new UserMessage(prompt));
		ChatResponse chatResponse = chatClient.prompt()
				.messages(memMessage)
				.call()
				.chatResponse();
		memMessage.add(chatResponse.getResult().getOutput());
		return chatResponse.getResult().getOutput().getContent();
	}
}
