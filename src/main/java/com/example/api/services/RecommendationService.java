package com.example.api.services;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;

import java.util.Arrays;

public class RecommendationService {
    private static final String GUIDELINES = """
                Here are a list of Functional Requirements and Non Functional Requirements. Provide a technic recommendation
                 containing a technologies stack, proposed architecture and reasoning on why these must be used, supported by the non-functional requirements and based on ISO25010.
                 Remove emojis from your answer. Remove follow-up questions from your answer. Limit to provide the specified elements.
                
                Answer in JSON, with three fields: technologiesStack, proposedArchitecture and reasoning.
                Both the stack and the reasoning must be divided in a list.
                
                Try to present just the required stack and architecture for the provided requirements.
                Reasoning must be organized by the categories of ISO25010 in question in the format: "<category_name>": "<details>"
                The technologyStack must follow the next format "technology_name": "<technology_name>" and "technology_type": "<technology_type>"
                Inside the proposed architectures must go a list of "architecture_components" composed of "component_name": "<component_name>" and "function": "<what_it_does_in_context_of_architecture>",
                and a field called "description" that describes the architecture in less than 200 words
                
                Answer like an expert in software development an systems. Follow the instructions with care. This is the end of the instructions.
                """;

    public String getRecommendation(String modelName, String[] functionalRequirements,
                                    String[] nonFunctionalRequirements,String additionalContext){
        var openAiApi = OpenAiApi.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .baseUrl("https://openrouter.ai/api/")
                .build();
        var openAiChatOptions = OpenAiChatOptions.builder()
                .model(modelName)
                .build();
        var chatModel = OpenAiChatModel.builder().openAiApi(openAiApi).defaultOptions(openAiChatOptions).build();
        String promptText = GUIDELINES +"Functional Requirements: "+ Arrays.toString(functionalRequirements) +
                ", Non-Functional Requirements: "+ Arrays.toString(nonFunctionalRequirements) +"\n Additional Context"+
                additionalContext;
        Prompt prompt = new Prompt(promptText);
        ChatResponse response = chatModel.call(prompt);
        return(response.getResult().getOutput().getText());
    }
}
