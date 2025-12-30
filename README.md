# Tic Tac Toe – Spring Boot Web Game

A simple Spring Boot–based Tic Tac Toe web application demonstrating backend development with Java, Maven, and MVC architecture, along with frontend integration using HTML, CSS, and JavaScript.

## Tech Stack

- Java 17

- Spring Boot

- Maven (Maven Wrapper included)

- HTML, CSS, JavaScript

- Embedded Tomcat Server

## Project Structure
tic-tac-toe
├── .mvn/                  # Maven wrapper files
├── src/                   # Application source code
│   ├── main
│   │   ├── java            # Java backend code
│   │   └── resources       # HTML, CSS, JS files
├── .gitignore
├── .gitattributes
├── mvnw                   # Maven wrapper (Linux/Mac)
├── mvnw.cmd               # Maven wrapper (Windows)
├── pom.xml                # Maven configuration
├── README.md
└── LICENSE

## Prerequisites

Make sure the following are installed:

Java JDK 17 or later

VS Code or any Java IDE

Internet connection (for first build dependency download)

## Verify Java installation:

```
java -version
```

## steps to run application:
1. Clone the Repository
```
git clone https://github.com/patilrahul99/tic-tac-toe.git
```
2. go inside this folder:

```
cd tic-tac-toe
```
3. Run the application
--This project includes the Maven Wrapper, so Maven does not need to be installed separately.

Option 1: Run from VS Code (Recommended)

Open the project in VS Code:
```
code .
```

Open the main class:

 ```
src/main/java/**/TicTacToeApplication.java
```

Click the Run button above the main() method

Option 2: Run from Terminal
Windows
```
mvnw.cmd spring-boot:run
```
Linux / macOS
```
./mvnw spring-boot:run
```
Access the Application

After the application starts successfully, open:
```
http://localhost:8080
```

--other options:

Build and Run as JAR 

Build the application:
-bash
```
./mvnw clean package
```
Run the generated JAR:
-bash
```
java -jar target/*.jar
```

java -jar target/*.jar
