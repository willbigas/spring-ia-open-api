package br.com.willbigas.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

	private final ChatModel chatModel;

	public RecipeService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	public String createRecipe(String ingredients, String cuisine, String dietaryRestrictions) {

		var template = """
				I Want to create a recipe using the following ingredients: {ingredients}
				The cuisine type i prefer is {cuisine}.
				Please consider the follow dietary restrictions: {dietaryRestrictions}.
				Please provide me with a detailed recipe including title, list of ingredients and cooking instructions.
				""";

		PromptTemplate promptTemplate = new PromptTemplate(template);
		promptTemplate.add("ingredients" , ingredients);
		promptTemplate.add("cuisine" , cuisine);
		promptTemplate.add("dietaryRestrictions" , dietaryRestrictions);

		Prompt prompt = promptTemplate.create();

		return chatModel.call(prompt).getResult().getOutput().getText();
	}
}
