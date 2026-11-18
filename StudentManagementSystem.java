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
            int choice = readInt("Enter your choice: ");

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
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number from the menu.");
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("===== Student Management System =====");
        System.out.println("1. Add student");
        System.out.println("2. Remove student");
        System.out.println("3. Update student");
        System.out.println("4. View student details");
        System.out.println("5. Add grade for a student");
        System.out.println("6. Generate reports");
        System.out.println("7. Save records to file");
        System.out.println("8. Load records from file");
         System.out.println("9. List all students");
        System.out.println("0. Exit");
        System.out.println("=====================================");
    }

    /** re-prompts instead of crashing on bad input. */
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
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
            System.out.println("Input cannot be empty.");
        }
    }

    // ---- Stubs ----

    private void addStudent() {
        int id = readPositiveInt("Student ID: ");
        if (findStudent(id) != null) {
            System.out.println("A student with that ID already exists.");
            return;
        }
         String firstName = readString("First name: ");
        String lastName = readString("Last name: ");

        try {
            students.add(new Student(id, firstName, lastName));
            System.out.println("Student added: " + firstName + " " + lastName);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add student: " + e.getMessage());
        }
    }

    private void removeStudent() {
        int id = readInt("ID of student to remove: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }

        students.remove(student);
        System.out.println("Removed " + student.getFirstName() + " " + student.getLastName() + ".");
    }

    private void updateStudent() {
        int id = readInt("ID of student to update: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }

        System.out.println("Current name: " + student.getFirstName() + " " + student.getLastName());
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
            System.out.println("Student updated.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not update student: " + e.getMessage());
        }
    }

    private void viewStudentDetails() {
        int id = readInt("ID of student to view: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }

        printStudent(student);
    }

    private void addGrade() {
        int id = readInt("Student ID: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }

        String subjectName = readString("Subject name: ");
        int grade = readGrade("Grade (0-100): ");

        try {
            Subject existing = findSubject(student, subjectName);

            if (existing != null) {
                existing.setGrade(grade);
                System.out.println("Updated " + existing.getName() + " grade to " + grade + ".");
            } else {
                student.addSubject(new Subject(subjectName, grade));
                System.out.println("Added " + subjectName + " grade " + grade + ".");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Could not save grade: " + e.getMessage());
        }
    }

    private void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("There are no students yet.");
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
}
