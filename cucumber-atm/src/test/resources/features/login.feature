Feature: ATM Login
  As a bank customer
  I want to login to ATM system
  So that I can access my account

  Background:
    Given ATM system is ready
    And account "12345" with balance 1000.0 exists
    And account "67890" with balance 500.0 exists

  Scenario: Login with valid account
    When I login with id "12345"
    Then I should see welcome message
    And I should be logged in

  Scenario: Login with non-existent ID
    When I login with id "99999"
    Then I should see "Account not found" error
    And I should not be logged in