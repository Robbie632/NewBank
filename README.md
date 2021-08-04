# Cli Bank
Contains java code for the NewBank command line interface implemented for Software Engineering 2, Computer Science MSc, University of Bath.

# Background

The application consists of a server (server folder) which contains the business logic for the bank and a front-end (client folder) which allows a user to interact with the server.

# Getting started
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

## Logging in

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



## Tests

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
  

#### SHOWMYACCOUNTS:

- enter SHOWMYACCOUNTS

- shows a list of accounts as follows:

```Main opening balance : 250.0 currentBalance : 250.0```
```Savings opening balance : 250.0 currentBalance : 250.0```

- then a prompt to select other commands or terminate the program is shown:

```What else would you like to do?```

```SHOWMYACCOUNTS - to view a list of your accounts and their balance```
```NEWACCOUNT <Name> - to open a new account with the name you provide```
```MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another```
```PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing```
```END - TO exit NewBank.```


### Loans

To display the loans (use the command below)

````shell
SHOWMYLOANS
````

You will see the below (e.g. below using the user j with test data)
````shell 
Request from j
Total borrowed : £ 150.0         rate: 10.0%
````

Additional command will be supported such as
- Adding loans
- Making loans to other user
- Paying off loans


#### NEWACCOUNT [Name]:
where :
[Name] : the name of a new account

- enter NEWACCOUNT Checking

- if account does not already exist for customer, response should be:

```SUCCESS```

- if account already exists for customer, response should be:

```FAIL```

- then a prompt to select other commands or terminate the program is shown:

```What else would you like to do?```

```SHOWMYACCOUNTS - to view a list of your accounts and their balance```
```NEWACCOUNT <Name> - to open a new account with the name you provide```
```MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another```
```PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing```
```SHOWMYTRANSACTIONS - to return information on all transactions done during the session```
```END - TO exit NewBank.```



#### MOVE [Amount] [From] [To]:
where :

[amount] : the amount of money to be moved

[fromAccount] : name of an existing account associated with the person you are logged in as to move money from

[toAccount] : name of an existing account associated with the person you are logged in as to move money to


- enter MOVE 50 Main Savings

- if accounts [From] and [To] are not the same, and they both already exist for customer, response should be:

```SUCCESS```

- if accounts [From] and [To] are the same, or they both don't already exist for customer, response should be:

```FAIL```

- then a prompt to select other commands or terminate the program is shown:

```What else would you like to do?```

```SHOWMYACCOUNTS - to view a list of your accounts and their balance```
```NEWACCOUNT <Name> - to open a new account with the name you provide```
```MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another```
```PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing```
```SHOWMYTRANSACTIONS - to return information on all transactions done during the session```
```END - TO exit NewBank.```



#### PAY [Person/Company] [Amount]:
where :
[Person/Company] : the name of the person or company you wish to pay

[Amount] : the amount of money you wish to pay them

- enter PAY c 50

- if the customer to pay the money to exists and the money is transferred to the payee:

```SUCCESS```

- if account chosen to pay is the same as that of the customer instantiating the payment or the customer does not exist

```FAIL```

- then a prompt to select other commands or terminate the program is shown:

```What else would you like to do?```

```SHOWMYACCOUNTS - to view a list of your accounts and their balance```
```NEWACCOUNT <Name> - to open a new account with the name you provide```
```MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another```
```PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing```
```SHOWMYTRANSACTIONS - to return information on all transactions done during the session```
```END - TO exit NewBank.```


#### SHOWMYTRANSACTIONS

should return information on all transactions done during the session, 
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

- then a prompt to select other commands or terminate the program is shown:

```What else would you like to do?```

```SHOWMYACCOUNTS - to view a list of your accounts and their balance```
```NEWACCOUNT <Name> - to open a new account with the name you provide```
```MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another```
```PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing```
```SHOWMYTRANSACTIONS - to return information on all transactions done during the session```
```END - TO exit NewBank.```


#### END:

- enter END

- returns the following statement:

```****  Thank you for using NewBank  ****```

- will terminate program for customer


