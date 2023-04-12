package com.udacity.jdnd.c1.review.service;

import com.udacity.jdnd.c1.review.mapper.MessageMapper;
import com.udacity.jdnd.c1.review.model.ChatForm;
import com.udacity.jdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage chat = new ChatMessage();
        chat.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say" -> chat.setMessage(chatForm.getMessageText());
            case "Shout" -> chat.setMessage(chatForm.getMessageText().toUpperCase());
            case "Whisper" -> chat.setMessage(chatForm.getMessageText().toLowerCase());
        }

        messageMapper.addChatMessage(chat);
    }

    public List<ChatMessage> getMessages() {
        return messageMapper.getChatMessages();
    }
}
