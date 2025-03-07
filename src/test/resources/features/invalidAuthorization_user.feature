Feature: User Authorization API

  @Smoke
  Scenario: Verify user authorization with invalid credentials
    Given I have invalid user credentials
    When I send a POST request to login API "Invalid credentials"
    Then I should receive an error message "Invalid credentials"

