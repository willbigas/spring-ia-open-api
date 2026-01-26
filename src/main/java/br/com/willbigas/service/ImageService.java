package br.com.willbigas.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	private final OpenAiImageModel openAiImageModel;

	public ImageService(OpenAiImageModel openAiImageModel) {
		this.openAiImageModel = openAiImageModel;
	}

	public ImageResponse generateImage(String prompt, String quality, Integer n, Integer height, Integer width) {
		return openAiImageModel.call(new ImagePrompt(prompt, new OpenAiImageOptions().builder()
				.quality(quality)
				.N(n)
				.height(height)
				.width(width)
				.build()));
	}
}
