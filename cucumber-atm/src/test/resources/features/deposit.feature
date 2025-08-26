Feature: Deposit Money
  As a bank customer
  I want to deposit money
  So that I can increase my account balance

  Background:
    Given ATM system is ready for transactions
    And account "12345" with balance 1000.0 exists
    And I am logged in with account "12345"

  Scenario: Successful deposit
    When I deposit 500.0
    Then my balance should be 1500.0
    And I should see "Deposit successful" message

  Scenario: Deposit with invalid amount
    When I deposit -100.0
    Then my balance should be 1000.0
    And I should see "Invalid amount" error

  Scenario: Deposit zero amount
    When I deposit 0.0
    Then my balance should be 1000.0
    And I should see "Invalid amount" error

  Scenario Outline: Multiple deposit amounts
    When I deposit <amount>
    Then my balance should be <expected_balance>

    Examples:
      | amount | expected_balance |
      | 100.0  | 1100.0          |
      | 250.50 | 1250.50         |
      | 1000.0 | 2000.0          |