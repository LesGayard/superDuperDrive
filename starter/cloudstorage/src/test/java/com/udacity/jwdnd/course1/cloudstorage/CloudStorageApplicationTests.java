package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.controller.SignUpControllerTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	private static final String firstname = "Hulk";
	private static final String lastname = "de Gaard";
	private static final String username = "Yeahh";
	private static final String password = "password";

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {

		WebDriverManager.chromedriver().setup();
	}



	@BeforeEach
	public void beforeEach() {

		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}


	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void signupUserTest(){

		driver.get("http://localhost:" + this.port + "/signup");
		SignUpControllerTest signUpControllerTest = new SignUpControllerTest(driver);

		signUpControllerTest.signup(firstname,lastname,username,password);
		Assertions.assertEquals("Sign Up",driver.getTitle());
	}

	@Test
	public void test(){
		driver.get("http://google.com");
	}

	@Test
	public void testLocalhost(){
		driver.get("http://localhost:" + this.port);
	}
}
