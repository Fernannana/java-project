package studentmanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    private final ArrayList<Student> students = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    private static final String DATA_FILE = "Data.txt";

    public static void main(String[] args) {
        new StudentManagementSystem().run();
    }

    /** Main loop */
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readInt("Enter an option (0-9): ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    viewStudentDetails();
                    break;
                case 5:
                    addGrade();
                    break;
                case 6:
                    generateReports();
                    break;
                case 7:
                    saveToFile();
                    break;
                case 8:
                    loadFromFile();
                    break;
                case 9:
                    listAllStudents();
                    break;
                case 0:
                    System.out.println("Thank you for using the Student Management System!");
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number 0-9 from the menu.");
            }
        }
        scanner.close();
    }

   private void displayMenu() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("Please select an option by entering");
        System.out.println("the number next to the action.");
        System.out.println();

        System.out.println("--- STUDENT INFORMATION ---");
        System.out.println("1. Add a new student");
        System.out.println("2. Remove a student");
        System.out.println("3. Update student information");
        System.out.println("4. View student details");
        System.out.println("9. List all students");

        System.out.println();
        System.out.println("--- GRADE MANAGEMENT ---");
        System.out.println("5. Add or update student grades");
        System.out.println("6. Generate grade reports");

        System.out.println();
        System.out.println("--- FILE MANAGEMENT ---");
        System.out.println("7. Save student records");
        System.out.println("8. Load student records");

        System.out.println();
        System.out.println("0. Exit the program");
        System.out.println("==========================================");
    }

    /** re-prompts instead of crashing on bad input. */
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

