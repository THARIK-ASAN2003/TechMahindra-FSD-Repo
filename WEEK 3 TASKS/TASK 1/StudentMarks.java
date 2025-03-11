import java.util.*;

class Student {
    String name;
    int[] marks;
    int totalMarks;
    double averageMarks;

    // Constructor
    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
        calculateTotalAndAverage();
    }

    // Method to calculate total and average marks
    private void calculateTotalAndAverage() {
        totalMarks = Arrays.stream(marks).sum(); // Sum of marks
        averageMarks = (double) totalMarks / marks.length; // Average
    }

    // Display student details
    public void display() {
        System.out.printf("%-15s | Total: %3d | Average: %.2f\n", name, totalMarks, averageMarks);
    }
}

public class StudentSorter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Accept number of students
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        // Accept number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        List<Student> students = new ArrayList<>();

        // Input student details
        for (int i = 0; i < numStudents; i++) {
            scanner.nextLine(); // Consume newline
            System.out.print("Enter student " + (i + 1) + " name: ");
            String name = scanner.nextLine();

            int[] marks = new int[numSubjects];
            System.out.println("Enter marks for " + numSubjects + " subjects:");
            for (int j = 0; j < numSubjects; j++) {
                marks[j] = scanner.nextInt();
            }

            students.add(new Student(name, marks));
        }

        // Sorting the students in descending order of total marks
        students.sort((s1, s2) -> Integer.compare(s2.totalMarks, s1.totalMarks));

        // Displaying sorted results
        System.out.println("\nSorted Student List (By Total Marks - Descending Order):");
        System.out.println("---------------------------------------------------------");
        for (Student student : students) {
            student.display();
        }

        scanner.close();
    }
}
