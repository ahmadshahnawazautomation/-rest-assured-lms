Feature: Library Transactions
  @Smoke
  Scenario Outline: Borrow books successfully
    Given I have valid user credentials
    When I send a POST request to login API "<credentials>"
    Then I should receive a valid authentication token
    And The user should be authorized successfully
    When I borrow the book "<title>"
    Then I should receive a 201 status code
    And the response should contain a success message "<successMessage>"
    And the token should be updated
    Examples:
      | credentials      |title |successMessage|
      | valid credentials |Little Blue Truck|borrowed successfully|
  @Smoke
  Scenario Outline: Return books successfully
    Given I have valid user credentials
    When I send a POST request to login API "<credentials>"
    Then I should receive a valid authentication token
    And The user should be authorized successfully
    When I return the book "<title>"
    Then I should receive a 200 status code
    And the response should contain a success message "<successMessage>"
    And the token should be updated
    Examples:
      | credentials       |title |successMessage|
      | valid credentials |Little Blue Truck|returned successfully|
  @Smoke
  Scenario Outline: Validate borrowing history
    Given I have valid user credentials
    When I send a POST request to login API "<credentials>"
    Then I should receive a valid authentication token
    And The user should be authorized successfully
    When I request my borrowing history
    Then I should receive a 200 status code
    And the response should contain the borrowing history
    And the borrowing history should not be empty
    Examples:
      | credentials       |
      | valid credentials |
