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
    
    // Student grades
    private int mathGrade;
    private int scienceGrade;
    private int historyGrade;
    private int englishGrade;
 
 // Constructor
    public Student(int studentID, String firstName, String lastName,
                   int mathGrade, int scienceGrade,
                   int historyGrade, int englishGrade) {

        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mathGrade = mathGrade;
        this.scienceGrade = scienceGrade;
        this.historyGrade = historyGrade;
        this.englishGrade = englishGrade;
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

    public int getMathGrade() {
        return mathGrade;
    }

    public int getScienceGrade() {
        return scienceGrade;
    }

    public int getHistoryGrade() {
        return historyGrade;
    }

    public int getEnglishGrade() {
        return englishGrade;
    
    }
        
    // Setters with grade validation
    public void setMathGrade(int mathGrade) {
        if (mathGrade >= 0 && mathGrade <= 100) {
            this.mathGrade = mathGrade;
        } else {
            System.out.println("Invalid Math grade. Enter 0-100.");
        }
    }

    public void setScienceGrade(int scienceGrade) {
        if (scienceGrade >= 0 && scienceGrade <= 100) {
            this.scienceGrade = scienceGrade;
        } else {
            System.out.println("Invalid Science grade. Enter 0-100.");
        }
    }

    public void setHistoryGrade(int historyGrade) {
        if (historyGrade >= 0 && historyGrade <= 100) {
            this.historyGrade = historyGrade;
        } else {
            System.out.println("Invalid History grade. Enter 0-100.");
        }
    }

    public void setEnglishGrade(int englishGrade) {
        if (englishGrade >= 0 && englishGrade <= 100) {
            this.englishGrade = englishGrade;
        } else {
            System.out.println("Invalid English grade. Enter 0-100.");
        }    
        
    }    
}
