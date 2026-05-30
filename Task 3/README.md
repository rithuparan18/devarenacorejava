# 📚 Library Management System 
**Course:** Java Fundamentals - Week 3 (Object-Oriented Programming)

> **A robust, console-based Java application demonstrating core Object-Oriented Programming (OOP) paradigms, state management, and strict encapsulation.**

---

## 📑 Table of Contents
1. [Project Overview](#-project-overview)
2. [OOP Principles Applied](#-oop-principles-applied)
3. [Core Features](#-core-features)
4. [System Architecture](#-system-architecture)
5. [Setup & Execution](#-setup--execution)
6. [Testing the Application](#-testing-the-application)

---

## 🎯 Project Overview
This project transitions away from basic procedural programming by modeling a real-world Library System using strict Object-Oriented architecture. It manages the complex, dynamic relationships between Library Patrons (`Members`) and Library Inventory (`Books`) through a centralized controller (`Library`), ensuring data integrity at all times.

---

## 🧠 OOP Principles Applied
To achieve enterprise-grade code quality, this system heavily enforces the following paradigms:

* **Encapsulation (Data Hiding):** All class attributes are declared as `private` to protect the internal state of objects. State modifications are exclusively handled through controlled `public` getter and setter methods.
* **Object Interactions (Coupled State):** Methods like `borrowBook()` contain built-in business logic. When a book is borrowed, the system automatically adds the book to the member's inventory *and* updates the book's `isAvailable` flag to `false` simultaneously.
* **Class Relationships (Aggregation):** The system utilizes `ArrayLists` to map *has-a* relationships (e.g., a `Library` *has-a* list of Members; a `Member` *has-a* list of borrowed Books).

---

## ✨ Core Features
* **Inventory Management:** Instantiate and store books with distinct properties (ISBN, Title, Author, Genre).
* **Patron Registration:** Create and manage library members with unique IDs and contact information.
* **Transactional Logic:** Secure borrowing and returning mechanisms that sync object states.
* **Validation Checks:** Built-in safeguards that prevent logical errors, such as double-borrowing an already checked-out book.
* **Dynamic Search:** Case-insensitive search algorithms to locate books by matching title, author, or genre keywords.

---

## 🛠️ System Architecture
The application is strictly divided into four files to enforce the **Single Responsibility Principle**:

| Class File | Responsibility |
| :--- | :--- |
| `LibrarySystem.java` | The main execution thread and user interface (CLI menu loop). |
| `Library.java` | The central controller/database managing arrays of both books and members. |
| `Member.java` | Encapsulates patron data and their specific array of borrowed items. |
| `Book.java` | Encapsulates individual item data, descriptors, and availability status. |

---

## 🚀 Setup & Execution

**Prerequisites:** * Java Development Kit (JDK) 11 or higher
* Command-Line Interface / Terminal

**Installation Steps:**

1. Clone this repository to your local machine:
    git clone https://github.com/rithuparan18/Task3/src.git

2. Navigate to the source directory:
    cd Library-Management-System/src

3. Compile all Java class files:
    javac *.java

4. Execute the main application:
    java LibrarySystem

---

## 🧪 Testing the Application
The application includes pre-loaded dummy data for immediate testing.

1. Run the app and select **Option 3** to verify the pre-loaded inventory.
2. Select **Option 6** to borrow a book.
   * **Test ID:** `M001`
   * **Test ISBN:** `978-3-16-148410-0`
3. Select **Option 6** again and try to borrow the exact same book. The system will successfully block you, proving the encapsulated state logic works.
