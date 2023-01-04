package com.udacity.jdnd.c1.review.controller;

import com.udacity.jdnd.c1.review.model.ChatForm;
import com.udacity.jdnd.c1.review.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatController {
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
    public String addChatMessage(ChatForm chatForm, Model model) {
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
