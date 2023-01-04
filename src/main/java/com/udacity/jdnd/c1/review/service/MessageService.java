package com.udacity.jdnd.c1.review.service;

import com.udacity.jdnd.c1.review.model.ChatForm;
import com.udacity.jdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private List<ChatMessage> chatMessages;

    @PostConstruct
    public void postConstruct() {
        this.chatMessages = new ArrayList<>();
    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage chat = new ChatMessage();
        chat.setUsername(chatForm.getUsername());

        switch (chatForm.getMessageType()) {
            case "Say" -> chat.setMessage(chatForm.getMessageText());
            case "Shout" -> chat.setMessage(chatForm.getMessageText().toUpperCase());
            case "Whisper" -> chat.setMessage(chatForm.getMessageText().toLowerCase());
        }

        chatMessages.add(chat);
    }

    public List<ChatMessage> getMessages() {
        return chatMessages;
    }
}
