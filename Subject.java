
package StudentManagementSystem;

public class Subject {

    // Attributes
    private String name;
    private int grade;

    // Constructor
    public Subject(String name, int grade) {
        this.name = name;
        setGrade(grade);
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for grade
    public int getGrade() {
        return grade;
    }

    // Setter for grade
    public void setGrade(int grade) {
        if (grade >= 0 && grade <= 100) {
            this.grade = grade;
        }

        else {
            System.out.println("Invalid grade. Enter a grade between 0 and 100.");
        }
    }

    // Display subject information
    public void displaySubject() {
        System.out.println("Subject: " + name);
        System.out.println("Grade: " + grade);
    }
}
