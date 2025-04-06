@tag
Feature: Error Validation
  I want to use this template for my feature file
	
	Background:
	Given I landed on Ecommerce Page
	
  @ErrorValidation
  Scenario Outline: Negative Login Scenario
    Given I login with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username  							| password|	
      | randomschitz@gmail.com 	|	Abh@1234|
