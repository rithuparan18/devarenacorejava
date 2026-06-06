
import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeReportGenerator {

    public static void generateSalaryStats(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No data available for reporting.");
            return;
        }

        double totalSalary = 0;
        double highest = employees.get(0).getSalary();
        double lowest = employees.get(0).getSalary();
        String highestEarner = employees.get(0).getName();
        String lowestEarner = employees.get(0).getName();

        for (Employee emp : employees) {
            totalSalary += emp.getSalary();
            if (emp.getSalary() > highest) {
                highest = emp.getSalary();
                highestEarner = emp.getName();
            }
            if (emp.getSalary() < lowest) {
                lowest = emp.getSalary();
                lowestEarner = emp.getName();
            }
        }

        System.out.println("\n💰 SALARY STATISTICS:");
        System.out.println("• Total Employees: " + employees.size());
        System.out.println(String.format("• Total Payroll: ₹%.2f", totalSalary));
        System.out.println(String.format("• Average Salary: ₹%.2f", (totalSalary / employees.size())));
        System.out.println(String.format("• Highest Salary: ₹%.2f (%s)", highest, highestEarner));
        System.out.println(String.format("• Lowest Salary: ₹%.2f (%s)", lowest, lowestEarner));
    }

    public static void generateDepartmentSummary(ArrayList<Employee> employees) {
        if (employees.isEmpty()) return;

        HashMap<String, ArrayList<Employee>> deptMap = new HashMap<>();
        for (Employee emp : employees) {
            deptMap.putIfAbsent(emp.getDepartment(), new ArrayList<>());
            deptMap.get(emp.getDepartment()).add(emp);
        }

        System.out.println("\n🏢 DEPARTMENT SUMMARY:");
        for (String dept : deptMap.keySet()) {
            ArrayList<Employee> deptEmps = deptMap.get(dept);
            double total = 0;
            for (Employee e : deptEmps) total += e.getSalary();
            System.out.println(String.format("• %s: %d employees, Average Salary: ₹%.2f", 
                    dept, deptEmps.size(), (total / deptEmps.size())));
        }
    }
}