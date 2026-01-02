package br.com.willbigas.controller;

import br.com.willbigas.service.ChatService;
import org.apache.coyote.Response;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenerativeAIController {

	private final ChatService service;

	public GenerativeAIController(ChatService service) {
		this.service = service;
	}

	@GetMapping("ask-ai")
	public ResponseEntity<String> getResponse(@RequestParam String prompt) {
		return ResponseEntity.ok(service.getResponse(prompt));
	}

	@GetMapping("ask-ai-with-parameters")
	public ResponseEntity<String> getResponse(@RequestParam String prompt, @RequestParam Double temperature, @RequestParam String model) {
		return ResponseEntity.ok(service.getResponseWithOptions(prompt, model, temperature));
	}

	@GetMapping("models")
	public ResponseEntity<List<String>> getModels() {
		return ResponseEntity.ok(service.getModels());
	}

}
