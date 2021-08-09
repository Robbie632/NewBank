# NewBank CLI
This repository contains code (Java) to enable the NewBank Command Line Interface (CLI). This is implemented for the University of Bath Software Engineering 2 module, for the Computer Science MSc.


# Background

---

The application consists of a server (server folder) which contains the business logic for the bank and a front-end (client folder) which allows an authorised user to interact with the server.

# Contents

---


1. [Getting started](#getting-started)
2. [Running the application](#running-the-application)
    - [Method 1 - Using IntelliJ](#option-1---use-intellij)
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

To run and interact with the bank you need to:

### Compile the code

#### Option 1 - Use Intellij

If using Intellij, open the project, navigate to the Main.java file, and click play.

#### Option 2 - From the command line

If working from the command line, navigate to the root of the project and run the following command:

```bash
javac -d ./out ./client/*.java ./server/*.java ./Main.java
```
This compiles all the code and outputs it into a directory called ``'out'``

To start the application run the following command:

```bash
java -classpath ./out/ Main
```
This runs the compiled Main file within the new ``'out'`` directory.
<br>

## Logging In

---

The CLI tool currently has a hardcoded set of users that it checks when you log in. To create a new user
manually navigate to the ``NewBank.java`` class and add the new user to the below method. The code should then be re-compiled and may be run as described in Section 2.


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

This will show a list of accounts, for example:

````Main opening balance : 250.0 currentBalance : 250.0````
````Savings opening balance : 250.0 currentBalance : 250.0````

<br>

#### Creating accounts:

To create a new account for an authorised user enter: ````NEWACCOUNT <NAME_OF_NEW_ACCOUNT> ````

Where ```<Name>``` is the name of the new account.

- If the account does not already exist, the response should be: ```SUCCESS```

- If account already exists for customer, the response should be ```FAIL```

A prompt to either select other commands or terminate the program is then shown:

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

Where:

```[amount]```  is the amount of money to be moved

```[fromAccount]``` is the name of an existing account associated with the person you are logged in as to move money from

```[toAccount]``` is the name of an existing account associated with the person you are logged in as to move money to

e.g  ``enter MOVE 50 Main Savings``

```Please confirm the above details are correct (Y/N)```

- enter Y and the command entered is executed.

- enter N and the command entered is not executed.

- if wrong input is submitted, this is indicated:

```Wrong input, please enter 'Y' for yes or 'N' for no```

- if accounts ```[From]``` and ```[To]``` are not the same, and they both already exist for customer, the response should read:

```SUCCESS```

- if accounts ```[From]``` and ```[To]``` are the same, or at least one account does not exist, the response should read:

```FAIL```

- To confirm that a user is not allowed to have a negative balance:

e.g. enter ``MOVE 500 Main Savings`` when there are less than 500 in the account.

```Insufficient funds``` - The command is not executed and fails

<br>

### Loans

---

To display the loans use the command ````SHOWMYLOANS````

You will see the below (e.g. using the user j with test data)
````shell
Request from j
Total borrowed : £ 150.0         rate: 10.0%
````

Additional commands shall be supported such as
- Adding loans.
- Making loans to other users.
- Paying off loans.

<br>

### Pay another user

---

To pay another user enter the command ``PAY [Person/Company] [Amount]``

where:

```[Person/Company]``` is the name of the person or company you wish to pay

```[Amount]``` is the amount of money you wish to pay them

e.g. enter ``PAY c 50``

```Please confirm the above details are correct (Y/N)```

- enter Y and the command entered is executed.

- enter N and the command entered is not executed.

- if the wrong input is submitted, a statement indicating this is printed:

```Wrong input, please enter 'Y' for yes or 'N' for no```

- if the customer to pay the money to exists, and all other conditions are met (e.g. enough money in the outgoing account), then the money is transferred to the payee:

```SUCCESS```

- if the incoming account selected is the same as that of the customer instantiating the payment, or if the payee does not exist:

```FAIL```

- If the payment would result in the outgoing account having a negative balance, the command is not executed and fails.

- enter PAY c 500

```Insufficient funds```


### Transactions

---

To view a list of all transactions enter ```SHOWMYTRANSACTIONS```

This returns information on all transactions completed during the session,
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

- run code via command line (see Section 2)

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

- Follow the same process as described above, with the wrong username or password.

- Wrong username:

- response should be:

```Username entered is invalid.```

Then the username and password are requested again

- Enter the correct username but incorrect password

- response should be:

```incorrect username inputted```

Then the username and password are requested again
