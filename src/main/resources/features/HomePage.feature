Feature: Change item order
  Background:
    Given I am logged with username as 'standard_user' and password as 'c2VjcmV0X3NhdWNl'
    And I enter the shop page
    Scenario Outline: Sorting the items by title
      When I choose to sort the items by title from '<Item Order>'
      Then the items are sorted by title from '<Item Order>'
      Examples:
        | Item Order |
        | az         |
        | za         |
    Scenario Outline: Sorting the items by price
      When I choose to sort the items by price from '<price order>'
      Then the items are sorted by price from '<price order>'
      Examples:
        | price order |
        | lohi        |
        | hilo        |
