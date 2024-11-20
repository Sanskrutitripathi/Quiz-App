# Quiz Application

## Overview
A Java-based Quiz Application leveraging MySQL database connectivity to manage quiz scores for different subjects (General Knowledge, Science, and Mathematics). The system allows users to participate in quizzes, track their scores, and update records in the database. Designed to enhance understanding of file handling and JDBC.

---

## Features
- **Subject-Based Quizzes**: Includes quizzes on General Knowledge, Science, and Mathematics.
- **Score Management**: Tracks and stores user scores for each quiz in a MySQL database.
- **Dynamic Question Loading**: Reads quiz questions, options, and answers from external text files.
- **User Interaction**: Simple menu-driven interface for ease of use.

---

## Prerequisites
- **Java Development Kit (JDK)**: Ensure JDK is installed on your system.
- **XAMPP**: For running MySQL and managing the database.
- **MySQL Connector/J**: Required for JDBC connectivity.
- **Text Files**: 
  - `gk.txt`, `sci.txt`, `maths.txt` must be in the program's directory and formatted as follows:
    ```
    Question: What is the capital of France?
    Options: a) Berlin, b) Madrid, c) Paris, d) Rome
    Answer: c
    ```

---

## Database Setup

1. Start the **MySQL** server in XAMPP.
2. Open `phpMyAdmin` (`http://localhost/phpmyadmin/`) and create a database:
   ```sql
   CREATE DATABASE quiz;
3. Create the scoreboard table:
   ```sql
   CREATE TABLE scoreboard (
    UserName VARCHAR(50) PRIMARY KEY,
    gk_score INT,
    sci_score INT,
    maths_score INT,
    total_score INT
   );

---

## How to Run

1. Compile the Program:
   ```bash
   javac QuizApp.java
2. Run the Program:
   ```bash
   java QuizApp

---

## File Structure
- **QuizApp.java**: The main application file.
- **gk.txt, sci.txt, maths.txt**: Files containing quiz questions, options, and answers.
- **scoreboard (MySQL table)**: Stores user scores for each quiz.

## Key Concepts
- **File Handling**: Reads quiz data from external .txt files.
- **JDBC Integration**: Connects and interacts with a MySQL database.
- **Database Management**: Stores and updates user scores dynamically.

## Technologies Used
- **Programming Language**: Java
- **Database**: MySQL (via XAMPP)
- **Libraries**: JDBC
