
package studentmanagementsystem;

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
         try {
            if (grade < 0 || grade > 100) {
                throw new IllegalArgumentException(
                    "Grade must be between 0 and 100."
                );
            }

            this.grade = grade;

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid grade: " + e.getMessage());
        }
    }

    // Display subject information
    public void displaySubject() {
        System.out.println("Subject: " + name);
        System.out.println("Grade: " + grade);
    }
}
