@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
		Given I landed on Ecommerce Page
	
  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given I login with username <username> and password <password>
    When I add the <productName> to cart
    And I checkout <productName> and submit the order
    Then " Thankyou for the order. " message is displayed on Confirmation Page

    Examples: 
      | username  							| password |	productName			|
      | randomschitz@gmail.com 	|	Abhi@1234| 	ZARA COAT 3			|
      | kiwenil128@lxheir.com 	|	Abhi@1234| 	ADIDAS ORIGINAL	|