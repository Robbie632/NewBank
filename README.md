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






