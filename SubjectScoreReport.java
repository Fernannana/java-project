/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;

import java.util.ArrayList;

public class SubjectScoreReport {
    
 private ArrayList<Student> students;

    // Constructor
    public SubjectScoreReport(ArrayList<Student> students) {
        this.students = students;
    }

    // Display grades from highest to lowest for each subject
        
    public void displayHighestToLowest() {

       if (students.isEmpty()) {
            System.out.println("No students available for the report.");
            return;
        }

        // Store all subject names
        ArrayList<String> subjectNames = new ArrayList<>();

        for (Student student : students) {
            for (Subject subject : student.getSubjects()) {

                if (!subjectNames.contains(subject.getName())) {
                    subjectNames.add(subject.getName());
                }
            }
        }

        // Display grades for each subject
        for (String subjectName : subjectNames) {

            ArrayList<Student> subjectStudents = new ArrayList<>();

            // Find students who have a grade for this subject
            for (Student student : students) {
                if (findSubject(student, subjectName) != null) {
                    subjectStudents.add(student);
                }
            }

            // Sort grades from highest to lowest
            for (int i = 0; i < subjectStudents.size() - 1; i++) {

                for (int j = 0; j < subjectStudents.size() - 1 - i; j++) {

                    int grade1 = findSubject(subjectStudents.get(j), subjectName).getGrade();
                    int grade2 = findSubject(subjectStudents.get(j + 1), subjectName).getGrade();

                    if (grade1 < grade2) {
                        Student temp = subjectStudents.get(j);
                        subjectStudents.set(j, subjectStudents.get(j + 1));
                        subjectStudents.set(j + 1, temp);
                    }
                }
            }

            // Display the sorted grades
            System.out.println();
            System.out.println("===== " + subjectName + " Grades =====");

            for (Student student : subjectStudents) {

                Subject subject = findSubject(student, subjectName);

                System.out.println(
                    student.getFirstName() + " " +
                    student.getLastName() + " - " +
                    subject.getGrade()
                );
            }
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

    // Display the highest and lowest grade for one subject
    public void displayHighestAndLowest(String subjectName) {

        int highest = -1;
        int lowest = 101;

        String highestStudent = "";
        String lowestStudent = "";

        for (Student student : students) {

            Subject subject = findSubject(student, subjectName);

            if (subject != null) {

                int grade = subject.getGrade();

                if (grade > highest) {
                    highest = grade;
                    highestStudent = student.getFirstName() + " " + student.getLastName();
                }

                if (grade < lowest) {
                    lowest = grade;
                    lowestStudent = student.getFirstName() + " " + student.getLastName();
                }
            }
        }

        System.out.println();
        System.out.println("===== " + subjectName + " Highest and Lowest Grades =====");

        if (highest == -1) {
            System.out.println("No grades found for " + subjectName + ".");
        } else {
            System.out.println("Highest grade: " + highest + " - " + highestStudent);
            System.out.println("Lowest grade: " + lowest + " - " + lowestStudent);
        }
    }
}
