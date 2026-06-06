# 🏢 Employee Management System
**Course:** Java Fundamentals - Week 4 (Collections & File Handling)

> **An enterprise-grade, console-based Java application demonstrating the Java Collections Framework, Data Persistence, and robust Exception Handling.**

---

## 📑 Table of Contents
1. [Project Overview](#-project-overview)
2. [Core Concepts Applied](#-core-concepts-applied)
3. [Core Features](#-core-features)
4. [System Architecture](#-system-architecture)
5. [Setup & Execution](#-setup--execution)
6. [Testing the Application](#-testing-the-application)

---

## 🎯 Project Overview
This project bridges the gap between volatile memory structures and non-volatile storage. It allows organizations to manage employee lifecycles via full CRUD (Create, Read, Update, Delete) operations, utilizing the Java Collections Framework (`ArrayList`, `HashMap`) while applying advanced Exception Handling protocols to ensure systemic stability during File I/O operations.

---

## 🧠 Core Concepts Applied
To achieve enterprise-grade code quality, this system heavily enforces the following paradigms:

* **Advanced Data Structures:** Utilizes both an `ArrayList` (for sequential iteration and sorting) and a `HashMap` (for O(1) constant-time record lookups by ID).
* **State Synchronization:** Ensures that operations (Add, Update, Delete) dynamically sync across multiple collection types simultaneously to prevent memory leaks or orphaned records.
* **Data Persistence (Serialization):** Implements `ObjectOutputStream` and `ObjectInputStream` to write binary object states directly to `.dat` files, persisting data between application launches.
* **Exception Handling:** Extensive use of `try-catch` and `try-with-resources` blocks to handle `IOException`, `ClassNotFoundException`, and `NumberFormatException`, guaranteeing the system never crashes from bad user input or missing files.

---

## ✨ Core Features
* **Full CRUD Functionality:** Create new records, read directories, update existing employee parameters, and securely delete records.
* **O(1) Quick Search:** Enter a specific Employee ID to fetch the record instantly using the underlying Hash Table logic.
* **Analytical Reporting:** Generate dynamically calculated salary statistics (min, max, average, totals) and department-wise summaries.
* **Automated File Generation:** The system automatically builds the `data/` directory and `.dat` files if they do not exist upon launch.

---

## 🛠️ System Architecture
The application is strictly divided into four files to enforce the **Single Responsibility Principle**:

| Class File | Responsibility |
| :--- | :--- |
| `EmployeeManagementSystem.java` | The main execution thread, UI (CLI menu loop), and collection syncing. |
| `EmployeeFileHandler.java` | Dedicated utility class strictly for Try-With-Resources File I/O operations. |
| `EmployeeReportGenerator.java` | Handles the logic and iterations for generating analytical reports. |
| `Employee.java` | Serializable Data Model encapsulating employee descriptors and auto-generating dates. |

---

## 🚀 Setup & Execution

**Prerequisites:** * Java Development Kit (JDK) 11 or higher
* Command-Line Interface / Terminal

**Installation Steps:**

1. Clone this repository to your local machine:
    git clone https://github.com/rithuparan18/Task4.git

2. Navigate to the source directory:
    cd Task4/src

3. Compile all Java class files:
    javac *.java

4. Execute the main application:
    java EmployeeManagementSystem

---

## 🧪 Testing the Application
The application includes robust exception handling and state validation for immediate testing.

1. Run the app and select **Option 1** to add an employee.
2. When prompted for a salary, enter letters (e.g., `abc`). The system will successfully catch the `NumberFormatException` and ask you to try again instead of crashing.
3. Finish adding the employee with an ID of `E001`.
4. Try to add another employee with the exact same ID `E001`. The `HashMap` validation will successfully block the duplicate entry.
5. Select **Option 7** to Save & Exit. Restart the application to verify the binary file deserialized correctly and your data persisted!
