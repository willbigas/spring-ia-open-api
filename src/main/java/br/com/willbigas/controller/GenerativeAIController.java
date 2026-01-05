package br.com.willbigas.controller;

import br.com.willbigas.service.ChatService;
import br.com.willbigas.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenerativeAIController {

	private final ChatService chatService;
	private final RecipeService	recipeService;

	public GenerativeAIController(ChatService chatService, RecipeService recipeService) {
		this.chatService = chatService;
		this.recipeService = recipeService;
	}

	@GetMapping("ask-ai")
	public ResponseEntity<String> getResponse(@RequestParam String prompt) {
		return ResponseEntity.ok(chatService.getResponse(prompt));
	}

	@GetMapping("ask-ai-with-parameters")
	public ResponseEntity<String> getResponse(@RequestParam String prompt, @RequestParam Double temperature, @RequestParam String model) {
		return ResponseEntity.ok(chatService.getResponseWithOptions(prompt, model, temperature));
	}

	@GetMapping("recipe-creator")
	public ResponseEntity<String> recipeCreator(
			@RequestParam String ingredients,
			@RequestParam(defaultValue = "any") String cuisine,
			@RequestParam(defaultValue = "none") String dietaryRestrictions) {
		return ResponseEntity.ok(recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions));
	}

	@GetMapping("models")
	public ResponseEntity<List<String>> getModels() {
		return ResponseEntity.ok(chatService.getModels());
	}

}
