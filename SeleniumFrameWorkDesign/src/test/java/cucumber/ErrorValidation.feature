
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @tag2
  Scenario Outline: User is not logged in with invalid user name and password
    Given Landed on the Ecommerce page
    When I logged in with the username <name> and password <password>
    Then "Incorrect email or password." validation error message is displayed

    Examples: 
      | name  										| password 			|
      | waqarafzal11@yopmail.com 	| Selenium 			|