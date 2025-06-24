package io.dolphin.mallordermcpclient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {org.springframework.ai.autoconfigure.mcp.client.SseHttpClientTransportAutoConfiguration.class})
public class MallOrderMcpClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderMcpClientApplication.class, args);
    }

    private String userInput = "查询订单ID为ORD20250414001的订单详情";

    @Bean
    public CommandLineRunner  predefinedQuestions(ChatClient.Builder chatClientBuilder,
                                                  ToolCallbackProvider toolCallbackProvider,
                                                  ConfigurableApplicationContext context) {
        return args -> {
            ChatClient chatClient = chatClientBuilder.defaultTools(toolCallbackProvider).build();
            System.out.println("\n>>> QUESTION: " + userInput);
            System.out.println("\n>>> ASSISTANT: " + chatClient.prompt(userInput).call().content());

            context.close();
        };
    }
}
