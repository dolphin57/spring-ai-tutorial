package io.dolphin.alibaba.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ChatClientController {
    private final ChatClient chatClient;

    public ChatClientController(ChatClient.Builder  builder) {
        this.chatClient = builder
//                .defaultSystem("你是一个演员, 请列出你所参演的电影")
                .defaultSystem("你是一个友好的聊天机器人，回答问题时要使用{voice}的语气")
                .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "input") String input) {
        return this.chatClient.prompt()
                .user(input)
                .call()
                .content();
    }

    @GetMapping("/ai")
    Map<String, String> completion(@RequestParam(value = "message", defaultValue = "说一个笑话") String message,
                                   @RequestParam("voice") String voice) {
        return Map.of(
                "completion",
                this.chatClient.prompt()
                        .system(sp -> sp.param("voice", voice))
                        .user(message)
                        .call()
                        .content());
    }
}
