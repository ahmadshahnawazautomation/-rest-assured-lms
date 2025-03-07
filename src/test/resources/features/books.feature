Feature: Verify Books scenarios
  @Smoke
  Scenario Outline: Fetch all books
    Given I have valid user credentials
    When I send a POST request to login API "<credentials>"
    Then I should receive a valid authentication token
    And The user should be authorized successfully
    When I request to get all books
    Then I should receive a 200 status code
    And the response body should not be empty
    And the total available copies should be greater than zero
    Examples:
      | credentials       |
      | valid credentials |


  @Smoke
  Scenario Outline: Fetch books by title
    Given I have valid user credentials
    When I send a POST request to login API "valid credentials"
    Then I should receive a valid authentication token
    And The user should be authorized successfully
    When I request to get books by title "<title>"
    Then I should receive a 200 status code
    And the response body should contain "<title>"

    Examples:
      | title                |
      | The Very Busy Spider |
      | Little Blue Truck    |
      | The Day the Crayons Quit|
  @Smoke
  Scenario Outline: Fetch books by author
    Given I have valid user credentials
    When I send a POST request to login API "valid credentials"
    Then I should receive a valid authentication token
    And The user should be authorized successfully
    When I request to get books by author "<author>"
    Then I should receive a 200 status code
    And the response body should contain "<author>"

    Examples:
      | author           |
      | Jane Yolen       |
      | Audrey Penn      |
      | Beatrix Potter   |

