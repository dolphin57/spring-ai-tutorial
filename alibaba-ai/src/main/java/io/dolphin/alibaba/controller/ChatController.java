package io.dolphin.alibaba.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder  builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "input") String input) {
        return this.chatClient.prompt()
                .user(input)
                .call()
                .content();
    }

    @GetMapping("/chatR")
    public ChatResponse chatR(@RequestParam(value = "input") String input) {
        return this.chatClient.prompt()
                .user(input)
                .call()
                .chatResponse();
    }

    @GetMapping(value = "/stream",  produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestParam(value = "input") String input) {
        return this.chatClient.prompt()
                .user(input)
                .stream()
                .content();
    }

    /**
     * 演员电影信息类
     */
    record ActorFilms(String actor, List<String> movies){}

    @GetMapping("/movies")
    public ActorFilms movies(@RequestParam(value = "input") String input) throws Exception {
        return this.chatClient.prompt()
                .user(input)
                .call()
                .entity(ActorFilms.class);
    }

    @GetMapping("/moviesL")
    public List<ActorFilms> moviesL(@RequestParam(value = "input") String input) throws Exception {
        return this.chatClient.prompt()
                .user(input)
                .call()
                .entity(new ParameterizedTypeReference<List<ActorFilms>>() {});
    }

}
