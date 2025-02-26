import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the teacher for the number of students
        System.out.print("Enter the number of students: ");
        int numOfStudents = scanner.nextInt();

        // Create an array to store student grades
        double[] grades = new double[numOfStudents];

        // Input grades for each student
        for (int i = 0; i < numOfStudents; i++) {
            System.out.print("Enter grade for student " + (i + 1) + ": ");
            grades[i] = scanner.nextDouble();
        }

        // Calculate the average, highest, and lowest grades
        double total = 0;
        double highest = grades[0];
        double lowest = grades[0];

        for (double grade : grades) {
            total += grade;
            if (grade > highest) {
                highest = grade;
            }
            if (grade < lowest) {
                lowest = grade;
            }
        }

        double average = total / numOfStudents;

        // Display the results
        System.out.println("\nResults:");
        System.out.println("Average Grade: " + average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}


OUTPUT:

Enter the number of students: 5
Enter grade for student 1: 97.8
Enter grade for student 2: 91.9
Enter grade for student 3: 85.7
Enter grade for student 4: 79.6
Enter grade for student 5: 78.8

Results:
Average Grade: 86.76
Highest Grade: 97.8
Lowest Grade: 78.8
