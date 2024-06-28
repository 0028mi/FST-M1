@activity4
Feature: Data driven test without Example

@loginTest @loginSuccess
Scenario: Testing with Data from Scenario
    Given User is on Login page
    When User enters "admin" and "password"
    Then Read the page title and confirmation message as "Welcome Back, admin"
    And Close the Browser