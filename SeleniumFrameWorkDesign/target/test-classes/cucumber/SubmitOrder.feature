
@tag
Feature: Purchase the Order from Ecommerce Website
  I want to use this template for my feature file
  
  
  Background:
  Given Landed on the Ecommerce page

  @Regression
  Scenario: Submit Purchase Order with Positive Data
    Given I logged in with the username <name> and password <password>
    When I add the product <productname> to the Cart
    And Checkout product <productname> and submit the order and search countryname <countryname>
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page


    Examples: 
      | name  									| password 			| productname  					|	countryname		|
      | waqarafzal@yopmail.com 	| Selenium123 	| ADIDAS ORIGINAL 			| Ind						|
