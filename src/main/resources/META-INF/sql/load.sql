INSERT INTO users (email) VALUES ('mohammadi2@gmail.com')
INSERT INTO bankaccounts (accountnumber,"User", balance) VALUES ('123','mohammadi2@gmail.com',100)
INSERT INTO bankaccounts (accountnumber,"User", balance) VALUES ('124','mohammadi2@gmail.com',0)
INSERT INTO bankingtransactions (id,transaction_type,amount, creditaccount) VALUES ('1','deposit',100,'123')