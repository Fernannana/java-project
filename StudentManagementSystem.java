import java.util.Scanner;

/**
 * The menu actions are stubs for now, fill them in as Student, Subject, file handling, and reports.
 */
public class StudentManagementSystem {

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
        int id = readInt("Student ID: ");
        String name = readString("Student name: ");
        // TODO: create a Student object and store it (ArrayList or HashMap)
        System.out.println("(stub) Would add student " + id + " - " + name);
    }

    private void removeStudent() {
        int id = readInt("ID of student to remove: ");
        // TODO: find and remove the student
        System.out.println("(stub) Would remove student " + id);
    }

    private void updateStudent() {
        int id = readInt("ID of student to update: ");
        // TODO: find the student and update their details
        System.out.println("(stub) Would update student " + id);
    }

    private void viewStudentDetails() {
        int id = readInt("ID of student to view: ");
        // TODO: display the student's info and grades
        System.out.println("(stub) Would show details for student " + id);
    }

    private void addGrade() {
        int id = readInt("Student ID: ");
        String subject = readString("Subject: ");
        int grade = readInt("Grade (0-100): ");
        // TODO: validate 0-100 (Requirement 4) and add a Subject to the student
        System.out.println("(stub) Would add " + subject + " grade " + grade + " for student " + id);
    }

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