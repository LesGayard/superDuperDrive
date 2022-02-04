package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.controller.*;
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

	private static final String noteTitle = "noteTitle";
	private static final String noteDescription = "This is the note description test !! ";

	private static final String noteTitleUpdate = "noteTitle updated";
	private static final String noteDescriptionUpdate = "This is the note update description test !! ";

	private static final String title = "title";
	private static final String url = "https://google.com ";
	private static final String credentialPassword= "Credential Password !";



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

	/* SIGN UP A NEW USER TEST AND LOG IN THE REGISTERED USER VERIFIES THEY CAN ACCESS THE HOME PAGE */
	@Test
	public void signupLoginUserHomeTest(){

		driver.get("http://localhost:" + this.port + "/signup");
		SignUpControllerTest signUpControllerTest = new SignUpControllerTest(driver);

		/* sign up */
		signUpControllerTest.signup(firstname,lastname,username,password);
		Assertions.assertEquals("Sign Up",driver.getTitle());

		driver.get("http://localhost:" + this.port + "/");
		LoginControllerTest loginControllerTest = new LoginControllerTest(driver);

		/* log in and get the home page */
		loginControllerTest.login(username,password);
		Assertions.assertEquals("Home",driver.getTitle());

		/* logout from home to the login page */
		driver.get("http://localhost:" + this.port + "/home");
		HomeControllerTest homeControllerTest = new HomeControllerTest(driver);
		homeControllerTest.logout();
		Assertions.assertEquals("Login", driver.getTitle());

	}

	/* LOGS THE  USER IN WITHOUT REGISTRATION MUST FAIL*/
	@Test
	public void loginWithoutAuthentication(){
		driver.get("http://localhost:" + this.port + "/");
		LoginControllerTest loginControllerTest = new LoginControllerTest(driver);

		loginControllerTest.login(firstname,password);
		Assertions.assertNotEquals("Sign Up",driver.getTitle());
	}

	/* Write a Selenium test that logs in an existing user,
	creates a note and verifies that the note details are visible in the note list.*/
	@Test
	public void createNoteTest() throws InterruptedException{
		driver.get("http://localhost:" + this.port + "/signup");
		SignUpControllerTest signUpControllerTest = new SignUpControllerTest(driver);

		/* sign up */
		signUpControllerTest.signup(firstname,lastname,username,password);
		Assertions.assertEquals("Sign Up",driver.getTitle());

		driver.get("http://localhost:" + this.port + "/");
		LoginControllerTest loginControllerTest = new LoginControllerTest(driver);

		/* log in and get the home page */
		loginControllerTest.login(username,password);
		Assertions.assertEquals("Home",driver.getTitle());

		/* create a new note */
		driver.get("http://localhost:" + this.port + "/home");
		NoteControllerTest noteControllerTest = new NoteControllerTest(driver);

		noteControllerTest.displayNote();
		Assertions.assertEquals("Home", driver.getTitle());

		Thread.sleep(2000);
		noteControllerTest.createNote(noteTitle,noteDescription);
		Assertions.assertEquals("Home", driver.getTitle());
	}

	/* Write a Selenium test that logs in an existing user with existing notes,
	clicks the edit note button on an existing note,
	changes the note data, saves the changes, and verifies that the changes appear in the note list. */
	@Test
	public void updateNoteTest() throws InterruptedException{
		createNoteTest();
		Thread.sleep(4000);
		driver.get("http://localhost:" + this.port + "/home");

		NoteControllerTest noteControllerTest = new NoteControllerTest(driver);
		noteControllerTest.displayNote();
		Thread.sleep(4000);
		noteControllerTest.updateNote(noteTitleUpdate,noteDescriptionUpdate);

		Assertions.assertEquals("Home", driver.getTitle());
	}

	/* Write a Selenium test that logs in an existing user with existing notes,
	 clicks the delete note button on an existing note,
	  and verifies that the note no longer appears in the note list. */
	@Test
	public void deleteNoteTest() throws InterruptedException{
		createNoteTest();
		Thread.sleep(3000);

		driver.get("http://localhost:" + this.port + "/home");
		NoteControllerTest noteControllerTest = new NoteControllerTest(driver);
		noteControllerTest.displayNote();
		Thread.sleep(3000);
		noteControllerTest.deleteNote();

		Assertions.assertEquals("Home", driver.getTitle());
	}

	/*Write a Selenium test that logs in an existing user,
	creates a credential and verifies that the credential details are visible in the credential list.*/
	@Test
	public void createCredential() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/signup");
		SignUpControllerTest signUpControllerTest = new SignUpControllerTest(driver);

		/* sign up */
		signUpControllerTest.signup(firstname,lastname,username,password);
		Assertions.assertEquals("Sign Up",driver.getTitle());

		driver.get("http://localhost:" + this.port + "/");
		LoginControllerTest loginControllerTest = new LoginControllerTest(driver);

		/* log in and get the home page */
		loginControllerTest.login(username,password);
		Assertions.assertEquals("Home",driver.getTitle());

		/* Go to the credential tab */
		driver.get("http://localhost:" + this.port + "/home");
		CredentialControllerTest credentialControllerTest = new CredentialControllerTest(driver);

		credentialControllerTest.displayCredential();
		Thread.sleep(4000);
		/* Create the credential */
		credentialControllerTest.createCredential(url,username,credentialPassword);

		Assertions.assertEquals("Home", driver.getTitle());
	}


}
