package br.com.willbigas.controller;

import br.com.willbigas.service.ChatService;
import br.com.willbigas.service.ImageService;
import br.com.willbigas.service.RecipeService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenerativeAIController {

	private final ChatService chatService;
	private final RecipeService recipeService;
	private final ImageService imageService;

	public GenerativeAIController(ChatService chatService, RecipeService recipeService, ImageService imageService) {
		this.chatService = chatService;
		this.recipeService = recipeService;
		this.imageService = imageService;
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

	@GetMapping("image-creator")
	public ResponseEntity<List<String>> imageCreator(
			@RequestParam String prompt,
			@RequestParam(defaultValue = "hd") String quality,
			@RequestParam(defaultValue = "1") String n,
			@RequestParam(defaultValue = "1024") String height,
			@RequestParam(defaultValue = "1024") String width) {

		ImageResponse imageResponse = imageService.generateImage(prompt, quality, Integer.valueOf(n), Integer.valueOf(height), Integer.valueOf(width));

		return ResponseEntity.ok(imageResponse.getResults().stream().map(result -> result.getOutput().getUrl()).toList());
	}

}
