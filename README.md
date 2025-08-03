Expense Tracker Application
This is a full-stack web application designed to help users track their personal expenses. It features a modern front-end for data entry and visualization, and a robust back-end powered by Spring Boot and a MySQL database.

Key Features
Create and Manage Expenses: Users can add, view, and manage their daily expenses through a simple and intuitive web interface.

RESTful API: A secure and well-structured REST API built with Spring Boot handles all data operations, including creating, reading, updating, and deleting expenses.

Data Persistence: All expense data is stored in a MySQL database, ensuring that information is saved and available for future use.

Expense Reporting: The application provides powerful reporting features, including:

A breakdown of expenses by category.

A visual representation of spending habits using a dynamic pie chart.

Modern Front-End: The user interface is built with HTML, CSS, and plain JavaScript, ensuring a clean, responsive, and fast experience.

Technologies Used
Backend:

Java 17: The core programming language for the back-end.

Spring Boot: The framework used to create the REST API.

Spring Data JPA: Simplifies database interaction by providing a powerful data access layer.

Maven: Manages project dependencies and builds.

Database:

MySQL: The relational database used to store all expense data.

Frontend:

HTML, CSS, JavaScript: The core building blocks of the front-end.

Chart.js: A popular JavaScript library used to create the expense reporting pie chart.

Getting Started
1. Backend Setup
Clone the Repository: If you have not already, clone this repository to your local machine.

Database Configuration:

Ensure you have a MySQL server running.

Create a database named expense_tracker_db.

Open the src/main/resources/application.properties file and verify the database connection details match your MySQL setup.

Run the Application:

Open the project in Eclipse.

Right-click on the main application file (ExpenseTrackerAppApplication.java) and select Run As > Spring Boot App.

2. Frontend Setup
The front-end files are located in src/main/resources/static/. Once the Spring Boot application is running, the front-end will be automatically served.

3. Access the Application
Open your web browser and navigate to http://localhost:8080. You can now start adding expenses, viewing reports, and interacting with the application.
