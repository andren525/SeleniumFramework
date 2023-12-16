Feature: Add product to cart
  Background:
    Given I am logged with username as 'standard_user' and password as 'c2VjcmV0X3NhdWNl'
    And I enter the shop page
    Scenario: adding an item
      When I add an item to the cart
      Then an item is present in the cart
    Scenario: adding an item from the item page
      When I go to the item page
      And I add the item to the cart
      Then cart is been updated
    Scenario: removing an item from the shop page
      And I add an item to the cart
      When I remove the item
      Then cart displays no items
    Scenario: removing an item from the cart page
      And I add an item to the cart
      And I am on the cart page
      When I remove the item from the cart
      Then There are no items displayed in the cart page
    Scenario: removing an item from the cart page
      And I go to the item page
      And I add the item to the cart
      When I remove the item from the item page
      Then there are no items displayed in the cart