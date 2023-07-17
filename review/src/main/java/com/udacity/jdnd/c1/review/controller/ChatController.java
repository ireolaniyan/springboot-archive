package com.udacity.jdnd.c1.review.controller;

import com.udacity.jdnd.c1.review.model.ChatForm;
import com.udacity.jdnd.c1.review.service.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private static final Logger LOGGER = Logger.getLogger(ChatController.class.getName());
    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String showChatPage(ChatForm chatForm, Model model) {
        model.addAttribute("chatMessages", messageService.getMessages());
        return "chat";
    }

    @PostMapping
    public String addChatMessage(ChatForm chatForm, Model model, Authentication authentication) {
        String username = authentication.getName();
        LOGGER.log(Level.INFO, String.format("%s - %s", "LOGGED IN USER", username));
        chatForm.setUsername(username);

        messageService.addMessage(chatForm);
        chatForm.setMessageText("");
        model.addAttribute("chatMessages", messageService.getMessages());
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public String[] allMessageTypes() {
        return new String[]{"Say", "Shout", "Whisper"};
    }
}
