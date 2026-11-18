package studentmanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

/** Still need to update once requirements 3 and 5 are completed **/
public class StudentManagementSystem {

    private final ArrayList<Student> students = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

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

    // ---- Stubs ----

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

    // -- this seection still need to be updated once req 3 and 5 are done-- //
    private void generateReports() {
        // TODO: average per student, highest/lowest per subject, sorted list
        System.out.println("(stub) Would generate reports");
    }

    private void saveToFile() {
        // TODO: Requirement 3 - write records to a text file with try-catch
        System.out.println("(stub) Would save records");
    }

    private void loadFromFile() {
        // TODO: Requirement 3 - read records from a text file with try-catch
        System.out.println("(stub) Would load records");
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

// Read a positive number
private int readPositiveInt(String prompt) {
    while (true) {
        int number = readInt(prompt);

        if (number > 0) {
            return number;
        }

        System.out.println("Please enter a number greater than 0.");
    }
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
