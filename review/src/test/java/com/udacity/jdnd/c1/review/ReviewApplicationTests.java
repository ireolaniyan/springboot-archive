package com.udacity.jdnd.c1.review;

import com.udacity.jdnd.c1.review.model.ChatMessage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewApplicationTests {

	@LocalServerPort
	private int port;
	private static WebDriver webDriver;
	private String baseURL;

	@BeforeAll
	public static void beforeAll() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver(chromeOptions);
	}

	@AfterAll
	public static void afterAll() {
		webDriver.quit();
		webDriver = null;
	}

	@BeforeEach
	public void beforeEach() {
		baseURL = "http://localhost:" + port;
	}

	@Test
	void testUserSignupLoginAndSubmitMessage() {
		String username = "ireolaniyan";
		String password = "secr3tpassw0rd";
		String message = "Hello!";

		webDriver.get(baseURL + "/signup");
		SignUpPage signUpPage = new SignUpPage(webDriver);
		signUpPage.signup("Ire", "Olaniyan", username, password);

		webDriver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(webDriver);
		loginPage.login(username, password);

		ChatPage chatPage = new ChatPage(webDriver);
		chatPage.sendChatMessage(message);
		ChatMessage sentMessage = chatPage.getChatMessage();

		assertEquals(username, sentMessage.getUsername());
		assertEquals(message, sentMessage.getMessage());
	}

}
