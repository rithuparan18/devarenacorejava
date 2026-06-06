
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private static ArrayList<Employee> employees;
    private static HashMap<String, Employee> employeeMap;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        employees = EmployeeFileHandler.loadFromFile();
        employeeMap = new HashMap<>();
        
        // Rebuild HashMap lookup table after loading from file
        for (Employee emp : employees) {
            employeeMap.put(emp.getId().toUpperCase(), emp);
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add New Employee (Create)");
            System.out.println("2. View All Employees (Read)");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee (Update)");
            System.out.println("5. Delete Employee (Delete)");
            System.out.println("6. Generate Reports");
            System.out.println("7. Exit & Save");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": addEmployee(); break;
                case "2": viewAllEmployees(); break;
                case "3": searchEmployee(); break;
                case "4": updateEmployee(); break;
                case "5": deleteEmployee(); break;
                case "6": generateReports(); break;
                case "7":
                    EmployeeFileHandler.saveToFile(employees);
                    running = false;
                    System.out.println("System shutting down... Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please select 1-7.");
            }
        }
        scanner.close();
    }

    private static void addEmployee() {
        System.out.println("\n=== ADD EMPLOYEE ===");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine().trim().toUpperCase();

        if (employeeMap.containsKey(id)) {
            System.out.println("❌ Error: Employee with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Department: ");
        String dept = scanner.nextLine().trim();
        System.out.print("Enter Position: ");
        String pos = scanner.nextLine().trim();
        
        double salary = 0;
        while (true) {
            try {
                System.out.print("Enter Salary (₹): ");
                salary = Double.parseDouble(scanner.nextLine().trim());
                if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative.");
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a numerical value.");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }

        Employee emp = new Employee(id, name, dept, pos, salary);
        employees.add(emp);
        employeeMap.put(id, emp);
        System.out.println("✅ Employee added successfully!");
    }

    private static void viewAllEmployees() {
        System.out.println("\n=== ALL EMPLOYEES ===");
        if (employees.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        for (Employee emp : employees) {
            System.out.println(emp.toString());
        }
    }

    private static void searchEmployee() {
        System.out.print("\nEnter Employee ID to quick-search (or press Enter to skip): ");
        String id = scanner.nextLine().trim().toUpperCase();
        
        // Fast O(1) Lookup using HashMap
        if (!id.isEmpty()) {
            if (employeeMap.containsKey(id)) {
                System.out.println("\n✅ Match Found:");
                System.out.println(employeeMap.get(id).toString());
            } else {
                System.out.println("❌ No employee found with ID: " + id);
            }
            return;
        }

        System.out.print("Enter Name or Department to search: ");
        String keyword = scanner.nextLine().trim().toLowerCase();
        boolean found = false;
        
        System.out.println("\n🔍 Search Results:");
        // O(n) Iterative Search using ArrayList
        for (Employee emp : employees) {
            if (emp.getName().toLowerCase().contains(keyword) || 
                emp.getDepartment().toLowerCase().contains(keyword)) {
                System.out.println(emp.toString());
                found = true;
            }
        }
        if (!found) System.out.println("No matching records found.");
    }

    private static void updateEmployee() {
        System.out.print("\nEnter Employee ID to update: ");
        String id = scanner.nextLine().trim().toUpperCase();

        if (!employeeMap.containsKey(id)) {
            System.out.println("❌ Employee not found.");
            return;
        }

        Employee emp = employeeMap.get(id);
        System.out.println("Updating details for: " + emp.getName());
        System.out.println("(Press Enter to keep current value)");

        System.out.print("New Name [" + emp.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) emp.setName(name);

        System.out.print("New Position [" + emp.getPosition() + "]: ");
        String pos = scanner.nextLine().trim();
        if (!pos.isEmpty()) emp.setPosition(pos);

        System.out.print("New Salary [" + emp.getSalary() + "]: ");
        String salaryStr = scanner.nextLine().trim();
        if (!salaryStr.isEmpty()) {
            try {
                emp.setSalary(Double.parseDouble(salaryStr));
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid salary format. Salary unchanged.");
            }
        }
        System.out.println("✅ Employee updated successfully.");
    }

    private static void deleteEmployee() {
        System.out.print("\nEnter Employee ID to delete: ");
        String id = scanner.nextLine().trim().toUpperCase();

        if (employeeMap.containsKey(id)) {
            Employee emp = employeeMap.get(id);
            employees.remove(emp);   // Remove from ArrayList
            employeeMap.remove(id);  // Remove from HashMap
            System.out.println("✅ Employee " + id + " has been deleted.");
        } else {
            System.out.println("❌ Employee not found.");
        }
    }

    private static void generateReports() {
        System.out.println("\n=== PERFORMANCE & SALARY REPORTS ===");
        EmployeeReportGenerator.generateSalaryStats(employees);
        EmployeeReportGenerator.generateDepartmentSummary(employees);
    }
}