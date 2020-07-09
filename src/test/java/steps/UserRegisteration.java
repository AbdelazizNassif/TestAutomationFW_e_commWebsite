package steps;

import org.testng.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.UserRegistrationPage;
import tests.TestBase;
/*Inheritance to get driver*/
public class UserRegisteration extends TestBase{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	
	@Given("^the user is in the home page$")
	public void the_user_is_in_the_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		
		registerObject = new UserRegistrationPage(driver);
	}

	@When("^I click on register link$")
	public void i_click_on_register_link() {
	    // Write code here that turns the phrase above into concrete actions
	    Assert.assertTrue(driver.getCurrentUrl().contains("register"));
	}
	
	/*
	@When("^I entered the user data$")
	public void i_entered_the_user_data(){
	    // Write code here that turns the phrase above into concrete actions
		registerObject.userRegistration("Mustafa", "Nabil", "MustafaNabil94@test.com", "123456789");
	}
	*/
	/*Reading data from feature file --DDT with features*/
	@When("^I entered \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void i_entered(String firstname, String lastname, String email, String password){
	    // Write code here that turns the phrase above into concrete actions
		registerObject.userRegistration(firstname, lastname, email, password);

	}

	@Then("^the registeration page displayed successfully$")
	public void the_registeration_page_displayed_successfully(){
	    // Write code here that turns the phrase above into concrete actions
		registerObject.userLogout();
	}
}
