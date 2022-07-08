
DROP TABLE IF EXISTS ATM_DETAILS;  
CREATE TABLE ATM_DETAILS (  
atm_id INT NOT NULL PRIMARY KEY,  
denomination_5  INT NOT NULL default 0,  
denomination_10  INT NOT NULL default 0,  
denomination_20  INT NOT NULL default 0,  
denomination_50  INT NOT NULL default 0,  
total_amount  INT NOT NULL default 0
);  


DROP TABLE IF EXISTS ACCOUNT_DETAILS;  
CREATE TABLE ACCOUNT_DETAILS (  
account_number int NOT NULL PRIMARY KEY,  
pin  INT NOT NULL default 0,  
balance  INT NOT NULL default 0,  
overdraft  INT NOT NULL default 0
);  

DROP TABLE IF EXISTS TRANSACTION_DETAILS;  
CREATE TABLE TRANSACTION_DETAILS ( 
transaction_id varchar(50) NOT NULL PRIMARY KEY,
transaction_date timestamp not null,
account_number  INT NOT NULL, 
opening_balance  INT NOT NULL default 0,
withdrawal  INT NOT NULL default 0,  
deposit  INT NOT NULL default 0,  
closing_balance  INT NOT NULL default 0
); 




