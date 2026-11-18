package studentmanagementsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortedGrades {

    // Average of a student's grades
    public static double getAverage(Student student) {
        Subject[] subjects = student.getSubjects();

        if (subjects.length == 0) {
            return 0;
        }

        int total = 0;
        for (Subject subject : subjects) {
            total += subject.getGrade();
        }
        return (double) total / subjects.length;
    }

    // Compares students by average grade
    private static Comparator<Student> byAverage() {
        return Comparator.comparingDouble(SortedGrades::getAverage);
    }

    // Tie-breaker: last name, then first name (ignoring case)
    private static Comparator<Student> byName() {
        return Comparator
                .comparing(Student::getLastName, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Student::getFirstName, String.CASE_INSENSITIVE_ORDER);
    }

    // 1. Lowest to highest
    public static List<Student> sortAscending(List<Student> students) {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(byAverage());
        return sorted;
    }

    // 2. Highest to lowest
    public static List<Student> sortDescending(List<Student> students) {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(byAverage().reversed());
        return sorted;
    }

    // 3. Highest to lowest, ties sorted by name
    public static List<Student> sortDescendingWithTieBreaker(List<Student> students) {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(byAverage().reversed().thenComparing(byName()));
        return sorted;
    }

    // Prints a ranked list of students
    public static void printSorted(List<Student> sorted) {
        int rank = 1;
        for (Student s : sorted) {
            System.out.printf("%d. %s %s (ID %d): %.2f%n",
                    rank++, s.getFirstName(), s.getLastName(),
                    s.getStudentID(), getAverage(s));
        }
    }

    // Asks the user for a sort order, then prints the sorted report
    public static void showReport(List<Student> students, Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("There are no students to sort.");
            return;
        }

        System.out.println();
        System.out.println("=== Sorted Grades Report ===");
        System.out.println("1. Lowest to highest");
        System.out.println("2. Highest to lowest");
        System.out.println("3. Highest to lowest (ties sorted by name)");
        System.out.print("Choose a sort order: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        List<Student> sorted;
        switch (choice) {
            case 1:
                sorted = sortAscending(students);
                break;
            case 2:
                sorted = sortDescending(students);
                break;
            case 3:
                sorted = sortDescendingWithTieBreaker(students);
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        System.out.println();
        printSorted(sorted);
    }
}