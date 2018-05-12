Feature: Create user
  User should be registered by unique email address

  Scenario: Create some user
    When i register a user with emai address "salam@alayk"
    Then i should find a user with email address "salam@alayk"
