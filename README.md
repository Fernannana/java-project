# Student Management System

A console-based Java application for managing student records, tracking grades across multiple subjects, and generating reports. Built as a group project for our Java programming course to practice object-oriented programming, file handling, and exception handling.

## Team

| Name | Role / Responsibilities |
| --- | --- |
| Maria Garcia | GitHub Repository, User Interaction, TBD|
| Makalah Emir Cordova | Student class, Reports, TBD|
| Farheen Abdia | Documentation, Subject class, TBD|

## Features

- Add, remove, and update student records
- Track grades for multiple subjects per student
- Input validation (grades must be between 0 and 100)
- Reports:
  - Average grade for each student
  - Highest and lowest grade for a specific subject
  - List of students sorted by grade
- Save student data to a text file and load it back
- Menu-driven interface using `Scanner`

## Project Structure

```
java-project/
├── src/
│   ├── Student.java                  # Student data (ID, name, grades)
│   ├── Subject.java                  # Subject name and grade
│   ├── StudentManagementSystem.java  # Core operations and reports
│   └── Main.java                     # Program entry point and menu
├── Data.txt                          # Saved student records
├── .gitignore
└── README.md
```

## Getting Started

### Requirements

- Java JDK 11 or newer (check with `java -version`)
- Git

### Clone the repository

```bash
git clone https://github.com/Fernannana/java-project.git
cd java-project
```

### Compile and run

```bash
javac src/*.java -d out
java -cp out Main
```

## Usage

When the program starts, a menu is displayed. Enter the number for the action you want:

```
1. Add student
2. Remove student
3. Update student
4. Add / update grades
5. View student details
6. Generate reports
7. Save to file
8. Load from file
9. Exit
```


## Data File

Student records are saved to and loaded from `Data.txt`. Describe the format here once it's decided, for example:

```
ID,Name,Subject:Grade;Subject:Grade
101,Jane Doe,Math:90;Science:85
```

## OOP Concepts Used

- **Encapsulation:** private fields with getters/setters in `Student` and `Subject`
- **Inheritance and polymorphism:** _describe where you used them, e.g., different student types or grading systems_
- **Exception handling:** `try-catch` for invalid input and file errors
- **Data structures:** _e.g., `ArrayList<Student>` and `HashMap<String, Double>`_
- **Algorithms:** sorting students by grade, searching by ID or name

## Team Workflow

- `main` is protected. All changes go through pull requests.
- Create a branch for each task: `feature/<short-name>` or `fix/<short-name>`.
- Each pull request needs at least one teammate's review before merging.
- Commit often with clear messages.

## Course Information

- **Course:** CIS 3332 - Java Programming
- **Instructor:** Professor Gu
- **Due date:** November 24, 2026
