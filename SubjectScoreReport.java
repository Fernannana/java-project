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

    // Display the highest and lowest grade for a named subject
    public void displayHighestAndLowest(String subjectName) {
        if (students.isEmpty()) {
            System.out.println("No students available for the report.");
            return;
        }

        if (subjectName == null || subjectName.trim().isEmpty()) {
            System.out.println("Please enter a valid subject name.");
            return;
        }

        ArrayList<Student> subjectStudents = new ArrayList<>();
        for (Student student : students) {
            if (findSubject(student, subjectName) != null) {
                subjectStudents.add(student);
            }
        }

        if (subjectStudents.isEmpty()) {
            System.out.println("No grades recorded for " + subjectName + ".");
            return;
        }

        Student highest = subjectStudents.get(0);
        Student lowest = subjectStudents.get(0);
        int highestGrade = findSubject(highest, subjectName).getGrade();
        int lowestGrade = highestGrade;

        for (Student student : subjectStudents) {
            int grade = findSubject(student, subjectName).getGrade();
            if (grade > highestGrade) {
                highestGrade = grade;
                highest = student;
            }
            if (grade < lowestGrade) {
                lowestGrade = grade;
                lowest = student;
            }
        }

        System.out.println();
        System.out.println("===== " + subjectName + " Highest and Lowest =====");
        System.out.println("Highest: " + highest.getFirstName() + " "
                + highest.getLastName() + " - " + highestGrade);
        System.out.println("Lowest: " + lowest.getFirstName() + " "
                + lowest.getLastName() + " - " + lowestGrade);
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
}
