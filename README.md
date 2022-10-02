# elated-silver-5908
## _Online Banking System_
Online Banking system is an application which automates manual tasks, like managing transactions, transfering money, checking balance, etc.

## Java Concepts used in this projects
- OOPs
- Exception Handling
- Inheritance
- DAO pattern
- Date and time handling

## Features
- Create Account
- Edit Account Details
- Delete Account
- Transfer Money
- Check Transaction History

Online Banking System can be used by accountant and customer.

### Accountant functionalities
- Create new account for customer
- Edit customers account details using their account number
- Removing any account using account number
- Viewing customers account details using account number
- View all the account at once
 
### Customer Functionalities
- Transfering Money to someone using account number.
- Checking Transaction history
- Checking available balance

# How to run Online banking system

##### Create MySQL Database and Tables

CREATE DATABASE project1

--Customer table to store customers data
CREATE TABLE customer_details
(
customer_id int primary key,
first_name varchar(20),
last_name varchar(20),
gender varchar(10),
age int,
mob varchar(12),
email varchar(30),
dob date,
city varchar(12),
account_type varchar(10),
account_num int,
balance int not null,
password varchar(30)
);

--Transation table to store transaction history
CREATE TABLE 
(
cid int,
recievers_acc int,
dot DATE,
senders_acc int,
transaction_amount INT(7),
balance int not null
);
---
Accountants Email-ID and Password:-
- 123@gmail.com
- 1234

#### Workflow of this project :-

##### Accountant's workflow
- Login as accountant(use email-id and password provided above)
- Add new account and fill all the details
- View account details using account number and check if all the data filled by you is correct or not.
- If you want to edit some details, the select edit option and update the details.
- Select exit after your work has been done
- 
##### Customer's workflow
- Login as customer(use email-id and password submitted at the time of account creation)
- Check balance
- Transfer money to someone
- Check Transaction history

