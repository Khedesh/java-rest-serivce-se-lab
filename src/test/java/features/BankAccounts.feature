Feature: Create Bank Account
  A Bank Account should be registered by unique Account Number

  Scenario: Create some user
    Given I have registered a user with email "salam@alayk"
    When I register a bank account with account number "123456" for user "salam@alayk"
    Then I should find a bank account with account number "123456" and balance 0 for user "salam@alayk"
