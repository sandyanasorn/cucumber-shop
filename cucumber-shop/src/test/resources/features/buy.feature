Feature: Buy products
  As a customer
  I want to buy products

  Background:
    Given the store is ready to service customers
    And a product "Bread" with price 20.50 and stock of 5 exists
    And a product "Jam" with price 80.00 and stock of 10 exists
    And a product "Milk" with price 25.00 and stock of 3 exists

  Scenario: Buy one product Bread
    When I buy "Bread" with quantity 2
    Then total should be 41.00

  Scenario: Buy multiple products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    Then total should be 121.00

  Scenario: Buy three different products
    When I buy "Bread" with quantity 1
    And I buy "Jam" with quantity 1
    And I buy "Milk" with quantity 2
    Then total should be 130.50

  Scenario: Buy product with exact stock amount
    When I buy "Milk" with quantity 3
    Then total should be 75.00
    And "Milk" should have 0 items in stock

  Scenario: Try to buy more than available stock
    When I try to buy "Milk" with quantity 5
    Then I should get insufficient stock error
    And total should be 0.00
    And "Milk" should have 3 items in stock

  Scenario: Successfully buy within stock limit
    When I buy "Bread" with quantity 3
    Then total should be 61.50
    And "Bread" should have 2 items in stock

  Scenario: Buy all available stock
    When I buy "Bread" with quantity 5
    Then total should be 102.50
    And "Bread" should have 0 items in stock