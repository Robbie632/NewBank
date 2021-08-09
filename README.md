# Cli Bank
Contains java code for the NewBank command line interface implemented for Software Engineering 2, Computer Science MSc, University of Bath.


# Background 

---

The application consists of a server (server folder) which contains the business logic for the bank and a front-end (client folder) which allows a user to interact with the server.

# Contents

---


1. [Getting started](#getting-started)
2. [Running the application](#running-the-application)
    - [Method 1 - Using intellij](#option-1---use-intellij)
    - [Method 2 - Using the command line](#option-2---from-the-command-line)
3. [Logging In](#logging-in)
 
4. [Supported commands](#commands) 
   - [Navigation](#navigation)
   - [Accounts](#showmyaccounts)
      - [View accounts](#showmyaccounts)
      - [Create accounts](#creating-accounts)
      - [Transfer between accounts](#transfer-between-accounts)
   - [Loans](#loans)
      - [View loans](#loans)
   - [Payments](#pay-another-user)
   - [Transactions](#transactions)
   
<br>

# Getting started

---

```bash
git clone https://github.com/Robbie632/NewBank.git
```

## Running the application

To run and interact with the bank you need to 

### Compile the code

#### Option 1 - Use Intellij

If using Intellij the open up the project and navigate the Main.java file and click play

#### Option 2 - From the command line

Navigate to the root of the project and run the command below. (This compiles all the code and outputs it into a directory called ``'out'``)

```bash
javac -d ./out ./client/*.java ./server/*.java ./Main.java
```

To start the application run (This runs the compiled Main file within the out directory)

```bash
java -classpath ./out/ Main
```

<br>

## Logging In

---

The cli tool currently has a hardcoded set of users that it checks when you log in. To create a new user you need to 
manually go into the ``NewBank.java`` class and add yourself in the method below. Then re-compile and run the code 
steps from above


````java
	private void addTestData() {
		Customer bhagy = new Customer();
		//set password
		bhagy.setPassword("1");
		bhagy.addAccount(new Account("Main", 1000.0));
		bhagy.addAccount(new Account("Savings", 1000.0));
		customers.put("b", bhagy);
		
		Customer christina = new Customer();
		//set password
		christina.setPassword("2");
		christina.addAccount(new Account("Savings", 1500.0));
		christina.addAccount(new Account("Main", 100.0));
		customers.put("c", christina);
		
		Customer john = new Customer();
		//set password
		john.setPassword("3");
		john.addAccount(new Account("Main", 250.0));
		john.addAccount(new Account("Savings", 250.0));
		customers.put("j", john);

		/* Create  a new user here by uncommenting the below and setting values  */

//		Customer john = new Customer();
//		john.addAccount(new Account("Checking", 250.0));
//		customers.put("New user", john);

	}
````

<br>


##Commands

### Accounts

---



##### Show accounts:

To view the accounts for a user enter ````SHOWMYACCOUNTS````

This will show a list of accounts as follows:

```Main opening balance : 250.0 currentBalance : 250.0```
```Savings opening balance : 250.0 currentBalance : 250.0```

<br>

#### Creating accounts:

To create a a new account for a user enter ````NEWACCOUNT <NAME_OF_NEW_ACCOUNT> ````

Where ```<Name>``` is the name of the new account 

- If the account does not already exist,the response should be: ```SUCCESS```

- If account already exists for customer, response should be ```FAIL```

Then a prompt to select other commands or terminate the program is shown:

```What else would you like to do?```

```SHOWMYACCOUNTS - to view a list of your accounts and their balance```

```NEWACCOUNT <Name> - to open a new account with the name you provide```

```MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another```

```PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing```

```SHOWMYTRANSACTIONS - to return information on all transactions done during the session```

```END - TO exit NewBank.```

<br>


### Transfer between accounts

---

To create a payment use the command ``MOVE [Amount] [From] [To]``

where:

```[amount]```  is the amount of money to be moved

```[fromAccount]``` is the name of an existing account associated with the person you are logged in as to move money from

```[toAccount]``` is the name of an existing account associated with the person you are logged in as to move money to

e.g  ``enter MOVE 50 Main Savings``

```Please confirm the above details are correct (Y/N)```

- enter Y and the command entered is executed

- enter N and the command is not executed

- if wrong input is inputted, statement indicating this is printed:

```Wrong input, please enter 'Y' for yes or 'N' for no```

- if accounts ```[From]``` and ```[To]``` are not the same, and they both already exist for customer, response should be:

```SUCCESS```

- if accounts ```[From]``` and ```[To]``` are the same, or they both don't already exist for customer, response should be:

```FAIL```

- To confirm that a user is not allowed to have a negative balance

e.g. enter ``MOVE 500 Main Savings``

```Insufficient funds``` - The command is not executed and fails

<br>

### Loans

---

To display the loans use the command ````SHOWMYLOANS````

You will see the below (e.g. below using the user j with test data)
````shell 
Request from j
Total borrowed : £ 150.0         rate: 10.0%
````

Additional command will be supported such as
- Adding loans
- Making loans to other user
- Paying off loans

<br>

### Pay another user

---

To pay another user enter the command ``PAY [Person/Company] [Amount]``

where:

```[Person/Company]``` is the name of the person or company you wish to pay

```[Amount]``` is the amount of money you wish to pay them

e.g enter ``PAY c 50``

```Please confirm the above details are correct (Y/N)```

- enter Y and the command entered is executed

- enter N and the command is not executed

- if wrong input is inputted, statement indicating this is printed:

```Wrong input, please enter 'Y' for yes or 'N' for no```

- if the customer to pay the money to exists and the money is transferred to the payee:

```SUCCESS```

- if account chosen to pay is the same as that of the customer instantiating the payment or the customer does not exist

```FAIL```

- To confirm that a user is not allowed to have a negative balance

- enter PAY c 500

```Insufficient funds``` - The command is not executed and fails


### Transactions

---

To view a list of all transactions enter ```SHOWMYTRANSACTIONS```

This returns information on all transactions done during the session,
example output after having moved money is shown below:

```
transaction type was : 
move
Involved parties were: 
Savings
Main
amount was: 
1234.0
SUCCESS
```
### Navigation

---

The navigation menu below is displayed after each command:

```What else would you like to do?```

```SHOWMYACCOUNTS - to view a list of your accounts and their balance```

```SHOWMYLOANS - to view a list of loans```

```NEWACCOUNT <Name> - to open a new account with the name you provide```

```MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another```

```PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing```

```END - TO exit NewBank.```

```What else would you like to do?```


### Tests

---

### Manual Tests: 

- run code via command line (as explained above)

- enter username: 

```j```

- enter password 

```3```

- the response should be: 

```Log In Successful. What do you want to do?```

```SHOWMYACCOUNTS - to view a list of your accounts and their balance```

```NEWACCOUNT <Name> - to open a new account with the name you provide```

```MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another```

```PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing```

```SHOWMYTRANSACTIONS - to return information on all transactions done during the session```

```END - TO exit NewBank.```

- do same as above but enter wrong username or password

- do same as above but enter wrong username 


- response should be: 

```Username entered is valid.``` 

Then it asks for username and password again

- Enter correct username but incorrect password

- response should be:

```incorrect username inputted```
  


