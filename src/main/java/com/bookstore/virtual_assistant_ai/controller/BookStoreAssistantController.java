package com.bookstore.virtual_assistant_ai.controller;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bookstore")
public class BookStoreAssistantController {

    private final OpenAiChatModel chatModel;

    public BookStoreAssistantController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/informations")
    public String bookStoreChat(@RequestParam(value="message",
            defaultValue="What the top bestsellers of the last years?") String message) {
        return chatModel.call(message);
    }

    @GetMapping("/reviews")
    public String bookStoreReview(@RequestParam(value="book", defaultValue="Clean Code") String book){
        PromptTemplate promptTemplate = new PromptTemplate("""
                    Please, make a brief resume of the book {book}
                    and the author biography.
                """);
        promptTemplate.add("book", book);
        return this.chatModel.call(promptTemplate.create())
                .getResult()
                .getOutput()
                .getContent();
    }

    @GetMapping("/stream/informations")
    public Flux<String> bookStoreStreamChatReview(@RequestParam(value="message",
            defaultValue="What the top bestsellers books of the last years?") String message) {
        return chatModel.stream(message);
    }

