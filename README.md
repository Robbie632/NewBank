# Cli Bank
Contains java code for the NewBank command line interface implemented for software engineering 2 module of Bath Computer Science MSc

# Background

The application consists of a server (server folder) which contains the business logic for the bank and a front-end (client folder) which allows a user to interact with the server

# Getting started
```bash
git clone https://github.com/Robbie632/NewBank.git
```

## Running the application

To run and interact with the bank you need to 

### Compile the code

#### Option 1 - Use Intellij

If using intellij the open up the project and navigate the Main.java file and click play

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

You can enter any value for the password as this is not validated at the moment (tbc on how this will be implemented 
it needs to be persisted somehow e.g. txt.file, json.file or some kind of database)

````java
	private void addTestData() {
		Customer bhagy = new Customer();
		bhagy.addAccount(new Account("Main", 1000.0));
		customers.put("Bhagy", bhagy);
		
		Customer christina = new Customer();
		christina.addAccount(new Account("Savings", 1500.0));
		customers.put("Christina", christina);
		
		Customer john = new Customer();
		john.addAccount(new Account("Checking", 250.0));
		customers.put("John", john);

		// Create  a new user here by uncommenting the below and setting values 
        
//		Customer john = new Customer();
//		john.addAccount(new Account("Checking", 250.0));
//		customers.put("New user", john);

	}
````




