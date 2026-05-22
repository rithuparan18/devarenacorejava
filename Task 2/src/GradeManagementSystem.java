import java.util.ArrayList;
import java.util.Scanner;

public class GradeManagementSystem {
    private static ArrayList<StudentGrade> students = new ArrayList<>();
    private static final String[] SUBJECTS = {"Mathematics", "Science", "English", "History", "Computer"};
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        
        while(running) {
            System.out.println("\n=== GRADE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student Marks");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Generate Performance Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = getValidInt(1, 5);
            
            switch(choice) {
                case 1: addStudentMarks(); break;
                case 2: viewAllStudents(); break;
                case 3: searchStudent(); break;
                case 4: ReportGenerator.printClassSummary(students, SUBJECTS); break;
                case 5: 
                    running = false;
                    System.out.println("Thank you for using Grade Management System!");
                    break;
            }
        }
        scanner.close();
    }

    private static void addStudentMarks() {
        System.out.println("\n=== ADD STUDENT MARKS ===");
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        
        StudentGrade student = new StudentGrade(name, SUBJECTS.length);
        
        System.out.println("\nEnter marks for " + SUBJECTS.length + " subjects (0-100):");
        for(int i = 0; i < SUBJECTS.length; i++) {
            System.out.print(SUBJECTS[i] + ": ");
            student.setMark(i, getValidMark());
        }
        
        students.add(student);
        System.out.println("✅ Student marks added successfully!");
    }

    private static void viewAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");
        if(students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        
        System.out.printf("%-15s %-8s %-8s %-8s %-8s %-10s %-8s %-5s\n", 
                         "Name", "Math", "Sci", "Eng", "Hist", "Comp", "Avg", "Grade");
        System.out.println("-".repeat(80));
        
        for(StudentGrade s : students) {
            double avg = GradeCalculator.calculateAverage(s.getMarks());
            double[] m = s.getMarks();
            System.out.printf("%-15s %-8.2f %-8.2f %-8.2f %-8.2f %-10.2f %-8.2f %-5s\n", 
                            s.getName(), m[0], m[1], m[2], m[3], m[4], 
                            avg, GradeCalculator.getGradeCategory(avg));
        }
    }

    private static void searchStudent() {
        System.out.print("\nEnter Student Name to search: ");
        String query = scanner.nextLine().toLowerCase();
        
        boolean found = false;
        for (StudentGrade s : students) {
            if (s.getName().toLowerCase().contains(query)) {
                System.out.println("\n✅ Found Student: " + s.getName());
                double[] m = s.getMarks();
                for (int i = 0; i < SUBJECTS.length; i++) {
                    System.out.println(SUBJECTS[i] + ": " + m[i]);
                }
                double avg = GradeCalculator.calculateAverage(m);
                System.out.println("Average: " + String.format("%.2f", avg) + 
                                   " | Grade: " + GradeCalculator.getGradeCategory(avg));
                found = true;
            }
        }
        if (!found) System.out.println("❌ Student not found.");
    }

    // Validation Methods utilizing try-catch for error handling
    private static int getValidInt(int min, int max) {
        while(true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if(value >= min && value <= max) return value;
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch(NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid whole number: ");
            }
        }
    }
    
    private static double getValidMark() {
        while(true) {
            try {
                double mark = Double.parseDouble(scanner.nextLine());
                if(mark >= 0 && mark <= 100) return mark;
                System.out.print("Marks must be between 0 and 100. Please re-enter: ");
            } catch(NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }
}