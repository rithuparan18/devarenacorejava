import java.util.Scanner;

public class StudentInformationSystem {
    private static StudentManager manager = new StudentManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== STUDENT INFORMATION SYSTEM ===");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: 
                    running = false;
                    System.out.println("Thank you for using the System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private static void addStudent() {
        System.out.println("\n=== ADD NEW STUDENT ===");
        
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        if (!ValidationUtils.isValidAge(age)) {
            System.out.println("Error: Age must be positive!");
            return;
        }

        System.out.print("Enter Grade (0-100): ");
        double grade = Double.parseDouble(scanner.nextLine());
        if (!ValidationUtils.isValidGrade(grade)) {
            System.out.println("Error: Grade must be between 0 and 100!");
            return;
        }

        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();

        manager.addStudent(new Student(id, name, age, grade, contact));
        System.out.println("✅ Student added successfully!");
    }

    private static void viewAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");
        if (manager.getAllStudents().isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        System.out.printf("%-10s %-20s %-5s %-8s %-15s\n", "ID", "Name", "Age", "Grade", "Contact");
        System.out.println("-".repeat(65));

        for (Student s : manager.getAllStudents()) {
            System.out.printf("%-10s %-20s %-5d %-8.2f %-15s\n", 
                s.getStudentId(), s.getName(), s.getAge(), s.getGrade(), s.getContact());
        }
    }

    private static void searchStudent() {
        System.out.print("\nEnter Student ID or Name to search: ");
        String query = scanner.nextLine();
        Student s = manager.searchStudent(query);
        
        if (s != null) {
            System.out.println("\n✅ Found Student:");
            System.out.println("ID: " + s.getStudentId() + " | Name: " + s.getName() + 
                               " | Age: " + s.getAge() + " | Grade: " + s.getGrade());
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    private static void updateStudent() {
        System.out.print("\nEnter Student ID to update: ");
        String id = scanner.nextLine();
        Student s = manager.searchStudent(id);

        if (s != null) {
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter New Grade: ");
            double grade = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter New Contact: ");
            String contact = scanner.nextLine();

            if (ValidationUtils.isValidAge(age) && ValidationUtils.isValidGrade(grade)) {
                manager.updateStudent(id, name, age, grade, contact);
                System.out.println("✅ Student updated successfully!");
            } else {
                System.out.println("❌ Validation failed. Update cancelled.");
            }
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("\nEnter Student ID to delete: ");
        String id = scanner.nextLine();
        if (manager.deleteStudent(id)) {
            System.out.println("✅ Student deleted successfully!");
        } else {
            System.out.println("❌ Student not found.");
        }
    }
}