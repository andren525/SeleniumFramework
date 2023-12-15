Feature: Checkout
  Background:
    Given I am logged with username as 'standard_user' and password as 'c2VjcmV0X3NhdWNl'
    And I enter the shop page
    Scenario:
      When I add an item to the cart
      Then an item is present in the cart
