Feature: User Resgisteration
	I want to check that the user can register to e-commerce website.
	
	Scenario Outline: User Registeration
										 
	Given the user is in the home page
	When  I click on register link
	And   I entered "<firstname>", "<lastname>", "<email>", "<password>"
	Then  the registeration page displayed successfully
	
	Examples:
 	| firstname | lastname | email | password |
 	| ahmed | mohamed | ahmedTurnkeyn2@test.com | 123456789 |
 	| mostafi | turky | mostafiTurnkey2@test.com | 123456789 |
 	
 	