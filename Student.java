/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;

/**
 *
 * @author makalahcordova
 */
public class Student {

    // Student information
    private int studentID;
    private String firstName;
    private String lastName;


      // Array of subjects
    private Subject[] subjects;

  // Constructor
    public Student(int studentID, String firstName, String lastName) {

        setStudentID(studentID);
        setFirstName(firstName);
        setLastName(lastName);

        subjects = new Subject[0];
    }

    // Getters
    public int getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    // Setters with basic validation
    public void setStudentID(int studentID) {
        if (studentID > 0) {
            this.studentID = studentID;
        } else {
            System.out.println("Invalid student ID.");
        }
    }

    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.trim().isEmpty()) {
            this.firstName = firstName;
        } else {
            System.out.println("First name cannot be empty.");
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.trim().isEmpty()) {
            this.lastName = lastName;
        } else {
            System.out.println("Last name cannot be empty.");
        }
    }

    // Add a subject to the student's array
    public void addSubject(Subject subject) {

        if (subject == null) {
            System.out.println("Invalid subject.");
            return;
        }

        Subject[] newSubjects = new Subject[subjects.length + 1];

        for (int i = 0; i < subjects.length; i++) {
            newSubjects[i] = subjects[i];
        }

        newSubjects[subjects.length] = subject;

        subjects = newSubjects;
    }

    // Get a subject using its array position
    public Subject getSubject(int index) {

        try {
            return subjects[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Subject position does not exist.");
            return null;
        }
    }
}