private int readPositiveInt(String prompt) {
    while (true) {
        int number = readInt(prompt);

        if (number > 0) {
            return number;
        }

        System.out.println("Please enter a number greater than 0.");
    }
}

    /** Reads a non-empty line of text. */
    private String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private void addStudent() {
        System.out.println();
        System.out.println("===== ADD NEW STUDENT =====");
        System.out.println("Please enter the student's information.");

        int id = readPositiveInt("Enter student ID number: ");
        if (findStudent(id) != null) {
            System.out.println("A student with that ID number already exists.");
            return;
        }
         String firstName = readString("First name: ");
        String lastName = readString("Last name: ");

        try {
            students.add(new Student(id, firstName, lastName));
            System.out.println("Student successfully added: " + firstName + " " + lastName);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add student: " + e.getMessage());
        }
    }

    private void removeStudent() {
        System.out.println();
        System.out.println("===== REMOVE STUDENT =====");
        
        int id = readInt("Enter ID number of student to remove: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }

        students.remove(student);
        System.out.println(" Sucessfully removed " + student.getFirstName() + " " + student.getLastName() + ".");
    }

    private void updateStudent() {
        System.out.println();
        System.out.println("===== UPDATE STUDENT INFORMATION =====");

        int id = readInt(" Enter ID number of student to update: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }

        System.out.println("Current name: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Enter new information below.");  
        System.out.println("(Press Enter to keep a value unchanged.)");

        String newFirst = readOptionalString("New first name: ");
        String newLast = readOptionalString("New last name: ");

        try {
            if (!newFirst.isEmpty()) {
                student.setFirstName(newFirst);
            }
            if (!newLast.isEmpty()) {
                student.setLastName(newLast);
            }
            System.out.println("Student information successfully updated.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not update student: " + e.getMessage());
        }
    }

    private void viewStudentDetails() {
        System.out.println();
        System.out.println("===== VIEW STUDENT DETAILS =====");
    
        int id = readInt("ID of student to view: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }

        printStudent(student);
    }

    private void addGrade() {
        System.out.println();
        System.out.println("===== ADD OR UPDATE STUDENT GRADE =====");
        
        int id = readInt("Enter student ID number: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }

        String subjectName = readString("Enter the subject name: ");
        int grade = readGrade("Enter the grade (0-100): ");

        try {
            Subject existing = findSubject(student, subjectName);

            if (existing != null) {
                existing.setGrade(grade);
                System.out.println("Sucessfully updated " + existing.getName() + " grade to " + grade + ".");
            } else {
                student.addSubject(new Subject(subjectName, grade));
                System.out.println("Successfully added " + subjectName + " grade " + grade + ".");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Could not save grade: " + e.getMessage());
        }
    }

    private void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("There are no students yet. Please add a student first");
            return;
        }

        System.out.println();
        System.out.println("--- All Students ---");
        for (Student s : students) {
            System.out.println(s.getStudentID() + " - " + s.getFirstName() + " " + s.getLastName());
        }
    }
// ---------- reports -------- // author: Maria Garcia
    private void generateReports() {
        boolean inReports = true;

        while (inReports) {
            System.out.println();
            System.out.println("===== GRADE REPORTS =====");
            System.out.println("1. Average grade for each student");
            System.out.println("2. Highest and lowest grade for a subject");
            System.out.println("3. All grades by subject (highest to lowest)");
            System.out.println("4. Students sorted by average grade");
            System.out.println("0. Back to main menu");

            int choice = readInt("Choose a report: ");

            if (choice != 0 && students.isEmpty()) {
                System.out.println("There are no students yet. Please add a student first.");
                return;
            }

            switch (choice) {
                case 1:
                    SortedGrades.showAverageReport(students);
                    break;
                case 2:
                    String subjectName = readString("Enter the subject name: ");
                    new SubjectScoreReport(students).displayHighestAndLowest(subjectName);
                    break;
                case 3:
                    new SubjectScoreReport(students).displayHighestToLowest();
                    break;
                case 4:
                    SortedGrades.showReport(students, scanner);
                    break;
                case 0:
                    inReports = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 0-4.");
            }
        }
    }
    private void saveToFile() {
        if (students.isEmpty()) {
            System.out.println("There are no students to save.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {

            for (Student student : students) {
                StringBuilder line = new StringBuilder();
                line.append(student.getStudentID()).append("\t")
                    .append(student.getFirstName()).append("\t")
                    .append(student.getLastName());

                for (Subject subject : student.getSubjects()) {
                    line.append("\t")
                        .append(subject.getName()).append(":").append(subject.getGrade());
                }

                writer.write(line.toString());
                writer.newLine();
            }

            System.out.println("Saved " + students.size() + " student(s) to " + DATA_FILE + ".");

        } catch (IOException e) {
            System.out.println("Could not save to " + DATA_FILE + ": " + e.getMessage());
        }
    }

    private void loadFromFile() {
        // Read into a temporary list first, so a problem never wipes the current records
        ArrayList<Student> loaded = new ArrayList<>();
        int skipped = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                Student student = parseStudent(line, lineNumber, loaded);
                if (student == null) {
                    skipped++;
                } else {
                    loaded.add(student);
                }
            }

        } catch (java.io.FileNotFoundException e) {
            System.out.println(DATA_FILE + " was not found. Save records first (option 7).");
            return;
        } catch (IOException e) {
            System.out.println("Could not read " + DATA_FILE + ": " + e.getMessage());
            return;
        }

        students.clear();
        students.addAll(loaded);

        System.out.println("Loaded " + loaded.size() + " student(s) from " + DATA_FILE + ".");
        if (skipped > 0) {
            System.out.println(skipped + " line(s) were skipped because of invalid data.");
        }
    }

    /** Turns one line of the file into a Student, or returns null (with a message) if invalid. */
    private Student parseStudent(String line, int lineNumber, ArrayList<Student> alreadyLoaded) {
        String[] parts = line.split("\t");

        if (parts.length < 3) {
            System.out.println("Line " + lineNumber + " skipped: not enough fields.");
            return null;
        }

        try {
            int id = Integer.parseInt(parts[0].trim());
            String firstName = parts[1].trim();
            String lastName = parts[2].trim();

            if (id <= 0 || firstName.isEmpty() || lastName.isEmpty()) {
                System.out.println("Line " + lineNumber + " skipped: invalid ID or name.");
                return null;
            }

            for (Student existing : alreadyLoaded) {
                if (existing.getStudentID() == id) {
                    System.out.println("Line " + lineNumber + " skipped: duplicate ID " + id + ".");
                    return null;
                }
            }

            Student student = new Student(id, firstName, lastName);

            // Remaining fields are Subject:Grade
            for (int i = 3; i < parts.length; i++) {
                String field = parts[i].trim();
                int colon = field.lastIndexOf(':');

                if (colon <= 0) {
                    System.out.println("Line " + lineNumber + " skipped: bad grade entry '" + field + "'.");
                    return null;
                }

                String subjectName = field.substring(0, colon).trim();
                int grade = Integer.parseInt(field.substring(colon + 1).trim());

                if (grade < 0 || grade > 100) {
                    System.out.println("Line " + lineNumber + " skipped: grade " + grade
                            + " is not between 0 and 100.");
                    return null;
                }

                student.addSubject(new Subject(subjectName, grade));
            }

            return student;

        } catch (NumberFormatException e) {
            System.out.println("Line " + lineNumber + " skipped: a number could not be read.");
            return null;
        }
    }

    // Find a student using their ID number
private Student findStudent(int id) {
    for (Student student : students) {
        if (student.getStudentID() == id) {
            return student;
        }
    }
    return null;
}

// Read text that can be left blank
private String readOptionalString(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim();
}

// Read and validate a grade
private int readGrade(String prompt) {
    while (true) {
        int grade = readInt(prompt);

        if (grade >= 0 && grade <= 100) {
            return grade;
        }

        System.out.println("Please enter a grade between 0 and 100.");
    }
}

// Find a subject for a student
private Subject findSubject(Student student, String subjectName) {
    for (Subject subject : student.getSubjects()) {
        if (subject.getName().equalsIgnoreCase(subjectName)) {
            return subject;
        }
    }
    return null;
}

// Display student information
private void printStudent(Student student) {
    System.out.println();
    System.out.println("Student ID: " + student.getStudentID());
    System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());

    System.out.println("Grades:");

    if (student.getSubjects().length == 0) {
        System.out.println("No grades recorded.");
    } else {
        for (Subject subject : student.getSubjects()) {
            System.out.println(subject.getName() + ": " + subject.getGrade());
        }
    }
}
}
