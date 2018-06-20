Feature: Create user
  User should be registered by unique email address

  Scenario: Create some user
    When I register a user with email address "salam@alayk"
    Then I should find a user with email address "salam@alayk"

  Scenario: Create bad user
    When I register a user with email address "salamalayk"
