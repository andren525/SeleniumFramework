@login
Feature: LogIn
  Background:
    Given I go to the Website
  Scenario Outline: Testing the authentication
    When I specify my username as '<username>' and my password as '<password>'
    Then I can log into the Website

    Examples:
      | username                | password          |
      | standard_user           | c2VjcmV0X3NhdWNl  |
      | problem_user            | c2VjcmV0X3NhdWNl  |
      | performance_glitch_user | c2VjcmV0X3NhdWNl  |
      | error_user              | c2VjcmV0X3NhdWNl  |
      | visual_user             | c2VjcmV0X3NhdWNl  |

  @negative
  Scenario Outline: I login with wrong credentials
    When I specify my username as '<username>' and my password as '<password>'
    Then I expect validation message as '<message>' is displayed
    Examples:
      | username                | password             | message                |
      | locked_out_user         | c2VjcmV0X3NhdWNl     | lockedmessage          |
      | locked_out_user         | d3JvbmdwYXNzd29yZA== | wrongusermessage       |
      | standard_user           | d3JvbmdwYXNzd29yZA== | wrongusermessage       |
      | performance_glitch_user | d3JvbmdwYXNzd29yZA== | wrongusermessage       |
      | problem_user            | d3JvbmdwYXNzd29yZA== | wrongusermessage       |
      |                         |                      | missingusernamemessage |
      |                         | YW55cGFzc3dvcmQ=     | missingusernamemessage |
      | standard_user           |                      | missingpasswordmessage |
      | anyusername             |                      | missingpasswordmessage |
