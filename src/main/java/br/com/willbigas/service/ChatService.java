package br.com.willbigas.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ChatService {

	private final ChatModel chatModel;

	public ChatService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	public String getResponse(String prompt) {
		return chatModel.call(prompt);
	}

	public String getResponseWithOptions(String prompt, String model, Double precision) {
		ChatResponse response = chatModel.call(new Prompt(prompt, OpenAiChatOptions.builder()
				.model(model)
				.temperature(precision)
				.build()));

		return response.getResult().getOutput().getText();
	}

	public List<String> getModels() {
		return Arrays.stream(OpenAiApi.ChatModel.values()).map(OpenAiApi.ChatModel::getValue).toList();
	}
}
