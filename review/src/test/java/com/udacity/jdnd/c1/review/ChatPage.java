package com.udacity.jdnd.c1.review;

import com.udacity.jdnd.c1.review.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {
    @FindBy(id = "messageText")
    private WebElement textField;
    @FindBy(id = "submitMessage")
    private WebElement submitButton;
    @FindBy(className = "chatMessageUsername")
    private WebElement chatMessageUsername;
    @FindBy(className = "chatMessageText")
    private WebElement chatMessageText;

    public ChatPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void sendChatMessage(String message) {
        this.textField.sendKeys(message);
        this.submitButton.click();
    }

    public ChatMessage getChatMessage() {
        ChatMessage result = new ChatMessage();
        result.setUsername(chatMessageUsername.getText());
        result.setMessage(chatMessageText.getText());
        return result;
    }
}
