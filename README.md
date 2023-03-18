# TodoApp

TodoApp is a simple application that allows users to manage their tasks. The application is designed using a three-tier architecture, consisting of a presentation layer, a business logic layer, and a data access layer.

The data access layer is implemented using two DAO implementations: one that stores data in memory, and another that uses a SQLite database. This allows the application to be easily extended to support other data storage solutions in the future.

## Technologies

The following technologies are used in the implementation of TodoApp:

- Java programming language
- Android Studio
- SQLite database

## Architecture

The architecture of TodoApp follows a three-tier architecture:

- Presentation layer: This layer is responsible for displaying the user interface to the user and handling user input.
- Business logic layer: This layer contains the application's business logic, which is responsible for processing data and making decisions.
- Data access layer: This layer is responsible for interacting with the data storage solution and providing data to the business logic layer.

## Implementation

The application's data access layer is implemented using two DAO implementations:

- MemoryDAO: This DAO implementation stores data in memory, making it easy to implement and test.
- SQLiteDAO: This DAO implementation uses a SQLite database to store data, providing a more robust and scalable data storage solution.

The DAO implementations are designed to be easily interchangeable, allowing the application to use different data storage solutions without requiring significant changes to the rest of the application.

## Future Work

There are several areas in which TodoApp could be improved in the future:

- Improved user interface: The application's user interface could be improved to make it more intuitive and user-friendly.
- Integration with third-party services: The application could be extended to integrate with third-party services, such as calendars or task management systems.


![image](https://user-images.githubusercontent.com/96589855/226100034-04ba1ec5-1d98-453d-9451-20aa0695e864.png)
![image](https://user-images.githubusercontent.com/96589855/226100068-781a83dc-3f01-4236-924f-00fd5af336b4.png)
