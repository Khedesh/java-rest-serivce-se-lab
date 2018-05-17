Feature: Banking Transactions
  Do multiple types of banking transactions

  Scenario: Deposit transaction
    Given I have registered a user with email "salam@alayk"
    And I have registered a bank account with account number "123456" for user "salam@alayk"
    When I deposit 200 units in account "123456"
    Then I should find a bank account with account number "123456" and balance 200 for user "salam@alayk"
    And I should find a deposit transaction on credit account "123456" with amount 200

  Scenario: Withdraw transaction
    Given I have registered a user with email "salam@alayk"
    And I have registered a bank account with account number "123456" for user "salam@alayk"
    When I deposit 200 units in account "123456"
    And I withdraw 50 units from account "123456"
    Then I should find a bank account with account number "123456" and balance 150 for user "salam@alayk"
    And I should find a deposit transaction on credit account "123456" with amount 200
    And I should find a withdraw transaction on credit account "123456" with amount 50

  Scenario: Transfer transaction
    Given I have registered a user with email "salam@alayk"
    And I have registered a bank account with account number "123456" for user "salam@alayk"
    And I have registered a bank account with account number "1234567" for user "salam@alayk"
    When I deposit 200 units in account "123456"
    And I Transfer 50 units from account "123456" to account "1234567"
    Then I should find a bank account with account number "123456" and balance 150 for user "salam@alayk"
    And I should find a bank account with account number "1234567" and balance 50 for user "salam@alayk"
    And I should find a deposit transaction on credit account "123456" with amount 200
    And I should find a transfer transaction from account "123456" to account "1234567" with amount 50